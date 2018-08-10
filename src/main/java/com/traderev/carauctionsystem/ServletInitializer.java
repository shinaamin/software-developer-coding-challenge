package com.traderev.carauctionsystem;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author SHINA.
 * @version 1.0
 * @since 2018-08-07
 */
public class ServletInitializer extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CarAuctionSystemApplication.class);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.traderev.carauctionsystem.controller")) // show specific class path only
				.paths(PathSelectors.any())
				.build()
				.apiInfo(metaInfo());
	}

	private ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo(
				"Car Auction System", // title
				"Documentation of Project Car Auction System", // sub-title
				"1.0", // api version
				"", // term of service
				new Contact("Shina Amin", "", "shinaamin27@gmail.com"), // author
				"", // License Type
				"");
		return apiInfo;
	}
}
