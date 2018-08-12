package com.traderev.carauctionsystem.service;

import com.google.common.base.Preconditions;
import com.traderev.carauctionsystem.exception.ResourceNotFoundException;
import com.traderev.carauctionsystem.model.Car;
import com.traderev.carauctionsystem.repo.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author SHINA.
 * @version 1.0
 * @since 2018-08-12
 */
@Service
public class CarServiceImplementation implements CarService {


    @Autowired
    private CarRepository carRepository;

    @Override
    public Car saveCar(Car car) {
         return carRepository.save(car);
    }

    @Override
    public Car updateCar(Car car, Long carId) {
        Preconditions.checkArgument(car.getCarId() == carId, "Car Id does not match");
        Optional<Car> carObj = getCarById(carId);
        if (carObj.isPresent()) {
            car.setCarId(carId);
            carRepository.save(car);
            return car;
        } else {
            throw new ResourceNotFoundException(carId.toString(), "car not found");
        }
    }

    @Override
    public List<Car> getAllCars() {
        List<Car> carList = new ArrayList<>();
        carRepository.findAll().forEach(e -> carList.add(e));
        return carList;
    }

    @Override
    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public boolean checkCarExistsById(Long id) {
        return carRepository.existsById(id);
    }

}
