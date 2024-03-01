package ru.bulanov.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.bulanov.models.Person;

import java.util.List;

@Component
public class PeopleDAO {
    private final SessionFactory sessionFactory;
    @Autowired
    public PeopleDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Person> index(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT p From Person p", Person.class).getResultList();
    }

}
