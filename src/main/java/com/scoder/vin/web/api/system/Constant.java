package com.scoder.vin.web.api.system;

/**
 * @author H
 */

public enum Constant {

    /**
     *
     */
    OAUTH_GITHUB_RECEIVE_CODE_FAILED("40000", "code can not be null.", "一般在接收 code 值为 null 的时候使用"),
    OAUTH_GITHUB_GET_ACCESS_TOKEN_FAILED("40001", "please check the code.", "使用 code 获取 accessToken 时失败"),
    AUTH_TOKEN_EXPIRED("41000", "token has expired."),
    AUTH_TOKEN_EXCEPTION("41001", "token is abnormal."),
    Auth_TOKEN_IS_NOT_EXISTS("41002", "token is not exists, please check request."),
    AUTH_DOES_NOTE_HAVE_PERMISSION("41003", "does not have permission."),
    VIN_USER_ID_CAN_NOT_BE_NULL("42000", "user id can not be null.");

    Constant(String code, String message) {
        this.code = code;
        this.message = message;
    }

    Constant(String code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    private String code;
    private String message;
    private String description;

    public String code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public String description() {
        return this.description;
    }

}
