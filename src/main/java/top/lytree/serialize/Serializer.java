package top.lytree.serialize;


import top.lytree.serialize.algorithm.SerializerAlgorithm;

/**
 * 序列化
 *
 * 
 * @since 2023-03-26 20:24:25
 */
public interface Serializer {

    SerializerAlgorithm getSerializerAlgorithm();

    /**
     * 编码
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     * 解码
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
