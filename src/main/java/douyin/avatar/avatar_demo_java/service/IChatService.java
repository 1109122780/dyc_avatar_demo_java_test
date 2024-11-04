package douyin.avatar.avatar_demo_java.service;

import douyin.avatar.avatar_demo_java.response.dto.ChatResponseDTO;
import douyin.avatar.avatar_demo_java.service.dto.ChatDTO;

public interface IChatService {

    ChatResponseDTO Chat(ChatDTO chatDTO) throws Exception;

}
