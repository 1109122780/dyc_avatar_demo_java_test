package douyin.avatar.avatar_demo_java.controller;

import douyin.avatar.avatar_demo_java.request.vo.OnBoardingRequestVo;
import douyin.avatar.avatar_demo_java.service.IOnBoardingService;
import douyin.avatar.avatar_demo_java.service.dto.OnBoardingDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OnBoardingController {

    private static final Logger logger = LoggerFactory.getLogger(OnBoardingController.class);

    @Autowired
    private IOnBoardingService onBoardingService;

    // 定义一个处理 HTTP GET 请求的方法
    @PostMapping(value = "/avatar/serv/onboarding")
    public void OnBoarding(@RequestBody OnBoardingRequestVo onBoardingRequestVo, HttpServletResponse resp) {
        // 验证参数，可根据业务自行判断

        // 将用户入参数复制，复制DTO
        OnBoardingDTO onBoardingDTO = new OnBoardingDTO();
        BeanUtils.copyProperties(onBoardingRequestVo, onBoardingDTO);

        // 调用服务
        onBoardingService.OnBoarding(onBoardingDTO, resp);
    }
}
