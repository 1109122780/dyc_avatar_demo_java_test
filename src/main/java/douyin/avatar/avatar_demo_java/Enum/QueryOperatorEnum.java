package douyin.avatar.avatar_demo_java.Enum;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum QueryOperatorEnum {

    IN(1, "IN"),
    NOT_IN(2, "NOT_IN"),
    ;

    private final Integer code;
    private final String msg;

    QueryOperatorEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @JsonValue
    public Integer getCode() {
        return this.code;
    }

}
