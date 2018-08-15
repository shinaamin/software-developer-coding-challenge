package com.traderev.carauctionsystem.controller;

import com.traderev.carauctionsystem.model.Bid;
import com.traderev.carauctionsystem.model.User;
import com.traderev.carauctionsystem.service.BidService;
import com.traderev.carauctionsystem.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

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

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private BidService bidService;

    @ApiOperation(
            value = "Create user.")
    @RequestMapping(method = POST)
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        logger.debug("Executing addUser method in UserController class");
        User usr = userService.saveUser(user);
        return new ResponseEntity(usr, HttpStatus.CREATED);
    }

    @ApiOperation(
            value = "Update user.")
    @RequestMapping(method = PUT, value = "/{userId}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user, @NotNull @PathVariable Long userId) {
        logger.debug("Executing updateUser method in UserController class");
        User usr = userService.updateUser(user, userId);
        return new ResponseEntity(usr, HttpStatus.OK);
    }


    @ApiOperation(
            value = "Get all users.")
    @RequestMapping(method = GET)
    public ResponseEntity<List<User>> findUsers() {
        logger.debug("Executing findUsers method in UserController class");
        List<User> list = userService.findAllUsers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "Get user.")
    @RequestMapping(method = GET, value = "/{userId}")
    public ResponseEntity<User> findUserById(@NotNull @PathVariable("userId") Long userId) {
        logger.debug("Executing findUserById method in UserController class");
        User user = userService.findUserById(userId);
        if(user != null)
        {
            return new ResponseEntity(user, HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(
            value = "Delete user.")
    @RequestMapping(method = DELETE, value = "/{userId}")
    public ResponseEntity deleteUserById(@NotNull @PathVariable("userId") Long userId) {
        logger.debug("Executing deleteUserById method in UserController class");
        userService.deleteUserById(userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "Get all bids of user.")
    @RequestMapping(method = GET, value = "/{userId}/bids")
    public ResponseEntity<List<Bid>> findUserBids(@NotNull @PathVariable("userId") Long userId) {
        logger.debug("Executing findUserBids method in UserController class");
        List<Bid> bidList = bidService.findUserBids(userId);
        if(!CollectionUtils.isEmpty(bidList))
        {
            return new ResponseEntity(bidList, HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Get latest user bid for specific car.")
    @RequestMapping(method = GET, value = "/{userId}/bid")
    public ResponseEntity<List<Bid>> findUserBidOnCar(@NotNull @PathVariable("userId") Long userId,
                                                      @NotNull @RequestParam("carId") Long carId) {
        logger.debug("Executing findUserBidOnCar method in UserController class");
        Bid bidObj = bidService.findUserBidOnCar(userId, carId);
        if(bidObj != null)
        {
            return new ResponseEntity(bidObj, HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
