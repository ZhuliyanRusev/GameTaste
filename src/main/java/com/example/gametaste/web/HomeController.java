package com.example.gametaste.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(HttpSession httpSession, Model model) {

        if (httpSession.getAttribute("user") == null) {
            return "index";
        }
        return "home";
    }
}
