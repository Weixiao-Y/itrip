package cn.weixiao.itrip.transport;

import cn.weixiao.itrip.pojo.entity.LableDic;
import cn.weixiao.itrip.service.LableDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <b>爱旅行-系统字典信息传输层接口实现类</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("lableDicTransport")
@RequestMapping("/lable/trans")
public class LableDicTransportImpl implements LableDicTransport{
	@Autowired
	private LableDicService lableDicService;

	/**
	 * <b>根据查询获得信息列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/query")
	public List<LableDic> getListByQuery(LableDic query) throws Exception {
		return lableDicService.getListByQuery(query);
	}
}
