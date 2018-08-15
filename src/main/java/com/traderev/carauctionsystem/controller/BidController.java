package com.traderev.carauctionsystem.controller;

import com.traderev.carauctionsystem.model.Bid;
import com.traderev.carauctionsystem.service.BidService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * Controller class to handle Bid related web requests.
 *
 * @author SHINA.
 * @version 1.0
 * @since 2018-08-10
 */

@RestController
@RequestMapping("/bids")
@Api(value = "bids", description = "All bids related info", tags = "bids")
public class BidController {
    private static final Logger logger = LoggerFactory.getLogger(BidController.class);

    @Autowired
    private BidService bidService;

    @ApiOperation(value = "Create or update bid of car."
            , notes = "If bid already exists for car by user then it will just update existing bid else it will create new bid in database.")
    @RequestMapping(method = {POST, PUT})
    public ResponseEntity addOrUpdateBid(@Valid @RequestBody Bid bid) {
        logger.debug("Executing addOrUpdateBid method in BidController class");
        //If bid already exists for car by user then it will just update existing bid else it will create new bid in database.
        Bid bidObj = bidService.saveBid(bid);
        if (bidObj != null) {
            return new ResponseEntity(bidObj, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Get all bids available in system.")
    @RequestMapping(method = GET)
    public ResponseEntity<List<Bid>> getBids() {
        logger.debug("Executing getBids method in BidController class");
        List<Bid> list = bidService.findAllBids();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
