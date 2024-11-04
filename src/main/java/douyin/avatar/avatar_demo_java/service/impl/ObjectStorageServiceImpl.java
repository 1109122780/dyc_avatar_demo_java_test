package douyin.avatar.avatar_demo_java.service.impl;


import com.volcengine.tos.TOSV2;
import com.volcengine.tos.TOSV2ClientBuilder;
import com.volcengine.tos.TosClientException;
import com.volcengine.tos.TosServerException;
import com.volcengine.tos.model.object.PutObjectInput;
import com.volcengine.tos.model.object.PutObjectOutput;
import douyin.avatar.avatar_demo_java.service.ObjectStorageService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
public class ObjectStorageServiceImpl implements ObjectStorageService {
    @Override
    public String putObjectDemo(String accessKey, String secretKey) {
        String endpoint = "tos-cn-beijing.volces.com";
        String region = "cn-beijing";

        String bucketName = "tt556323deca5319da01-env-zwpx8giagh";
        String objectKey = "test/example_object.txt";

        TOSV2 tos = new TOSV2ClientBuilder().build(region, endpoint, accessKey, secretKey);
        StringBuilder responseBody = new StringBuilder();

        try {
//            CreateBucketV2Input bucketInput = new CreateBucketV2Input().setBucket(bucketName);
//            CreateBucketV2Output bucketOutput = tos.createBucket(bucketInput);
//            responseBody.append("bucket created: ").append(bucketOutput.getLocation());
            String data = "1234567890abcdefghijklmnopqrstuvwxyz~!@#$%^&*()_+<>?,./   :'1234567890abcdefghijklmnopqrstuvwxyz~!@#$%^&*()_+<>?,./   :'";
            ByteArrayInputStream stream = new ByteArrayInputStream(data.getBytes());
            PutObjectInput putObjectInput = new PutObjectInput().setBucket(bucketName).setKey(objectKey).setContent(stream);
//            PutObjectInput putObjectInput = new PutObjectInput().setKey(objectKey).setContent(stream);
            PutObjectOutput output = tos.putObject(putObjectInput);
            responseBody.append("putObject succeed, object's etag is ").append(output.getEtag()).append("\n")
                    .append("putObject succeed, object's crc64 is ").append(output.getHashCrc64ecma());
        } catch (TosClientException e) {
            // 操作失败，捕获客户端异常，一般情况是请求参数错误，此时请求并未发送
            responseBody.append("putObject failed\nMessage: ").append(e.getMessage());
            if (e.getCause()!= null) {
                responseBody.append("\nCause: ").append(e.getCause().getMessage());
            }
        } catch (TosServerException e) {
            // 操作失败，捕获服务端异常，可以获取到从服务端返回的详细错误信息
            responseBody.append("putObject failed\nStatusCode: ").append(e.getStatusCode()).append("\nCode: ")
                    .append(e.getCode()).append("\nMessage: ").append(e.getMessage()).append("\nRequestID: ")
                    .append(e.getRequestID());
        } catch (Throwable t) {
            // 作为兜底捕获其他异常，一般不会执行到这里
            responseBody.append("putObject failed\nunexpected exception, message: ").append(t.getMessage());
        }
        return responseBody.toString();
    }
}
