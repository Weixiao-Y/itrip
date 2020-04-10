package cn.weixiao.itrip.service;

import cn.weixiao.itrip.pojo.vo.OrderLinkUserVo;

import java.util.List;
import java.util.Map;

/**
 * <b>爱旅行-订单联系人业务层接口</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
public interface OrderLinkUserService {
	/**
	 * <b>根据查询条件查询列表</b>
	 * @param param
	 * @return
	 */
	List<OrderLinkUserVo> getOrderLinkUserListByParam(Map<String, Object> param) throws Exception;
}
