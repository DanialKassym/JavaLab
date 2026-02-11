package com.springproject.Spring.CRUD;

import com.springproject.Spring.Users.UsersRepository;
import com.springproject.Spring.Users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
/*
@Controller
public class Controllers {
    private UserRepository userRepository;

    @Autowired
    public Controllers(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public String list(
            @RequestParam(name="id", required = false) Long id,
            Model model){

        Users form = id != null? userRepository.getById(id) : new Users();

        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("form", form);
        model.addAttribute("editMode", id != null);
        return "users";
    }

    @PostMapping("/user/create/")
    public String CreateUser(@PathVariable("username") String username) {
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(15);
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        Users user = new Users(username,date);
        userRepository.save(user);
        return "redirect:/users";
    }

    @PutMapping("/user/update/{username}")
    public String UpdateUser(@PathVariable("username") String username,@RequestParam("newuser") String newUsername) {
        Users users = userRepository.findByname(username);
        if (users == null){
            return "redirect:/users";
        }
        users.setName(newUsername);
        return "redirect:/users";
    }

    @DeleteMapping("/user/delete/{id}")
    public String DeleteUser(@PathVariable("id") Long id) {
        userRepository.deleteById(id);

        return "redirect:/users";
    }
}*/

@Controller
@RequestMapping("/users")
public class CRUDControllers {

    private final UsersRepository usersRepository;

    @Autowired
    public CRUDControllers(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @GetMapping
    public String getListofUsers(
            @RequestParam(name="id", required = false) Long id,
            Model model){

        Users form = id != null? usersRepository.getById(id) : new Users();

        model.addAttribute("users", usersRepository.findAll());
        model.addAttribute("form", form);
        model.addAttribute("editMode", id != null);
        return "users";
    }

    @PostMapping
    public String createUser(Users user) {
        usersRepository.save(user);
        return "redirect:/users";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable(name="id") Long id, Users userForm) {
        Users userModel = usersRepository.getById(id);
        userModel.setName(userForm.getName());

        usersRepository.save(userModel);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable(name="id") Long id) {
        usersRepository.deleteById(id);
        return "redirect:/users";
    }
}
