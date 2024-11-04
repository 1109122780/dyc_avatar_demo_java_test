package douyin.avatar.avatar_demo_java.response;

import java.util.Map;

public class ApiResponse {
    private String body;
    private Map<String, String> headers;

    // 生成getter和setter方法
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}
