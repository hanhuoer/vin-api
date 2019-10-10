package com.scoder.vin.web.api.common.message;

import com.scoder.vin.web.api.common.code.SuccessCode;

import java.io.Serializable;

/**
 * @author H
 */
public class HulkResponse<T> implements Response<T>, Serializable {

    private static final long serialVersionUID = -5508341719984417455L;

    private String code;
    private String message;
    private T data;

    public HulkResponse() {
    }

    private HulkResponse(String code, T data) {
        this.code = code;
        this.data = data;
    }

    private HulkResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private HulkResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public T getData() {
        return this.data;
    }

    static HulkResponse<Void> success() {
        return new HulkResponse<>(SuccessCode.SUCCESS.code(), SuccessCode.SUCCESS.message());
    }

    static <T> HulkResponse<T> success(T data) {
        return new HulkResponse<>(SuccessCode.SUCCESS.code(), SuccessCode.SUCCESS.message(), data);
    }

    static <T> HulkResponse<T> success(String code, String message) {
        return new HulkResponse<>(code, message);
    }

    static <T> HulkResponse<T> success(String code, T data) {
        return new HulkResponse<>(code, data);
    }

    static <T> HulkResponse<T> success(String code, String message, T data) {
        return new HulkResponse<>(code, message, data);
    }

}
