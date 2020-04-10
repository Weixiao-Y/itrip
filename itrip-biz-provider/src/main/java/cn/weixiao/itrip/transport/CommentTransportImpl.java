package cn.weixiao.itrip.transport;

import cn.weixiao.itrip.pojo.entity.Comment;
import cn.weixiao.itrip.pojo.entity.ItripImage;
import cn.weixiao.itrip.pojo.vo.ItripListCommentVO;
import cn.weixiao.itrip.pojo.vo.ItripScoreCommentVO;
import cn.weixiao.itrip.pojo.vo.Page;
import cn.weixiao.itrip.pojo.vo.comment.ItripAddCommentVO;
import cn.weixiao.itrip.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <b>爱旅行-酒店评论模块传输层接口实现类</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("commentTransport")
@RequestMapping("/comment/trans")
public class CommentTransportImpl implements CommentTransport{
	@Autowired
	private CommentService commentService;

	/**
	 * <b>根据查询条件查询平均分</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/hotelId")
	public ItripScoreCommentVO gethotelscore(@RequestParam Long hotelId) throws Exception {
		return commentService.gethotelscore(hotelId);
	}

	/**
	 * <b>根据 hotelId 查询评论数量</b>
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/id")
	public Integer getCommentCountByParam(@RequestParam Map<String, Object> param) throws Exception {
		return commentService.getCommentCountByParam(param);
	}

	/**
	 * <b>根据查询条件查询分页对象</b>
	 * @param param
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/param")
	public Page queryCommentPageByMap(@RequestParam  Map<String, Object> param,@RequestParam  Integer pageNo, @RequestParam Integer pageSize) throws Exception {
		return commentService.queryCommentPageByMap(param,  pageNo,  pageSize);
	}

	/**
	 * <b>新增评论</b>
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/add")
	public boolean addComment(@RequestBody Comment comment) throws Exception {
		return commentService.addComment(comment);
	}

}
