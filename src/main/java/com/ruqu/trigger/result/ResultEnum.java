package com.ruqu.trigger.result;

/**
 * @Author Reny
 * @Date 2018-09-09 10:07
 */
public enum ResultEnum {

    /** 操作成功 */
    SUCCESS(200,"操作成功"),
    /** 错误请求 */
    BAD_REQUEST(400, "操作失败"),
    /** 服务器拒绝请求 */
    FORBIDDEN(403, "权限不足"),
    /** 未找到给定资源 */
    NOT_FOUND(404, "没有找到相关资源"),
    /** 不支持当前方法 */
    METHOD_NOT_ALLOWED(405, "不支持当前请求方法"),
    /** 请求重复提交 */
    NOT_ACCEPTABLE(406, "请求重复提交"),
    /** 请求参数错误 */
    PARAM_ERROR(407, "请求参数错误"),
    /** 内部错误 */
    INTERNAL_SERVER_ERROR(500, "服务器运行异常"),
    /** 服务器不支持请求的工具 */
    NOT_IMPLEMENTED(501,"服务器不支持的请求"),
    /** 服务不可用 */
    SERVICE_UNAVAILABLE(503,"服务不可用");

    private final Integer code;
    private final String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
