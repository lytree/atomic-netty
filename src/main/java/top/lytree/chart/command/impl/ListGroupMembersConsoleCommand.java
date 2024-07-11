package top.lytree.chart.command.impl;

import io.netty.channel.Channel;

import top.lytree.chart.protocol.request.ListGroupMembersRequestPacket;
import top.lytree.chart.command.ConsoleCommand;

import java.util.Scanner;

/**
 * 获取群组成员信息命令
 *
 * 
 * @since 2023-04-06 20:45:10
 */
public class ListGroupMembersConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("请输入要查询群组的 groupId：");
        String groupId = scanner.next();

        // 初始化请求对象
        ListGroupMembersRequestPacket requestPacket = new ListGroupMembersRequestPacket();
        requestPacket.setGroupId(groupId);

        channel.writeAndFlush(requestPacket);
    }
}
