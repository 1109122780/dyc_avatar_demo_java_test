package douyin.avatar.avatar_demo_java.controller;

import douyin.avatar.avatar_demo_java.request.ApiRequest;
import douyin.avatar.avatar_demo_java.response.ApiResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ApiCallController {
    @PostMapping("/runOpenApi")
    public ResponseEntity<ApiResponse> runOpenApi(@RequestBody ApiRequest req) {
        ApiResponse resp = new ApiResponse();
        resp.setHeaders(new HashMap<>());
        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            if (req.getHeaders() != null) {
                for (Map.Entry<String, String> entry : req.getHeaders().entrySet()) {
                    headers.add(entry.getKey(), entry.getValue());
                }
            }
            HttpEntity<String> entity = new HttpEntity<>(req.getBody(), headers);

            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            requestFactory.setConnectTimeout(10000);
            requestFactory.setReadTimeout(10000);
            restTemplate.setRequestFactory(requestFactory);
            // 发送HTTP请求并获取响应
            ResponseEntity<String> httpResp = restTemplate.exchange(
                    req.getUrl(),
                    HttpMethod.valueOf(req.getMethod().toUpperCase()),
                    entity,
                    String.class
            );
            // 处理响应
            resp.setBody(httpResp.getBody());
            for (Map.Entry<String, List<String>> entry : httpResp.getHeaders().entrySet()) {
                resp.getHeaders().put(entry.getKey(), String.join(",", entry.getValue()));
            }

            return ResponseEntity.ok(resp);
        } catch (Exception e) {
            resp.setBody("请求失败，err: " + e.getMessage());
            return ResponseEntity.status(500).body(resp);
        }
    }
}
