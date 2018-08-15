package com.traderev.carauctionsystem.controller;

import com.traderev.carauctionsystem.model.Bid;
import com.traderev.carauctionsystem.model.Car;
import com.traderev.carauctionsystem.service.BidService;
import com.traderev.carauctionsystem.service.BidServiceImplementation;
import com.traderev.carauctionsystem.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CarService carService;

    @Autowired
    private BidService bidService;

    @ApiOperation(value = "Create car.")
    @RequestMapping(method = POST)
    public ResponseEntity<Car> addCar(@Valid @RequestBody Car car) {
        logger.debug("Executing addCar method in CarController class");
        Car carObj = carService.saveCar(car);
        return new ResponseEntity(carObj, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update car.")
    @RequestMapping(method = PUT, value = "/{carId}")
    public ResponseEntity<Car> updateCar(@Valid @RequestBody Car car,  @NotNull @PathVariable Long carId) {
        logger.debug("Executing updateCar method in CarController class");
        Car carObj = carService.updateCar(car, carId);
        return new ResponseEntity(carObj, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all cars details.")
    @RequestMapping(method = GET)
    public ResponseEntity<List<Car>> findCars() {
        logger.debug("Executing findCars method in CarController class");
        List<Car> list = carService.findAllCars();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "Get car details.")
    @RequestMapping(method = GET, value = "/{carId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> findCarById(@NotNull @PathVariable("carId") Long carId) {
        logger.debug("Executing findCarById method in CarController class");
        Car car = carService.findCarById(carId);
        if (car != null) {
            return new ResponseEntity(car, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Delete car.")
    @RequestMapping(method = DELETE, value = "/{carId}")
    public ResponseEntity deleteCarById(@NotNull @PathVariable("carId") Long carId) {
        logger.debug("Executing deleteCarById method in CarController class");
        carService.deleteCarById(carId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "Get all bids of car.")
    @RequestMapping(method = GET, value = "/{carId}/bids")
    public ResponseEntity<List<Bid>> findCarBidHistory(@NotNull @PathVariable("carId") Long carId) {
        logger.debug("Executing findCarBidHistory method in CarController class");
        List<Bid> bidList = bidService.findCarBids(carId);
        if (!CollectionUtils.isEmpty(bidList)) {
            return new ResponseEntity(bidList, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(
            value = "Get winning bid of car.")
    @RequestMapping(method = GET, value = "/{carId}/winningBid")
    public ResponseEntity<Bid> findCarWinningBid(@NotNull@PathVariable("carId") Long carId) {
        logger.debug("Executing findCarWinningBid method in CarController class");
        Bid bidObj = bidService.findCarWinningBid(carId);
        if (bidObj == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(bidObj, HttpStatus.OK);
        }
    }
}
