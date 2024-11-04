package douyin.avatar.avatar_demo_java.caller.model.memory.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import douyin.avatar.avatar_demo_java.Enum.MemoryTypeEnum;
import douyin.avatar.avatar_demo_java.Enum.QueryOperatorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemoryTypeCondition {

    @JsonProperty("operator")
    private QueryOperatorEnum operator;

    @JsonProperty("memory_type")
    private MemoryTypeEnum memoryType;
}
