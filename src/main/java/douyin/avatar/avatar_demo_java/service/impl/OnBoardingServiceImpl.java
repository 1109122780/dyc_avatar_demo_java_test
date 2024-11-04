package douyin.avatar.avatar_demo_java.service.impl;

import douyin.avatar.avatar_demo_java.Enum.ContentTypeEnum;
import douyin.avatar.avatar_demo_java.Enum.RoleEnum;
import douyin.avatar.avatar_demo_java.Enum.SegTypeEnum;
import douyin.avatar.avatar_demo_java.response.CopilotContent;
import douyin.avatar.avatar_demo_java.response.HttpResponse;
import douyin.avatar.avatar_demo_java.response.common.BaseResp;
import douyin.avatar.avatar_demo_java.response.dto.OnBoardingResponseDTO;
import douyin.avatar.avatar_demo_java.service.IOnBoardingService;
import douyin.avatar.avatar_demo_java.service.dto.OnBoardingDTO;
import douyin.avatar.avatar_demo_java.util.ChunkSendWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OnBoardingServiceImpl implements IOnBoardingService {

    private static final Logger logger = LoggerFactory.getLogger(OnBoardingServiceImpl.class);

    @Override
    public void OnBoarding(OnBoardingDTO onBoardingRequestDTO, HttpServletResponse resp) {
        // 开场白 demo, 可根据业务自行调整。
        List<String> respString = new ArrayList<>();
        respString.add("你好,");
        respString.add("我是赵小小,");
        respString.add("两性情感");
        respString.add("专家,");
        respString.add("您有什么");
        respString.add("想咨询");
        respString.add("的问题");

        // 初始化一个chunk发送器
        ChunkSendWriter<HttpResponse> chunkSendWriter = new ChunkSendWriter<>();

        // 循环这个demo数据向调用方发送数据
        for (int idx = 0; idx < respString.size(); idx++) {
            try {
                // 组装返回数据
                OnBoardingResponseDTO onBoardingResponseDTO = new OnBoardingResponseDTO();
                onBoardingResponseDTO.setStreamFinish(false);

                CopilotContent copilotContent = new CopilotContent();
                copilotContent.setContentType(ContentTypeEnum.TEXT);
                copilotContent.setContent(respString.get(idx));
                copilotContent.setRoleEnum(RoleEnum.Assistant);
                copilotContent.setSegFinish(false);
                if (idx == respString.size() - 1) {
                    copilotContent.setSegFinish(true);
                }
                copilotContent.setSegType(SegTypeEnum.ANSWER);
                onBoardingResponseDTO.setCopilotContent(copilotContent);
                onBoardingResponseDTO.setBaseResp(new BaseResp());

                // 发送
                chunkSendWriter.Send(resp, HttpResponse.SuccessResponse(onBoardingResponseDTO));
                // 模拟休眠
                Thread.sleep(200);
            } catch (Exception e) {
                logger.error("OnBoarding.send error={}", e.getMessage(), e);
            }
        }

        // 定义一些sug类型的数据demo
        List<String> sugList = new ArrayList<>();
        sugList.add("异地恋该如何长期维护稳定？");
        sugList.add("女朋友总是长期不回答消息，表明了什么？");
        sugList.add("如何给暗恋多年的女神表白？");

        // 发送
        for (int idx = 0; idx < sugList.size(); idx++) {
            try {
                // 组装返回数据
                OnBoardingResponseDTO onBoardingResponseDTO = new OnBoardingResponseDTO();
                onBoardingResponseDTO.setStreamFinish(false);

                CopilotContent copilotContent = new CopilotContent();
                copilotContent.setContentType(ContentTypeEnum.TEXT);
                copilotContent.setContent(sugList.get(idx));
                copilotContent.setRoleEnum(RoleEnum.Assistant);
                copilotContent.setSegFinish(false);
                if (idx == sugList.size() - 1) {
                    copilotContent.setSegFinish(true);
                    onBoardingResponseDTO.setStreamFinish(true);
                }
                copilotContent.setSegType(SegTypeEnum.FOLLOWUP);
                onBoardingResponseDTO.setCopilotContent(copilotContent);
                onBoardingResponseDTO.setBaseResp(new BaseResp());

                // 发送
                chunkSendWriter.Send(resp, HttpResponse.SuccessResponse(onBoardingResponseDTO));
                Thread.sleep(200);
            } catch (Exception e) {
                logger.error("OnBoarding.send error={}", e.getMessage(), e);
            }
        }

        try {
            chunkSendWriter.EndWriteChunked(resp);
        } catch (Exception e) {
            logger.error("OnBoarding.send error={}", e.getMessage(), e);
        }

    }
}