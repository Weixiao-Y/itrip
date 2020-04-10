package cn.weixiao.itrip.transport;

import cn.weixiao.itrip.pojo.entity.UserLinkUser;
import cn.weixiao.itrip.pojo.vo.ItripAddUserLinkUserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <b>爱旅行-常用联系人传输层接口</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/userInfo/trans")
public interface UserInfoTransport {
	/**
	 * <b>新增常用联系人</b>
	 * @param userLinkUser
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/addUser")
	int addUserLinkuser(@RequestBody UserLinkUser userLinkUser) throws Exception;

	/**
	 * <b>查询订单中已有的联系人</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/select")
	List<Long> getItripOrderLinkUserIdsByOrder() throws Exception;

	/**
	 * <b>删除联系人</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/delete")
	Integer deleteItripUserLinkUserByIds(@RequestParam Long id) throws Exception;
}
