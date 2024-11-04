package douyin.avatar.avatar_demo_java.Enum;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum RoleEnum {

    DEFAULT(0, "_"),
    SYSTEM(1, "Role_System"),
    USER(2, "Role_User"),
    Assistant(3, "Role_Assistant"),
    ;

    private final Integer code;
    private final String msg;

    RoleEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @JsonValue
    public Integer getCode() {
        return this.code;
    }

}
