package top.lytree.serialize.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import top.lytree.protocol.Packet;
import top.lytree.serialize.PacketCodeC;

/**
 * 将消息编码成ByteBuf
 *
 * 
 * @since 2023-03-30 20:27:44
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        PacketCodeC.encode(out, msg);
    }
}
