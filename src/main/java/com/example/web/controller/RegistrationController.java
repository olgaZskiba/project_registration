package com.example.web.controller;

import com.example.web.bean.TgUserTable;
import com.example.web.bean.UserRoles;
import com.example.web.dao.repository.TgUserTableDaoWebRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private TgUserTableDaoWebRepository userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(TgUserTable user, Map<String, Object> model) {
        TgUserTable userFromDb = userRepo.findByUserName(user.getUserName());

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(UserRoles.USER));
        userRepo.save(user);

        return "redirect:/login";
    }

}
