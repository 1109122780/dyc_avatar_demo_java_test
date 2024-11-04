package douyin.avatar.avatar_demo_java.caller.model.memory.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import douyin.avatar.avatar_demo_java.request.common.TrafficSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AvatarRequestInfo {

    // 所有 Id 均只能使用 英文字符、数字、下划线、中划线
    @JsonProperty("biz_id")
    private String bizID;

    // 业务流量标识信息
    @JsonProperty("traffic_source")
    private TrafficSource trafficSource;

    // 用户在分身场景下open_id
    @JsonProperty("open_id")
    private String openID;

    // 分身容器app_id
    @JsonProperty("avatar_app_id")
    private String avatarAppID;

    //  原子能力租户id，用于原子能力数据隔离隔离，可用于测试或评测场景，若无特殊诉求使用 default 或留空
    @JsonProperty("tenant_id")
    private String tenantID;

    // 消息发送者的应用类别，主要用于记忆模块策略配置
    @JsonProperty("provider_id")
    private String providerID;

}
