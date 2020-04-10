package cn.weixiao.itrip.dao;

import cn.weixiao.itrip.pojo.entity.AreaDic;
import cn.weixiao.itrip.pojo.entity.Comment;
import cn.weixiao.itrip.pojo.vo.ItripListCommentVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <b>爱旅行-酒店评论模块数据持久层接口</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface CommentDao {

	/**
	 * <b>按照查询条件查询信息列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<Comment> findListByQuery(Comment query) throws Exception;

	/**
	 * <b>根据查询条件查询评论数</b>
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Integer getCommentCountByMap(Map<String, Object> param) throws Exception;

	List<ItripListCommentVO> getItripCommentListByMap(Map<String, Object> param) throws Exception;

	/**
	 * <b>新增评论</b>
	 * @param comment
	 * @return
	 */
	Integer insertComment(Comment comment) throws Exception;
}
