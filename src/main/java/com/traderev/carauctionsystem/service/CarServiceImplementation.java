package com.traderev.carauctionsystem.service;

import com.google.common.base.Preconditions;
import com.traderev.carauctionsystem.exception.ResourceNotFoundException;
import com.traderev.carauctionsystem.model.Car;
import com.traderev.carauctionsystem.repo.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImplementation implements CarService {

    private static final Logger logger = LoggerFactory.getLogger(CarServiceImplementation.class);

    @Autowired
    private CarRepository carRepository;

    @Override
    public Car saveCar(Car car) {
        logger.debug("Executing saveCar method in CarServiceImplementation class");
         return carRepository.save(car);
    }

    @Override
    public Car updateCar(Car car, Long carId) {
        logger.debug("Executing updateCar method in CarServiceImplementation class");
        Preconditions.checkArgument(car.getCarId() == carId, "Car Id does not match");
        Car carObj = findCarById(carId);
        if (carObj != null) {
            car.setCarId(carId);
            carRepository.save(car);
            return car;
        } else {
            throw new ResourceNotFoundException(carId.toString(), "car not found");
        }
    }

    @Override
    public List<Car> findAllCars() {
        logger.debug("Executing findAllCars method in CarServiceImplementation class");
        List<Car> carList = new ArrayList<>();
        carRepository.findAll().forEach(e -> carList.add(e));
        return carList;
    }

    @Override
    public Car findCarById(Long carId) {
        logger.debug("Executing findCarById method in CarServiceImplementation class");
        Optional<Car> car = carRepository.findById(carId);
        try {
            return car.get();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public void deleteCarById(Long carId) {
        logger.debug("Executing deleteCarById method in CarServiceImplementation class");
        boolean carExists = checkCarExistsById(carId);
        if (carExists) {
            carRepository.deleteById(carId);
        }
    }

    @Override
    public boolean checkCarExistsById(Long id) {
        logger.debug("Executing checkCarExistsById method in CarServiceImplementation class");
        return carRepository.existsById(id);
    }

}
