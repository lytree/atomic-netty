package top.lytree.session;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 登录Session信息
 *
 * 
 * @since 2023-04-01 21:09:27
 */
@Data
@AllArgsConstructor
public class Session {

    private String userId;

    private String userName;

    @Override
    public String toString() {
        return userId + ":" + userName;
    }
}
