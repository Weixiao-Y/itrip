package cn.weixiao.itrip.transport;

import cn.weixiao.itrip.pojo.entity.Hotel;
import cn.weixiao.itrip.pojo.entity.HotelOrder;
import cn.weixiao.itrip.pojo.vo.Page;
import cn.weixiao.itrip.pojo.vo.PersonalHotelOrderVO;
import cn.weixiao.itrip.pojo.vo.PersonalOrderRoomVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * <b>爱旅行-酒店订单传输层接口</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/hotelorder/trans")
public interface HotelOrderTransport {

	/**
	 * <b>根据查询条件查询列表信息</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/list")
	List<HotelOrder> getHotelListByQuery(@RequestBody HotelOrder query) throws Exception;

	/**
	 * <b>保存酒店订单</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/save")
	boolean saveHotelOrder(@RequestBody HotelOrder hotelOrder) throws Exception;

	/**
	 * <b>更新订单信息</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/update")
	boolean updateHotelOrder(@RequestBody HotelOrder hotelOrder) throws Exception;

	/**
	 * <b>根据订单号查询订单</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/id")
	HotelOrder getHotelOrderById(@RequestParam Long orderId) throws Exception;

	/**
	 * <b>根据订单编号查询订单</b>
	 * @param orderNo
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/no")
	HotelOrder getHotelOrderByNo(@RequestParam String orderNo) throws Exception;

	/**
	 * <b>根据订单编号查询房型相关信息</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/orderId")
	PersonalOrderRoomVO getPersonalOrderRoomInfo(@RequestParam Long orderId) throws Exception;

	/**
	 * <b>根据param, pageNo, pageSize查询分页对象</b>
	 * @param param
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/param")
	Page queryOrderPageByMap(@RequestParam Map<String, Object> param, @RequestParam Integer pageNo, @RequestParam Integer pageSize) throws Exception;
}
