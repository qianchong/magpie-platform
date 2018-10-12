//package edu.common;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
//
//
//public class AuthRedisTokenStroe {
//    @Autowired
//    private RedisConnectionFactory redisConnectionFactory;
//
//    @Bean
//    RedisTokenStore redisTokenStore(){
//        return new RedisTokenStore(redisConnectionFactory);
//    }
//}
