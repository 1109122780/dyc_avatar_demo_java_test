package douyin.avatar.avatar_demo_java.caller.model.memory.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemoryRecallRequest {

    @JsonProperty("params")
    private MemoryRecallParams params;

    @JsonProperty("request_info")
    private AvatarRequestInfo requestInfo;

}
