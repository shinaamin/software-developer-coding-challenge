package com.traderev.carauctionsystem.repo;

import com.traderev.carauctionsystem.model.Car;
import org.springframework.data.repository.CrudRepository;

/**
 * Crud Repository class for Car entity operations.
 *
 * @author SHINA.
 * @version 1.0
 * @since 2018-08-10
 */
public interface CarRepository extends CrudRepository<Car, Long> {
    Car findByModelNumber(String modelNumber);
}
