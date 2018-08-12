package com.traderev.carauctionsystem.controller;

import com.google.common.base.Preconditions;
import com.traderev.carauctionsystem.model.Bid;
import com.traderev.carauctionsystem.model.Car;
import com.traderev.carauctionsystem.model.User;
import com.traderev.carauctionsystem.service.BidService;
import com.traderev.carauctionsystem.service.CarService;
import com.traderev.carauctionsystem.model.Car;
import com.traderev.carauctionsystem.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Controller class to handle car related web requests.
 *
 * @author SHINA.
 * @version 1.0
 * @since 2018-08-08
 */

@RestController
@RequestMapping("/cars")
@Api(value = "car", description = "All cars related info", tags = "car")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private BidService bidService;

    @ApiOperation(value = "Create car.")
    @RequestMapping(method = POST)
    public ResponseEntity<Car> addCar(@Valid @RequestBody Car car) {
        Car carObj = carService.saveCar(car);
        return new ResponseEntity(carObj, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update car.")
    @RequestMapping(method = PUT, value = "/{carId}")
    public ResponseEntity<Car> updateCar(@Valid @RequestBody Car car, @PathVariable Long carId) {
        Car carObj = carService.updateCar(car, carId);
        return new ResponseEntity(carObj, HttpStatus.OK);
    }

    @ApiOperation(value = "get all cars.")
    @RequestMapping(method = GET)
    public ResponseEntity<List<Car>> getCars() {
        List<Car> list = carService.getAllCars();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation(
            value = "get car")
    @RequestMapping(method = GET, value = "/{carId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> findCarById(@PathVariable("carId") Long carId) {
        Car car = carService.getCarById(carId);
        if (car != null) {
            return new ResponseEntity(car, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(
            value = "delete car",
            notes = "Not available.")
    @RequestMapping(method = DELETE, value = "/{carId}")
    public ResponseEntity deleteCarById(@PathVariable("carId") Long carId) {
        carService.deleteCarById(carId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(
            value = "get all bids of car",
            notes = "Not available.")
    @RequestMapping(method = GET, value = "/{carId}/bids")
    public ResponseEntity<List<Bid>> findCarBidHistory(@NotNull @PathVariable("carId") Long carId) {
        List<Bid> bidList = bidService.findCarBids(carId);
        if (!CollectionUtils.isEmpty(bidList)) {
            return new ResponseEntity(bidList, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(
            value = "get winning bid of car.",
            notes = "Not available.",
            produces = "application/json")
    @RequestMapping(method = GET, value = "/{carId}/winningBid")
    public ResponseEntity<Bid> findCarWinningBid(@PathVariable("carId") Long carId) {
        Bid bidObj = bidService.findCarWinningBid(carId);
        if (bidObj == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(bidObj, HttpStatus.OK);
        }
    }
}
