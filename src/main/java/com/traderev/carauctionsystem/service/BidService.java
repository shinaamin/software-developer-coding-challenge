package com.traderev.carauctionsystem.service;

import com.traderev.carauctionsystem.model.Bid;

import java.util.List;

/**
 * @author SHINA.
 * @version 1.0
 * @since 2018-08-11
 */
public interface BidService {

    /**
     * This method is used to save Bid details.
     * @param bid  The bid to be saved
     * @return Bid This returns Bid created.
     */
    Bid saveBid(Bid bid);

    /**
     * This method is used to update Bid details.
     * @param userId The user id for which bid is to be updated.
     * @param carId The car id for which bid is to be updated.
     * @param bid  The bid details to be updated.
     * @return Bid This returns updated bid.
     */
    Bid updateBid(Bid bid, Long userId, Long carId);


    /**
     * This method is used to get details of all Bids.
     * @return Bid This returns all the Bids present in system.
     */
    List<Bid> findAllBids();

    /**
     * This method is used to find Bid by using carId and UserId.
     * @param userId The user id for which bid exists.
     * @param carId The car id for which bid exists.
     * @return Bid. This returns Bid.
     */
    Bid findBid(Long userId, Long carId);

    /**
     * This method is used to find all Bids of car.
     * @param carId The car id for which bid exists.
     * @return List<Bid> This returns list of bids.
     */
    List<Bid> findCarBids(Long carId);

    /**
     * This method is used to find all Bids of user.
     * @param userId The user id for which bid exists.
     * @return List<Bid> This returns list of bids.
     */
    List<Bid> findUserBids(Long userId);

    /**
     * This method is used to find current winning bid of car.
     * @param carId The car id for which bid exists.
     * @return Bid This returns Bid.
     */
    Bid findCarWinningBid(Long carId);

    /**
     * This method is used to find user bid on car.
     * @param carId The car id for which bid exists.
     * @param userId The user id for which bid exists.
     * @return Bid This returns Bid.
     */
    Bid findUserBidOnCar(Long userId, Long carId);
}
