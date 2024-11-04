package douyin.avatar.avatar_demo_java.Enum;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum ContentTypeEnum {

    TEXT(0, "ContentType_TEXT"),
    FORMAT(1, "ContentType_FORMAT"),
    CARD(2, "ContentType_CARD"),
    ;

    private final Integer code;
    private final String msg;

    ContentTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @JsonValue
    public Integer getCode() {
        return this.code;
    }

}
