package douyin.avatar.avatar_demo_java.util.schema;

import com.volcengine.ark.runtime.model.completion.chat.ChatMessage;
import com.volcengine.ark.runtime.model.completion.chat.ChatMessageRole;
import douyin.avatar.avatar_demo_java.config.GlobalPromptConfig;
import douyin.avatar.avatar_demo_java.request.ChatContext;
import douyin.avatar.avatar_demo_java.request.Message;
import douyin.avatar.avatar_demo_java.util.Convertor;

import java.util.ArrayList;
import java.util.List;

public class ChatContentUtil {

    public static List<ChatMessage> FromMessages(Message thisMessage, ChatContext chatContext) {
        List<ChatMessage> chatMessages = new ArrayList<>();

        // 设置人设
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setRole(ChatMessageRole.SYSTEM);
        chatMessage.setContent(GlobalPromptConfig.PROMPT_STRING);
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
