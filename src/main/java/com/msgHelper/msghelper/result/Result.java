package com.msgHelper.msghelper.result;

import lombok.Data;

import java.io.Serializable;

/**
 * 后端统一返回结果
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {

    private Integer errno; //编码：0成功，它数字为失败,对应相应的错误编号
    private T data; //数据

    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.errno = 0;
        return result;
    }

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<T>();
        result.data = object;
        result.errno = 0;
        return result;
    }
    public static <T> Result<T> error(Integer errno) {
        Result<T> result = new Result<T>();
        result.errno = errno;
        return result;
    }

}