package douyin.avatar.avatar_demo_java.service.impl;

import douyin.avatar.avatar_demo_java.service.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DataBaseServiceFactory {
    @Qualifier("redis")
    @Autowired
    private DataBaseService redisImpl;
    @Qualifier("mongo")
    @Autowired
    private DataBaseService mongoImpl;
    public DataBaseService getHelloService(String target){
        if("redis".equalsIgnoreCase(target)){
            return redisImpl;
        }else if ("mongo".equalsIgnoreCase(target)){
            return mongoImpl;
        }
        return null;
    }
}
