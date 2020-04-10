package cn.weixiao.itrip.pojo.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>订单列表页</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
public class ItripListHotelOrderVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;                //订单id
	private Long hotelId;           //酒店id
	private String hotelName;	    //酒店的名称
	private String orderNo;         //订单号
	private Integer orderType;      //订单类型
	private String linkUserName;	//旅客的姓名，多个旅客的姓名之间用逗号隔开
	private Date creationDate;      //预定时间
	private Date checkInDate;       //入住时间（行程/有效日期）
	private Double payAmount;   //订单金额
	private Integer orderStatus;    //订单状态（0：待支付 1:已取消 2:支付成功 3:已消费）

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getLinkUserName() {
		return linkUserName;
	}

	public void setLinkUserName(String linkUserName) {
		this.linkUserName = linkUserName;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Double getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
}
