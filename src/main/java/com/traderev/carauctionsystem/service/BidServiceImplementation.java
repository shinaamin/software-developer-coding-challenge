package com.traderev.carauctionsystem.service;

import com.google.common.base.Preconditions;
import com.traderev.carauctionsystem.model.Bid;
import com.traderev.carauctionsystem.model.Car;
import com.traderev.carauctionsystem.repo.BidRepository;
import com.traderev.carauctionsystem.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;



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
