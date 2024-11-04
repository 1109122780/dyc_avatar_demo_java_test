package douyin.avatar.avatar_demo_java.service.impl;

import douyin.avatar.avatar_demo_java.service.DataBaseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Qualifier("redis")
public class DataBaseServiceRedisImpl implements DataBaseService {
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public String hello(String target) {
        if(!redisTemplate.hasKey(target)){
            return "";
        }
        return (String) redisTemplate.opsForValue().get(target);
    }

    @Override
    public void setName(String target, String name) {
        redisTemplate.opsForValue().set(target,name);
    }
}
