package top.lytree.handler.client;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.lytree.protocol.response.GroupMessageResponsePacket;

/**
 * 客户端处理群组消息响应的Handler
 *
 * 
 * @since 2023-04-07 20:31:40
 */
@ChannelHandler.Sharable
public class GroupMessageHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {

    /**
     * 单例
     */
    public static final GroupMessageHandler GROUP_MESSAGE_HANDLER = new GroupMessageHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket msg) throws Exception {
        System.out.println("收到群[" + msg.getFromGroupId() + "]中[" + msg.getFromUser() + "]发来的消息：" + msg.getMessage());
    }
}
