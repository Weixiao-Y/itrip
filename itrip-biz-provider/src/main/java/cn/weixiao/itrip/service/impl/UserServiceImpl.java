package cn.weixiao.itrip.service.impl;

import cn.weixiao.itrip.dao.UserDao;
import cn.weixiao.itrip.pojo.entity.User;
import cn.weixiao.itrip.service.UserService;
import cn.weixiao.itrip.util.ActiveCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <b>爱旅行-用户信息业务层接口实习类</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	/**
	 * <b>根据查询对象查询用户信息列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<User> getListByQuery(User query) throws Exception {
		// 调用数据持久层查询列表信息
		return userDao.findListByQuery(query);
	}

	/**
	 * <b>保存用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean saveUser(User user) throws Exception {
		// 设定用户注册时间
		user.setCreationDate(new Date());
		// 使用数据持久层保存用户信息
		int count = userDao.saveUser(user);
		if (count > 0) {
			// 产生激活码
			String activeCode = ActiveCodeUtil.createActiveCode();

			// 通过发送邮件，将激活码发送给用户
			return true;
		}
		return false;
	}
}
