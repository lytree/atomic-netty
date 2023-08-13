import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ByteBufTest {
    public static void main(String[] args) {
        ByteBuf buffer = Unpooled.buffer();

        buffer.release();
    }
}


