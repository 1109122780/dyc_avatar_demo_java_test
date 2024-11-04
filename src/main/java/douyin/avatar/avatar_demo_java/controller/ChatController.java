package douyin.avatar.avatar_demo_java.controller;

import douyin.avatar.avatar_demo_java.request.vo.ChatRequestVo;
import douyin.avatar.avatar_demo_java.response.HttpResponse;
import douyin.avatar.avatar_demo_java.response.dto.ChatResponseDTO;
import douyin.avatar.avatar_demo_java.service.IChatService;
import douyin.avatar.avatar_demo_java.service.dto.ChatDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private IChatService chatService;

    // 定义一个处理 HTTP GET 请求的方法
    @PostMapping("/avatar/serv/chat")
    @ResponseBody
    public HttpResponse Chat(@RequestBody ChatRequestVo chatRequestVo) {
        // 1. 校验参数, 根据业务自行判断

        // 2. 对象复制
        ChatDTO chatDTO = new ChatDTO();
        BeanUtils.copyProperties(chatRequestVo, chatDTO);
        try {
            // 3. 调用服务
            ChatResponseDTO chatResponseDTO = chatService.Chat(chatDTO);
            return HttpResponse.SuccessResponse(chatResponseDTO);
        } catch (Exception e) {
            logger.error("Request [Chat] fail, err={}, e", e.getMessage(), e);
            // 如果有异常，则返回异常的结构体
            return HttpResponse.ErrResponse(-1, e.getMessage());
        }

    }
}
