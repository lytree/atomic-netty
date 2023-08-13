package top.lytree.handler.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import top.lytree.protocol.request.ListGroupMembersRequestPacket;
import top.lytree.protocol.response.ListGroupMembersResponsePacket;
import top.lytree.util.SessionUtil;

/**
 * 服务端处理群组成员查询的Handler
 *
 * 
 * @since 2023-04-06 20:28:48
 */
@ChannelHandler.Sharable
public class ListGroupMembersHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {

    /**
     * 单例
     */
    public static final ListGroupMembersHandler LIST_GROUP_MEMBERS_HANDLER = new ListGroupMembersHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket msg) throws Exception {
        // 群组ID
        String groupId = msg.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        // 拼接群组成员名
        StringBuilder groupNames = new StringBuilder();
        for (Channel channel : channelGroup) {
            groupNames.append(SessionUtil.getUserName(channel)).append(",");
        }
        groupNames.deleteCharAt(groupNames.length() - 1);

        // 初始化响应对象
        ListGroupMembersResponsePacket responsePacket = new ListGroupMembersResponsePacket();
        responsePacket.setSuccess(true);
        responsePacket.setMembers(groupNames.toString());
        responsePacket.setGroupId(groupId);

        ctx.channel().writeAndFlush(responsePacket);
    }
}
