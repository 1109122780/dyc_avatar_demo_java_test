package douyin.avatar.avatar_demo_java.caller;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.function.Consumer;

public abstract class ExtendApiRestTplAbstract<T> {

    private static final Logger logger = LoggerFactory.getLogger(ExtendApiRestTplAbstract.class);

    @Autowired
    private RestTemplate restTemplate;

    // post请求外部api
    protected String PostCallMethod(String url, T requestData, Map<String, String> headMap) throws Exception {
        logger.info("[PostCallMethod] apiUrl={}, requestData={}, headMap={}", url, JSON.toJSONString(requestData), headMap);
        HttpHeaders headers = new HttpHeaders();
        // 设置外部的请求头
        for (Map.Entry<String, String> entry : headMap.entrySet()) {
            headers.set(entry.getKey(), entry.getValue());
        }
        HttpEntity<T> requestEntity = new HttpEntity<>(requestData, headers);
        // 发送请求
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
        logger.info("[PostCallMethod] apiUrl={}, response={}, body={}", url, JSON.toJSONString(responseEntity), new String(responseEntity.getBody().getBytes(StandardCharsets.UTF_8)));
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            // 如果http状态不是200，则直接抛出异常
            throw new Exception(responseEntity.getBody());
        }
        return responseEntity.getBody();
    }

    protected void CallSseStreamMethod(String url, T requestData, Map<String, String> headMap, Consumer<String> dataConsumer) {
        logger.info("[CallSseStreamMethod] apiUrl={}, requestData={}, headMap={}", url, JSON.toJSON(requestData), headMap);
        restTemplate.execute(url, HttpMethod.POST, request -> prepareRequest(request, requestData, headMap), response -> {
            try {
                InputStreamReader reader;
                reader = new InputStreamReader(response.getBody(), StandardCharsets.UTF_8);
                try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        dataConsumer.accept(line);
                    }
                }
                return null;
            } catch (IOException e) {
                logger.error("[CallSseStream] IOException, error={}", e.getMessage(), e);
                return null;
            }
        });
    }

    private void prepareRequest(ClientHttpRequest request, T requestData, Map<String, String> headMap) throws IOException {
        // 设置请求头到ClientHttpRequest
        if (headMap != null) {
            for (Map.Entry<String, String> headData : headMap.entrySet()) {
                request.getHeaders().set(headData.getKey(), headData.getValue());
            }
        }
        // 设置结构体到ClientHttpRequest
        if (requestData != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            String bodyJson = objectMapper.writeValueAsString(requestData);
            request.getBody().write(bodyJson.getBytes());
        }
    }

}
