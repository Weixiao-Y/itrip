package cn.weixiao.itrip.pojo.vo.comment;

import cn.weixiao.itrip.pojo.entity.ItripImage;

import java.io.Serializable;
import java.util.List;

/**
 * <b>新增评论VO</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
public class ItripAddCommentVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long hotelId;               // 酒店ID
	private Long orderId;               // 订单ID
	private Long productId;             // 房型ID
	private Integer productType;        // 订单类型(0:旅游产品 1:酒店产品 2:机票产品)
	private Integer isHavingImg;        // 是否包含图片(当用户上传评论时检测)0:无图片 1:有图片
	private Integer positionScore;      // 位置评分
	private Integer facilitiesScore;    // 设施评分
	private Integer serviceScore;       // 服务评分
	private Integer hygieneScore;       // 卫生评分
	private String tripMode;            // 接收数字类型] 出游类型（数据取自下拉列表）
	private Integer isOk;               // 是否满意（0：有待改善 1：值得推荐）
	private String content;             // 评论内容
	private ItripImage[] itripImages;   // 评论图片，根据用户是否上传图片（图片网络路径数组）

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public Integer getIsHavingImg() {
		return isHavingImg;
	}

	public void setIsHavingImg(Integer isHavingImg) {
		this.isHavingImg = isHavingImg;
	}

	public Integer getPositionScore() {
		return positionScore;
	}

	public void setPositionScore(Integer positionScore) {
		this.positionScore = positionScore;
	}

	public Integer getFacilitiesScore() {
		return facilitiesScore;
	}

	public void setFacilitiesScore(Integer facilitiesScore) {
		this.facilitiesScore = facilitiesScore;
	}

	public Integer getServiceScore() {
		return serviceScore;
	}

	public void setServiceScore(Integer serviceScore) {
		this.serviceScore = serviceScore;
	}

	public Integer getHygieneScore() {
		return hygieneScore;
	}

	public void setHygieneScore(Integer hygieneScore) {
		this.hygieneScore = hygieneScore;
	}

	public String getTripMode() {
		return tripMode;
	}

	public void setTripMode(String tripMode) {
		this.tripMode = tripMode;
	}

	public Integer getIsOk() {
		return isOk;
	}

	public void setIsOk(Integer isOk) {
		this.isOk = isOk;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ItripImage[] getItripImages() {
		return itripImages;
	}

	public void setItripImages(ItripImage[] itripImages) {
		this.itripImages = itripImages;
	}
}
