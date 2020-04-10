package cn.weixiao.itrip.controller;

import cn.weixiao.itrip.base.controller.BaseController;
import cn.weixiao.itrip.base.pojo.vo.ResponseDto;
import cn.weixiao.itrip.pojo.entity.User;
import cn.weixiao.itrip.pojo.entity.UserLinkUser;
import cn.weixiao.itrip.pojo.vo.ItripAddUserLinkUserVO;
import cn.weixiao.itrip.pojo.vo.ModifyUserLinkUserVO;
import cn.weixiao.itrip.transport.UserInfoTransport;
import cn.weixiao.itrip.transport.UserLinkUserTransport;
import cn.weixiao.itrip.transport.UserTransport;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <b>爱旅行-常用联系人控制器</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("userInfoController")
@RequestMapping(value = "/biz/api/userinfo")
public class UserInfoController extends BaseController {
	@Autowired
	private UserInfoTransport userInfoTransport;
	@Autowired
	private UserTransport userTransport;
	@Autowired
	private UserLinkUserTransport userLinkUserTransport;

	/**
	 * <b>新增常用联系人接口</b>
	 * @param itripAddUserLinkUserVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/adduserlinkuser")
	public ResponseDto<Object> addUserLinkuser(@RequestBody ItripAddUserLinkUserVO itripAddUserLinkUserVO) throws Exception {
		// 通过 Cookie 获得当前登录对象
		String userCode = "";
		Cookie[] cookies = request.getCookies();
		for (Cookie  cookie : cookies) {
			if ("user".equals(cookie.getName())) {
				userCode = cookie.getValue();
			}
		}
		// 通过userCode获得userId
		User query = new User();
		query.setUserCode(userCode);
		List<User> userList = userTransport.getListByQuery(query);
		if (userList != null && userList.size() > 0) {
			User user = userList.get(0);
			UserLinkUser userLinkUser = new UserLinkUser();
			userLinkUser.setLinkUserName(itripAddUserLinkUserVO.getLinkUserName());
			userLinkUser.setLinkIdCardType(itripAddUserLinkUserVO.getLinkIdCardType());
			userLinkUser.setLinkIdCard(itripAddUserLinkUserVO.getLinkIdCard());
			Integer userId = user.getId().intValue();
			userLinkUser.setUserId(userId);
			userLinkUser.setLinkPhone(itripAddUserLinkUserVO.getLinkPhone());
			userLinkUser.setCreatedBy(user.getId());
			userLinkUser.setCreationDate(new Date(System.currentTimeMillis()));
			int count = userInfoTransport.addUserLinkuser(userLinkUser);
			if (count > 0) {
				return ResponseDto.success("新增常用联系人成功");
			}
		}
		return ResponseDto.failure("新增常用联系人失败");
	}

	/**
	 * <b>删除常用联系人接口</b>
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/deluserlinkuser")
	public ResponseDto<Object> delUserLinkuser(Long[] ids) throws Exception {
		if (ids != null) {
			for (Long id: ids) {
				Integer count = userInfoTransport.deleteItripUserLinkUserByIds(id);
				if (count > 0) {
					return ResponseDto.success("删除常用联系人成功");
				}
			}
		}
		return ResponseDto.failure("删除常用联系人失败");
	}

	/**
	 * <b>修改常用联系人</b>
	 * @param userLinkUserVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/modifyuserlinkuser")
	public ResponseDto<Object> updateUserLinkUser(@RequestBody ModifyUserLinkUserVO userLinkUserVO) throws Exception {
		if (userLinkUserVO != null) {
			// 通过 Cookie 获得当前登录对象
			String userCode = "";
			Cookie[] cookies = request.getCookies();
			for (Cookie  cookie : cookies) {
				if ("user".equals(cookie.getName())) {
					userCode = cookie.getValue();
				}
			}
			// 通过userCode获得userId
			User query = new User();
			query.setUserCode(userCode);
			List<User> userList = userTransport.getListByQuery(query);
			User user = null;
			if (userList != null && userList.size() > 0) {
				user = userList.get(0);
			}
			UserLinkUser linkUser = new UserLinkUser();
			linkUser.setId(userLinkUserVO.getId());
			linkUser.setLinkUserName(userLinkUserVO.getLinkUserName());
			linkUser.setLinkIdCardType(userLinkUserVO.getLinkIdCardType());
			linkUser.setLinkIdCard(userLinkUserVO.getLinkIdCard());
			linkUser.setUserId(user.getId().intValue());
			linkUser.setLinkPhone(userLinkUserVO.getLinkPhone());
			linkUser.setModifiedBy(user.getId());
			linkUser.setModifyDate(new Date(System.currentTimeMillis()));
			Integer count = userLinkUserTransport.updateUserLinkUser(linkUser);
			if (count > 0) {
				return ResponseDto.success("修改成功！");
			}
		}
		return ResponseDto.failure("修改失败！");
	}
}
