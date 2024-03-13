package ru.bulanov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bulanov.models.Mood;
import ru.bulanov.models.Person;
import ru.bulanov.repositories.PeopleRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }
    public List<Person> findAll(){
        return peopleRepository.findAll();
    }
    public Person findById(int id){
        Optional<Person> person = peopleRepository.findById(id);
        return person.orElse(null);
    }
    public Optional<Person> findByEmail(String email){
        return peopleRepository.findByEmail(email);
    }
    @Transactional
    public void save(Person person){
        person.setMood(Mood.HAPPY);
        person.setCreateAt(new Date());
        peopleRepository.save(person);
    }
    @Transactional
    public void update(int id, Person updatePerson){
        updatePerson.setId(id);
        peopleRepository.save(updatePerson);
    }
    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }

}
