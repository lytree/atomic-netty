package top.lytree.protocol.response;

import top.lytree.protocol.Packet;
import top.lytree.protocol.command.Command;

/**
 * 心跳响应对象对象
 *
 * 
 * @since 2023-04-08 15:06:49
 */
public class HeartBeatResponsePacket extends Packet {
    @Override
    public Command getCommand() {
        return Command.HEART_BEAT_RESPONSE;
    }
}
