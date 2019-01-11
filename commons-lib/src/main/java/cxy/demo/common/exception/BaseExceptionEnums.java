package cxy.demo.common.exception;


import cxy.demo.common.enums.interfaces.Description;

/**
 *
 * @ClassName BaseExceptionEnums
 * @description 常见异常信息汇总
 * @author cxy
 * @date 2017年12月13日 上午10:26:03
 * @version 1.0.0
 */
@Description("异常信息")
public enum BaseExceptionEnums {

	success("000000", "成功", "成功"),
	// 错误编码分类
	E11000001("E11000001", "系统出现未知错误", "交易失败"),
	E10000001("E10000001", "未定义错误", "未定义错误"),
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
			if (c.getMsgCode() == msgCode) {
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
			if (c.getMsgCode() == msgCode) {
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
			if (c.getMsgCode() == msgCode) {
				return c.msgDesc;
			}
		}
		return null;
	}

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMsgDesc() {
		return msgDesc;
	}

	public void setMsgDesc(String msgDesc) {
		this.msgDesc = msgDesc;
	}

}
