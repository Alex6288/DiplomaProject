package com.example.diploma.controller;

import com.example.diploma.entity.Brand;
import com.example.diploma.entity.Category;
import com.example.diploma.entity.Product;
import com.example.diploma.entity.User;
import com.example.diploma.service.MainServiceImpl;
import com.example.diploma.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/admin")
public class AdminPageController {

    private final int DEFAULT_PAGE_SIZE = 3;
    private final String DEFAULT_SOFT_DIR = "asc";
    private final String REVERSE_DEFAULT_SOFT_DIR = "desc";

    @Autowired
    MainServiceImpl mainService;

    @Autowired
    AdminServiceImpl adminService;

    @GetMapping("/{pageNo}")
    public String getAdminPageNo(@PathVariable int pageNo,
                                 @RequestParam String sortDir,
                                 @RequestParam String sortField,
                                 Model model) {

        model = mainService.addCategoriesOnPage(model);
        model = mainService.addBrandNameOnPage(model);
        Page<User> page = adminService.getPaginatedPageUser(pageNo, DEFAULT_PAGE_SIZE, sortDir, sortField);
        model.addAttribute("userList", page.getContent());

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalUsers", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals(DEFAULT_SOFT_DIR) ? REVERSE_DEFAULT_SOFT_DIR : DEFAULT_SOFT_DIR);
        return "adminUsers";
    }

    @GetMapping("")
    public String getAdminPage(Model model) {
        return getAdminPageNo(1, DEFAULT_SOFT_DIR, "firstName", model);
    }


    //manage category
    @GetMapping("/category")
    public String getAdminCategoryPage(Model model) {
        return getAdminCategoryPageNo(1, DEFAULT_SOFT_DIR, "categoryName", model);
        ///return getAdminCategotyPageNo(1, "asc", "productName", model);
    }

    @GetMapping("/category/{pageNo}")
    public String getAdminCategoryPageNo(@PathVariable int pageNo,
                                         @RequestParam String sortDir,
                                         @RequestParam String sortField,
                                         Model model) {
        model = mainService.addCategoriesOnPage(model);
        model = mainService.addBrandNameOnPage(model);

        Page<Category> page = adminService.getPaginatedPageCategory(pageNo, DEFAULT_PAGE_SIZE, sortDir, sortField);
        model.addAttribute("categoryList", page.getContent());

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalCategory", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals(DEFAULT_SOFT_DIR) ? REVERSE_DEFAULT_SOFT_DIR : DEFAULT_SOFT_DIR);
        return "adminCategory";
    }

    @GetMapping("/categoryAdd")
    public String getPageAddNewCategory(Model model) {
        return "adminCategoryAdd";
    }

    @PostMapping("/categoryAdd")
    public String getPageAddNewCategory(Model model,
                                        @RequestParam String categoryName) {
        String message = adminService.addNewCategory(categoryName);
        model.addAttribute("message", message);
        if (message.contains("шибка")) {
            return "adminCategoryAdd";
        } else {
            return getAdminCategoryPageNo(1, DEFAULT_SOFT_DIR, "categoryName", model);
        }
    }

    @GetMapping("/categoryUpdate/{categoryId}")
    public String getPageAddUpdateCategory(Model model,
                                           @PathVariable Long categoryId) {
        model.addAttribute("categoryId", categoryId);
        return "adminCategoryUpdate";
    }

    @PostMapping("/categoryUpdate")
    public String getPageAddNewCategory(Model model,
                                        @RequestParam String categoryName,
                                        @RequestParam Long categoryId) {
        String message = adminService.updateCategoryById(categoryId, categoryName);
        model.addAttribute("message", message);
        if (message.contains("шибка")) {
            model.addAttribute("categoryId", categoryId);
            return "adminCategoryUpdate";
        } else {
            return getAdminCategoryPageNo(1, DEFAULT_SOFT_DIR, "categoryName", model);
        }
    }

