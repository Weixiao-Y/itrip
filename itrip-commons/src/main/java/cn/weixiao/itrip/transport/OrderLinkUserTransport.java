package cn.weixiao.itrip.transport;

import cn.weixiao.itrip.pojo.vo.OrderLinkUserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * <b>爱旅行-订单联系人传输层接口</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/orderLinkUser/trans")
public interface OrderLinkUserTransport {

	/**
	 * <b>根据条件查询列表</b>
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/param")
	List<OrderLinkUserVo> getOrderLinkUserListByParam(@RequestBody Map<String, Object> param) throws Exception;
}
