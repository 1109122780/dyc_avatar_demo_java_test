package douyin.avatar.avatar_demo_java.caller.model.extend;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExtendApiResponse {

    @JsonProperty("err_no")
    private int errNo;

    @JsonProperty("err_msg")
    private String errMsg;

    @JsonProperty("log_id")
    private String logId;

    @JsonProperty("data")
    private ExtendApiDataResponse data;
}
