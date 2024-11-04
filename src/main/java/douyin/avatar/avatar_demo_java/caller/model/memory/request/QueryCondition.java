package douyin.avatar.avatar_demo_java.caller.model.memory.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QueryCondition {

    // 各筛选条件均支持in与not_in选项，分别对应operator 1与2
    @JsonProperty("content_vector_model_name_condition")
    private ContentVectorModelNameCondition contentVectorModelNameCondition;

    // 组筛选
    @JsonProperty("group_condition")
    private GroupCondition groupCondition;

    // 记忆ID筛选
    @JsonProperty("memory_id_condition")
    private MemoryIdCondition memoryIdCondition;

    // 记忆类型筛选：1为episodic，2为semantic
    @JsonProperty("memory_type_condition")
    private MemoryTypeCondition memoryTypeCondition;

    // 记忆名筛选
    @JsonProperty("memory_name_condition")
    private MemoryNameCondition memoryNameCondition;

    // 副组筛选
    @JsonProperty("sub_group_condition")
    private SubGroupCondition subGroupCondition;

    // 记忆创建时间筛选：unix milli时间戳，起始时间与终止时间为闭区间
    @JsonProperty("create_time_condition")
    private CreateTimeCondition createTimeCondition;

    // 记忆更新时间筛选：unix milli时间戳，起始时间与终止时间为闭区间
    @JsonProperty("update_time_condition")
    private UpdateTimeCondition updateTimeCondition;

    // 记忆时间筛选：unix sec时间戳，起始时间与终止时间为闭区间
    @JsonProperty("memory_date_condition")
    private MemoryDateCondition memoryDateCondition;


}
