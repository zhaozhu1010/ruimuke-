package com.ks.ks.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ks.ks.bean.user;
import org.springframework.stereotype.Service;


/**
 * @author jinbin
 * @date 2018-07-08 21:04
 */
@Service("TokenService")
public class TokenService {
    public String getToken(user user) {
        String token = "";
        try {
            token = JWT.create().withAudience(String.valueOf(user.getUserName()))// 将 user 名字 保存到 token 里面
                    .sign(Algorithm.HMAC256(user.getUserPwd()));// 以 password 作为 token 的密钥
        } catch (Exception e) {
            e.printStackTrace();
        }

        return token;
    }
}
