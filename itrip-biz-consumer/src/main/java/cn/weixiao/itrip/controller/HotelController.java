package cn.weixiao.itrip.controller;

import cn.weixiao.itrip.base.controller.BaseController;
import cn.weixiao.itrip.base.enums.AreaHotEnum;
import cn.weixiao.itrip.base.enums.ImageTypeEnum;
import cn.weixiao.itrip.base.pojo.vo.ResponseDto;
import cn.weixiao.itrip.pojo.entity.AreaDic;
import cn.weixiao.itrip.pojo.entity.Hotel;
import cn.weixiao.itrip.pojo.entity.ItripImage;
import cn.weixiao.itrip.pojo.entity.LableDic;
import cn.weixiao.itrip.pojo.vo.ItripAreaDicVO;
import cn.weixiao.itrip.pojo.vo.ItripLabelDicVO;
import cn.weixiao.itrip.pojo.vo.SearchDetailsHotelVO;
import cn.weixiao.itrip.transport.AreaDicTransport;
import cn.weixiao.itrip.transport.HotelTransport;
import cn.weixiao.itrip.transport.ItripImageTransport;
import cn.weixiao.itrip.transport.LableDicTransport;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>爱旅行-主业务酒店模块控制器</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@Api(value = "hotelController",tags = "酒店模块交互接口")
@RestController("hotelController")
@RequestMapping("/biz/api/hotel")
public class HotelController extends BaseController {
	@Autowired
	private AreaDicTransport areaDicTransport;
	@Autowired
	private LableDicTransport lableDicTransport;
	@Autowired
	private HotelTransport hotelTransport;
	@Autowired
	private ItripImageTransport itripImageTransport;
	/**
	 * <b>查询热门城市</b>
	 * @param isChina
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查询国内、国外的热门城市(1:国内 2:国外)", produces ="application/json", httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Integer" ,value = "是否属于国内城市")
	})
	@ApiResponses({
			@ApiResponse(code = 200, message = "响应成功，返回列表")
	})
	@GetMapping(value = "/queryhotcity/{isChina}")
	public ResponseDto<Object> queryHotCityList(@PathVariable("isChina") Integer isChina) throws Exception {
		// 创建查询对象
		AreaDic query = new AreaDic();
		// 设置查询条件：是否属于国内城市
		query.setIsChina(isChina);
		// 设置查询条件：热门城市
		query.setIsHot(AreaHotEnum.AREA_HOT_YES.getCode());

		// 查询列表
		List<AreaDic> areaDicList = areaDicTransport.getListByQuery(query);

		return ResponseDto.success(areaDicList);
	}

	@GetMapping(value = "/queryhotelfeature")
	public ResponseDto<Object> queryHotelFeature() throws Exception {
		// 创建查询对象
		LableDic query = new LableDic();
		query.setParentId(16L);
		List<ItripLabelDicVO> itripLabelDicVOList = null;
		List<LableDic> lableDicList = lableDicTransport.getListByQuery(query);
		if (lableDicList != null && lableDicList.size() > 0) {
			itripLabelDicVOList = new ArrayList<>();
			for (LableDic lableDic: lableDicList) {
				ItripLabelDicVO vo = new ItripLabelDicVO();
				BeanUtils.copyProperties(lableDic, vo);
				itripLabelDicVOList.add(vo);
			}
			return ResponseDto.success(itripLabelDicVOList);
		}
		return ResponseDto.failure("查询失败！");
	}

	/**
	 * <b>根据酒店id查询酒店特色、商圈、酒店名称（视频文字描述）</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "根据酒店id查询酒店特色、商圈、酒店名称（视频文字描述）", produces ="application/json", httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Long" ,value = "酒店Id")
	})
	@ApiResponses({
			@ApiResponse(code = 200, message = "响应成功，返回列表")
	})
	@GetMapping(value = "/getvideodesc/{hotelId}")
	public ResponseDto<Object> getVideoDesc(@PathVariable("hotelId") Long hotelId) throws Exception {
		Hotel hotel = hotelTransport.getHotelById(hotelId);
		return ResponseDto.success(hotel);
	}

	/**
	 * <b>根据酒店id查询酒店特色和介绍</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/queryhoteldetails/{hotelId}")
	 public ResponseDto<Object> queryHotelDetails(@PathVariable("hotelId") Long hotelId) throws Exception {
		List<SearchDetailsHotelVO> resultList = new ArrayList<SearchDetailsHotelVO>();
		// 根据酒店主键查询酒店信息
		Hotel hotel = hotelTransport.getHotelById(hotelId);
		// 增加数据
		resultList.add(new SearchDetailsHotelVO("酒店介绍", hotel.getDetails()));
		// 查询该酒店对应的特色信息列表
		LableDic lableDicQuery = new LableDic();
		lableDicQuery.setHotelId(hotelId);
		List<LableDic> lableDicList = lableDicTransport.getListByQuery(lableDicQuery);
		if (lableDicList != null && lableDicList.size() > 0) {
			for (LableDic lableDic : lableDicList) {
				resultList.add(new SearchDetailsHotelVO(lableDic.getName(), lableDic.getDescription()));
			}
		}
		return ResponseDto.success(resultList);
	 }

	/**
	 * <b>根据酒店id查询酒店设施</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/queryhotelfacilities/{hotelId}")
	public ResponseDto<Object> queryHotelFacilities(@PathVariable("hotelId") Long hotelId) throws Exception {
		Hotel hotel = hotelTransport.getHotelById(hotelId);
		return ResponseDto.success(hotel.getFacilities());
	}

	/**
	 * <b>根据酒店id查询酒店政策</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/queryhotelpolicy/{hotelId}")
	public ResponseDto<Object> queryHotelPolicy(@PathVariable("hotelId") Long hotelId) throws Exception {
		Hotel hotel = hotelTransport.getHotelById(hotelId);
		return ResponseDto.success(hotel.getHotelPolicy());
	}

	/**
	 * <b>根据targetId查询酒店图片(type=0)</b>
	 * @param targetId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/getimg/{targetId}")
	public ResponseDto<Object> getImgForHotel(@PathVariable("targetId") Long targetId) throws Exception {
		ItripImage query = new ItripImage();
		query.setTargetId(targetId);
		query.setType(String.valueOf(ImageTypeEnum.IMAGE_TYPE_HOTEL.getCode()));
		List<ItripImage> itripImageList = itripImageTransport.getTtripImageListByQuery(query);

		if (itripImageList.size() > 0) {
			return ResponseDto.success(itripImageList.get(0));
		}
		return ResponseDto.success( new  ItripImage());
	}

	/**
	 * <b>根据城市查询商圈</b>
	 * @param cityId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/querytradearea/{cityId}")
	public ResponseDto<Object> queryTradeArea(@PathVariable("cityId") Long cityId) throws Exception {
		List<AreaDic> areaDicList = null;
		List<ItripAreaDicVO> itripAreaDicVOList = null;
		AreaDic query = new AreaDic();
		query.setIsTradingArea(1);
		query.setParent(cityId);

		areaDicList = areaDicTransport.getListByQuery(query);
		if (areaDicList != null && areaDicList.size() > 0) {
			itripAreaDicVOList = new ArrayList<>();
			for (AreaDic areaDic: areaDicList) {
				ItripAreaDicVO itripAreaDicVO = new ItripAreaDicVO();
				BeanUtils.copyProperties(areaDic, itripAreaDicVO);
				itripAreaDicVOList.add(itripAreaDicVO);
			}
			return ResponseDto.success(itripAreaDicVOList);
		}
		return ResponseDto.failure("查询失败！");
	}
}
