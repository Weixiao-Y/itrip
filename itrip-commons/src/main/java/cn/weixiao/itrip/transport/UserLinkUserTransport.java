package cn.weixiao.itrip.transport;

import cn.weixiao.itrip.pojo.entity.UserLinkUser;
import cn.weixiao.itrip.pojo.vo.ModifyUserLinkUserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <b>爱旅行-常用联系人传输层接口</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/linkuser/trans")
public interface UserLinkUserTransport {
	/**
	 * <b>根据查询信息获得查询列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/list")
	List<UserLinkUser> queryUserLinkUserListByQuery(@RequestBody UserLinkUser query) throws Exception;

	/**
	 * <b>修改联系人信息</b>
	 * @param linkUser
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/update")
	Integer updateUserLinkUser(@RequestBody UserLinkUser linkUser) throws Exception;
}
