package cn.weixiao.itrip.service.impl;

import cn.weixiao.itrip.dao.UserLinkUserDao;
import cn.weixiao.itrip.pojo.entity.UserLinkUser;
import cn.weixiao.itrip.pojo.vo.ModifyUserLinkUserVO;
import cn.weixiao.itrip.service.UserLinkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * <b>爱旅行-常用联系人业务层接口实现类</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("userLinkUserService")
@Transactional
public class UserLinkUserServiceImpl implements UserLinkUserService {
	@Autowired
	private UserLinkUserDao userLinkUserDao;
	/**
	 * <b>根据查询信息查询联系人列表</b>
	 * @return
	 * @throws Exception
	 */
	 public List<UserLinkUser> getUserLinkUserListByQuery(UserLinkUser query) throws Exception {
		List<UserLinkUser> userLinkUserList = userLinkUserDao.findUserLinkUserListByQuery(query);

		if (userLinkUserList != null) {
			return userLinkUserList;
		}
		return new ArrayList<UserLinkUser>();
	}

	/**
	 * <b>修改联系人信息</b>
	 * @param linkUser
	 * @return
	 * @throws Exception
	 */
	public Integer updateUserLinkUser(UserLinkUser linkUser) throws Exception {
		return userLinkUserDao.updateUserLinkUser(linkUser);
	}
}
