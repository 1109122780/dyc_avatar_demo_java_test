package douyin.avatar.avatar_demo_java.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import douyin.avatar.avatar_demo_java.Enum.ContentTypeEnum;
import douyin.avatar.avatar_demo_java.Enum.RoleEnum;
import douyin.avatar.avatar_demo_java.Enum.SegTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CopilotContent {

    @JsonProperty("type")
    private ContentTypeEnum contentType;

    @JsonProperty("content")
    private String content;

    @JsonProperty("role")
    private RoleEnum roleEnum;

    @JsonProperty("seg_finish")
    private boolean segFinish;

    @JsonProperty("seg_type")
    private SegTypeEnum segType;
}
