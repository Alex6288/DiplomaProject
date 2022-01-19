package com.example.diploma.controller;

import com.example.diploma.entity.*;
import com.example.diploma.repas.*;
import com.example.diploma.service.HomeServiceImpl;
import com.example.diploma.service.MainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = {"","/user"})
public class HomeController {

    @Autowired
    HomeServiceImpl homeService;
    @Autowired
    MainServiceImpl mainService;

    @GetMapping("/home")
    public String getHomePage(Model model) {
        model = mainService.addCategoriesOnPage(model);
        model = mainService.addBrandLogoImg(model);

        Page<Product> page = homeService.findFirstTopProducts();
        List<Product> productList = page.getContent();
        model.addAttribute("popularProduct", productList);

        return "home";
    }

}
