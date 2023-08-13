package top.lytree.protocol.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.lytree.protocol.Packet;
import top.lytree.protocol.command.Command;

/**
 * 客户端发送消息的对象
 *
 * 
 * @since 2023-03-28 21:12:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageRequestPacket extends Packet {

    private String message;

    private String userId;

    private String userName;

    private String toUserId;

    private String toUserName;

    @Override
    public Command getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
