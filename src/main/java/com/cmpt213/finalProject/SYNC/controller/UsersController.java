package com.cmpt213.finalProject.SYNC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import com.cmpt213.finalProject.SYNC.models.UserModel;
import com.cmpt213.finalProject.SYNC.service.*;




@Controller
public class UsersController{
    @Autowired
    private UsersService userService;

    @GetMapping("/")
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("user", new UserModel());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("user", new UserModel());
        return "login_page";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserModel userModel) {
        System.out.println("register request" + userModel);
        UserModel registerUser = userService.registerUser(userModel.getLogin(), userModel.getPassword(), userModel.getEmail());
        return registerUser == null ? "error_page" : "redirect:/login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute UserModel userModel, Model model) {
        System.out.println("login request" + userModel);
        UserModel authenticate = userService.authentication(userModel.getLogin(), userModel.getPassword());
        
        if(authenticate != null){
            model.addAttribute("userLogin", userModel.getLogin());
            return "personalAccount";
        }
        else{
            return "error_page";
        }
        // return "redirect:/register?success";
    }
    
    
    
}