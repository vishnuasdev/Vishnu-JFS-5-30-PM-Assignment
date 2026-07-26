package com.example.mvcwithoutspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/info")
    public String getStudentInfo(Model model) {
        model.addAttribute("name", "Vishnu");
        model.addAttribute("age", 20);
        return "info";
    }
}