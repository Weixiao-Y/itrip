package cn.weixiao.itrip.controller;

import cn.weixiao.itrip.base.controller.BaseController;
import cn.weixiao.itrip.base.enums.UserActivatedEnum;
import cn.weixiao.itrip.base.enums.UserTypeEnum;
import cn.weixiao.itrip.base.pojo.vo.ResponseDto;
import cn.weixiao.itrip.pojo.entity.User;
import cn.weixiao.itrip.pojo.vo.UserVO;
import cn.weixiao.itrip.transport.UserTransport;
import cn.weixiao.itrip.util.MD5Util;
import cn.weixiao.itrip.util.RegValidationUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <b>爱旅行-认证模块控制器</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("authController")
@RequestMapping("/auth/api")
public class AuthController extends BaseController {
	@Autowired
	private UserTransport userTransport;
	/**
	 * <b>用户名注册验证-电子邮箱</b>
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/ckusr")
	public ResponseDto<Object> checkUserEmailForRegistry(String name) throws Exception {
		// 校验用户所提交的电子邮件是否有效（是否为空，以及是不是一个电子邮件格式）
		if (RegValidationUtil.validateEmail(name)) {
			// 校验通过之后，通过注册中心找到对应的生产者进行数据库校验
			// 封装查询对象
			User query = new User();
			query.setUserCode(name);
			// 进行查询
			List<User> userList = userTransport.getListByQuery(query);
			// 进行结果判断
			if (userList == null || userList.size() == 0) {
				// 此时用户注册所填写的邮箱地址可用
				return ResponseDto.success();
			}
		}
		return ResponseDto.failure("该邮箱地址已被注册！");
	}

	/**
	 * <b>使用电子邮件注册用户信息</b>
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/doregister")
	public ResponseDto<Object> registryUser(@RequestBody UserVO userVO) throws Exception {
		// 校验用户所给定的信息是否有效
		if (RegValidationUtil.validateEmail(userVO.getUserCode())
				&& userVO.getUserPassword() != null && !"".equals(userVO.getUserPassword())) {
			// 对于密码进行MD5加密
			userVO.setUserPassword(MD5Util.encrypt(userVO.getUserPassword()));
			// 将用户注册UserVO转换成User对象
			User user = new User();
			BeanUtils.copyProperties(userVO, user);
			// 当调用该方法的时候，用户属于自主注册
			user.setUserType(UserTypeEnum.USER_TYPE_REG.getCode());
			// 将激活状态设置为未激活
			user.setActivated(UserActivatedEnum.USER_ACTIVATED_NO.getCode());
			// 使用传输层，远程调用生产者进行用户信息注册工作
			boolean flag = userTransport.saveUser(user);
			if (flag) {
				// 注册成功
				return ResponseDto.success();
			}
		}
		return ResponseDto.failure("注册失败！");
	}

	/**
	 * <b>使用手机号码注册用户信息</b>
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/registerbyphone")
	public ResponseDto<Object> registryByCellphone(@RequestBody UserVO userVO) throws Exception {
		// 校验用户所给定的信息是否有效
		if (RegValidationUtil.validateCellphone(userVO.getUserCode())
				&& userVO.getUserPassword() != null && !"".equals(userVO.getUserPassword())) {
			// 进行唯一性校验
			User query = new User();
			query.setUserCode(userVO.getUserCode());
			List<User> userList = userTransport.getListByQuery(query);
			if (userList == null || userList.size() == 0) {
				// 此时的手机号码未进行注册
				// 对于密码进行MD5加密
				userVO.setUserPassword(MD5Util.encrypt(userVO.getUserPassword()));
				// 将用户注册UserVO转换成User对象
				User user = new User();
				BeanUtils.copyProperties(userVO, user);
				// 当调用该方法的时候，用户属于自主注册
				user.setUserType(UserTypeEnum.USER_TYPE_REG.getCode());
				// 将激活状态设置为未激活
				user.setActivated(UserActivatedEnum.USER_ACTIVATED_NO.getCode());
				// 使用传输层，远程调用生产者进行用户信息注册工作
				boolean flag = userTransport.saveUser(user);
				if (flag) {
					// 注册成功
					return ResponseDto.success();
				}
			}
			return ResponseDto.failure("该手机号码已经被注册！");
		}
		return ResponseDto.failure("注册失败！");
	}

	/**
	 * <b>激活注册用户-邮箱</b>
	 * @param user
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@PutMapping(value = "/activate")
	public ResponseDto<Object> activeUser(String user, String code) throws Exception {
		// 校验用户所给定的user和code有效
		if (user != null && !"".equals(user.trim()) && code != null && !"".equals(code.trim())) {
			// 通过user在Redis中查询相应的code
			String activeCode = userTransport.getActiveCodeByUserCode(user);
			// 比较两个code是否相同
			if (code.equals(activeCode)) {
				// 修改用户的激活状态
				User updateUser = new User();
				updateUser.setUserCode(user);
				updateUser.setActivated(UserActivatedEnum.USER_ACTIVATED_YES.getCode());
				// 在数据库中更新用户数据
				userTransport.updateUser(updateUser);
				return ResponseDto.success();
			}
			return ResponseDto.failure("激活码不正确！");
		}
		return ResponseDto.failure("激活失败！");
	}

	/**
	 * <b>激活注册用户-手机号码</b>
	 * @param user
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@PutMapping(value = "/validatephone")
	public ResponseDto<Object> activeUserByCellphone(String user, String code) throws Exception {
		// 校验用户所给定的user和code有效
		if (user != null && !"".equals(user.trim()) && code != null && !"".equals(code.trim())) {
			// 通过user在Redis中查询相应的code
			String activeCode = userTransport.getActiveCodeByUserCode(user);
			// 比较两个code是否相同
			if (code.equals(activeCode)) {
				// 修改用户的激活状态
				User updateUser = new User();
				updateUser.setUserCode(user);
				updateUser.setActivated(UserActivatedEnum.USER_ACTIVATED_YES.getCode());
				// 在数据库中更新用户数据
				userTransport.updateUser(updateUser);
				return ResponseDto.success();
			}
			return ResponseDto.failure("激活码不正确！");
		}
		return ResponseDto.failure("激活失败！");
	}
}
