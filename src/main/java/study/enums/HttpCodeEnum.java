package study.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @author Persolute
 * @version 1.0
 * @description 状态码
 * @email 1538520381@qq.com
 * @date 2024/1/28 19:41
 */
@Getter
public enum HttpCodeEnum {
    SUCCESS(200, "操作成功"),
    ERROR(500, "服务器异常"),
    FIELD_MISSING(501, "字段缺失"),
    ACCOUNT_MISSING(502, "请输入账号"),
    PASSWORD_MISSING(503, "请输入密码"),
    ACCOUNT_UNEXIST(504, "账号不存在"),
    PASSWORD_ERROR(505, "密码错误");

    private final int code;
    private final String msg;

    HttpCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
