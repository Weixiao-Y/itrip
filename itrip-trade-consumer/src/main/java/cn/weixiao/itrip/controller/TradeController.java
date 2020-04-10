package cn.weixiao.itrip.controller;

import cn.weixiao.itrip.base.controller.BaseController;
import cn.weixiao.itrip.base.pojo.vo.ResponseDto;
import cn.weixiao.itrip.pojo.entity.HotelOrder;
import cn.weixiao.itrip.transport.HotelOrderTransport;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletResponse;

/**
 * <b>爱旅行-支付模块控制器</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("tradeController")
@RequestMapping("/trade/api")
public class TradeController extends BaseController {
	@Autowired
	private HotelOrderTransport hotelOrderTransport;

	@GetMapping(value = "/prepay/{tradeNo}")
	public void testTrade(@PathVariable("tradeNo") String tradeNo) throws Exception {
		HotelOrder hotelOrder = hotelOrderTransport.getHotelOrderByNo(tradeNo);
		AlipayClient alipayClient =  new DefaultAlipayClient(
				"https://openapi.alipaydev.com/gateway.do" ,
				"2016101900722116",
				"MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCNwE19kwKa7VipNZ136U3bokvmyjXdHgzbyWtPpdOCvF3xv1J/s39BvVDus/93wWMHTICJN+prs/ZMHTr298Ok+O8i3YC75zFM/hQPVh8eGBrxarEPLxTT3oqez53uWlGdZ2cRLs06UKvgCoH54ibGZ4EjraKXur13m4EKne8IvrZzcCyzG5CwQh7BPtVLe7n18D5XggEqLoxDSl11md5tyPBk0GS+tSkAhITUkIf/vJGwiaa0/KgOGBWXptuQwNMCAURdEnX8MlHzwYC15U9gpyBsvVQtMCCmzF5cNQy9pGmNNpGEtPTHsUEwpcYft8NcIs+93HZlllfx0A6PPANhAgMBAAECggEAB3OFX0V8DJLPZ02nMmd0LUhxvvcAL9hliqQi3PP1k1i9g3iwKIBR/y2pdHLHyg7DWt3dn9jtnK3AE64xrEvH4AMp5uZH3xi/EbIFcezwA8/2kpwuJIMwEBcRqFl3jtD2m1SVKWURarBU6JkEzhLiQOmt8ycsdddeIExvUxSMPSRdQY7qus28DkiqPV9T0jYoM36B5is44ICL4UiMcj9p39d3puOadoLjg5BenVbo/iDpOrCKRa0yY4/18mPHUSl3Z6FoBAkiNa1TFnk7PD6i4z9UpKwiWnNxtyLEzozE9RnfCwFqTYTtbFEEd+ke2vEdCN+WuB0DtDwRBQcvF7dLlQKBgQDAroEXAA4AIfFWLWDhmIUgDRyD6JusN/4ktCTasyrmDd3utLNkSj6J4d+/cjbzwRkmlqhevBYzB8ehIdXiyGgr4JZVCu9JDalyr05QgBmzqi+E4jMrIzyOGyEDPi33+ro2bgOEmGAP7LNAVQmuxwzHHBu09T1Lom5zhL3106vWnwKBgQC8VTt0ZFLVJKqpES/T7auXVaOxw068HBGcjiYNERCDKvvDnLMCOti1rS4VcDT+W3pfUL0FOaoJqeuT3hgu4pgSbYaay/7kyk5CMJXWtG4dncel8cYNgB7PEMxHn84yH6rNU1TWwW/ygq2wiI+f2axmJMijwp9USI92XXNJMMvl/wKBgEBZRxVmWEalD1NHfmZ5KGSG3HIVOgAvBvo9C13ankYMdYTl5k9lGDO2MqJeQ4nugDbJ89sHl6ZdP8dJZIQDh/63V515NGbCYEfD0jTCQ1MkUBBh9tK+RtfyqXjKyFFb89p/rT8YIGTRGSAwBjbPlS3d7kbl7B2ZVqfPuh0iHu5jAoGANOtCJIdYGxoNdiolJUigMYrXz0hQ5FyCulna6UsFN8YpLiLmzdaZNBtwWofH7DzqGsRGCKTWWZbMYiVC6FG1Z2AtNWviuIj8Cm86ezUByAKgqclQ3FRG0+olbG7D8qOhS8aBjUKXYS5E/dixerogTidB1XoHaRhmb1z82vECKiMCgYAniovAwcw3kUqyngend/532lGWZWupW74BmIuwuknHyx1AfHhVNXfQCVU67D7lNbS1uIseia0SfMhk/asMfhYUI8WIjmRWSDkbwPgs+Bb41HiOmjp7oiO2UdECT1av+iLKlyqqjapnqR/79j70NvJIAE+aS2d8fsdRMUX85MXeIg==",
				"json", "UTF-8",
				"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjcBNfZMCmu1YqTWdd+lN26JL5so13R4M28lrT6XTgrxd8b9Sf7N/Qb1Q7rP/d8FjB0yAiTfqa7P2TB069vfDpPjvIt2Au+cxTP4UD1YfHhga8WqxDy8U096Kns+d7lpRnWdnES7NOlCr4AqB+eImxmeBI62il7q9d5uBCp3vCL62c3AssxuQsEIewT7VS3u59fA+V4IBKi6MQ0pddZnebcjwZNBkvrUpAISE1JCH/7yRsImmtPyoDhgVl6bbkMDTAgFEXRJ1/DJR88GAteVPYKcgbL1ULTAgpsxeXDUMvaRpjTaRhLT0x7FBMKXGH7fDXCLPvdx2ZZZX8dAOjzwDYQIDAQAB",
				"RSA2");  //获得初始化的AlipayClient
		AlipayTradePagePayRequest alipayRequest =  new  AlipayTradePagePayRequest(); //创建API对应的request 
		alipayRequest.setReturnUrl( "http://localhost/trade/api/notify?tradeNo=" + tradeNo);
		alipayRequest.setNotifyUrl( "http://localhost/itrip" ); //在公共参数中设置回跳和通知地址
		alipayRequest.setBizContent( "{"  +
				"    \"out_trade_no\":\""+tradeNo+"\","  +
				"    \"product_code\":\"FAST_INSTANT_TRADE_PAY\","  +
				"    \"total_amount\":\""+hotelOrder.getPayAmount()+"\","  +
				"    \"subject\":\"Iphone6 16G\","  +
				"    \"body\":\"Iphone6 16G\","  +
				"    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\","  +
				"    \"extend_params\":{"  +
				"    \"sys_service_provider_id\":\"2088511833207846\""  +
				"    }" +
				"  }" ); //填充业务参数 
		String form= "" ;
		try  {
			form = alipayClient.pageExecute(alipayRequest).getBody();  //调用SDK生成表单 
		}  catch  (AlipayApiException e) {
			e.printStackTrace();
		}
		response.setContentType( "text/html;charset=UTF-8" );
		response.getWriter().write(form); //直接将完整的表单html输出到页面
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * <b>更该订单状态</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/notify")
	public void trackPaymentStatus() throws Exception {
		String tradeNo = request.getParameter("tradeNo");
		if (tradeNo != null && !"".equals(tradeNo)) {
			HotelOrder query = new HotelOrder();
			query.setTradeNo(tradeNo);
			query.setOrderStatus(2);
			query.setPayType(1);
			boolean flag = hotelOrderTransport.updateHotelOrder(query);
			if (flag == true) {
				response.sendRedirect("http://localhost/itrip");
			}
		}
	}
}
