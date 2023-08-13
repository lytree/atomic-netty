package top.lytree.command.impl;

import io.netty.channel.Channel;
import top.lytree.command.ConsoleCommand;
import top.lytree.protocol.request.GroupMessageRequestPacket;

import java.util.Scanner;

/**
 * 向群聊发送消息的控制台命令
 *
 * 
 * @since 2023-04-07 20:16:31
 */
public class SendToGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入要发送消息的群组：");
        String groupId = scanner.next();
        System.out.print("输入要发送的内容：");
        String message = scanner.next();

        channel.writeAndFlush(new GroupMessageRequestPacket(groupId, message));
    }
}
