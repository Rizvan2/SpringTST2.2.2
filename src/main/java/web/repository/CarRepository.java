package web.repository;

import org.springframework.stereotype.Repository;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRepository {
    private final List<Car> cars;

    public CarRepository() {
        cars = new ArrayList<>();
        cars.add(new Car("Toyota", "Camry", 2020));
        cars.add(new Car("Honda", "Accord", 2019));
        cars.add(new Car("Ford", "Mustang", 2021));
        cars.add(new Car("Chevrolet", "Malibu", 2018));
        cars.add(new Car("Nissan", "Altima", 2022));
    }

    public List<Car> findAll() {
        return cars;
    }
}
