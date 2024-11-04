package douyin.avatar.avatar_demo_java.caller.model.memory.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import douyin.avatar.avatar_demo_java.Enum.QueryOperatorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemoryNameCondition {

    @JsonProperty("operator")
    private QueryOperatorEnum operator;

    @JsonProperty("memory_names")
    private List<String> memoryNames;
}
