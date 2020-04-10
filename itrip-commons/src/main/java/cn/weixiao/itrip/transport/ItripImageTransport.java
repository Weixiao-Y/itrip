package cn.weixiao.itrip.transport;

import cn.weixiao.itrip.pojo.entity.ItripImage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <b>爱旅行-图片传输层接口</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/img/trans")
public interface ItripImageTransport {
	/**
	 * <b>根据查询条件查询信息列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/list")
	List<ItripImage> getTtripImageListByQuery(@RequestBody ItripImage query) throws Exception;
}
