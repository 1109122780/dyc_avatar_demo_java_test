package douyin.avatar.avatar_demo_java.service.dto;

import com.alibaba.fastjson2.JSON;
import douyin.avatar.avatar_demo_java.request.ChatContext;
import douyin.avatar.avatar_demo_java.request.CommonContext;
import douyin.avatar.avatar_demo_java.request.Message;
import douyin.avatar.avatar_demo_java.request.common.BizContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OnBoardingDTO {

    private Message message;

    private ChatContext chatContext;

    private String replyMessageID;

    private BizContext bizContext;

    private CommonContext commonContext;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
