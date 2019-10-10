package com.scoder.vin.web.api.common.message;

import java.io.Serializable;

public interface Response<T> extends Serializable {

    String getCode();

    String getMessage();

    T getData();

    /**
     * <p>默认方式</p>
     * {
     * "code": "20000",
     * "message": "success"
     * }
     *
     * @return Response
     */
    static Response<Void> success() {
        return HulkResponse.success();
    }

    /**
     * <p>默认的成功方式，外加一个 data </p>
     * {
     * "code": "20000",
     * "message": "success",
     * "data": data
     * }
     *
     * @param data data
     * @return Response
     */
    static <T> Response<T> success(T data) {
        return HulkResponse.success(data);
    }

    /**
     * <p>自定义，只返回 code message</p>
     * {
     * "code": code,
     * "message": message
     * }
     *
     * @param code    code
     * @param message message
     * @return Response
     */
    static <T> Response<T> success(String code, String message) {
        return HulkResponse.success(code, message);
    }

    /**
     * <p>自定义，只返回 code，data</p>
     * {
     * "code": code,
     * "message": data
     * }
     *
     * @param code code
     * @param data data
     * @return Response
     */
    static <T> Response<T> success(String code, T data) {
        return HulkResponse.success(code, data);
    }

    /**
     * <p>自定义，code，message，data 全自定义</p>
     * {
     * "code": code,
     * "message": message,
     * "data": data
     * }
     *
     * @param code    code
     * @param message message
     * @param data    data
     * @return Response
     */
    static <T> Response<T> success(String code, String message, T data) {
        return HulkResponse.success(code, message, data);
    }

}
