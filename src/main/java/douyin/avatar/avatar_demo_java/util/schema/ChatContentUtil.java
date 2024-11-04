package douyin.avatar.avatar_demo_java.util.schema;

import com.volcengine.ark.runtime.model.completion.chat.ChatMessage;
import com.volcengine.ark.runtime.model.completion.chat.ChatMessageRole;
import douyin.avatar.avatar_demo_java.config.GlobalPromptConfig;
import douyin.avatar.avatar_demo_java.request.ChatContext;
import douyin.avatar.avatar_demo_java.request.Message;
import douyin.avatar.avatar_demo_java.util.Convertor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ChatContentUtil {

    private static final Logger logger = LoggerFactory.getLogger(ChatContentUtil.class);

    public static List<ChatMessage> FromMessages(Message thisMessage, ChatContext chatContext, List<String> longHistory) {
        List<ChatMessage> chatMessages = new ArrayList<>();

        // 长期记忆直接merge到system prompt
        String longHistoryStr = "";
        if (longHistory != null && !longHistory.isEmpty()) {
            for (String history : longHistory) {
                longHistoryStr = String.format("%s%s\n", longHistoryStr, history);
            }
        }

        String prompt = String.format(GlobalPromptConfig.PROMPT_STRING, longHistoryStr);
        logger.info("system prompt: {}", prompt);
        // 设置人设
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setRole(ChatMessageRole.SYSTEM);
        chatMessage.setContent(prompt);
        // 加入数组中
        chatMessages.add(chatMessage);


        if (chatContext != null) {
            List<Message> history = chatContext.getMessageContext();
            // 设置历史聊天记录
            if (history != null && !history.isEmpty()) {
                for (Message message : history) {
                    ChatMessage chatMessageH = new ChatMessage();
                    chatMessageH.setRole(Convertor.AvatarServRole2ArkRole(message.roleEnum));
                    chatMessageH.setContent(message.getMessageContent().getContent());
                    // 加入数组中
                    chatMessages.add(chatMessageH);
                }
            }
        }

        // 加入本次聊天记录
        ChatMessage chatMessageNow = new ChatMessage();
        chatMessageNow.setRole(ChatMessageRole.USER);
        chatMessageNow.setContent(thisMessage.getMessageContent().getContent());
        // 加入数组中
        chatMessages.add(chatMessageNow);

        return chatMessages;
    }

}
