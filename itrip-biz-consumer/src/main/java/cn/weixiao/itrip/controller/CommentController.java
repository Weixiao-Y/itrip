package cn.weixiao.itrip.controller;

import cn.weixiao.itrip.base.controller.BaseController;
import cn.weixiao.itrip.base.enums.ImageTypeEnum;
import cn.weixiao.itrip.base.pojo.vo.ResponseDto;
import cn.weixiao.itrip.pojo.entity.*;
import cn.weixiao.itrip.pojo.vo.ItripHotelDescVO;
import cn.weixiao.itrip.pojo.vo.ItripScoreCommentVO;
import cn.weixiao.itrip.pojo.vo.Page;
import cn.weixiao.itrip.pojo.vo.SearchCommentVO;
import cn.weixiao.itrip.pojo.vo.comment.ItripAddCommentVO;
import cn.weixiao.itrip.transport.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.Cookie;
import javax.xml.bind.SchemaOutputResolver;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * <b>爱旅行-主业务酒店评论模块控制器</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("commentController")
@RequestMapping("/biz/api/comment")
public class CommentController extends BaseController {
	@Autowired
	private CommentTransport commentTransport;
	@Autowired
	private ItripImageTransport itripImageTransport;
	@Autowired
	private HotelTransport hotelTransport;
	@Autowired
	private LableDicTransport lableDicTransport;
	@Autowired
	private UserTransport userTransport;
	/**
	 * <b>据酒店id查询酒店平均分</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/gethotelscore/{hotelId}")
	public ResponseDto<Object> gethotelscore(@PathVariable("hotelId") Long hotelId) throws Exception {
		ItripScoreCommentVO itripScoreCommentVO = commentTransport.gethotelscore(hotelId);
		return ResponseDto.success(itripScoreCommentVO);
	}

	/**
	 * <b>据酒店id查询酒店评论数</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/getcount/{hotelId}")
	public ResponseDto<Object> getCount(@PathVariable("hotelId") Long hotelId) throws Exception {
		Integer count = 0;
		Map<String, Integer> countMap = new HashMap<String, Integer>();
		Map<String, Object> param = new HashMap<String, Object>();
		if (hotelId != null) {
			param.put("hotelId", hotelId);
			count = getCommentCountByMap(param);
			if (count != -1) {
				countMap.put("allcomment", count);
			}
			param.put("isOk",1);    //0：有待改善 1：值得推荐
			count = getCommentCountByMap(param);
			if(count != -1){
				countMap.put("isok",count);
			}
			param.put("isOk",0);    //0：有待改善 1：值得推荐
			count = getCommentCountByMap(param);
			if(count != -1 ){
				countMap.put("improve",count);
			}
			Map<String, Object> param1 = new HashMap<String, Object>();
			param1.put("isHavingImg",1);  //0:无图片 1:有图片
			param1.put("hotelId", hotelId);
			count = getCommentCountByMap(param1);
			if(count != -1){
				countMap.put("havingimg",count);
			}
		} else {
			return ResponseDto.failure("参数hotelId为空");
		}
		return ResponseDto.success(countMap);
	}

	public Integer getCommentCountByMap(Map<String,Object> param) throws Exception{
		Integer count = -1;
		count = commentTransport.getCommentCountByParam(param);
		return count;
	}

	/**
	 * <b>根据评论类型查询评论列表，并分页显示</b>
	 * @param searchCommentVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getcommentlist")
	public ResponseDto<Object> getCommentList(@RequestBody SearchCommentVO searchCommentVO) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		Integer isHavingImg = searchCommentVO.getIsHavingImg();
		Integer isOk = searchCommentVO.getIsOk();
		if (isHavingImg == -1) {
			isHavingImg = null;
		}
		if (isOk == -1) {
			isOk = null;
		}
		param.put("hotelId",searchCommentVO.getHotelId());
		param.put("isHavingImg", isHavingImg);
		param.put("isOk", isOk);
		Page page = commentTransport.queryCommentPageByMap(param,
				searchCommentVO.getPageNo(),
				searchCommentVO.getPageSize());
		return ResponseDto.success(page);
	}

	/**
	 * <b>根据targetId查询酒店图片(type=2)</b>
	 * @param targetId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/getimg/{targetId}")
	public ResponseDto<Object> getImgForHotel(@PathVariable("targetId") String targetId) throws Exception {
		ItripImage query = new ItripImage();
		query.setTargetId(Long.valueOf(targetId));
		query.setType(String.valueOf(ImageTypeEnum.IMAGE_TYPE_COMMENT.getCode()));
		List<ItripImage> itripImageList = itripImageTransport.getTtripImageListByQuery(query);
		if (itripImageList != null && itripImageList.size() > 0) {
			return ResponseDto.success(itripImageList);
		}
		return ResponseDto.success( new ArrayList<ItripImage>());
	}

	/**
	 * <b> 获取酒店相关信息（酒店名称、酒店星级）</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/gethoteldesc/{hotelId}")
	public ResponseDto<Object> getHotelDesc(@PathVariable("hotelId") String hotelId) throws Exception {
		if (hotelId != null && !"".equals(hotelId)) {
			Hotel hotel = hotelTransport.getHotelById(Long.valueOf(hotelId));
			ItripHotelDescVO hotelDescVO = new ItripHotelDescVO();
			hotelDescVO.setHotelName(hotel.getHotelName());
			hotelDescVO.setHotelId(hotel.getId());
			hotelDescVO.setHotelLevel(hotel.getHotelLevel());
			return ResponseDto.success(hotelDescVO);
		}
		return ResponseDto.success(new ItripHotelDescVO());
	}

	/**
	 * <b>查询出游类型列表</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/gettraveltype")
	public ResponseDto<Object> getTravelType() throws Exception {
		Long parentId = 107L;
		LableDic query = new LableDic();
		query.setParentId(parentId);
		List lableDic = lableDicTransport.getListByQuery(query);
		return ResponseDto.success(lableDic);
	}

	/**
	 * <b>新增评论接口</b>
	 * @param itripAddCommentVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/add")
	public ResponseDto<Object> add(@RequestBody ItripAddCommentVO itripAddCommentVO) throws Exception {
		// 通过 Cookie 获得当前登录对象
		String userCode = "";
		Cookie[] cookies = request.getCookies();
		for (Cookie  cookie : cookies) {
			if ("user".equals(cookie.getName())) {
				userCode = cookie.getValue();
			}
		}
		// 通过userCode获得userId
		User query = new User();
		query.setUserCode(userCode);
		List<User> userList = userTransport.getListByQuery(query);
		if (userList != null && userList.size() > 0) {
			User user = userList.get(0);
			Comment comment = new Comment();
			comment.setContent(itripAddCommentVO.getContent());
			comment.setHotelId(itripAddCommentVO.getHotelId());
			comment.setIsHavingImg(itripAddCommentVO.getIsHavingImg());
			comment.setPositionScore(itripAddCommentVO.getPositionScore());
			comment.setFacilitiesScore(itripAddCommentVO.getFacilitiesScore());
			comment.setHygieneScore(itripAddCommentVO.getHygieneScore());
			comment.setOrderId(itripAddCommentVO.getOrderId());
			comment.setServiceScore(itripAddCommentVO.getServiceScore());
			comment.setProductId(itripAddCommentVO.getProductId());
			comment.setProductType(itripAddCommentVO.getProductType());
			comment.setIsOk(itripAddCommentVO.getIsOk());
			comment.setTripMode(Long.valueOf(itripAddCommentVO.getTripMode()));
			comment.setCreatedBy(user.getId());
			comment.setCreationDate(new Date(System.currentTimeMillis()));
			comment.setUserId(user.getId());
			// 保存图片
			List<ItripImage> itripImageList = null;
			System.out.println("itripImage:" + itripAddCommentVO.getItripImages());
			if (itripAddCommentVO.getIsHavingImg() == 1) {
				itripImageList = new ArrayList<>();
				int i = 1;
				for (ItripImage itripImage : itripAddCommentVO.getItripImages()) {
					// 图片上传顺序位置
					itripImage.setPosition(i);
					itripImage.setCreatedBy(user.getId());
					itripImage.setCreationDate(new Date(System.currentTimeMillis()));
					itripImage.setType("2");
					itripImageList.add(itripImage);
					i++;
				}
			}
			comment.setImageList(itripImageList);
			boolean flag = commentTransport.addComment(comment);
			if (flag == true) {
				return ResponseDto.success("新增评论成功！");
			}
		}
		return ResponseDto.failure("新增评论失败！");
	}

	/**
	 * <b>图片上传接口</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/upload")
	public ResponseDto<Object> upload(@RequestPart("file") MultipartFile multipartFile) throws Exception {
		List<String> dataList = new ArrayList<String>();
		// 获取文件数量
		int fileCount = request.getParts().size();
		// 获取文件名
		String realName = multipartFile.getOriginalFilename();
		// 重命名时，最重要的就是文件的扩展名不能发生变化
		String suffix = realName.substring(realName.lastIndexOf("."), realName.length());
		// 形成新的文件名
		String fileName = System.currentTimeMillis() + suffix;

		// 通过MultipartFile获得文件的输入流对象
		InputStream is = multipartFile.getInputStream();

		//  获得了上传所提供的文件流之后，就可以读取输入流中的数据，写入到对应的输出流中
		// 设定文件保存路径
		String path = "E:\\MySelf\\GraduationProject\\nginx-1.16.1\\view\\itrip\\static\\media";
		// 根据文件夹获得对应文件对象
		File uploadFolder = new File(path);
		String imgUrl = path+ "/" + fileName;
		dataList.add(imgUrl);

		if (!uploadFolder.exists()) {
			// 新建文件夹
			uploadFolder.mkdirs();
		}

		// 根据文件在服务器上的上传路径和文件名，获得目标文件File对象
		File uploadFile = new File(path + File.separator + fileName + "_200x200.jpg");

		// 创建输出流对象
		OutputStream os = new FileOutputStream(uploadFile);
		// 为了加快读写速度，使用commons-io组件
		IOUtils.copy(is, os);

		// 获取文件上传的绝对路径
		// 打印出掉结果是编译后的target文件夹下的路径
		// String projectUrl = request.getServletContext().getRealPath("/");
		// 关闭流
		os.close();
		is.close();
		return ResponseDto.success(dataList);
	}
}
