package cn.weixiao.itrip.dao;

import cn.weixiao.itrip.pojo.entity.UserLinkUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <b>爱旅行-常用联系人数据持久层接口</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface UserLinkUserDao {

	/**
	 * <b>根据查询获得列表信息</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<UserLinkUser> findUserLinkUserListByQuery(UserLinkUser query) throws Exception;

	/**
	 * <b>删除联系人</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Integer deleteItripUserLinkUserByIds(Long id) throws Exception;

	/**
	 * <b>修改联系人信息</b>
	 * @param linkUser
	 * @return
	 * @throws Exception
	 */
	Integer updateUserLinkUser(UserLinkUser linkUser) throws Exception;
}
