package douyin.avatar.avatar_demo_java.service.impl;

import com.volcengine.ark.runtime.model.completion.chat.ChatCompletionRequest;
import douyin.avatar.avatar_demo_java.caller.AIModelApiRestTemplate;
import douyin.avatar.avatar_demo_java.caller.AIModelConsumer;
import douyin.avatar.avatar_demo_java.caller.MemoryApiRestTemplate;
import douyin.avatar.avatar_demo_java.config.GlobalPromptConfig;
import douyin.avatar.avatar_demo_java.service.IChatStreamService;
import douyin.avatar.avatar_demo_java.service.dto.ChatStreamDTO;
import douyin.avatar.avatar_demo_java.util.ChunkSendWriter;
import douyin.avatar.avatar_demo_java.util.schema.ChatContentUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatStreamServiceImpl implements IChatStreamService {


    private static final Logger logger = LoggerFactory.getLogger(ChatStreamServiceImpl.class);

    @Autowired
    private AIModelApiRestTemplate aiModelApiRestTemplate;

    @Autowired
    private MemoryApiRestTemplate memoryApiRestTemplate;

    @Override
    public void ChatStream(ChatStreamDTO chatStreamDTO, HttpServletResponse resp) {
        // 1. 调用一下长期记忆能力, demo
        List<String> longHistory = memoryApiRestTemplate.CallMemoryApiRestMethod(chatStreamDTO);

        // 2. 获取用户的输入
        ChatCompletionRequest streamChatCompletionRequest = ChatCompletionRequest.builder()
                .model(GlobalPromptConfig.MODEL_EP)
                .messages(ChatContentUtil.FromMessages(chatStreamDTO.getMessage(), chatStreamDTO.getChatContext(), longHistory))
                .stream(true)
                .build();

        // 初始化回调处理器
        ChatStreamChunkHandle chatStreamChunkHandle = new ChatStreamChunkHandle(new ChunkSendWriter<>());
        AIModelConsumer aiModelConsumer = new AIModelConsumer(chatStreamDTO, resp, chatStreamChunkHandle);
        // 调用大模型, 流式
        aiModelApiRestTemplate.CallAIModelRestStream(streamChatCompletionRequest, aiModelConsumer);
    }
}
