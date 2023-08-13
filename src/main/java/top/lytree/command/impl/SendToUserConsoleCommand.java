package top.lytree.command.impl;

import io.netty.channel.Channel;
import top.lytree.command.ConsoleCommand;
import top.lytree.protocol.request.MessageRequestPacket;
import top.lytree.session.Session;
import top.lytree.util.SessionUtil;

import java.util.Scanner;

/**
 * 给某个用户发送消息的命令
 *
 * 
 * @since 2023-04-02 22:29:29
 */
public class SendToUserConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        // 初始化要发送的消息
        MessageRequestPacket messageRequestPacket = initialMessageRequest(scanner, SessionUtil.getSession(channel));
        // 发送消息
        channel.writeAndFlush(messageRequestPacket);
    }

    /**
     * 初始化要发送的消息
     */
    private static MessageRequestPacket initialMessageRequest(Scanner scanner, Session session) {
        System.out.println("输入要发送的用户名: ");
        String toUserName = scanner.next();
        System.out.println("输入消息内容: ");
        String message = scanner.next();
        MessageRequestPacket messageRequestPacket = new MessageRequestPacket();

        messageRequestPacket.setUserName(session.getUserName());
        messageRequestPacket.setUserId(session.getUserId());
        messageRequestPacket.setToUserName(toUserName);
        messageRequestPacket.setMessage(message);

        return messageRequestPacket;
    }
}
