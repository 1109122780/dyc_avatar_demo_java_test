package douyin.avatar.avatar_demo_java.service.impl;


import douyin.avatar.avatar_demo_java.model.TextDetectionRequest;
import douyin.avatar.avatar_demo_java.service.TextDetectionService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class TextDetectionServiceImpl implements TextDetectionService {

    private static final String API_URL = "http://developer-toutiao-com.openapi.dyc.ivolces.com/api/v2/tags/text/antidirt";

    @Override
    public String performTextDetection(TextDetectionRequest request) {
        RestTemplate restTemplate = new RestTemplate();

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 创建包含请求体和请求头的HttpEntity对象
        HttpEntity<TextDetectionRequest> entity = new HttpEntity<>(request, headers);

        try {
            // 发送请求并获取返回结果
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(API_URL, entity, String.class);
            return responseEntity.getBody();
        } catch (RestClientException e) {
            // 处理请求发送过程中的异常
            e.printStackTrace();
            return null;
        }
    }
}
