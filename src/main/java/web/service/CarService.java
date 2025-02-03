package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.Car;
import web.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getCars(int count) {
        List<Car> carList = carRepository.findAll();
        if (count >= carList.size()) {
            return carList;
        }
        return carList.subList(0, count);
    }

}
