package com.yozakuraMinato.monoteBe.general.model;

public record ResponseBody<T>(
       T data,
       String error
) {

    public static <T> ResponseBody<T> data(T data) {
        return new ResponseBody<>(data, null);
    }

    public static <T> ResponseBody<T> error(String error) {
        return new ResponseBody<>(null, error);
    }

}
