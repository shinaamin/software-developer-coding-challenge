package com.traderev.carauctionsystem.repo;

import com.traderev.carauctionsystem.model.Bid;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
/**
 * Crud Repository class for Bid entity operations.
 *
 * @author SHINA.
 * @version 1.0
 * @since 2018-08-10
 */
public interface BidRepository extends CrudRepository<Bid, Long> {
    Bid findByCarIdAndUserId(Long carId, Long UserId);

    List<Bid> findByUserIdOrderByCreationDate(Long userId);

    List<Bid> findByCarIdOrderByBidAmountDesc(Long carId);

}
