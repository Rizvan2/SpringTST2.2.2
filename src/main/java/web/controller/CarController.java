package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarServiceImp;

import java.util.List;

@Controller
public class CarController {
    private final CarServiceImp carServiceImp;

    @Autowired
    public CarController(CarServiceImp carService) {
        this.carServiceImp = carService;
    }

    @GetMapping(value = "/cars", produces = "text/html;charset=UTF-8")
    public String printCars(@RequestParam(value = "count", defaultValue = "5") int count, ModelMap model) {
        List<Car> cars = carServiceImp.getCars(count);
        model.addAttribute("cars", cars);
        return "cars";
    }
}
