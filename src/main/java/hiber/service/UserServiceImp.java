package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;
    private final LocalSessionFactoryBean getSessionFactory;

    @Autowired
    public UserServiceImp(UserDao userDao, LocalSessionFactoryBean getSessionFactory) {
        this.userDao = userDao;
        this.getSessionFactory = getSessionFactory;
    }

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getCarUser(String model, Long series) {
        return userDao.getCarUser(model, series);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

}
