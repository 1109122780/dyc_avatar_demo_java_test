package douyin.avatar.avatar_demo_java.Enum;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum MessageContentTypeEnum {

    TEXT(0, "MessageContentType_TEXT"),
    FORMAT(1, "MessageContentType_FORMAT"),
    CARD(2, "MessageContentType_CARD"),
    ;

    private final Integer code;
    private final String msg;

    MessageContentTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @JsonValue
    public Integer getCode() {
        return this.code;
    }
}
