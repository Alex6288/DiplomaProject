package com.example.diploma.controller;

import com.example.diploma.controller.entity.ProductShopConst;
import com.example.diploma.entity.Product;
import com.example.diploma.service.entity.ResponseProductInfo;
import com.example.diploma.service.entity.SearchData;
import com.example.diploma.service.MainServiceImpl;
import com.example.diploma.service.ProductServiceImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = {"","/user"})
public class ProductShopController {

    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private MainServiceImpl mainService;


    @GetMapping("/productShop")
    public String viewProductShop(@RequestParam(name = "categoryId") String categoryId,
                                  Model model) {
        model = mainService.addBrandNameOnPage(model);
        model = mainService.addCategoriesOnPage(model);
        model.addAttribute("categoryId", categoryId);
        return "product_shop";
    }

    @PostMapping("/productShop")
    public String viewProductShopSearch(@RequestParam String findByName,
                                  Model model) {
        model = mainService.addBrandNameOnPage(model);
        model = mainService.addCategoriesOnPage(model);
        model.addAttribute("findByName", findByName);
        return "product_shop";
    }


    @GetMapping("/productShops")
    @ResponseBody
    public ResponseProductInfo viewProductShops() {
        Page<Product> page = productService.findPaginatedSearch(ProductShopConst.DEFAULT_PAGE_NO, ProductShopConst.DEFAULT_PRODUCT_PAGE_SIZE
                , null, null, null, null, null);
        return new ResponseProductInfo(page.getContent(), page.getTotalPages(), page.getTotalElements(), ProductShopConst.DEFAULT_PAGE_NO, ProductShopConst.DEFAULT_PRODUCT_PAGE_SIZE);
    }

    @PostMapping("/productShops")
    @ResponseBody
    public ResponseProductInfo viewProductShopSearch(@RequestParam(name = "brandNames", required = false) JSONArray brandNames,
                                                     @RequestParam(required = false) String priceStart,
                                                     @RequestParam(required = false) String priceEnd,
                                                     @RequestParam(required = false) JSONArray categoryNames,
                                                     @RequestParam(required = false) String findByName) {
        int pageNo = ProductShopConst.DEFAULT_PAGE_NO;
        int pageSize = ProductShopConst.DEFAULT_PRODUCT_PAGE_SIZE;
        List<Object> brandList = brandNames == null ? null : brandNames.toList();
        List<Object> categoryList = categoryNames == null ? null : categoryNames.toList();
        Page<Product> page = productService.findPaginatedSearch(pageNo, pageSize,
                findByName,
                brandList,
                categoryList,
                priceStart,
                priceEnd);
        return new ResponseProductInfo(page.getContent(),
                new SearchData(brandList,
                        priceStart,
                        priceEnd,
                        categoryList,
                        findByName),
                page.getTotalPages(),
                page.getTotalElements(),
                pageNo, pageSize);
    }



    @GetMapping("/productShops/{pageNoRequest}/{pageSizeRequest}/{categoryId}")
    @ResponseBody
    public ResponseProductInfo viewProductShops(@PathVariable String pageNoRequest,
                                                @PathVariable String pageSizeRequest,
                                                @PathVariable(required = false) String categoryId) {
        int pageSize, pageNo;
        List<Object> categoryNames = new ArrayList<>();
        if (categoryId.equals("0")) {
            categoryNames = null;
        } else {
            categoryNames.add(productService.getCategoryById(Long.parseLong(categoryId)).getCategoryName());
        }

        if (pageSizeRequest.equals("null")) pageSize = ProductShopConst.DEFAULT_PRODUCT_PAGE_SIZE;
        else pageSize = Integer.parseInt(pageSizeRequest);
        if (pageNoRequest.equals("null")) pageNo = ProductShopConst.DEFAULT_PAGE_NO;
        else pageNo = Integer.parseInt(pageNoRequest);
        Page<Product> page = productService.findPaginatedSearch(pageNo, pageSize
                , null, null, categoryNames, null, null);
        return new ResponseProductInfo(page.getContent(),
                new SearchData(null, null, null, categoryNames, null),
                page.getTotalPages(), page.getTotalElements(), pageNo, pageSize);
    }

    @PostMapping("/productShops/{pageNoRequest}/{pageSizeRequest}")
    @ResponseBody
    public ResponseProductInfo viewProductShopsSearch(@PathVariable String pageNoRequest,
                                                      @PathVariable String pageSizeRequest,
                                                      @RequestParam JSONObject searchData) {
        int pageSize, pageNo;
        JSONArray jsonBrandNames =  searchData.get("brandNames").getClass().getName().equals("org.json.JSONObject$Null") ? null : (JSONArray) searchData.get("brandNames");
        List<Object> brandNamesList;
        if (jsonBrandNames == null) brandNamesList = null;
        else brandNamesList = jsonBrandNames.toList();

        JSONArray jsonCategoryNames = searchData.get("categoryNames").getClass().getName().equals("org.json.JSONObject$Null") ? null : (JSONArray) searchData.get("categoryNames");
        List<Object> categoryNamesList;
        if (jsonCategoryNames == null) categoryNamesList = null;
        else categoryNamesList = jsonCategoryNames.toList();

        String findByName = searchData.get("findByName").getClass().getName().equals("org.json.JSONObject$Null") ? null : (String) searchData.get("findByName");
        String priceStart = searchData.get("priceStart").getClass().getName().equals("org.json.JSONObject$Null") ? null : (String) searchData.get("priceStart");
        String priceEnd = searchData.get("priceEnd").getClass().getName().equals("org.json.JSONObject$Null") ? null : (String) searchData.get("priceEnd");
        if (pageSizeRequest.equals("null")) pageSize = ProductShopConst.DEFAULT_PRODUCT_PAGE_SIZE;
        else pageSize = Integer.parseInt(pageSizeRequest);
        if (pageNoRequest.equals("null")) pageNo = ProductShopConst.DEFAULT_PAGE_NO;
        else pageNo = Integer.parseInt(pageNoRequest);

        System.out.println(" categoryNamesList = " + categoryNamesList);
        Page<Product> page = productService.findPaginatedSearch(pageNo, pageSize
                , findByName, brandNamesList, categoryNamesList, priceStart, priceEnd);

        return new ResponseProductInfo(page.getContent(),
                new SearchData(brandNamesList, priceStart, priceEnd, categoryNamesList, findByName),
                page.getTotalPages(), page.getTotalElements(), pageNo, pageSize);
    }
}