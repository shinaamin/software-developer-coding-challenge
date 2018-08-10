package com.traderev.carauctionsystem.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class to handle car related web requests.
 *
 * @author SHINA.
 * @version 1.0
 * @since 2018-08-10
 */

@RestController
@RequestMapping("/cars")
@Api(value = "cars", description = "All cars related info", tags = "cars")
public class CarController {
}
