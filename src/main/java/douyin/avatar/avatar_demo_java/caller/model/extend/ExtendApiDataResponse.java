package douyin.avatar.avatar_demo_java.caller.model.extend;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExtendApiDataResponse {

    @JsonProperty("http_code")
    private int httpCode;

    @JsonProperty("resp_body")
    private String respBody;
}
