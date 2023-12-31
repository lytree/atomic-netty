package top.lytree.handler.client;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.lytree.protocol.response.QuitGroupResponsePacket;

/**
 * 客户端处理退出群组响应
 *
 * 
 * @since 2023-04-06 20:05:43
 */
@ChannelHandler.Sharable
public class QuitGroupHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {

    /**
     * 单例
     */
    public static final QuitGroupHandler QUIT_GROUP_HANDLER = new QuitGroupHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket msg) throws Exception {
        if (msg.isSuccess()) {
            System.out.println("退出群聊[" + msg.getGroupId() + "]成功！");
        } else {
            System.out.println("退出群聊[" + msg.getGroupId() + "]失败！");
        }
    }
}
