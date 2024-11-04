package douyin.avatar.avatar_demo_java.service.impl;

import douyin.avatar.avatar_demo_java.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceFactory {
    @Qualifier("redis")
    @Autowired
    private HelloService redisImpl;
    @Qualifier("mongo")
    @Autowired
    private HelloService mongoImpl;
    public HelloService getHelloService(String target){
        if("redis".equalsIgnoreCase(target)){
            return redisImpl;
        }else if ("mongo".equalsIgnoreCase(target)){
            return mongoImpl;
        }
        return null;
    }
}
