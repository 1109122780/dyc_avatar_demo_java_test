package douyin.avatar.avatar_demo_java.caller;

import douyin.avatar.avatar_demo_java.service.IChunkStreamHandle;
import douyin.avatar.avatar_demo_java.service.dto.ChatStreamDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

public class AIModelConsumer implements Consumer<String> {

    private static final Logger logger = LoggerFactory.getLogger(AIModelConsumer.class);

    private HttpServletResponse response;

    private IChunkStreamHandle chunkStreamHandle;

    private ChatStreamDTO chatStreamDTO;

    public AIModelConsumer(ChatStreamDTO chatStreamDTO, HttpServletResponse resp, IChunkStreamHandle chunkStreamHandle) {
        this.response = resp;
        this.chunkStreamHandle = chunkStreamHandle;
        this.chatStreamDTO = chatStreamDTO;
    }

    @Override
    public void accept(String s) {
        logger.info("[AIModelConsumer] accept do chunk stream resp:{}", s);
        chunkStreamHandle.DoChunkStream(this.chatStreamDTO, s, this.response);
    }

    @Override
    public Consumer<String> andThen(Consumer<? super String> after) {
        return Consumer.super.andThen(after);
    }

}
