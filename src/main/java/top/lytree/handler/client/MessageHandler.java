package top.lytree.handler.client;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.lytree.protocol.response.MessageResponsePacket;

import java.util.Date;

/**
 * 消息处理Handler
 *
 * 
 * @since 2023-03-30 20:20:44
 */
@ChannelHandler.Sharable
public class MessageHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    /**
     * 单例
     */
    public static final MessageHandler MESSAGE_HANDLER = new MessageHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) {
        if (msg.isSuccess()) {
            System.out.println(msg.getFromUserId() + ":" + msg.getFromUserName() + " -> " + msg.getMessage());
        } else {
            System.out.println(new Date() + ": 收到服务端的消息: " + msg.getMessage());
        }
    }
}
