package com.traderev.carauctionsystem.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * BidIdentity class represent Bid class composite key information.
 *
 * @author SHINA.
 * @version 1.0
 * @since 2018-08-07
 */
public class BidIdentity implements Serializable {
    private Long carId;

    private Long userId;

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidIdentity that = (BidIdentity) o;
        return carId == that.carId &&
                userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, userId);
    }
}
