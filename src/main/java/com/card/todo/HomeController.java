package com.card.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    TodoSet ts;

    @RequestMapping("/")
    public String showList(Model model) {
        model.addAttribute("todos", ts.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String todoAdd(Model model) {
        model.addAttribute("todo", new Todo());
        return "todoform";
    }

    @PostMapping("/submit")
    public String todoSubmit(@Valid Todo todo, BindingResult br) {
        if (br.hasErrors()) {
            return "todoform";
        }
        ts.save(todo);
        return "redirect:/";
    }

    @RequestMapping("/view/{id}")
    public String viewTask(@PathVariable("id") long id, Model model) {
        model.addAttribute("todo", ts.findById(id).get());
        model.addAttribute("todos", ts.findAll());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateTask(@PathVariable("id") long id, Model model) {
        model.addAttribute("todo", ts.findById(id).get());
        return "todoform";
    }

    @RequestMapping("/complete/{id}")
    public String finishTask(@PathVariable("id") long id) {
        Todo td = ts.findById(id).get();
        td.setCompleted(true);
        ts.save(td);
        return "redirect:/";
    }
}
