package hiber;

import hiber.dao.UserDaoImp;
import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

      userService.add(new User("User 7", "Lastname7", "user7@mail.ru", new Car("Series7", 7L)));
        userService.add(new User("User 2", "Lastname2", "user2@mail.ru", new Car("Series2", 2L)));
        userService.add(new User("User 3", "Lastname3", "user3@mail.ru", new Car("Series3", 3L)));
        userService.add(new User("User 4", "Lastname4", "user4@mail.ru", new Car("Series4", 4L)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
        }
        System.out.println("Car = " + userService.getCarUser("Series7", 7L).getCar().toString());
        context.close();
    }
}
