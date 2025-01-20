package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final Configuration config = Util.getHibernateConfiguration();
    private final SessionFactory sessionFactory = config.buildSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS users ("
                    + "id BIGINT AUTO_INCREMENT PRIMARY KEY, "
                    + "name VARCHAR(45), "
                    + "lastName VARCHAR(45), "
                    + "age TINYINT) ENGINE=MyISAM").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица успешно создана");
        } catch (HibernateException e) {
            throw new RuntimeException("Не удалось создать таблицу пользователей", e);
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица удалена");
        } catch (HibernateException e) {
            throw new RuntimeException("Не удалось удалить таблицу пользователей", e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.createQuery("FROM User WHERE name = :name AND lastName = :lastName AND age = :age", User.class)
                    .setParameter("name", name)
                    .setParameter("lastName", lastName)
                    .setParameter("age", age)
                    .uniqueResult();
            if (user == null) {
                session.save(new User(name, lastName, age));
                System.out.println("Пользователь с именем " + name + " создан");
            } else {
                System.out.println("Такой пользователь уже существует");
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException("Не удалось сохранить пользователя", e);
        }
    }

    @Override
    public void removeUserById(int id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                System.out.println("Пользаватель удален");
            } else {
                System.out.println("Пользователь с таким id не существует");
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException("Не удалось удалить пользователя по id: " + id, e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<User> users = session.createQuery("FROM User", User.class).getResultList();
            session.getTransaction().commit();
            return users;
        } catch (HibernateException e) {
            throw new RuntimeException("Не удалось получить всех пользователей", e);
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица очищена");
        } catch (HibernateException e) {
            throw new RuntimeException("Не удалось очистить таблицу", e);
        }
    }
}
