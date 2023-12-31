package top.lytree.handler.server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.lytree.protocol.request.HeartBeatRequestPacket;
import top.lytree.protocol.response.HeartBeatResponsePacket;

/**
 * 服务端心跳处理Handler
 *
 * 
 * @since 2023-04-08 15:06:08
 */
@ChannelHandler.Sharable
public class HeartBeatHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {

    /**
     * 单例
     */
    public static final HeartBeatHandler
            HEART_BEAT_HANDLER = new HeartBeatHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket msg) throws Exception {
        // 直接回写心跳ACK
        ctx.writeAndFlush(new HeartBeatResponsePacket());
    }
}
