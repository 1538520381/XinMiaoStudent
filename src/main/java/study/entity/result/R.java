package study.entity.result;

import lombok.Data;
import study.enums.HttpCodeEnum;

import java.io.Serializable;

/**
 * @author Persolute
 * @version 1.0
 * @description 统一返回结果封装
 * @email 1538520381@qq.com
 * @date 2024/1/28 19:32
 */
@Data
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    // 状态码
    private Integer code;

    // 信息
    private String msg;

    // 数据
    private T data;

    private R(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> R<T> success() {
        return success(HttpCodeEnum.SUCCESS.getMsg(), null);
    }

    public static <T> R<T> success(String msg) {
        return success(msg, null);
    }

    public static <T> R<T> success(T data) {
        return success(HttpCodeEnum.SUCCESS.getMsg(), data);
    }

    public static <T> R<T> success(String msg, T data) {
        return new R<>(HttpCodeEnum.SUCCESS.getCode(), msg, data);
    }

    public static <T> R<T> error() {
        return error(HttpCodeEnum.ERROR, HttpCodeEnum.ERROR.getMsg(), null);
    }

    public static <T> R<T> error(HttpCodeEnum httpCodeEnum) {
        return error(httpCodeEnum, httpCodeEnum.getMsg(), null);
    }

    public static <T> R<T> error(String msg) {
        return error(HttpCodeEnum.ERROR, msg, null);
    }

    public static <T> R<T> error(T data) {
        return error(HttpCodeEnum.ERROR, HttpCodeEnum.ERROR.getMsg(), data);
    }

    public static <T> R<T> error(HttpCodeEnum httpCodeEnum, String msg) {
        return error(httpCodeEnum, msg, null);
    }

    public static <T> R<T> error(HttpCodeEnum httpCodeEnum, T data) {
        return error(httpCodeEnum, httpCodeEnum.getMsg(), data);
    }

    public static <T> R<T> error(String msg, T data) {
        return error(HttpCodeEnum.ERROR, msg, data);
    }

    public static <T> R<T> error(HttpCodeEnum httpCodeEnum, String msg, T data) {
        return new R<>(httpCodeEnum.getCode(), msg, data);
    }
}