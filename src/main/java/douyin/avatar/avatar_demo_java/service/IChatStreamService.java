package douyin.avatar.avatar_demo_java.service;

import douyin.avatar.avatar_demo_java.service.dto.ChatStreamDTO;
import jakarta.servlet.http.HttpServletResponse;

public interface IChatStreamService {

    void ChatStream(ChatStreamDTO chatStreamDTO, HttpServletResponse resp);
}
