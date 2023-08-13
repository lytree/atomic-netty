package top.lytree.handler.client;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.lytree.protocol.response.LoginResponsePacket;
import top.lytree.session.Session;
import top.lytree.util.SessionUtil;

import java.util.Date;

/**
 * 登录业务处理Handler
 *
 * 
 * @since 2023-03-30 20:19:55
 */
@ChannelHandler.Sharable
public class LoginHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    /**
     * 单例
     */
    public static final LoginHandler LOGIN_HANDLER = new LoginHandler();

    /**
     * 处理登录response对象
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) {
        if (msg.isSuccess()) {
            System.out.println(new Date() + ": 客户端登录成功");
            // 标记登录成功
            SessionUtil.bindSession(new Session(msg.getUserId(), msg.getUserName()), ctx.channel());
        } else {
            System.out.println(new Date() + ": 客户端登录失败，原因：" + msg.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接被关闭");
    }
}
