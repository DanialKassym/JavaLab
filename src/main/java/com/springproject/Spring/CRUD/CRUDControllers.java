package com.springproject.Spring.CRUD;

import com.springproject.Spring.Users.UsersRepository;
import com.springproject.Spring.Users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
