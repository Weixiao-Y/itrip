package cn.weixiao.itrip.service.impl;

import cn.weixiao.itrip.base.enums.OrderStatusEnum;
import cn.weixiao.itrip.dao.CommentDao;
import cn.weixiao.itrip.dao.HotelOrderDao;
import cn.weixiao.itrip.dao.ItripImageDao;
import cn.weixiao.itrip.pojo.entity.Comment;
import cn.weixiao.itrip.pojo.entity.Hotel;
import cn.weixiao.itrip.pojo.entity.HotelOrder;
import cn.weixiao.itrip.pojo.entity.ItripImage;
import cn.weixiao.itrip.pojo.vo.ItripListCommentVO;
import cn.weixiao.itrip.pojo.vo.ItripScoreCommentVO;
import cn.weixiao.itrip.pojo.vo.Page;
import cn.weixiao.itrip.pojo.vo.comment.ItripAddCommentVO;
import cn.weixiao.itrip.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <b>爱旅行-酒店评论模块业务层接口实现类</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private ItripImageDao itripImageDao;
	@Autowired
	private HotelOrderDao hotelOrderDao;
	/**
	 * <b>根据查询条件查询平均分</b>
	 * @param hotelId
	 * @return
	 */
	public ItripScoreCommentVO gethotelscore(Long hotelId) throws Exception {
		Comment query = new Comment();
		query.setHotelId(hotelId);
		List<Comment> commentList = commentDao.findListByQuery(query);
		ItripScoreCommentVO itripScoreCommentVO = new ItripScoreCommentVO();
		float score = 0;
		float positionScore = 0;
		float facilitiesScore = 0;
		float serviceScore = 0;
		float hygieneScore = 0;
		if (commentList != null && commentList.size() > 0) {
			for (Comment comment:commentList) {
				score = score + comment.getScore();
				positionScore = positionScore + comment.getPositionScore();
				facilitiesScore = facilitiesScore + comment.getFacilitiesScore();
				serviceScore = serviceScore + comment.getServiceScore();
				hygieneScore = serviceScore + comment.getServiceScore();
			}
			DecimalFormat format = new DecimalFormat("#.0f");
			itripScoreCommentVO.setAvgScore(Float.valueOf(format.format(score / commentList.size())));
			itripScoreCommentVO.setAvgPositionScore(Float.valueOf(format.format(positionScore / commentList.size())));
			itripScoreCommentVO.setAvgFacilitiesScore(Float.valueOf(format.format(facilitiesScore / commentList.size())));
			itripScoreCommentVO.setAvgServiceScore(Float.valueOf(format.format(serviceScore / commentList.size())));
			itripScoreCommentVO.setAvgHygieneScore(Float.valueOf(format.format(hygieneScore / commentList.size())));
			return itripScoreCommentVO;
		}
		return new ItripScoreCommentVO();
	}

	/**
	 * <b>根据 hotelId 条件查询评论数量</b>
	 * @param param
	 * @return
	 */
	public Integer getCommentCountByParam(Map<String, Object> param) throws Exception {
		Integer count = commentDao.getCommentCountByMap(param);
		return count;
	}

	/**
	 * <b>根据查询条件查询分页对象</b>
	 * @param param
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page<ItripListCommentVO> queryCommentPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize) throws Exception {
		Integer total = commentDao.getCommentCountByMap(param);
		Page page = new Page(pageNo, pageSize, total);
	    param.put("beginPos", page.getBeginPos());
		param.put("pageSize", page.getPageSize());
		List<ItripListCommentVO> itripCommentList = commentDao.getItripCommentListByMap(param);
		page.setRows(itripCommentList);
		return page;
	}

	/**
	 * <b>新增评论</b>
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	public boolean addComment(Comment comment) throws Exception {
		if (comment != null) {
			// 计算综合评分，综合评分=(设施+卫生+位置+服务)/4
			float score = 0;
			score = (comment.getFacilitiesScore()+comment.getHygieneScore()
					+comment.getPositionScore()+comment.getServiceScore()) / 4;
			comment.setScore(Math.round(score));
			Long commentId = 0L;
			Comment resultComment = new Comment();
			List<ItripImage> itripImages = comment.getImageList();
			if (commentDao.insertComment(comment) > 0) {
				List<Comment> commentList = commentDao.findListByQuery(comment);
				if (commentList != null && commentList.size() > 0) {
					resultComment = commentList.get(0);
					commentId = resultComment.getId();
				}
				if (itripImages != null && itripImages.size() > 0 && commentId > 0) {
					for (ItripImage itripImage: itripImages) {
						System.out.println(itripImage);
						System.out.println(itripImage.getImgUrl());
						System.out.println(itripImage.getPosition());
						System.out.println(itripImage.getCreatedBy());
						System.out.println(itripImage.getCreationDate());
						itripImage.setTargetId(commentId);
						// 保存图片
					int count = itripImageDao.insertImage(itripImage);
						System.out.println(count);
					}
				}
				// 更新订单状态为4（已评论）
				HotelOrder query = new HotelOrder();
				query.setId(resultComment.getOrderId());
				query.setCreatedBy(resultComment.getCreatedBy());
				query.setOrderStatus(OrderStatusEnum.ORDER_STATUS_COMMENT.getCode());
				hotelOrderDao.updateHotelOrder(query);
				return true;
			}
		}
		return false;
	}

}
