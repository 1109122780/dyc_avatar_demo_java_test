package douyin.avatar.avatar_demo_java.caller.model.memory.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import douyin.avatar.avatar_demo_java.response.common.BaseResp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemoryRecallResponse {

    @JsonProperty("data")
    private MemoryRecallRespData data;

    @JsonProperty("base_resp")
    private BaseResp baseResp;
}
