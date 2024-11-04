package douyin.avatar.avatar_demo_java.util;

import com.alibaba.fastjson.JSON;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ChunkSendWriter<T> {

    public final String predictTransSign = "\r\n";
    public final String FinishTransSign = "0\r\n\r\n";

    private final Logger logger = LoggerFactory.getLogger(ChunkSendWriter.class);

    public ChunkSendWriter() {
    }

    public void Send(HttpServletResponse response, T data) throws Exception {
        // 获取长度
        String dataString = JSON.toJSONString(data);
        String hLen = this.getHexString(dataString.getBytes(StandardCharsets.UTF_8));

        // 写返回信息头
        response.setHeader("X-Use-Chunk", "1");
        response.setHeader("Content-Type", "application/json");

        // 拼接
        byte[] dataByte = (hLen + predictTransSign + dataString + predictTransSign).getBytes(StandardCharsets.UTF_8);
        writeChunkedResponse(response, dataByte);

    }

    private void writeChunkedResponse(HttpServletResponse response, byte[] data) throws IOException {
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(data);
            outputStream.flush();
        } catch (Exception e) {
            logger.error("writeChunkedResponse fail, err", e);
            throw e;
        }
    }

    public void EndWriteChunked(HttpServletResponse response) {
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            if (outputStream != null) {
                outputStream.write(FinishTransSign.getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            logger.error("ChunkSendWriter EndWriteChunked fail, err={}", e.getMessage(), e);
        }
    }

    private String getHexString(byte[] byteArray) throws Exception {
        int length = byteArray.length;
        String lengthStr = String.format("%06X", length);

        if (lengthStr.length() > 6) {
            throw new Exception(String.format("send data error, len[%d] is too large or invalid", lengthStr.length()));
        }

        return lengthStr;
    }

}
