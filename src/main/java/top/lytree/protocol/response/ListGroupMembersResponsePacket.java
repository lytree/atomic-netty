package top.lytree.protocol.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.lytree.protocol.Packet;
import top.lytree.protocol.command.Command;

/**
 * 获取群组成员的响应对象
 *
 * 
 * @since 2023-04-06 20:26:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    /**
     * 逗号分隔成员名
     */
    private String members;

    private boolean success;

    @Override
    public Command getCommand() {
        return Command.LIST_MEMBERS_RESPONSE;
    }
}
