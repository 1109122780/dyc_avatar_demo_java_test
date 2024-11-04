package douyin.avatar.avatar_demo_java.caller;

import com.alibaba.fastjson.JSON;
import douyin.avatar.avatar_demo_java.caller.model.memory.request.AvatarRequestInfo;
import douyin.avatar.avatar_demo_java.caller.model.memory.request.MemoryRecallParams;
import douyin.avatar.avatar_demo_java.caller.model.memory.request.MemoryRecallRequest;
import douyin.avatar.avatar_demo_java.caller.model.memory.response.Memory;
import douyin.avatar.avatar_demo_java.caller.model.memory.response.MemoryRecallHttpResponse;
import douyin.avatar.avatar_demo_java.service.dto.ChatDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryApiRestTemplate extends ExtendApiRestTplAbstract<MemoryRecallRequest> {

    private static final Logger logger = LoggerFactory.getLogger(MemoryApiRestTemplate.class);

    private static final String MEMORY_API_URL = "https://open-ai.byted.org/dy_open_api/avatar/atomic/api/recall_memory/";

    // 调用记忆的api接口
    public List<String> CallMemoryApiRestMethod(ChatDTO chatDTO) {
        List<String> resultList = new ArrayList<>();
        // 封装参数
        MemoryRecallRequest memoryRecallRequest = new MemoryRecallRequest();
        try {
            MemoryRecallParams memoryRecallParams = new MemoryRecallParams();
            memoryRecallParams.setDsl(null);
            memoryRecallParams.setQuery(chatDTO.getMessage().getMessageContent().getContent());
            memoryRecallParams.setLimit(10);

            AvatarRequestInfo avatarRequestInfo = new AvatarRequestInfo();
            avatarRequestInfo.setBizID(chatDTO.getBizContext().getBizID());
            avatarRequestInfo.setTrafficSource(chatDTO.getCommonContext().getTrafficSource());
            avatarRequestInfo.setOpenID(chatDTO.getCommonContext().getUserInfo().getOpenID());
            avatarRequestInfo.setAvatarAppID(chatDTO.getCommonContext().getAvatarInfo().getAvatarAppID());
            avatarRequestInfo.setTenantID("ebtest_1");
            avatarRequestInfo.setProviderID("ebtest_1");

            memoryRecallRequest.setParams(memoryRecallParams);
            memoryRecallRequest.setRequestInfo(avatarRequestInfo);
        } catch (Exception e) {
            return resultList;
        }
        try {
            Map<String, String> headMap = new HashMap<>();
            headMap.put("Content-Type", "application/json");
            headMap.put("x-use-ppe", "1");
            headMap.put("x-tt-env", "ppe_aiavatar_v1");
            // todo
            headMap.put("access-token", "clt.f0c473038bfb307392d778a8895609dbEqDt3XRmIEeiyHApGy9mlg30jeR1_hl");

            String responseBody = this.PostCallMethod(MEMORY_API_URL, memoryRecallRequest, headMap);
            MemoryRecallHttpResponse memoryRecallHttpResponse = JSON.parseObject(responseBody, MemoryRecallHttpResponse.class);
            if (memoryRecallHttpResponse == null) {
                return resultList;
            }
            if (memoryRecallHttpResponse.getData() == null || memoryRecallHttpResponse.getData().getData().getMemories().isEmpty()) {
                return resultList;
            }
            for (Memory data : memoryRecallHttpResponse.getData().getData().getMemories()) {
                resultList.add(data.getContent());
            }
            logger.info("CallExtendApiRestMethod resultList: {}", resultList);
            return resultList;
        } catch (Exception e) {
            logger.error("CallExtendApiRestMethod 调用异常, error={}", e.getMessage(), e);
            return resultList;
        }
    }
}
