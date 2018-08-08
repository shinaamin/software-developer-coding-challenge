package com.traderev.carauctionsystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Bid is an entity class to store bid related information.
 *
 * @author SHINA.
 * @version 1.0
 * @since 2018-08-07
 */

@Entity
@Table(name = "bids")
@IdClass(BidIdentity.class)
public class Bid implements Serializable {

    @Id
    @Column(name = "car_id")
    private int carId;

    @Id
    @Column(name = "user_id")
    private int userId;

    @NotNull
    @Column(name = "price")
    private Integer price;

    @Column(name = "creation_date")
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "car_id", insertable = false, updatable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bid bid = (Bid) o;
        return Double.compare(bid.price, price) == 0 &&
                Objects.equals(creationDate, bid.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, creationDate);
    }
}
