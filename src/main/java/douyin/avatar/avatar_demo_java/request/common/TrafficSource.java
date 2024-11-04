package douyin.avatar.avatar_demo_java.request.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrafficSource {
    @JsonProperty("source")
    private String source;

    @JsonProperty("enter_from")
    private String enterFrom;

    @JsonProperty("traffic_type")
    private String trafficType;

    @JsonProperty("extra")
    private Map<String, String> extra;
}
