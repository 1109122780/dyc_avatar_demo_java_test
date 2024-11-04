package douyin.avatar.avatar_demo_java.service;

import douyin.avatar.avatar_demo_java.service.dto.ChatStreamDTO;
import jakarta.servlet.http.HttpServletResponse;

public interface IChunkStreamHandle {

    void DoChunkStream(ChatStreamDTO chatStreamDTO, String data, HttpServletResponse resp);

    void EndChunkStream(String data, HttpServletResponse resp);
}
