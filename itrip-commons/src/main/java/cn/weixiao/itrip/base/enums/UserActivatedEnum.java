package cn.weixiao.itrip.base.enums;

/**
 * <b>爱旅行-用户激活状态枚举对象</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
public enum UserActivatedEnum {
	USER_ACTIVATED_NO(0),
	USER_ACTIVATED_YES(1)
	;
	private int code;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	private UserActivatedEnum(int code) {
		this.code = code;
	}
}
