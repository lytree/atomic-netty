package top.lytree.handler.server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import top.lytree.util.SessionUtil;

/**
 * 身份验证Handler
 *
 * 
 * @since 2023-04-01 20:29:53
 */
@ChannelHandler.Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {

    /**
     * 单例
     */
    public static final AuthHandler AUTH_HANDLER = new AuthHandler();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (SessionUtil.hasLogin(ctx.channel())) {
            // 如果登录成功则移除该处理节点
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        } else {
            // 没有成功的话需要关闭连接
            ctx.channel().close();
        }
    }

    /**
     * 当登录成功节点被移除时回调
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (SessionUtil.hasLogin(ctx.channel())) {
            System.out.println("当前连接登录验证完毕，无需再次验证, AuthHandler 被移除");
        } else {
            System.out.println("无登录验证，强制关闭连接!");
        }
    }
}
