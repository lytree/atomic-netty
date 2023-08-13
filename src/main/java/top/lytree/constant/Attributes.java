package top.lytree.constant;

import io.netty.util.AttributeKey;
import top.lytree.session.Session;

/**
 * 一些必要的Channel Attribute 常量
 *
 * 
 * @since 2023-04-01 21:42:37
 */
public interface Attributes {

    AttributeKey<Session> SESSION = AttributeKey.newInstance("SESSION");
}
