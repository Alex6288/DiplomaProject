package com.example.diploma.service.interfaces;

import com.example.diploma.entity.Brand;
import com.example.diploma.entity.Category;
import com.example.diploma.entity.Product;
import com.example.diploma.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface IAdminService {

    Model getAllUsersOnPage(Model model);
    Page<User> getPaginatedPageUser(int pageNo, int pageSize, String sortDir, String propertyName);
    Page<Product> getPaginatedPageProduct(int pageNo, int pageSize, String sortDir, String propertyName);
    Page<Category> getPaginatedPageCategory(int pageNo, int pageSize, String sortDir, String propertyName);
    Page<Brand> getPaginatedPageBrand(int pageNo, int pageSize, String sortDir, String propertyName);
    Brand getBrandById(Long id);
    Product getProductById(Long id);
    String addNewUser(String login, String pass, String firstName, String lastName, String middleName, String phone, String email, String countryName, String cityName);
    String addNewCategory(String categoryName);
    String addNewProduct(String productName, String productCategory, String productBrand, Integer productQuantity, Integer productPrice, String productDscrShort, String productDscrFull, MultipartFile productImg);
    String addNewBrand(String brandName, MultipartFile brandImg);
    String deleteUserById(Long id);
    String deleteCategoryById(Long id);
    String deleteBrandById(Long id);
    String deleteProductById(Long id);
    String updateCategoryById(Long id, String newCategoryName);
    String updateBrandById(Long id, String newBrandName, MultipartFile imgFile);
    String updateProduct(Long id, String productName, String productCategory, String productBrand, Integer productQuantity, Integer productPrice, String productDscrShort, String productDscrFull, MultipartFile productImg);
    //String updateBrandById(Long id, String newBrandName);
    String lockedUserById(Long id);

}
