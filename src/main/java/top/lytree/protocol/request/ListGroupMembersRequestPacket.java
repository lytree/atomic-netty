package top.lytree.protocol.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.lytree.protocol.Packet;
import top.lytree.protocol.command.Command;

/**
 * 获取群组成员列表请求
 *
 * 
 * @since 2023-04-06 20:24:23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    public Command getCommand() {
        return Command.LIST_MEMBERS_REQUEST;
    }
}
