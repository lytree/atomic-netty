package top.lytree.protocol.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.lytree.protocol.Packet;
import top.lytree.protocol.command.Command;

/**
 * 群组消息请求
 *
 * 
 * @since 2023-04-07 20:14:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupMessageRequestPacket extends Packet {

    private String groupId;

    private String message;

    public GroupMessageRequestPacket(String groupId, String message) {
        this.groupId = groupId;
        this.message = message;
    }

    @Override
    public Command getCommand() {
        return Command.SEND_GROUP_MESSAGE_REQUEST;
    }
}
