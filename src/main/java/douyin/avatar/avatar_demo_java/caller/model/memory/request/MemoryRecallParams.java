package douyin.avatar.avatar_demo_java.caller.model.memory.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemoryRecallParams {

    @JsonProperty("dsl")
    private MemoryDsl dsl;

    @JsonProperty("query")
    private String query;

    @JsonProperty("limit")
    private int limit;

}
