package com.yozakuraMinato.monoteBe.common.payload;

public record ApplicationResponse<T>(

       T data,
       String error

) {

    public static <T> ApplicationResponse<T> data(T data) {
        return new ApplicationResponse<>(data, null);
    }

    public static <T> ApplicationResponse<T> error(String error) {
        return new ApplicationResponse<>(null, error);
    }

}
