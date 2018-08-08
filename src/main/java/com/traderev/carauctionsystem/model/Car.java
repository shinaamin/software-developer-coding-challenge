package com.traderev.carauctionsystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Car is entity class to store Car related information.
 *
 * @author SHINA.
 * @version 1.0
 * @since 2018-08-07
 */

@Entity
@Table(name = "cars")
public class Car implements Serializable {

   public enum Color {
        RED,
        WHITE,
        BLUE,
        GREEN,
        YELLOW,
        BLACK,
        GREY,
        GOLD
    }

   public enum Type {
        HATCHBACK,
        SEDAN,
        MPV,
        SUV
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "car_id")
    private Integer carId;

    @NotBlank
    @Size(min = 2, max = 20)
    @Column(name = "model_num", unique = true)
    private String modelNumber;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @NotNull
    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private Color color;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "buildYear")
    private Date buildYear;

    @NotNull
    @Size(max = 100)
    @Column(name = "manufacturer_name")
    private String manufacturerName;

    @NotNull
    @Column(name = "min_amt")
    private Integer minimumBidAmount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    private List<Bid> bidList;

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Date getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(Date buildYear) {
        this.buildYear = buildYear;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public Integer getMinimumBidAmount() {
        return minimumBidAmount;
    }

    public void setMinimumBidAmount(Integer minimumBidAmount) {
        this.minimumBidAmount = minimumBidAmount;
    }

    public List<Bid> getBidList() {
        return bidList;
    }

    public void setBidList(List<Bid> bidList) {
        this.bidList = bidList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return carId == car.carId &&
                Objects.equals(modelNumber, car.modelNumber) &&
                type == car.type &&
                Objects.equals(buildYear, car.buildYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, modelNumber, type, buildYear);
    }
}
