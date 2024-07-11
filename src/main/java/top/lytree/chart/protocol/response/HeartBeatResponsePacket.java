package top.lytree.chart.protocol.response;

import top.lytree.chart.protocol.Packet;
import top.lytree.chart.protocol.command.Command;

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
