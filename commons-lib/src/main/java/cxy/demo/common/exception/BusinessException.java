package cxy.demo.common.exception;

/**
 *
 * @ClassName BusinessException
 * @description 业务异常
 * @author cxy
 * @date 2017年12月13日 上午10:25:23
 * @version 1.0.0
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -1577838229822404049L;

	private String errorCode;
	private String errorArgs;
	// private String[] errorArgs; //配合配置文件使用

	/**
	 * 错误码需要在BaseExceptionEnums中注册
	 * 
	 * @param errorCode
	 * @param errorArgs
	 */
	public BusinessException(String errorCode, String errorArgs) {
		super();
		if (null == errorArgs || "".equals(errorArgs)) {
			this.errorArgs = errorCode;
		}
		BaseExceptionEnums baseExceptionEnums = BaseExceptionEnums.getEnumTypeByMsgCode(errorCode);
		if (null == baseExceptionEnums) {
			String str = BaseExceptionEnums.E10000001.getMessage() + "[" + errorCode + ", " + errorArgs + "]";
			this.errorCode = BaseExceptionEnums.E10000001.getMsgCode();
			this.errorArgs = str;
		} else {
			this.errorCode = errorCode;
			this.errorArgs = errorArgs;
		}
	}

	/**
	 * 已注册的业务错误码
	 * 
	 * @param baseExceptionEnums
	 */
	public BusinessException(BaseExceptionEnums baseExceptionEnums) {
		super();
		this.errorCode = baseExceptionEnums.getMsgCode();
		this.errorArgs = baseExceptionEnums.getMessage();
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorArgs() {
		return errorArgs;
	}

	public void setErrorArgs(String errorArgs) {
		this.errorArgs = errorArgs;
	}

	@Override
	public String toString() {
		return "业务异常 [错误码=" + errorCode + ", 错误信息=" + errorArgs + "]";
	}

}
