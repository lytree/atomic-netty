package top.lytree.chart.serialize.impl;

import top.lytree.json.JSONObject;
import top.lytree.chart.serialize.Serializer;
import top.lytree.chart.serialize.algorithm.SerializerAlgorithm;

public class JSONSerializer implements Serializer {
    @Override
    public SerializerAlgorithm getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSONObject.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSONObject.parseObject(bytes, clazz);
    }
}
