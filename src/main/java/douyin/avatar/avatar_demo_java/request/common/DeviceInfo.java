package douyin.avatar.avatar_demo_java.request.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeviceInfo {
    @JsonProperty("app_id")
    private Integer appID;

    @JsonProperty("app_version")
    private Long appVersion;

    @JsonProperty("device_id")
    private Long deviceID;

    @JsonProperty("device_platform")
    private String devicePlatform;
}
