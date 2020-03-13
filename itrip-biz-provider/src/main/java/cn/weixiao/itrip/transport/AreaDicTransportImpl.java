package cn.weixiao.itrip.transport;

import cn.weixiao.itrip.pojo.entity.AreaDic;
import cn.weixiao.itrip.service.AreaDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <b>爱旅行-区域字典信息传输层接口实现类</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("areaDicTransport")
@RequestMapping("/area/trans")
public class AreaDicTransportImpl implements AreaDicTransport{
	@Autowired
	private AreaDicService areaDicService;

	/**
	 * <b>根据查询获得信息列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/query")
	public List<AreaDic> getListByQuery(AreaDic query) throws Exception {
		return areaDicService.getListByQuery(query);
	}
}
