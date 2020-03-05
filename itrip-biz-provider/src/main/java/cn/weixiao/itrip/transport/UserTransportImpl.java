package cn.weixiao.itrip.transport;

import cn.weixiao.itrip.pojo.entity.User;
import cn.weixiao.itrip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userTransport")
@RequestMapping("/user/trans")
public class UserTransportImpl implements UserTransport {
	@Autowired
	private UserService userService;
	/**
	 * <b>根据查询信息查询用户信息列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/list")
	public List<User> getListByQuery(User query) throws Exception {
		return userService.getListByQuery(query);
	}
}