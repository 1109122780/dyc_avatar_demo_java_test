package douyin.avatar.avatar_demo_java.Enum;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum SegTypeEnum {
    ANSWER(0, "SegType_Answer"),
    FOLLOWUP(1, "SegType_FollowUp"), // 推荐词
    ;

    private final Integer code;
    private final String msg;

    SegTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @JsonValue
    public Integer getCode() {
        return this.code;
    }

}
