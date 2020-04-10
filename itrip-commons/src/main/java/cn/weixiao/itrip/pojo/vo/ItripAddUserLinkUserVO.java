package cn.weixiao.itrip.pojo.vo;

import java.io.Serializable;

public class ItripAddUserLinkUserVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String linkUserName;        // 常用刚联系人姓名
	private Integer linkIdCardType;     // 证件类型
	private String linkIdCard;          // 证件号码
	private String linkPhone;           // 联系电话
	private Long userId;                // 用户ID

	public String getLinkUserName() {
		return linkUserName;
	}

	public void setLinkUserName(String linkUserName) {
		this.linkUserName = linkUserName;
	}

	public Integer getLinkIdCardType() {
		return linkIdCardType;
	}

	public void setLinkIdCardType(Integer linkIdCardType) {
		this.linkIdCardType = linkIdCardType;
	}

	public String getLinkIdCard() {
		return linkIdCard;
	}

	public void setLinkIdCard(String linkIdCard) {
		this.linkIdCard = linkIdCard;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
