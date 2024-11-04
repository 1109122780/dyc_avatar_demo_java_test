package douyin.avatar.avatar_demo_java.response.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class BaseResp {

    @JsonProperty("StatusMessage")
    private String statusMessage;

    @JsonProperty("statusCode")
    private Integer statusCode;

    @JsonProperty("Extra")
    private Map<String, String> Extra;

    public BaseResp() {
        this.statusMessage = "success";
        this.statusCode = 0;
    }

}
