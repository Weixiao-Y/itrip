package cn.weixiao.itrip.service;

import cn.weixiao.itrip.pojo.entity.UserLinkUser;
import cn.weixiao.itrip.pojo.vo.ModifyUserLinkUserVO;
import net.sf.jsqlparser.statement.execute.Execute;

import java.util.List;

/**
 * <b>爱旅行-常用联系人业务层接口</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
public interface UserLinkUserService {

	/**
	 * <b>根据查询信息查询联系人列表</b>
	 * @return
	 * @throws Exception
	 */
	List<UserLinkUser> getUserLinkUserListByQuery(UserLinkUser query) throws Exception;

	/**
	 * <b>修改联系人信息</b>
	 * @param linkUser
	 * @return
	 * @throws Exception
	 */
	Integer updateUserLinkUser(UserLinkUser linkUser) throws Exception;
}
