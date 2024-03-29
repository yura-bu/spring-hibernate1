package ru.bulanov.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bulanov.models.Person;
import ru.bulanov.services.PeopleService;
import ru.bulanov.utils.PersonValidator;


@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final PersonValidator personValidator;

    public PeopleController(PeopleService peopleService, PersonValidator personValidator) {
        this.peopleService = peopleService;
        this.personValidator = personValidator;
    }
    @GetMapping
    public String index(Model model){
        model.addAttribute("people", peopleService.findAll());
        return "person/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleService.findById(id));
        return "person/show";
    }
    @GetMapping("/new")
    public String newForm(@ModelAttribute("person") Person person){
        return "person/new";
    }
    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()){
            return "person/new";
        }
       peopleService.save(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleService.findById(id));
        return "person/edit";
    }
    @PatchMapping("/{id}")
    public String changePerson(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()){
            return "person/edit";
        }
        peopleService.update(id, person);

        return "redirect:/people";
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id){
       peopleService.delete(id);
        return "redirect:/people";
    }

}
