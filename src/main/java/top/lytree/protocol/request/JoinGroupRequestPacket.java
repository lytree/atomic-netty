package top.lytree.protocol.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import top.lytree.protocol.Packet;
import top.lytree.protocol.command.Command;

/**
 * 加入群组请求
 *
 * 
 * @since 2023-04-03 20:35:59
 */
@EqualsAndHashCode(callSuper = true)
@Data@NoArgsConstructor
@AllArgsConstructor
public class JoinGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Command getCommand() {
        return Command.JOIN_GROUP_REQUEST;
    }
}
