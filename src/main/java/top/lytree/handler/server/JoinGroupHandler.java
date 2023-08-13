package top.lytree.handler.server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import top.lytree.protocol.request.JoinGroupRequestPacket;
import top.lytree.protocol.response.JoinGroupResponsePacket;
import top.lytree.util.SessionUtil;

/**
 * 服务端加入群聊的Handler
 *
 * 
 * @since 2023-04-03 20:41:44
 */
@ChannelHandler.Sharable
public class JoinGroupHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {

    /**
     * 单例
     */
    public static final JoinGroupHandler JOIN_GROUP_HANDLER = new JoinGroupHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket msg) {
        // 要加入的群组ID
        String groupId = msg.getGroupId();
        // 将新用户添加进来
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.add(ctx.channel());

        // 初始化响应对象
        JoinGroupResponsePacket responsePacket = new JoinGroupResponsePacket();
        responsePacket.setGroupId(groupId);
        responsePacket.setSuccess(true);
        responsePacket.setMessage(SessionUtil.getUserName(ctx.channel()) + " 加入群聊");

        channelGroup.writeAndFlush(responsePacket);
    }
}
