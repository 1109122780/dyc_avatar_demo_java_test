package douyin.avatar.avatar_demo_java.request.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BizContext {

    @JsonProperty("biz_id")
    private String bizID;

}
