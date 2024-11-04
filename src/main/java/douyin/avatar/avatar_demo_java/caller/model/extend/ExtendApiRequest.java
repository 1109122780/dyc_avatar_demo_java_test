package douyin.avatar.avatar_demo_java.caller.model.extend;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    {
        "proxy_type": 1,
        "api_id": "外部api的id，即外部能力域名+path，例如https://baidu.com/query_tag/",
        "req_body_content": "json结构化的api请求参数，会放到http请求body体中"
    }
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExtendApiRequest {

    @JsonProperty("proxy_type")
    private Integer proxyType;

    @JsonProperty("api_id")
    private String apiID;

    @JsonProperty("req_body")
    private String reqBody;

}
