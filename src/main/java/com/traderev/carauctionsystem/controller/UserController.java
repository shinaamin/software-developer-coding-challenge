package com.traderev.carauctionsystem.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class to handle user related web requests.
 *
 * @author SHINA.
 * @version 1.0
 * @since 2018-08-08
 */

@RestController
@RequestMapping("/users")
@Api(value = "users", description = "All user related info", tags = "users")
public class UserController {
}
