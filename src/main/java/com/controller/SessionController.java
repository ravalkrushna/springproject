package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bean.UserBean;
import com.dao.UserDao;

@Controller
public class SessionController {

    @Autowired
    UserDao userDao;

    @GetMapping("/signup")
    public String signup() {
        return "SignUp"; 
    }

    @GetMapping("/")
    public String login() {
        return "Login"; 
    }

    @PostMapping("/saveuser")
    public String saveUser(UserBean userBean, Model model) {
        try {
            userDao.saveUser(userBean); 
            model.addAttribute("message", "Signup successful! Please login.");
            return "Login"; 
        } catch (Exception e) {
            e.printStackTrace(); 
            model.addAttribute("error", "Email already exists or an error occurred!");
            return "SignUp"; 
        }
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email,
                            @RequestParam("password") String password,
                            Model model) {

        UserBean user = userDao.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            model.addAttribute("user", user); 
            return "redirect:/Home"; 
        } else {
            model.addAttribute("error", "Invalid email or password"); 
            return "Login"; 
        }
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "Home";
    }

}
