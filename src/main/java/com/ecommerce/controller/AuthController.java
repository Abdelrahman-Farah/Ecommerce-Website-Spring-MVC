package com.ecommerce.controller;

import com.ecommerce.entity.User;
import com.ecommerce.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthController {

    // TODO: init binder
    //make the field final [abdelkarim]
    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login-page";
    }

    @GetMapping("/register")
    public String registrationPage(Model theModel) {
        theModel.addAttribute("user", new User());
        return "auth/registration-page";
    }

    @PostMapping("/processRegistrationForm")
    //use DTOs instead of the entities [abdelkarim]
    public String processRegistration(
            @Valid @ModelAttribute("user") User theUser,
            BindingResult theBindingResult,
            HttpSession session,
            Model theModel
    ){

        if (theBindingResult.hasErrors()){
            return "auth/registration-page";
        }

        //No need to write any logic into th controller layer [abdelkarim]
        String email = theUser.getEmail();
        User temp = userService.findUserByEmail(email);
        if (temp != null){
            // TODO: Display email Error
            theModel.addAttribute("emailError", "This email is already registered.");
            return "auth/registration-page";
        }

        userService.save(theUser);

        session.setAttribute("user", theUser);

        return "redirect:/";
    }
}
