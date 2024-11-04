package douyin.avatar.avatar_demo_java.response.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import douyin.avatar.avatar_demo_java.response.CopilotContent;
import douyin.avatar.avatar_demo_java.response.common.BaseResp;
import douyin.avatar.avatar_demo_java.response.common.TraceInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatResponseDTO {

    @JsonProperty("content")
    private CopilotContent copilotContent;

    @JsonProperty("trace_info")
    private TraceInfo traceInfo;

    @JsonProperty("base_resp")
    private BaseResp baseResp;

}
