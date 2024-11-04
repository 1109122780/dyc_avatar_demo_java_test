package douyin.avatar.avatar_demo_java.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import douyin.avatar.avatar_demo_java.Enum.MessageContentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageContent {

    @JsonProperty("type")
    private MessageContentTypeEnum messageContentType;

    @JsonProperty("content")
    private String content;
}
