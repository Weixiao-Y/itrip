package cn.weixiao.itrip.transport;

import cn.weixiao.itrip.pojo.entity.UserLinkUser;
import cn.weixiao.itrip.pojo.vo.ModifyUserLinkUserVO;
import cn.weixiao.itrip.service.UserLinkUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController("userLinkUserTransport")
@RequestMapping("/linkuser/trans")
public class UserLinkUserTransportImpl implements UserLinkUserTransport{
	@Autowired
	private UserLinkUserService userLinkUserService;

	/**
	 * <b>根据查询信息获得查询列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/list")
	public List<UserLinkUser> queryUserLinkUserListByQuery(@RequestBody UserLinkUser query) throws Exception {
		return userLinkUserService.getUserLinkUserListByQuery(query);
	}

	/**
	 * <b>修改联系人信息</b>
	 * @param linkUser
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/update")
	public Integer updateUserLinkUser(@RequestBody UserLinkUser linkUser) throws Exception {
		return userLinkUserService.updateUserLinkUser(linkUser);
	}

}
