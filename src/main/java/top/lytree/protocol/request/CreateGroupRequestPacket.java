package top.lytree.protocol.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.lytree.protocol.Packet;
import top.lytree.protocol.command.Command;

import java.util.List;

/**
 * 创建群组请求对象
 *
 * 
 * @since 2023-04-02 22:53:23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CreateGroupRequestPacket extends Packet {

    /**
     * 群组包含的用户名们
     */
    private List<String> userNameList;

    @Override
    public Command getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}
