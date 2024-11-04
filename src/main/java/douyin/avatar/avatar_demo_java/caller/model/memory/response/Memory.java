package douyin.avatar.avatar_demo_java.caller.model.memory.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import douyin.avatar.avatar_demo_java.Enum.MemoryTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Memory {

    @JsonProperty("memory_id")
    private String memoryID;

    @JsonProperty("user_id")
    private String userID;

    @JsonProperty("avatar_id")
    private String avatarID;

    @JsonProperty("memory_type")
    private MemoryTypeEnum memoryType;

    @JsonProperty("memory_name")
    private String memoryName;

    @JsonProperty("group")
    private String group;

    @JsonProperty("sub_group")
    private String subGroup;

    @JsonProperty("content")
    private String content;

    @JsonProperty("content_vector")
    private List<Float> contentVector;

    @JsonProperty("created_at_unix_milli")
    private int createdAtUnixMilli;

    @JsonProperty("last_updated_at_unix_milli")
    private int lastUpdatedAtUnixMilli;

    @JsonProperty("memory_date_at_unix_sec")
    private int memoryDateAtUnixSec;

    @JsonProperty("ext")
    private Map<String, String> ext;
}
