package com.traderev.carauctionsystem.service;


import com.google.common.base.Preconditions;
import com.traderev.carauctionsystem.exception.ResourceNotFoundException;
import com.traderev.carauctionsystem.model.Bid;
import com.traderev.carauctionsystem.model.Car;
import com.traderev.carauctionsystem.repo.BidRepository;
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

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Override
    public Bid saveBid(Bid bid) {
        //verify user exists
        Preconditions.checkArgument(userService.checkUserExistsById(bid.getUserId()));
        // verify bidAmount is greater than or equal to car minimum bid amount
        Car car = carService.getCarById(bid.getCarId());
        if(car != null) {
            BigDecimal minBidAmount = car.getMinimumBidAmount();
            Preconditions.checkArgument(bid.getBidAmount().compareTo(minBidAmount) >= 0 , "Bidding amount should be greater than or equal to " + minBidAmount);

            Date date = new Date();
            bid.setCreationDate(date);
            return bidRepository.save(bid);
        }
        else
        {
            return  null;
        }
    }

    @Override
    public Bid updateBid(Bid bid, Long userId, Long carId) {
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
    public List<Bid> getAllBids() {
        List<Bid> bidList = new ArrayList<>();
        bidRepository.findAll().forEach(e -> bidList.add(e));
        return bidList;
    }

    @Override
    public Bid findBid(Long userId, Long carId) {
        return bidRepository.findByCarIdAndUserId(carId, userId);
    }

    @Override
    public List<Bid> findCarBids(Long carId) {
        return bidRepository.findByCarIdOrderByBidAmountDesc(carId);
    }

    @Override
    public List<Bid> findUserBids(Long userId) {
        return bidRepository.findByUserIdOrderByCreationDate(userId);
    }

    @Override
    public Bid findCarWinningBid(Long carId) {
        List<Bid> bidList = bidRepository.findByCarIdOrderByBidAmountDesc(carId);
        if (!CollectionUtils.isEmpty(bidList)) {
            return bidList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Bid findUserBidOnCar(Long userId, Long carId) {
        return bidRepository.findByCarIdAndUserId(carId, userId);
    }

}
