package top.lytree.handler.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.lytree.protocol.request.MessageRequestPacket;
import top.lytree.protocol.response.MessageResponsePacket;
import top.lytree.util.SessionUtil;

import java.util.Date;

/**
 * 服务端处理Message Handler
 *
 * 
 * @since 2023-03-30 20:34:37
 */
@ChannelHandler.Sharable
public class MessageHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    /**
     * 单例
     */
    public static final MessageHandler MESSAGE_HANDLER = new MessageHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) {
        // 初始化需要转发的消息信息
        MessageResponsePacket messageResponsePacket = initialResponseMessage(msg);

        System.out.println(new Date() + ": 收到客户端[" + msg.getUserName() + "]给[" + msg.getToUserName() + "]的消息: " + msg.getMessage());

        // 回消息
        Channel channel = SessionUtil.getChannel(msg.getToUserName());

        if (channel != null && SessionUtil.hasLogin(channel)) {
            channel.writeAndFlush(messageResponsePacket);
        } else {
            String message = "[" + msg.getToUserName() + "] 不在线，发送失败!";
            ctx.channel().writeAndFlush(new MessageResponsePacket(message));
            System.out.println(message);
        }
    }

    /**
     * 初始化要回复的信息
     */
    private MessageResponsePacket initialResponseMessage(MessageRequestPacket msg) {
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage(msg.getMessage());
        messageResponsePacket.setFromUserId(msg.getUserId());
        messageResponsePacket.setFromUserName(msg.getUserName());
        messageResponsePacket.setSuccess(true);

        return messageResponsePacket;
    }
}
