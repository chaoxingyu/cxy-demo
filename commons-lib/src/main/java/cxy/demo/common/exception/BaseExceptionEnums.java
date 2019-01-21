package cxy.demo.common.exception;


import cxy.demo.common.enums.interfaces.Description;

/**
 * @author cxy
 * @version 1.0.0
 * @ClassName BaseExceptionEnums
 * @description 常见异常信息汇总
 * @date 2017年12月13日 上午10:26:03
 */
@Description("异常信息")
public enum BaseExceptionEnums {

    /*
     * 成功
     */
    success("000000", "成功", "成功"),
    /*
     * 未知错误
     */
    E11000001("E11000001", "系统出现未知错误", "交易失败"),
    /*
     * 未定义错误
     */
    E10000001("E10000001", "未定义错误", "未定义错误"),
    /*
     * 数据不合法
     */
    EV1000001("EV1000001", "上送数据不合法", "上送数据不合法");

    private String msgCode; // 错误码
    private String message; // 错误信息
    private String msgDesc; // 详细描述

    private BaseExceptionEnums(String msgCode, String message, String msgDesc) {
        this.msgCode = msgCode;
        this.message = message;
        this.msgDesc = msgDesc;
    }

    /**
     * 通过msgCode获取枚举类型
     *
     * @param msgCode
     * @return BaseExceptionEnums
     */
    public static BaseExceptionEnums getEnumTypeByMsgCode(String msgCode) {
        for (BaseExceptionEnums c : BaseExceptionEnums.values()) {
            if (c.getMsgCode().equals(msgCode)) {
                return c;
            }
        }
        return null;
    }

    /**
     * 根据 msgCode 查询 message
     *
     * @param msgCode
     * @return String
     */
    public static String getMessageByMsgCode(String msgCode) {
        for (BaseExceptionEnums c : BaseExceptionEnums.values()) {
            if (c.getMsgCode().equals(msgCode)) {
                return c.message;
            }
        }
        return null;
    }

    /**
     * 根据 msgCode 查询 msgDescs
     *
     * @param msgCode
     * @return String
     */
    public static String getMsgDescByMsgCode(String msgCode) {
        for (BaseExceptionEnums c : BaseExceptionEnums.values()) {
            if (c.getMsgCode().equals(msgCode)) {
                return c.msgDesc;
            }
        }
        return null;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public String getMessage() {
        return message;
    }

    public String getMsgDesc() {
        return msgDesc;
    }

}
