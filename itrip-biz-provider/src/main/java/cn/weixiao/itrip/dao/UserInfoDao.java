package cn.weixiao.itrip.dao;

import cn.weixiao.itrip.pojo.entity.UserLinkUser;
import org.springframework.stereotype.Repository;

/**
 * <b>爱旅行-常用联系人数据持久层接口</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface UserInfoDao {
	/**
	 * <b>保存常用联系人</b>
	 * @param userLinkUser
	 * @return
	 */
	int saveLinkUser(UserLinkUser userLinkUser) throws Exception;
}
