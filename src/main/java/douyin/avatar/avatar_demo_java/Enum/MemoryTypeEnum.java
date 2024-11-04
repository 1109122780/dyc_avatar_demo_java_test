package douyin.avatar.avatar_demo_java.Enum;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum MemoryTypeEnum {

    EPISODIC(1, "Episodic"),
    SEMANTIC(2, "Semantic"),
    ;

    private final Integer code;
    private final String msg;

    MemoryTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @JsonValue
    public Integer getCode() {
        return this.code;
    }

}
