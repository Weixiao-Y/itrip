package cn.weixiao.itrip.service;

import cn.weixiao.itrip.pojo.entity.Comment;
import cn.weixiao.itrip.pojo.entity.ItripImage;
import cn.weixiao.itrip.pojo.vo.ItripListCommentVO;
import cn.weixiao.itrip.pojo.vo.ItripScoreCommentVO;
import cn.weixiao.itrip.pojo.vo.Page;
import cn.weixiao.itrip.pojo.vo.comment.ItripAddCommentVO;

import java.util.List;
import java.util.Map;

/**
 * <b>爱旅行-酒店评论模块业务层接口</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CommentService {
	/**
	 * <b>根据查询条件查询平均分</b>
	 * @param hotelId
	 * @return
	 */
	ItripScoreCommentVO gethotelscore(Long hotelId) throws Exception;

	/**
	 * <b>根据 hotelId 条件查询评论数量</b>
	 * @param param
	 * @return
	 */
	Integer getCommentCountByParam(Map<String, Object> param) throws Exception;

	/**
	 * <b>根据查询条件查询分页对象</b>
	 * @param param
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	Page queryCommentPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize) throws Exception;

	/**
	 * <b>新增评论</b>
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	boolean addComment(Comment comment) throws Exception;

}
