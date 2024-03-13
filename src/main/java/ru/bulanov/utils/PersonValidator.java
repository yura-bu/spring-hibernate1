package ru.bulanov.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.bulanov.models.Person;
import ru.bulanov.services.PeopleService;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;


@Component
public class PersonValidator implements Validator {


    private final PeopleService peopleService;
    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Person.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
//        String regex = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$";
//        Pattern pattern = Pattern.compile(regex);
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//        String strDate = formatter.format(person.getDayOfBirth());
        if (peopleService.findByEmail(person.getEmail()).isPresent()) {
            errors.rejectValue("email", "", "Этот email уже существует");
        }
        if (person.getDayOfBirth() == null) {
                System.out.println("Не валидная дата");
                errors.rejectValue("dayOfBirth", "", "Не валидная дата");

            }
        }
}
