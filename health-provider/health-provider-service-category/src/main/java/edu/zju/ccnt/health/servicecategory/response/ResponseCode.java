package edu.zju.ccnt.health.servicecategory.response;


public enum ResponseCode {     //调用本网站api接口的返回码

    SUCCESS(10000, "SUCCESS"),
    ERROR(20000, "ERROR"),
    EXPIRED_TOKEN(50014, "EXPIRED_TOKEN"),

    NEED_LONGIN(50007, "NEED_LOGIN"),
    ILLEGLE_ARGUMENT(2, "ILLEGAL_ARGUMENT");

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
