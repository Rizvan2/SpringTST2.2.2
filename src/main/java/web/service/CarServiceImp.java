package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.Car;
import web.dao.CarDaoImp;

import java.util.List;

@Service
public class CarServiceImp implements CarService {
    private final CarDaoImp carDaoImp;

    @Autowired
    public CarServiceImp(CarDaoImp carDaoImp) {
        this.carDaoImp = carDaoImp;
    }

    @Override
    public List<Car> getCars(int count) {
        List<Car> carList = carDaoImp.findAll();
        if (count >= carList.size()) {
            return carList;
        }
        return carList.subList(0, count);
    }

}
