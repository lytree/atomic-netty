package top.lytree.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import top.lytree.chart.command.ConsoleCommandManger;
import top.lytree.chart.handler.MyIdleStateHandler;
import top.lytree.chart.handler.SplitHandler;
import top.lytree.chart.handler.client.HeartBeatHandler;
import top.lytree.chart.protocol.request.MessageRequestPacket;
import top.lytree.udp.handler.client.ClientHandler;
import top.lytree.udp.handler.server.ServerHandler;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static top.lytree.chart.handler.client.ClientHandler.CLIENT_HANDLER;
import static top.lytree.chart.serialize.codec.PacketCodecHandler.PACKET_CODEC_HANDLER;

/**
 * Netty 客户端
 *
 * @since 2023-03-28 20:25:46
 */
public class NettyClient {


    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioDatagramChannel.class)
                    .handler(new ClientHandler());
            Channel ch = b.bind(7398).sync().channel();
            //向目标端口发送信息
            ch.writeAndFlush(new DatagramPacket(
                    Unpooled.copiedBuffer("你好端口7397的bugstack虫洞栈，我是客户端小爱，你在吗！", Charset.forName("GBK")),
                    new InetSocketAddress("255.255.255.255", 7397))).sync();
            ch.closeFuture().await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

}
