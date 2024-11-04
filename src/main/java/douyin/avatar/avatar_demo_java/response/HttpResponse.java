package douyin.avatar.avatar_demo_java.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HttpResponse {

    @JsonProperty("err_no")
    private int errNo;

    @JsonProperty("err_msg")
    private String errMsg;

    @JsonProperty("data")
    private Object data;

    // 成功返回结构体
    public static HttpResponse SuccessResponse(Object data) {
        return new HttpResponse(0, "success", data);
    }

    // 失败结构体
    public static HttpResponse ErrResponse(int code, String msg) {
        return new HttpResponse(code, msg, new HashMap<String, Object>());
    }
}
