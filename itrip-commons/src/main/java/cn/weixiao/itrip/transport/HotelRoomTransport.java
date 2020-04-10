package cn.weixiao.itrip.transport;

import cn.weixiao.itrip.pojo.entity.HotelRoom;
import cn.weixiao.itrip.pojo.vo.SearchHotelRoomVO;
import cn.weixiao.itrip.pojo.vo.ValidateRoomStoreVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <b>爱旅行-酒店房间模块传输层接口</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/hotelroom/trans")
public interface HotelRoomTransport {

	/**
	 * <b>查询酒店房间列表-此刻可以预定的房间列表</b>
	 * @param searchHotelRoomVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/queryhotelroombyhotel")
	List<HotelRoom> queryHotelRoombyHotel(@RequestBody SearchHotelRoomVO searchHotelRoomVO) throws Exception;

	/**
	 * <b>根据房间主键查询房间信息</b>
	 * @param roomId
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/id")
	HotelRoom queryHotelRoomById(@RequestParam Long roomId) throws Exception;

	/**
	 * <b>根据查询条件获得可用房间数量</b>
	 * @param validateRoomStoreVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/store")
	int queryHotelRoomStoreByDate(@RequestBody ValidateRoomStoreVO validateRoomStoreVO) throws Exception;
}
