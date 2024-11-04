package douyin.avatar.avatar_demo_java.caller;

import com.alibaba.fastjson.JSON;
import com.volcengine.ark.runtime.model.completion.chat.ChatCompletionRequest;
import com.volcengine.ark.runtime.model.completion.chat.ChatCompletionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AIModelApiRestTemplate extends ExtendApiRestTplAbstract<ChatCompletionRequest> {

    private static final Logger logger = LoggerFactory.getLogger(AIModelApiRestTemplate.class);

    // todo
    // private static final String API_URL = "https://ark.cn-beijing.volces.com/api/v3/chat/completions";
    private static final String API_URL = "http://ark-vg.dyc.ivolces.com/api/v3/chat/completions";

    // 调用大模型的api接口
    public void CallAIModelRestStream(ChatCompletionRequest chatCompletionRequest, AIModelConsumer aiModelConsumer) {
        try {
            Map<String, String> headMap = new HashMap<>();
            // 请求参数封装, 如果请求头需要，则在这里加入
            headMap.put("Accept", "text/event-stream");
            headMap.put("Cache-Control", "no-cache");
            headMap.put("Connection", "keep-alive");
            // todo
            // headMap.put("Authorization", "Bearer 3dad2a1f-7c1c-4350-80f8-dd057ffa3aba");
            headMap.put("Content-Type", "application/json");
            // 调用sse流式接口
            this.CallSseStreamMethod(API_URL, chatCompletionRequest, headMap, aiModelConsumer);
        } catch (Exception e) {
            logger.error("[CallAIModelRestStream] 调用异常, error={}", e.getMessage(), e);
        }
    }

    // 调用大模型的api接口，非流式接口
    public ChatCompletionResult CallAIModelRestNotStream(ChatCompletionRequest chatCompletionRequest) throws Exception {
        Map<String, String> headMap = new HashMap<>();
        // 调用post接口
        headMap.put("Accept", "application/json");
        // todo
        // headMap.put("Authorization", "Bearer 3dad2a1f-7c1c-4350-80f8-dd057ffa3aba");
        headMap.put("Content-Type", "application/json");
        String responseBody = this.PostCallMethod(API_URL, chatCompletionRequest, headMap);
        return JSON.parseObject(responseBody, ChatCompletionResult.class);
    }
}
