package com.springproject.Spring.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MVCController {
    @GetMapping("/mvc/{username}")
    public String getModelUsername(@PathVariable("username") String username,Model model){
        model.addAttribute("username","MVC - Your name is: "+ username);
        return "home.html";
    }
    @GetMapping("/mvc")
    public String getModelMessage(@RequestParam("message") String message,Model model){
        model.addAttribute("message","MVC -Your message is: "+ message);
        return "home.html";
    }
    @GetMapping("/mvc/{username}/")
    public String getModelUsernameMessage(
            @PathVariable("username") String username,
            @RequestParam("message") String message,
            Model model) {

        model.addAttribute("username", "MVC -Your entered name is: " + username);
        model.addAttribute("message", "MVC -Your message is: " + message);

        return "home.html";
    }
}
