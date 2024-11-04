package douyin.avatar.avatar_demo_java.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrafficEnv {

    @JsonProperty("Open")
    private boolean open;

    @JsonProperty("Env")
    private String env;
}
