package top.lytree.handler.server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import top.lytree.protocol.request.QuitGroupRequestPacket;
import top.lytree.protocol.response.QuitGroupResponsePacket;
import top.lytree.util.SessionUtil;

/**
 * 服务端处理离开群组的Handler
 *
 * 
 * @since 2023-04-06 19:51:41
 */
@ChannelHandler.Sharable
public class QuitGroupHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    /**
     * 单例
     */
    public static final QuitGroupHandler QUIT_GROUP_HANDLER = new QuitGroupHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket msg) throws Exception {
        // 要退出的群组ID
        String groupId = msg.getGroupId();
        // 在群组中移除该channel
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.remove(ctx.channel());

        // 返回响应
        QuitGroupResponsePacket responsePacket = new QuitGroupResponsePacket();
        responsePacket.setGroupId(groupId);
        responsePacket.setSuccess(true);

        ctx.channel().writeAndFlush(responsePacket);
    }
}
