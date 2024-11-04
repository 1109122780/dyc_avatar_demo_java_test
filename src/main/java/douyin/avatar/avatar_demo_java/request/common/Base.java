package douyin.avatar.avatar_demo_java.request.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import douyin.avatar.avatar_demo_java.request.TrafficEnv;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Base {

    @JsonProperty("LogID")
    private String logID;

    @JsonProperty("Caller")
    private String caller;

    @JsonProperty("Addr")
    private String addr;

    @JsonProperty("Client")
    private String client;

    @JsonProperty("TrafficEnv")
    private TrafficEnv trafficEnv;

    @JsonProperty("Extra")
    private Map<String, String> extra;
}
