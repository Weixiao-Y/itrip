package cn.weixiao.itrip.transport;

import cn.weixiao.itrip.pojo.vo.OrderLinkUserVo;
import cn.weixiao.itrip.service.OrderLinkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <b>爱旅行-订单联系人传输层接口</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("orderLinkUserTransport")
@RequestMapping("/orderLinkUser/trans")
public class OrderLinkUserTransportImpl implements OrderLinkUserTransport{
	@Autowired
	private OrderLinkUserService orderLinkUserService;
	/**
	 * <b>根据条件查询列表</b>
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/param")
	 public List<OrderLinkUserVo> getOrderLinkUserListByParam( Map<String, Object> param) throws Exception {
	 	return orderLinkUserService.getOrderLinkUserListByParam(param);
	 }
}
