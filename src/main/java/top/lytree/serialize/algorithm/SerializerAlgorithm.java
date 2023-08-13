package top.lytree.serialize.algorithm;

/**
 * 序列化算法
 *
 * 
 * @since 2023-03-26 20:26:22
 */
public enum SerializerAlgorithm {

    JSON(Byte.valueOf("1"));

    private final Byte value;

    SerializerAlgorithm(Byte value) {
        this.value = value;
    }

    public Byte getValue() {
        return value;
    }
}
