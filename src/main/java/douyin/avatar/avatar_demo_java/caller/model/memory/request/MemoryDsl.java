package douyin.avatar.avatar_demo_java.caller.model.memory.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemoryDsl {

    @JsonProperty("query_conditions")
    private QueryCondition queryConditions;

    @JsonProperty("enable_vector_search")
    private boolean enableVectorSearch;

    @JsonProperty("enable_keyword_search")
    private boolean enableKeywordSearch;

}
