package douyin.avatar.avatar_demo_java.controller;

import douyin.avatar.avatar_demo_java.request.vo.ChatStreamRequestVo;
import douyin.avatar.avatar_demo_java.service.IChatStreamService;
import douyin.avatar.avatar_demo_java.service.dto.ChatStreamDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatStreamController {

    private static final Logger logger = LoggerFactory.getLogger(ChatStreamController.class);

    @Autowired
    private IChatStreamService chatStreamService;

    // 定义一个处理 HTTP GET 请求的方法
    @PostMapping(value = "/avatar/serv/chatstream")
    public void ChatStream(@RequestBody ChatStreamRequestVo chatStreamRequestVo, HttpServletResponse response) {
        // 验证参数, 根据业务自行判断

        // 参数复制
        ChatStreamDTO chatStreamDTO = new ChatStreamDTO();
        BeanUtils.copyProperties(chatStreamRequestVo, chatStreamDTO);

        // 调用服务
        chatStreamService.ChatStream(chatStreamDTO, response);
    }
}
