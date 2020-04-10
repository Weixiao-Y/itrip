package cn.weixiao.itrip.transport;

import cn.weixiao.itrip.pojo.entity.Hotel;
import cn.weixiao.itrip.pojo.entity.HotelRoom;
import cn.weixiao.itrip.pojo.vo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <b>爱旅行-酒店模块信息传输层接口</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/hotel/trans")
public interface HotelTransport {

	/**
	 * <b>根据热门城市查询酒店</b>
	 * @param queryVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/searchItripHotelListByHotCity")
	List<HotelVO> searchItripHotelListByHotCity(@RequestBody SearchHotCityVO queryVO) throws Exception;

	/**
	 * <b>根据主键查询对象信息</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/id")
	Hotel getHotelById(@RequestParam Long hotelId) throws Exception;

	/**
	 * <b>分页查询酒店列表</b>
	 * @param searchHotelVO
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/page")
	Page searchItripHotelPage(@RequestBody SearchHotelVO searchHotelVO,@RequestParam Integer pageNo, @RequestParam Integer pageSize) throws Exception;
}
