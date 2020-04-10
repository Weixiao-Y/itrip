package cn.weixiao.itrip.pojo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>爱旅行-用户信息实体对象</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@ApiModel(value = "User",description = "用户信息实体对象")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("用户id")
	private Long id;
	@ApiModelProperty("登录名称")
	private String userCode;
	@ApiModelProperty("登录密码")
	private String userPassword;
	@ApiModelProperty("用户类型（标识：0 自注册用户 1 微信登录 2 QQ登录 3 微博登录）")
	private Integer userType;
	@ApiModelProperty("平台ID（根据不同登录用户，进行相应存入：自注册用户主键ID、微信ID、QQID、微博ID）")
	private Long flatID;
	@ApiModelProperty("用户昵称")
	private String userName;
	@ApiModelProperty("微信账号")
	private String weChat;
	@ApiModelProperty("QQ账号")
	private String QQ;
	@ApiModelProperty("微博账号")
	private String weibo;
	@ApiModelProperty("百度账号")
	private String baidu;
	@ApiModelProperty("是否激活,(0 false，1 true,默认是0)")
	private int activated;
	// required = false （可有可无）
	private Date creationDate;
	private Long createdBy;
	private Date modifyDate;
	private Long modifiedBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Long getFlatID() {
		return flatID;
	}

	public void setFlatID(Long flatID) {
		this.flatID = flatID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getWeChat() {
		return weChat;
	}

	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}

	public String getQQ() {
		return QQ;
	}

	public void setQQ(String QQ) {
		this.QQ = QQ;
	}

	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	public String getBaidu() {
		return baidu;
	}

	public void setBaidu(String baidu) {
		this.baidu = baidu;
	}

	public int getActivated() {
		return activated;
	}

	public void setActivated(int activated) {
		this.activated = activated;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
