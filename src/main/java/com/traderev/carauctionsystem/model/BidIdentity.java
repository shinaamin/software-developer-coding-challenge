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

    private int carId;

    private int userId;

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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
