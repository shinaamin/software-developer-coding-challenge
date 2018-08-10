package com.traderev.carauctionsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * @author SHINA.
 * @version 1.0
 * @since 2018-08-07
 */
@SpringBootApplication
@EnableSwagger2
public class CarAuctionSystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(CarAuctionSystemApplication.class, args);
	}
}