    @GetMapping("/categoryDelete/{categoryId}")
    public String deleteCategoryById(Model model,
                                     @PathVariable Long categoryId) {
        String message = adminService.deleteCategoryById(categoryId);
        model.addAttribute("message", message);
        return getAdminCategoryPageNo(1, DEFAULT_SOFT_DIR, "categoryName", model);
    }

    //manage brand
    @GetMapping("/brand")
    public String getAdminBrandPage(Model model) {
        return getAdminBrandPageNo(1, DEFAULT_SOFT_DIR, "brandName", model);
    }

    @GetMapping("/brand/{pageNo}")
    public String getAdminBrandPageNo(@PathVariable int pageNo,
                                      @RequestParam String sortDir,
                                      @RequestParam String sortField,
                                      Model model) {
        model = mainService.addCategoriesOnPage(model);
        model = mainService.addBrandNameOnPage(model);

        Page<Brand> page = adminService.getPaginatedPageBrand(pageNo, DEFAULT_PAGE_SIZE, sortDir, sortField);
        model.addAttribute("brandList", page.getContent());

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalBrand", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals(DEFAULT_SOFT_DIR) ? REVERSE_DEFAULT_SOFT_DIR : DEFAULT_SOFT_DIR);
        return "adminBrand";
    }

    @GetMapping("/brandAdd")
    public String getPageAddNewBrand(Model model) {
        return "adminBrandAdd";
    }

    @PostMapping("/brandAdd")
    public String getPageAddNewBrand(Model model,
                                     @RequestParam String brandName,
                                     @RequestParam MultipartFile brandImg) {
        String message = adminService.addNewBrand(brandName, brandImg);
        model.addAttribute("message", message);
        if (message.contains("шибка")) {
            return "adminBrandAdd";
        } else {
            return getAdminBrandPageNo(1, DEFAULT_SOFT_DIR, "brandName", model);
        }
    }

    @GetMapping("/brandDelete/{brandId}")
    public String deleteBrandById(Model model,
                                  @PathVariable Long brandId) {
        String message = adminService.deleteBrandById(brandId);
        model.addAttribute("message", message);
        return getAdminBrandPageNo(1, DEFAULT_SOFT_DIR, "brandName", model);
    }

    @GetMapping("/brandUpdate/{brandId}")
    public String getPageAddUpdateBrand(Model model,
                                        @PathVariable Long brandId) {
        model.addAttribute("brand", adminService.getBrandById(brandId));
        return "adminBrandUpdate";
    }

    @PostMapping("/brandUpdate")
    public String getPageAddNewBrand(Model model,
                                     @RequestParam String brandName,
                                     @RequestParam Long brandId,
                                     @RequestParam(required = false) MultipartFile imgFile) {
        String message = adminService.updateBrandById(brandId, brandName, imgFile);
        model.addAttribute("message", message);
        if (message.contains("шибка")) {
            model.addAttribute("brandId", brandId);
            return "adminBrandUpdate";
        } else {
            return getAdminBrandPageNo(1, DEFAULT_SOFT_DIR, "brandName", model);
        }
    }

    //menage products

    @GetMapping("/product")
    public String getAdminProductPage(Model model) {
        return getAdminProductPageNo(1, DEFAULT_SOFT_DIR, "productName", model);
    }

    @GetMapping("/product/{pageNo}")
    public String getAdminProductPageNo(@PathVariable int pageNo,
                                        @RequestParam String sortDir,
                                        @RequestParam String sortField,
                                        Model model) {
        model = mainService.addCategoriesOnPage(model);
        model = mainService.addBrandNameOnPage(model);
        Page<Product> page = adminService.getPaginatedPageProduct(pageNo, DEFAULT_PAGE_SIZE, sortDir, sortField);
        model.addAttribute("productList", page.getContent());

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalProduct", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals(DEFAULT_SOFT_DIR) ? REVERSE_DEFAULT_SOFT_DIR : DEFAULT_SOFT_DIR);
        return "adminProduct";
    }

    @GetMapping("/productAdd")
    public String getPageAddNewProduct(Model model) {
        model = mainService.addCategoriesOnPage(model);
        model = mainService.addBrandNameOnPage(model);
        model.addAttribute("ok", "ok");
        return "adminProductAdd";
    }

