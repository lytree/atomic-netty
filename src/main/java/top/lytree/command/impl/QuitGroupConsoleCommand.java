package top.lytree.command.impl;

import io.netty.channel.Channel;
import top.lytree.command.ConsoleCommand;
import top.lytree.protocol.request.QuitGroupRequestPacket;

import java.util.Scanner;

/**
 * 退出群聊的Command
 *
 * 
 * @since 2023-04-06 20:06:45
 */
public class QuitGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入退出群组的ID：");
        String groupId = scanner.next();

        // 退出群组的请求
        QuitGroupRequestPacket requestPacket = new QuitGroupRequestPacket();
        requestPacket.setGroupId(groupId);

        channel.writeAndFlush(requestPacket);
    }
}
