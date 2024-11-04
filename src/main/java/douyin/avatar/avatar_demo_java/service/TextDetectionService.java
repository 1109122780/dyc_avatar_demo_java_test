package douyin.avatar.avatar_demo_java.service;


import douyin.avatar.avatar_demo_java.model.TextDetectionRequest;

public interface TextDetectionService {

    /**
     * 执行文本检测的方法
     * @param request 包含要检测文本的请求对象
     * @return 检测结果（这里可根据实际返回数据类型进一步细化，暂假设为String类型表示结果）
     */
    String performTextDetection(TextDetectionRequest request);
}