    @PostMapping("/productAdd")
    public String getPageAddNewProduct(Model model,
                                       @RequestParam String productName,
                                       @RequestParam String productCategory,
                                       @RequestParam String productBrand,
                                       @RequestParam Integer productQuantity,
                                       @RequestParam Integer productPrice,
                                       @RequestParam String productDscrShort,
                                       @RequestParam String productDscrFull,
                                       @RequestParam MultipartFile productImg) {
        String message = adminService.addNewProduct(productName, productCategory, productBrand, productQuantity, productPrice, productDscrShort, productDscrFull, productImg);
        model.addAttribute("message", message);
        if (message.contains("шибка")) {
            return "adminProductAdd";
        } else {
            return getAdminProductPageNo(1, DEFAULT_SOFT_DIR, "productName", model);
        }
    }

    @GetMapping("/productDelete/{productId}")
    public String deleteProduct(Model model,
                                @PathVariable Long productId){
        String message = adminService.deleteProductById(productId);
        model.addAttribute("message", message);
        return getAdminProductPageNo(1, DEFAULT_SOFT_DIR, "productName", model);
    }

    @GetMapping("/productUpdate/{productId}")
    public String getUpdateProductPage(Model model,
                                       @PathVariable Long productId){
        model = mainService.addCategoriesOnPage(model);
        model = mainService.addBrandNameOnPage(model);
        model.addAttribute("product", adminService.getProductById(productId));
        return "adminProductUpdate";
    }

    @PostMapping("/productUpdate")
    public String updateProduct(Model model,
                                       @RequestParam Long productId,
                                       @RequestParam String productName,
                                       @RequestParam String productCategory,
                                       @RequestParam String productBrand,
                                       @RequestParam Integer productQuantity,
                                       @RequestParam Integer productPrice,
                                       @RequestParam String productDscrShort,
                                       @RequestParam String productDscrFull,
                                       @RequestParam(required = false) MultipartFile productImg) {
        String message = adminService.updateProduct(productId, productName, productCategory, productBrand, productQuantity, productPrice, productDscrShort, productDscrFull, productImg);
        model.addAttribute("message", message);
        if (message.contains("шибка")) {
            return "adminProductUpdate";
        } else {
            return getAdminProductPageNo(1, DEFAULT_SOFT_DIR, "productName", model);
        }
    }

// manage action Users

    @GetMapping("/userAdd")
    public String getPageAddNewUser(Model model) {
        return "adminUserAdd";
    }

    @PostMapping("/userAdd")
    public String addNewUser(Model model,
                             @RequestParam String login,
                             @RequestParam String pass,
                             @RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam(required = false, defaultValue = "") String middleName,
                             @RequestParam String phone,
                             @RequestParam String email,
                             @RequestParam String countryName,
                             @RequestParam String cityName
    ) {
        String status = adminService.addNewUser(login, pass, firstName, lastName, middleName, phone, email, countryName, cityName);
        if (status.equals("ok")) {
            model.addAttribute("message", "Пользователь успешно добавлен");
            return getAdminPageNo(1, DEFAULT_SOFT_DIR, "firstName", model);
        } else {
            model.addAttribute("message", "Ошибка добавления");
            return getPageAddNewUser(model);
        }
    }


    @GetMapping("/deleteUser/id")
    public String deleteUser(Model model,
                             @RequestParam Long id) {
        String status = adminService.deleteUserById(id);
        if (status.equals("ok")) {
            model.addAttribute("message", "Пользователь удален успешно");
        } else {
            model.addAttribute("message", "Ошибка удаления");
        }
        return getAdminPageNo(1, DEFAULT_SOFT_DIR, "firstName", model);
    }

    @GetMapping("/lockedUser/{id}")
    public String lockUnlockUser(Model model,
                                 @RequestParam Long id){
        String status = adminService.lockedUserById(id);
        return getAdminPageNo(1, DEFAULT_SOFT_DIR, "firstName", model);
    }

}
