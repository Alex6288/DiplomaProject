package com.example.diploma.service.interfaces;

import org.springframework.ui.Model;

public interface IMainService {
    Model addCategoriesOnPage(Model model);
    Model addBrandNameOnPage(Model model);
    Model addBrandLogoImg(Model model);
}
