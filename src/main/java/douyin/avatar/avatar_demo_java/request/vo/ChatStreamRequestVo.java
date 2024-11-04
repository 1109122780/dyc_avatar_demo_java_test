package douyin.avatar.avatar_demo_java.request.vo;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.annotation.JsonProperty;
import douyin.avatar.avatar_demo_java.request.ChatContext;
import douyin.avatar.avatar_demo_java.request.CommonContext;
import douyin.avatar.avatar_demo_java.request.Message;
import douyin.avatar.avatar_demo_java.request.common.Base;
import douyin.avatar.avatar_demo_java.request.common.BizContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatStreamRequestVo {

    @JsonProperty("message")
    private Message message;

    @JsonProperty("chat_context")
    private ChatContext chatContext;

    @JsonProperty("reply_message_id")
    private String replyMessageID;

    @JsonProperty("biz_context")
    private BizContext bizContext;

    @JsonProperty("common_context")
    private CommonContext commonContext;

    @JsonProperty("base")
    private Base base;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
