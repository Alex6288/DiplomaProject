package com.example.diploma.service;

import com.example.diploma.entity.News;
import com.example.diploma.entity.ShopCart;
import com.example.diploma.repas.IBrands;
import com.example.diploma.repas.ICategory;
import com.example.diploma.repas.INewsRep;
import com.example.diploma.repas.IProductRep;
import com.example.diploma.service.interfaces.IHomePageService;
import com.example.diploma.service.interfaces.IMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainServiceImpl implements IMainService {

    @Autowired
    private ICategory categoryRep;
    @Autowired
    private IProductRep productRep;
    @Autowired
    private IBrands brandRep;
    @Autowired
    ShopCart shopCart;
    @Autowired
    IHomePageService homeService;

    @Override
    public Model addCategoriesOnPage(Model model) {
        model.addAttribute("categoryList", categoryRep.findDistinctCategoryName());
        model.addAttribute("numProductShopCart", shopCart.getShopCartItemSet().size());

        Page<News> pageNews = homeService.findTopNews();
        List<News> newsList = pageNews.getContent();
        model.addAttribute("newses", newsList);
        return model;
    }

    @Override
    public Model addBrandNameOnPage(Model model) {
        model.addAttribute("brandList", brandRep.getAllDistinctBrandName());
        return model;
    }

    @Override
    public Model addBrandLogoImg(Model model) {
        List<String> paths = new ArrayList<>();
        for(String path: brandRep.getAllLogoImgPath()) {
            path = path.replace(AdminServiceImpl.UPLOADED_BRAND_FOLDER, AdminServiceImpl.LOAD_LOGO_BRAND_ON_PAGE_FOLDER);
            System.out.println(path);
            paths.add(path);
        }
        model.addAttribute("brandsLogos", paths);
        return model;
    }
}
