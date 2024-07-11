package top.lytree.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import top.lytree.chart.handler.MyIdleStateHandler;
import top.lytree.chart.handler.SplitHandler;
import top.lytree.udp.handler.server.ServerHandler;

import java.util.Date;

import static top.lytree.chart.handler.server.AuthHandler.AUTH_HANDLER;
import static top.lytree.chart.handler.server.HeartBeatHandler.HEART_BEAT_HANDLER;
import static top.lytree.chart.handler.server.LoginHandler.LOGIN_HANDLER;
import static top.lytree.chart.handler.server.ServerHandler.SERVER_HANDLER;
import static top.lytree.chart.serialize.codec.PacketCodecHandler.PACKET_CODEC_HANDLER;

/**
 * Netty 服务端
 *
 * 
 * @since 2023-03-28 20:35:21
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        Bootstrap b = new Bootstrap();
        b.group(new NioEventLoopGroup()).channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST, true)    //广播
                .option(ChannelOption.SO_RCVBUF, 2048 * 1024)// 设置UDP读缓冲区为2M
                .option(ChannelOption.SO_SNDBUF, 1024 * 1024)// 设置UDP写缓冲区为1M
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        socketChannel.pipeline()
//                                .addLast(new LifeCycleHandler())
                                // 空闲检测
                                .addLast(new MyIdleStateHandler())
                                // 解决粘包和半包问题
                                .addLast(new SplitHandler())
                                .addLast(new ServerHandler());
//                                .addLast(new PacketEncoder());
                    }
                });

        ChannelFuture f = b.bind(7397).sync();
        f.channel().closeFuture().sync();
    }

}
