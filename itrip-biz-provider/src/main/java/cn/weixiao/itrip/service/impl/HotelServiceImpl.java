package cn.weixiao.itrip.service.impl;

import cn.weixiao.itrip.dao.HotelDao;
import cn.weixiao.itrip.pojo.entity.Hotel;
import cn.weixiao.itrip.pojo.vo.HotelVO;
import cn.weixiao.itrip.pojo.vo.Page;
import cn.weixiao.itrip.pojo.vo.SearchHotCityVO;
import cn.weixiao.itrip.pojo.vo.SearchHotelVO;
import cn.weixiao.itrip.service.HotelService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>爱旅行-酒店模块业务层接口实现类</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("hotelService")
@Transactional
public class HotelServiceImpl implements HotelService {
	@Autowired
	private SolrClient solrClient;
	@Autowired
	private HotelDao hotelDao;
	/**
	 * <b>根据热门城市查询酒店</b>
	 * @param queryVO
	 * @return
	 * @throws Exception
	 */
	public List<HotelVO> searchItripHotelListByHotCity(SearchHotCityVO queryVO) throws Exception {
		// 对于Spring Boot注入的SolrClient就是HttpSolrClient对象，进行强制类型转换
		HttpSolrClient httpSolrClient = (HttpSolrClient) solrClient;
		httpSolrClient.setParser(new XMLResponseParser());
		// 创建Solr的查询参数
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("cityId:" + queryVO.getCityId());
		solrQuery.setRows(queryVO.getCount());
		// 使用SolrClient进行查询，QueryResponse
		QueryResponse queryResponsey = solrClient.query(solrQuery);
		// 通过使用QueryResponse提取结果
		return queryResponsey.getBeans(HotelVO.class);
	}

	/**
	 * <b>根据主键查询对象信息</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	public Hotel getHotelById(Long hotelId) throws Exception {
		// 创建查询对象
		Hotel query = new Hotel();
		query.setId(hotelId);
		// 进行查询列表
		List<Hotel> hotelList = hotelDao.findListByQuery(query);
		if (hotelList != null && hotelList.size() > 0) {
			return hotelList.get(0);
		}
		return new Hotel();
	}

	/**
	 * <b>分页查询酒店信息</b>
	 * @param searchHotelVO
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page searchItripHotelPage(SearchHotelVO searchHotelVO, Integer pageNo, Integer pageSize) throws Exception {
		return null;
	}
}
