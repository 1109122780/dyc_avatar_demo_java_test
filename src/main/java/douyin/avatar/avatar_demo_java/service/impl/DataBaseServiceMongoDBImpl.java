package douyin.avatar.avatar_demo_java.service.impl;

import douyin.avatar.avatar_demo_java.service.DataBaseService;
import douyin.avatar.avatar_demo_java.service.dto.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Qualifier("mongo")
public class DataBaseServiceMongoDBImpl implements DataBaseService {
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public String hello(String target) {
        Query query = new Query();
        query.addCriteria(Criteria.where("target").is(target));
        User user = mongoTemplate.findOne(query, User.class, "demo");
        return user.name;
    }

    @Override
    public void setName(String target, String name) {
        if(!mongoTemplate.collectionExists("demo")){
            mongoTemplate.createCollection("demo");
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("target").is(target));
        Update update = Update.update("name", name);
        mongoTemplate.upsert(query,update,"demo");
    }
}
