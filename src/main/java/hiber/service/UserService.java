package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    User getCarUser(String model, Long series);

    List<User> listUsers();

}
