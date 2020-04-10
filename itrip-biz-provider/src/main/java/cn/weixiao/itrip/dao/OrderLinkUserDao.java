package cn.weixiao.itrip.dao;

import cn.weixiao.itrip.pojo.vo.OrderLinkUserVo;
import cn.weixiao.itrip.service.OrderLinkUserService;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <b>爱旅行-订单联系人数据持久层接口</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface OrderLinkUserDao {

	/**
	 * <b>根据查询条件查询列表</b>
	 * @param param
	 * @return
	 */
	List<OrderLinkUserVo> findOrderLinkUserByMap(Map<String, Object> param);
}
