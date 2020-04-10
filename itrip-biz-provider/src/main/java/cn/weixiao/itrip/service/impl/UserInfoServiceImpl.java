package cn.weixiao.itrip.service.impl;

import cn.weixiao.itrip.dao.OrderLinkUserDao;
import cn.weixiao.itrip.dao.UserInfoDao;
import cn.weixiao.itrip.dao.UserLinkUserDao;
import cn.weixiao.itrip.pojo.entity.UserLinkUser;
import cn.weixiao.itrip.pojo.vo.ItripAddUserLinkUserVO;
import cn.weixiao.itrip.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <b>爱旅行-常用联系人业务层接口实现类</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("userInfoService")
@Transactional
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
	private OrderLinkUserDao orderLinkUserDao;
	@Autowired
	private UserLinkUserDao userLinkUserDao;

	/**
	 * <b>新增常用联系人</b>
	 * @param userLinkUser
	 * @return
	 */
	public int addUserLinkuser(UserLinkUser userLinkUser) throws Exception {
		int count = userInfoDao.saveLinkUser(userLinkUser);
		if (count > 0) {
			return count;
		}
		return 0;
	}

	/**
	 * <b>查询已有订单中的联系人</b>
	 * @return
	 * @throws Exception
	 */
	public List<Long> getItripOrderLinkUserIdsByOrder() throws Exception {

		return null;
	}

	/**
	 * <b>删除联系人</b>
	 * @return
	 * @throws Exception
	 */
	public Integer deleteItripUserLinkUserByIds(Long id) throws Exception {
		System.out.println(id);
		return userLinkUserDao.deleteItripUserLinkUserByIds(id);
	}
}
