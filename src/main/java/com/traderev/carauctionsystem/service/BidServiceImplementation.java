package com.traderev.carauctionsystem.service;


import com.google.common.base.Preconditions;
import com.traderev.carauctionsystem.exception.ResourceNotFoundException;
import com.traderev.carauctionsystem.model.Bid;
import com.traderev.carauctionsystem.model.Car;
import com.traderev.carauctionsystem.repo.BidRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author SHINA.
 * @version 1.0
 * @since 2018-08-11
 */
@Service
public class BidServiceImplementation implements BidService {

    private static final Logger logger = LoggerFactory.getLogger(BidServiceImplementation.class);

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Override
    public Bid saveBid(Bid bid) {
        logger.debug("Executing saveBid method in BidServiceImplementation class");
        //verify user exists
        Preconditions.checkArgument(userService.checkUserExistsById(bid.getUserId()), "user does not exists");
        //verify user exists
        Preconditions.checkArgument(carService.checkCarExistsById(bid.getCarId()), "car does not exists");
        // verify bidAmount is greater than or equal to car minimum bid amount
        Car car = carService.findCarById(bid.getCarId());
        if (car != null) {
            BigDecimal minBidAmount = car.getMinimumBidAmount();
            Preconditions.checkArgument(bid.getBidAmount().compareTo(minBidAmount) >= 0, "Bidding amount should be greater than or equal to " + minBidAmount);

            Date date = new Date();
            bid.setCreationDate(date);
            return bidRepository.save(bid);
        } else {
            return null;
        }
    }

    @Override
    public Bid updateBid(Bid bid, Long userId, Long carId) {
        logger.debug("Executing updateBid method in BidServiceImplementation class");
        Preconditions.checkArgument(userService.checkUserExistsById(userId), "user id does not exist");
        Preconditions.checkArgument(carService.checkCarExistsById(carId), "car id does not exist");

        Bid bidObj = bidRepository.findByCarIdAndUserId(carId, userId);
        if (bidObj != null) {
            bidRepository.save(bid);
            return bid;
        } else {
            throw new ResourceNotFoundException("bid not found");
        }
    }

    @Override
    public List<Bid> findAllBids() {
        logger.debug("Executing findAllBids method in BidServiceImplementation class");
        List<Bid> bidList = new ArrayList<>();
        bidRepository.findAll().forEach(e -> bidList.add(e));
        return bidList;
    }

    @Override
    public Bid findBid(Long userId, Long carId)
    {
        logger.debug("Executing findBid method in BidServiceImplementation class");
        return bidRepository.findByCarIdAndUserId(carId, userId);
    }

    @Override
    public List<Bid> findCarBids(Long carId)
    {
        logger.debug("Executing findCarBids method in BidServiceImplementation class");
        return bidRepository.findByCarIdOrderByBidAmountDesc(carId);
    }

    @Override
    public List<Bid> findUserBids(Long userId) {
        logger.debug("Executing findUserBids method in BidServiceImplementation class");
        return bidRepository.findByUserIdOrderByCreationDate(userId);
    }

    @Override
    public Bid findCarWinningBid(Long carId) {
        logger.debug("Executing findCarWinningBid method in BidServiceImplementation class");
        List<Bid> bidList = bidRepository.findByCarIdOrderByBidAmountDesc(carId);
        if (!CollectionUtils.isEmpty(bidList)) {
            return bidList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Bid findUserBidOnCar(Long userId, Long carId) {
        logger.debug("Executing findUserBidOnCar method in BidServiceImplementation class");
        //verify user exists
        Preconditions.checkArgument(userService.checkUserExistsById(userId), "user does not exists");
        //verify user exists
        Preconditions.checkArgument(carService.checkCarExistsById(carId), "car does not exists");

        return bidRepository.findByCarIdAndUserId(carId, userId);
    }

}
