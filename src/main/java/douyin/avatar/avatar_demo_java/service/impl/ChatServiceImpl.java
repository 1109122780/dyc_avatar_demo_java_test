package douyin.avatar.avatar_demo_java.service.impl;

import com.alibaba.fastjson.JSON;
import com.volcengine.ark.runtime.model.completion.chat.ChatCompletionRequest;
import com.volcengine.ark.runtime.model.completion.chat.ChatCompletionResult;
import douyin.avatar.avatar_demo_java.Enum.ContentTypeEnum;
import douyin.avatar.avatar_demo_java.Enum.RoleEnum;
import douyin.avatar.avatar_demo_java.Enum.SegTypeEnum;
import douyin.avatar.avatar_demo_java.caller.AIModelApiRestTemplate;
import douyin.avatar.avatar_demo_java.caller.ExtendApiRestTemplate;
import douyin.avatar.avatar_demo_java.caller.model.extend.ExtendApiResponse;
import douyin.avatar.avatar_demo_java.config.GlobalPromptConfig;
import douyin.avatar.avatar_demo_java.response.CopilotContent;
import douyin.avatar.avatar_demo_java.response.dto.ChatResponseDTO;
import douyin.avatar.avatar_demo_java.service.IChatService;
import douyin.avatar.avatar_demo_java.service.dto.ChatDTO;
import douyin.avatar.avatar_demo_java.util.schema.ChatContentUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChatServiceImpl implements IChatService {

    private final static Logger logger = LoggerFactory.getLogger(ChatServiceImpl.class);

    @Autowired
    private ExtendApiRestTemplate extendApiRestTemplate;

    @Autowired
    private AIModelApiRestTemplate aiModelApiRestTemplate;

    @Override
    public ChatResponseDTO Chat(ChatDTO chatDTO) throws Exception {

        // 调用外部服务void的case，换成自己的外域api，还有参数 todo
        String apiID = "https://1k58b3nbl3le2-env-bFueXg6P40.service.douyincloud.run/api/user/info";

        // 2. 组装参数demo, 根据自己的接口去拼接
        Map<String, String> contentMap = new HashMap<>();
        // todo
        contentMap.put("open_id", "_0008lHfIVOBvxU6V7YcUdMIMEAqxfFLwKT8");
        String requestBody = JSON.toJSONString(contentMap);

        // 3. 调用请求函数，这个函数是专门请求外域的统一入口, 可根据自己的要求调整
        ExtendApiResponse extendApiResponse = extendApiRestTemplate.CallExtendApiRestMethod(apiID, requestBody);
        if (extendApiResponse != null) {
            logger.info("Chat CallExtendApiRestMethod extendApiResponse: {}", extendApiResponse);
        }
        ChatCompletionRequest streamChatCompletionRequest = ChatCompletionRequest.builder()
                .model(GlobalPromptConfig.MODEL_EP)
                .messages(ChatContentUtil.FromMessages(chatDTO.getMessage(), chatDTO.getChatContext(), null))
                .stream(false)
                .build();

        // 调用模型非流式
        ChatResponseDTO chatResponseDTO = new ChatResponseDTO();
        ChatCompletionResult chatCompletionResult = aiModelApiRestTemplate.CallAIModelRestNotStream(streamChatCompletionRequest);
        // 组装返回参数
        if (chatCompletionResult != null) {
            CopilotContent copilotContent = new CopilotContent();
            copilotContent.setSegType(SegTypeEnum.ANSWER);
            copilotContent.setContent(JSON.toJSONString(extendApiResponse) + " ### " + chatCompletionResult.getChoices().get(0).getMessage().getContent().toString());
            copilotContent.setRoleEnum(RoleEnum.Assistant);
            copilotContent.setSegFinish(true);
            copilotContent.setContentType(ContentTypeEnum.TEXT);
            chatResponseDTO.setCopilotContent(copilotContent);
        }
        return chatResponseDTO;
    }

}
