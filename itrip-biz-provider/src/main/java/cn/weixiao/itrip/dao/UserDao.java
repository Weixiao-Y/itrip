package cn.weixiao.itrip.dao;

import cn.weixiao.itrip.pojo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <b>爱旅行-用户信息数据持久层接口</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface UserDao {
	List<User> findListByQuery(User query) throws Exception;
}
