package douyin.avatar.avatar_demo_java.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import douyin.avatar.avatar_demo_java.Enum.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {

    @JsonProperty("conversation_id")
    private String conversationID;

    @JsonProperty("session_id")
    public String sessionID;

    @JsonProperty("message_id")
    public String messageID;

    @JsonProperty("role")
    public RoleEnum roleEnum;

    @JsonProperty("content")
    public MessageContent messageContent;

}
