package ru.bulanov.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.bulanov.DAO.PeopleDAO;
import ru.bulanov.models.Person;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {
    private final PeopleDAO peopleDAO;
    @Autowired
    public PersonValidator(PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if(peopleDAO.show(person.getName()).isPresent()){
            errors.rejectValue("name", "", "Это имя уже существует");
        }
    }
}
