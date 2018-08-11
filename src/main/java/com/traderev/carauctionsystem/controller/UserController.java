package com.traderev.carauctionsystem.controller;

import com.traderev.carauctionsystem.model.Bid;
import com.traderev.carauctionsystem.model.User;
import com.traderev.carauctionsystem.service.BidService;
import com.traderev.carauctionsystem.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    @Autowired
    private UserService userService;

    @Autowired
    private BidService bidService;

    @ApiOperation(
            value = "Create user.",
            notes = "Not available.")
    @RequestMapping(method = POST)
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        User usr = userService.saveUser(user);
        return new ResponseEntity(usr, HttpStatus.CREATED);
    }

    @ApiOperation(
            value = "Update user.",
            notes = "Not available.")
    @RequestMapping(method = PUT, value = "/{userId}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user, @PathVariable Long userId) {
        User usr = userService.updateUser(user, userId);
        return new ResponseEntity(usr, HttpStatus.OK);
    }


    @ApiOperation(
            value = "get all users.",
            notes = "Not available.")
    @RequestMapping(method = GET)
    public ResponseEntity<List<User>> getUsers() {
        List<User> list = userService.getAllUsers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation(
            value = "get car",
            notes = "Not available.")
    @RequestMapping(method = GET, value = "/{userId}")
    public ResponseEntity<User> findUserById(@PathVariable("userId") Long userId) {
        User user = userService.getUserById(userId);
        if(user != null)
        {
            return new ResponseEntity(user, HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(
            value = "delete user",
            notes = "Not available.")
    @RequestMapping(method = DELETE, value = "/{userId}")
    public ResponseEntity deleteUserById(@PathVariable("userId") Long userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(
            value = "get all bids of user",
            notes = "Not available.")
    @RequestMapping(method = GET, value = "/{userId}/bids")
    public ResponseEntity<List<Bid>> findUserBids(@NotNull @PathVariable("userId") Long userId) {
        List<Bid> bidList = bidService.findUserBids(userId);
        if(!CollectionUtils.isEmpty(bidList))
        {
            return new ResponseEntity(bidList, HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(
            value = "get all bids of user",
            notes = "Not available.")
    @RequestMapping(method = GET, value = "/{userId}/bid")
    public ResponseEntity<List<Bid>> findUserBidOnCar(@NotNull @PathVariable("userId") Long userId,
                                                      @NotNull @RequestParam("carId") Long carId) {
        Bid bidObj = bidService.findUserBidOnCar(userId, carId);
        if(bidObj != null)
        {
            return new ResponseEntity(bidObj, HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
