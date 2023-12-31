package top.lytree;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import top.lytree.command.ConsoleCommandManger;
import top.lytree.handler.MyIdleStateHandler;
import top.lytree.handler.SplitHandler;
import top.lytree.handler.client.HeartBeatHandler;
import top.lytree.protocol.request.MessageRequestPacket;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static top.lytree.handler.client.ClientHandler.CLIENT_HANDLER;
import static top.lytree.serialize.codec.PacketCodecHandler.PACKET_CODEC_HANDLER;

/**
 * Netty 客户端
 *
 * @since 2023-03-28 20:25:46
 */
public class NettyClient {

    /**
     * 最大重连间隔
     */
    private static final int MAX_RETRY = 5;

    private static final String HOST = "127.0.0.1";
    static ChannelFuture channelFuture;
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(new NioEventLoopGroup()).channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        socketChannel.pipeline()
                                // 空闲检测
                                .addLast(new MyIdleStateHandler())
                                .addLast(new SplitHandler())
                                .addLast(PACKET_CODEC_HANDLER)
                                .addLast(CLIENT_HANDLER)
                                // 心跳Handler
                                .addLast(new HeartBeatHandler());
//                                .addLast(new PacketEncoder());
                    }
                });

        connect(bootstrap, HOST, 8080, 5);
    }

    /**
     * 连接服务端 失败后不断重试
     */
    private static void connect(Bootstrap bootstrap, String host, int port, int retry) throws InterruptedException {

        for (; ; ) {
            channelFuture = bootstrap.connect(host, port).addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println(new Date() + ": 连接成功，准备登录");

                    // 连接成功后开启控制台读取消息
                    startConsoleThread(((ChannelFuture) future).channel());

                    // 测试粘包和半包
//                testPackage(((ChannelFuture) future).channel());
                } else if (retry == 0) {
                    System.err.println("重试次数已用完，放弃连接！");
                } else {
                    // 第几次重连
                    int order = (MAX_RETRY - retry) + 1;
                    // 定时任务下次执行重连的时间
                    int delay = 1 << order;
                    System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");

                    bootstrap.config().group().schedule(() -> {
                                try {
                                    connect(bootstrap, host, port, retry - 1);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            },
                            delay, TimeUnit.SECONDS);
                }
            });
            Thread.sleep(10000);
        }

    }

    /**
     * 开启读取控制台消息的线程
     */
    private static void startConsoleThread(Channel channel) {
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);

            while (!Thread.interrupted() && channel.isActive()) {
                // 执行命令
                ConsoleCommandManger consoleCommandManger = new ConsoleCommandManger();
                consoleCommandManger.execCommand(scanner, channel);
            }

            if (!channel.isActive()) {
                System.out.println("连接已断开，请重启客户端重试");
            }
        }).start();
    }

    /**
     * 测试粘包和半包
     */
    private static void testPackage(Channel channel) {
        MessageRequestPacket messageRequestPacket = new MessageRequestPacket();

        for (int i = 0; i < 100; i++) {
            messageRequestPacket.setMessage("你好哇，李银河" + i);
            channel.writeAndFlush(messageRequestPacket);
        }
    }
}
