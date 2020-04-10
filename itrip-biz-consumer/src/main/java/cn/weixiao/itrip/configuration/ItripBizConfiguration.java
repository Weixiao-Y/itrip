package cn.weixiao.itrip.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class ItripBizConfiguration {
	/*
	 * 对于Spring中的配置文件来说，也属于Spring的一种配置文件，那么Spring的配置文件的核心作用就是在创建Bean，
	 * 因此当使用配置类来替代原始的XML的时候，需要提供创建所需要对象的方法，当Spring框架加载的时候，
	 * 就会按照该方法，进行创建对应的Bean来进行管理
	 */
	@Bean
	public Docket createRestDocket() {
		// 创建Docket对象
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		// 设定在Swagger生成UI界面时，所显示的信息
		docket.apiInfo(apiInfo());
		return docket;
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfoBuilder()
				// 设置生成文档的接口
				.title("爱旅行项目后端交互接口")
				// 设定版本号
				.version("1.0.0")
				// 设定联系人
				.contact(new Contact("微笑", "", "13032910105@163.com"))
				.build();
		return apiInfo;
	}
}

