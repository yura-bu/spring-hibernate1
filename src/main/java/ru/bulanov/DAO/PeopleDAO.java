package ru.bulanov.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.bulanov.models.Person;

import java.util.List;
import java.util.Optional;

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
    @Transactional
    public Person show(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }
    @Transactional
    public void createPerson(Person person){
        Session session = sessionFactory.getCurrentSession();
        session.persist(person);
    }
    @Transactional
    public void changePerson(int id, Person person){
        Session session = sessionFactory.getCurrentSession();
        Person personChange = session.get(Person.class, id);
        personChange.setName(person.getName());
        personChange.setAge(person.getAge());
        session.refresh(person);
    }
    @Transactional
    public Optional<Person> show(String name){
        Session session = sessionFactory.getCurrentSession();
       return session.createQuery("select p from Person p where name=:parameterName", Person.class)
               .setParameter("parameterName", name)
               .getResultList().stream().findAny();
    }
    @Transactional
    public void delete(int id){
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        session.remove(person);
    }

}
