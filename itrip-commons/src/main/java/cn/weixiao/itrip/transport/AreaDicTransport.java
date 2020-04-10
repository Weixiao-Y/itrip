package cn.weixiao.itrip.transport;

import cn.weixiao.itrip.pojo.entity.AreaDic;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * <b>爱旅行-区域字典信息传输层接口</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/area/trans")
public interface AreaDicTransport {
	/**
	 * <b>根据查询获得信息列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/query")
	List<AreaDic> getListByQuery(@RequestBody AreaDic query) throws Exception;

	/**
	 * <b>根据map集合查询列表</b>
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/param")
	List<AreaDic> getListByMap(Map param) throws Exception;
}
