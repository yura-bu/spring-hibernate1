package ru.bulanov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bulanov.DAO.PeopleDAO;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleDAO peopleDAO;

    public PeopleController(PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }
    @GetMapping
    public String index(Model model){
        model.addAttribute("people", peopleDAO.index());
        return "person/index";
    }

}
