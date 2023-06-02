package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private Session session;
    public UserDaoHibernateImpl() {
    }
    @Override
    public void createUsersTable() {

        session = Util.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        transaction.commit();
        session.close();

    }
    @Override
    public void dropUsersTable() {

        session = Util.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.createNativeQuery("DROP TABLE IF EXISTS Users").executeUpdate();
        transaction.commit();
        session.close();
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {

        session = Util.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
        session.close();
    }
    @Override
    public void removeUserById(long id) {

        session = Util.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.get(User.class,id));
        transaction.commit();
        session.close();

    }
    @Override
    public List<User> getAllUsers() {
        session = Util.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List <User> all = session.createNativeQuery("SELECT * FROM Users", User.class).list();
        transaction.commit();
        session.close();
        return all;
    }

    @Override
    public void cleanUsersTable() {
        session = Util.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.createNativeQuery("TRUNCATE TABLE Users").executeUpdate();
        transaction.commit();
        session.close();

    }
}



