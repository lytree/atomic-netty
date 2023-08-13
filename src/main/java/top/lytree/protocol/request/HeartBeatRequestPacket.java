package top.lytree.protocol.request;

import top.lytree.protocol.Packet;
import top.lytree.protocol.command.Command;

/**
 * 客户端心跳请求对象
 *
 * 
 * @since 2023-04-08 15:02:09
 */
public class HeartBeatRequestPacket extends Packet {
    @Override
    public Command getCommand() {
        return Command.HEART_BEAT_REQUEST;
    }
}
