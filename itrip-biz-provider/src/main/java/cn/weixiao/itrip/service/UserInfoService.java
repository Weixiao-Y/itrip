package cn.weixiao.itrip.service;

import cn.weixiao.itrip.pojo.entity.UserLinkUser;
import cn.weixiao.itrip.pojo.vo.ItripAddUserLinkUserVO;

import java.util.List;

/**
 * <b>爱旅行-常用联系人业务层接口</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
public interface UserInfoService {
	/**
	 * <b>新增常用联系人</b>
	 * @param userLinkUser
	 * @return
	 */
	int addUserLinkuser(UserLinkUser userLinkUser) throws Exception;

	/**
	 * <b>查询已有订单中的联系人</b>
	 * @return
	 * @throws Exception
	 */
	List<Long> getItripOrderLinkUserIdsByOrder() throws Exception;

	/**
	 * <b>删除联系人</b>
	 * @return
	 * @throws Exception
	 */
	Integer deleteItripUserLinkUserByIds(Long id) throws Exception;
}
