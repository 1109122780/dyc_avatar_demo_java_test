package douyin.avatar.avatar_demo_java.caller;

import com.alibaba.fastjson.JSON;
import douyin.avatar.avatar_demo_java.caller.model.extend.ExtendApiRequest;
import douyin.avatar.avatar_demo_java.caller.model.extend.ExtendApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ExtendApiRestTemplate extends ExtendApiRestTplAbstract<String> {

    private static final Logger logger = LoggerFactory.getLogger(ExtendApiRestTemplate.class);

    private static final String EXTEND_API_URL = "http://open-ai-byted-org.dyc.ivolces.com/dy_open_api/ai/http_proxy/";

    // 调用外部的api接口
    public ExtendApiResponse CallExtendApiRestMethod(String apiUrl, String reqContent) {
        // 调用Get方法
        try {
            Map<String, String> headMap = new HashMap<>();
            headMap.put("Content-Type", "application/json");
            headMap.put("x-use-ppe", "1");
            headMap.put("x-tt-env", "ppe_aiavatar_v1");
            // 拼接请求链接
            ExtendApiRequest extendApiRequest = new ExtendApiRequest();
            extendApiRequest.setProxyType(1);
            extendApiRequest.setApiID(apiUrl);
            extendApiRequest.setReqBody(reqContent);

            String responseBody = this.PostCallMethod(EXTEND_API_URL, JSON.toJSONString(extendApiRequest), headMap);
            return JSON.parseObject(responseBody, ExtendApiResponse.class);
        } catch (Exception e) {
            logger.error("CallExtendApiRestMethod 调用异常, error={}", e.getMessage(), e);
            return null;
        }
    }
}
