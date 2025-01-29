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

    @Autowired
    private UserDao userDao;
    @Autowired
    private LocalSessionFactoryBean getSessionFactory;

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public User getCarUser(String model, Long series) {
        Session session = getSessionFactory.getObject().getCurrentSession();
        String hql = "SELECT u FROM User u JOIN u.car c WHERE c.model = :model AND c.series = :series";
        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("model", model);
        query.setParameter("series", series);
        return query.uniqueResult();
    }


}
