package cn.weixiao.itrip.transport;

import cn.weixiao.itrip.pojo.entity.UserLinkUser;
import cn.weixiao.itrip.pojo.vo.ItripAddUserLinkUserVO;
import cn.weixiao.itrip.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <b>爱旅行-常用联系人传输层接口实现类</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("userInfoTransport")
@RequestMapping("/userInfo/trans")
public class UserInfoTransportImpl implements UserInfoTransport{
	@Autowired
	private UserInfoService userInfoService;
	/**
	 * <b>新增常用联系人</b>
	 * @param userLinkUser
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/addUser")
	public  int addUserLinkuser(@RequestBody UserLinkUser userLinkUser) throws Exception {
		return userInfoService.addUserLinkuser(userLinkUser);
	}

	/**
	 * <b>查询订单中已有的联系人</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/select")
	public List<Long> getItripOrderLinkUserIdsByOrder() throws Exception {
		return userInfoService.getItripOrderLinkUserIdsByOrder();
	}

	/**
	 * <b>删除联系人</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/delete")
	public Integer deleteItripUserLinkUserByIds(@RequestBody Long id) throws Exception {
		return userInfoService.deleteItripUserLinkUserByIds(id);
	}
}
