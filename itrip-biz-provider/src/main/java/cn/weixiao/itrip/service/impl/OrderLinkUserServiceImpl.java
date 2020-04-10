package cn.weixiao.itrip.service.impl;

import cn.weixiao.itrip.dao.OrderLinkUserDao;
import cn.weixiao.itrip.pojo.vo.OrderLinkUserVo;
import cn.weixiao.itrip.service.OrderLinkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <b>爱旅行-订单联系人业务层接口实现类</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("orderLinkUserService")
@Transactional
public class OrderLinkUserServiceImpl implements OrderLinkUserService {
	@Autowired
	private OrderLinkUserDao orderLinkUserDao;
	/**
	 * <b>根据查询条件查询列表</b>
	 * @param param
	 * @return
	 */
	public List<OrderLinkUserVo> getOrderLinkUserListByParam(Map<String, Object> param) throws Exception {
		List<OrderLinkUserVo> resultList = orderLinkUserDao.findOrderLinkUserByMap(param);
		if (resultList != null && resultList.size() > 0) {
			return resultList;
		}
		return new ArrayList<OrderLinkUserVo>();
	}
}
