package cn.weixiao.itrip.pojo.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>查询个人订单搜索条件</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
public class ItripSearchOrderVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String orderNo;
	private String linkUserName;
	private Date startDate;
	private Date endDate;
	private Integer pageSize;
	private Integer pageNo;
	private Integer orderStatus;
	private Integer orderType;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getLinkUserName() {
		return linkUserName;
	}

	public void setLinkUserName(String linkUserName) {
		this.linkUserName = linkUserName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
}
