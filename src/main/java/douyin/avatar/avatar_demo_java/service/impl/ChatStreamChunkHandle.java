package douyin.avatar.avatar_demo_java.service.impl;

import com.alibaba.fastjson2.JSON;
import com.volcengine.ark.runtime.model.completion.chat.ChatCompletionChunk;
import douyin.avatar.avatar_demo_java.Enum.ContentTypeEnum;
import douyin.avatar.avatar_demo_java.Enum.RoleEnum;
import douyin.avatar.avatar_demo_java.Enum.SegTypeEnum;
import douyin.avatar.avatar_demo_java.config.GlobalPromptConfig;
import douyin.avatar.avatar_demo_java.response.CopilotContent;
import douyin.avatar.avatar_demo_java.response.HttpResponse;
import douyin.avatar.avatar_demo_java.response.common.BaseResp;
import douyin.avatar.avatar_demo_java.response.dto.ChatStreamResponseDTO;
import douyin.avatar.avatar_demo_java.service.IChunkStreamHandle;
import douyin.avatar.avatar_demo_java.service.dto.ChatStreamDTO;
import douyin.avatar.avatar_demo_java.util.ChunkSendWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatStreamChunkHandle implements IChunkStreamHandle {

    private final static Logger logger = LoggerFactory.getLogger(ChatStreamChunkHandle.class);

    private ChunkSendWriter<HttpResponse> chunkSendWriter;

    public ChatStreamChunkHandle(ChunkSendWriter<HttpResponse> chunkSendWriter) {
        this.chunkSendWriter = chunkSendWriter;
    }

    @Override
    public void DoChunkStream(ChatStreamDTO chatStreamDTO, String data, HttpServletResponse resp) {
        // 先去掉\r\n
        data = data.replaceFirst(chunkSendWriter.predictTransSign, "");
        // 去掉流体信息最开始的data前缀
        data = data.replaceFirst("^data: ", "");
        if (StringUtils.isEmpty(data)) {
            return;
        }
        if (data.equals("[DONE]")) {
            // 自定义逻辑, 如果数据结束了，结束的时候出一个卡片demo, 卡片逻辑
            if (chatStreamDTO != null) {
                if (chatStreamDTO.getMessage().getMessageContent().getContent().startsWith("卡片")) {
                    SendCardData(GlobalPromptConfig.XIAOHE_CARD, resp);
                }
            }
            // 结束数据发送
            // 发送一个空的数据
            EndChunkStream("", resp);
            this.chunkSendWriter.EndWriteChunked(resp);
            return;
        }
        //组装返回信息
        ChatStreamResponseDTO chatStreamResponseDTO = new ChatStreamResponseDTO();
        chatStreamResponseDTO.setStreamFinish(false);
        chatStreamResponseDTO.setBaseResp(new BaseResp());

        ChatCompletionChunk chatCompletionChunk = JSON.parseObject(data, ChatCompletionChunk.class);
        CopilotContent copilotContent = new CopilotContent();
        copilotContent.setSegType(SegTypeEnum.ANSWER);
        copilotContent.setContent(chatCompletionChunk.getChoices().get(0).getMessage().getContent().toString());
        copilotContent.setRoleEnum(RoleEnum.Assistant);
        copilotContent.setSegFinish(false);
        copilotContent.setContentType(ContentTypeEnum.TEXT);
        chatStreamResponseDTO.setCopilotContent(copilotContent);
        try {
            // 发送
            this.chunkSendWriter.Send(resp, HttpResponse.SuccessResponse(chatStreamResponseDTO));
        } catch (Exception e) {
            logger.error("[DoChunkStream] happen Exception, err={}", e.getMessage(), e);
        }
    }

    @Override
    public void EndChunkStream(String data, HttpServletResponse resp) {
        // 结束标志
        ChatStreamResponseDTO chatStreamResponseEndDTO = new ChatStreamResponseDTO();
        chatStreamResponseEndDTO.setStreamFinish(true);
        chatStreamResponseEndDTO.setBaseResp(new BaseResp());

        CopilotContent copilotContent = new CopilotContent();
        copilotContent.setSegType(SegTypeEnum.ANSWER);
        copilotContent.setContent(data);
        copilotContent.setContentType(ContentTypeEnum.TEXT);
        copilotContent.setRoleEnum(RoleEnum.Assistant);
        copilotContent.setSegFinish(true);
        chatStreamResponseEndDTO.setCopilotContent(copilotContent);
        try {
            this.chunkSendWriter.Send(resp, HttpResponse.SuccessResponse(chatStreamResponseEndDTO));
        } catch (Exception e) {
            logger.error("[EndChunkStream] happen Exception, err={}", e.getMessage(), e);
        }
    }

    public void SendCardData(String cardData, HttpServletResponse resp) {
        //组装返回信息
        ChatStreamResponseDTO chatStreamResponseDTO = new ChatStreamResponseDTO();
        chatStreamResponseDTO.setStreamFinish(false);
        chatStreamResponseDTO.setBaseResp(new BaseResp());

        CopilotContent copilotContent = new CopilotContent();
        copilotContent.setSegType(SegTypeEnum.ANSWER);
        copilotContent.setContent(cardData);
        copilotContent.setRoleEnum(RoleEnum.Assistant);
        copilotContent.setSegFinish(false);
        copilotContent.setContentType(ContentTypeEnum.CARD);
        chatStreamResponseDTO.setCopilotContent(copilotContent);
        try {
            // 发送
            this.chunkSendWriter.Send(resp, HttpResponse.SuccessResponse(chatStreamResponseDTO));
        } catch (Exception e) {
            logger.error("[SendCardData] happen Exception, err={}", e.getMessage(), e);
        }
    }

}
