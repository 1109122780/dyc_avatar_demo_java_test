package douyin.avatar.avatar_demo_java.util;

import com.volcengine.ark.runtime.model.completion.chat.ChatMessageRole;
import douyin.avatar.avatar_demo_java.Enum.RoleEnum;

public class Convertor {

    public static ChatMessageRole AvatarServRole2ArkRole(RoleEnum roleEnum) {
        switch (roleEnum) {
            case Assistant:
                return ChatMessageRole.ASSISTANT;
            case SYSTEM:
                return ChatMessageRole.SYSTEM;
            case USER:
                return ChatMessageRole.USER;
        }
        return ChatMessageRole.USER;
    }
}
