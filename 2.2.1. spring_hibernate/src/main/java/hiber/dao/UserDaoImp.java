package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserFromCarModelAndSeries(String model, int series) {
        try {
            TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User user where user.usersCar.model = :model and usersCar.series = :series");
            query.setParameter("model", model);
            query.setParameter("series", series);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Пользователь с такой машиной не найден!");
            return null;
        }
    }
}
