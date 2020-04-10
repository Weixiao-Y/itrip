package cn.weixiao.itrip.controller;

import cn.weixiao.itrip.base.controller.BaseController;
import cn.weixiao.itrip.base.pojo.vo.ResponseDto;
import cn.weixiao.itrip.pojo.entity.UserLinkUser;
import cn.weixiao.itrip.transport.UserLinkUserTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;

/**
 * <b>爱旅行-常用联系人控制层</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("userLinkUserController")
@RequestMapping("/biz/api/userinfo")
public class UserLinkUserController extends BaseController {
	@Autowired
	private UserLinkUserTransport userLinkUserTransport;

	/**
	 * <b>根据当前登录用户，获得联系人</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/queryuserlinkuser")
	public ResponseDto<Object> queryUserLinkUser() throws Exception {
		// 通过 Cookie 获得当前登录对象
		String userCode = "";
		Cookie[] cookies = request.getCookies();
		for (Cookie  cookie : cookies) {
			if ("user".equals(cookie.getName())) {
				userCode = cookie.getValue();
			}
		}

		// 封装查询对象
		UserLinkUser query = new UserLinkUser();
		query.setUserCode(userCode);
		return ResponseDto.success(userLinkUserTransport.queryUserLinkUserListByQuery(query));
	}

}
