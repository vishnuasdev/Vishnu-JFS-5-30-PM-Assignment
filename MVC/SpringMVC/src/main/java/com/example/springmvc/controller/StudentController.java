package com.example.springmvc.controller;

import com.example.springmvc.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        return "index";
    }

    @PostMapping("/calc")
    public String calculateResult(@ModelAttribute("student") Student student, Model model) {
        double total = student.getMark1() + student.getMark2() +
                student.getMark3() + student.getMark4() +
                student.getMark5();

        double average = total / 5.0;

        model.addAttribute("total", (int) total);
        model.addAttribute("average", (int) average);

        return "result";
    }

    @GetMapping("/profile")
    public ModelAndView getStudentProfile() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "Vishnu");
        mav.addObject("course", "Java Full Stack");
        mav.setViewName("profile");

        return mav;
    }
}