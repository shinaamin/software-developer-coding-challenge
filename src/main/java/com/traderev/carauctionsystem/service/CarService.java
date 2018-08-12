package com.traderev.carauctionsystem.service;

import com.traderev.carauctionsystem.model.Car;

import java.util.List;
import java.util.Optional;

/**
 * @author SHINA.
 * @version 1.0
 * @since 2018-08-12
 */
public interface CarService {

    Car saveCar(Car car);

    Car updateCar(Car car, Long carId);

    List<Car> getAllCars();

    Optional<Car> getCarById(Long id);

    void deleteCarById(Long id);

    boolean checkCarExistsById(Long id);

}
