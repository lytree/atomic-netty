package top.lytree.protocol;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import top.lytree.protocol.command.Command;

/**
 * 通信数据包基类
 *
 * 
 * @since 2023-03-26 20:16:22
 */
@Data
public abstract class Packet {

    /**
     * 协议版本
     */
    @JsonIgnore
    private Byte version = 1;

    /**
     * 序列化器 默认JSON解析
     */
    @JsonIgnore
    private Byte serializer = 1;

    /**
     * 获取这个请求的命令
     */
    @JsonIgnore
    public abstract Command getCommand();
}
