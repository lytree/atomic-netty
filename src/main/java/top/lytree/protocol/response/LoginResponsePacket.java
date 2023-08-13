package top.lytree.protocol.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.lytree.protocol.Packet;
import top.lytree.protocol.command.Command;

import static top.lytree.protocol.command.Command.LOGIN_RESPONSE;

/**
 * 客户端登录Response对象
 *
 * 
 * @since 2023-03-28 20:44:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;

    private String userId;

    private String userName;

    @Override
    public Command getCommand() {
        return LOGIN_RESPONSE;
    }
}
