package douyin.avatar.avatar_demo_java.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import douyin.avatar.avatar_demo_java.request.common.AvatarInfo;
import douyin.avatar.avatar_demo_java.request.common.DeviceInfo;
import douyin.avatar.avatar_demo_java.request.common.TrafficSource;
import douyin.avatar.avatar_demo_java.request.common.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonContext {

    @JsonProperty("user_info")
    private UserInfo userInfo;

    @JsonProperty("device_info")
    private DeviceInfo deviceInfo;

    @JsonProperty("avatar_info")
    private AvatarInfo avatarInfo;

    @JsonProperty("traffic_source")
    private TrafficSource trafficSource;

    @JsonProperty("ab_param")
    private String abParam;

}
