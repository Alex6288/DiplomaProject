package com.example.diploma.service;

import com.example.diploma.entity.*;
import com.example.diploma.repas.*;
import com.example.diploma.service.interfaces.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.management.relation.Role;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    IUserRep userRep;

    @Autowired
    IRolesRep roleRep;
    @Autowired
    IProductRep productRep;
    @Autowired
    ICategory categoryRep;
    @Autowired
    IBrands brandRep;
    @Autowired
    IProductRatingRep productRatingRep;


    @Override
    public String lockedUserById(Long id) {
        try {
            User lockUser = userRep.getById(id);
            lockUser.changeActive();
            userRep.save(lockUser);
            return "Успешно";
        } catch (Exception e ) {
            return "Ошибка блокирокки/разблокировки";
        }
    }

    @Override
    public String deleteUserById(Long id) {
        try {
            userRep.deleteById(id);
            return "ok";
        } catch (Exception e) {
            return "error";
        }
    }

    @Override
    public String addNewUser(String login, String pass, String firstName, String lastName, String middleName, String phone, String email, String countryName, String cityName) {
        try {

            User user = new User(firstName,
                    middleName,
                    lastName,
                    cityName,
                    countryName,
                    phone,
                    email,
                    login, pass);
            Roles role =  roleRep.getByRoleName("ROLE_USER");
            user.setRole(role);
            userRep.save(user);
//            UserRole userRole = new UserRole(user, roleRep.getByRoleName("ROLE_USER"));
//            userRoleRep.save(userRole);
            return "ok";
        } catch (Exception e) {
            return "error";
        }
    }

    @Override
    public String addNewCategory(String categoryName) {
        if (categoryRep.findByCategoryName(categoryName).orElse(null) == null) {
            Category category = new Category(categoryName);
            categoryRep.save(category);
            return "Успешное добавление категории";
        } else {
            return "Ошибка: такая категория существует или недопустимое имя";
        }
    }

    @Override
    public String deleteCategoryById(Long id) {
        try {
            categoryRep.deleteById(id);
            return "Успешно: категория удалена";
        } catch (Exception e) {
            return "Ошибка: ошибка удаления";
        }
    }

    @Override
    public String updateCategoryById(Long id, String newCategoryName) {
        try {
            if (categoryRep.updateCategory(id, newCategoryName) == 1)
                return "Успешно: категория обновлена";
            else return "Ошибка: ошибка обновления";
        } catch (Exception e) {
            return "Ошибка: ошибка обновления";
        }
    }

    public final static String UPLOADED_BRAND_FOLDER = "src/main/resources/static/img/brands/";
    public final static String UPLOADED_PRODUCT_FOLDER = "src/main/resources/static/img/product/";
    public final static String LOAD_LOGO_BRAND_ON_PAGE_FOLDER = "/img/brands/";
    public final static String LOAD_LOGO_PRODUCT_ON_PAGE_FOLDER = "/img/product/";

    private Path saveFile(MultipartFile file, String pathFile) {
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(pathFile + file.getOriginalFilename());
            if (!Files.exists(path)) {
                Files.write(path, bytes);
            }
            return path;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Brand getBrandById(Long id) {
        return brandRep.getById(id);
    }

    @Override
    public Product getProductById(Long id) {
        return productRep.getById(id);
    }

    @Override
    public String addNewBrand(String brandName, MultipartFile brandImg) {
        if (brandRep.findByBrandName(brandName).orElse(null) == null)
            try {
                Brand newBrand = new Brand(brandName, saveFile(brandImg, UPLOADED_BRAND_FOLDER).toString());
                brandRep.save(newBrand);
                return "Успешно : добавлен новый бренд";
            } catch (Exception e) {
                e.printStackTrace();
                return "Ошибка  : добавления нового бренда";
            }
        else
            return "Ошибка  : добавления нового бренда, такой бренд существет";
    }

    @Override
    public String deleteBrandById(Long id) {
        try {
            String filePath = brandRep.getById(id).getImgFilePath();
            File f = new File(filePath);
            if (f.exists())
                Files.delete(Path.of(filePath));
            brandRep.deleteById(id);
            return "Успешно : бренд удален";
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка : удаление бенда";
        }
    }

    @Override
    public String updateBrandById(Long id, String newBrandName, MultipartFile imgFile) {
        int count = 0;
        if (imgFile.getOriginalFilename().isEmpty()) {
            count = brandRep.updateBrand(id, newBrandName);
        } else {
            try {
                File sourseFile = new File(brandRep.getById(id).getImgFilePath());
                if (sourseFile.exists() && !sourseFile.isDirectory())
                    Files.delete(Path.of(brandRep.getById(id).getImgFilePath()));
                saveFile(imgFile, UPLOADED_BRAND_FOLDER);
                count = brandRep.updateBrand(id, newBrandName, UPLOADED_BRAND_FOLDER + imgFile.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            if (count == 1)
                return "Успешно: бренд обновлена";
            else return "Ошибка: ошибка обновления";
        } catch (Exception e) {
            return "Ошибка: ошибка обновления";
        }
    }

    // product manage


    @Override
    public String addNewProduct(String productName, String productCategory, String productBrand, Integer productQuantity, Integer productPrice, String productDscrShort, String productDscrFull, MultipartFile productImg) {
        try {
            System.out.println("Add product data");
            System.out.println("productName = " + productName);
            System.out.println("productCategory =" + productCategory);
            System.out.println("productBrand =" + productBrand);
            System.out.println("productQuantity =" + productQuantity);
            System.out.println("productPrice =" + productPrice);
            System.out.println("productDscrShort =" + productDscrShort);
            System.out.println("productDscrFull =" + productDscrFull);


            Brand brand = brandRep.getBrandByBrandName(productBrand);
            System.out.println(brand.getBrandName());
            Category category = categoryRep.getCategoryByCategoryName(productCategory);
            if (productRep.findProductByProductNameAndBrandAndCategory(productName, brand, category).orElse(null) == null) {
                saveFile(productImg, UPLOADED_PRODUCT_FOLDER);
                Product product = new Product(productName, brand, productPrice, productQuantity, productDscrShort, productDscrFull, LOAD_LOGO_PRODUCT_ON_PAGE_FOLDER + productImg.getOriginalFilename(), category);
                ProductRating productRating = new ProductRating(1L, 5L);
                productRatingRep.save(productRating);
                product.setProductRating(productRating);
                productRep.save(product);
                return "Успешно: добавлен новый продукт";
            } else
                return "Ошибка: продукт с такими именем, брендом и категорией уже существует";
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка: добавления продукта";
        }
    }

    @Override
    public String deleteProductById(Long id) {
        try {
            String filePath = productRep.getById(id).getMainPhotoPath();
            File f = new File(filePath);
            if (f.exists())
                Files.delete(Path.of(filePath));
            productRep.deleteById(id);
            return "Успешно : продукт удален";
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка : удаление продукта";
        }
    }

    @Override
    public String updateProduct(Long id, String productName, String productCategory, String productBrand, Integer productQuantity, Integer productPrice, String productDscrShort, String productDscrFull, MultipartFile productImg) {
        try {
            System.out.println();
            System.out.println("updateProduct method ");
            Product product = getProductById(id);
            product.setProductName(productName);
            product.setCategory(categoryRep.findByCategoryName(productCategory).get());
            product.setBrand(brandRep.findByBrandName(productBrand).get());
            product.setPrice(productPrice);
            product.setQuantity(productQuantity);
            product.setDescription_full(productDscrFull);
            product.setDescription_short(productDscrShort);
            System.out.println("product.quantityOld = " + product.getQuantity());
            System.out.println("productNewQuantity = " + productQuantity);
            System.out.println("product.quantityNew = " + product.getQuantity());
            if (!productImg.getOriginalFilename().isEmpty()) {
                System.out.println("productImg = " + productImg.getOriginalFilename());
                System.out.println("productImg = " + productImg);
                String fileName = product.getMainPhotoFileName();
                File sourseFile = new File(UPLOADED_PRODUCT_FOLDER + fileName);
                if (sourseFile.exists()) {
                    System.out.println(UPLOADED_PRODUCT_FOLDER + fileName);
                    Files.delete(Path.of(UPLOADED_PRODUCT_FOLDER + fileName));
                }
                saveFile(productImg, UPLOADED_PRODUCT_FOLDER);
                product.setMainPhotoPath(LOAD_LOGO_PRODUCT_ON_PAGE_FOLDER + productImg.getOriginalFilename());
            }
            productRep.save(product);
            return "Успешно : продукт обновлен";
        } catch (IOException e){
            e.printStackTrace();
            return "Ошибка: при удалении фото или загрузге данных";
        }

    }

    // paginate manage
    @Override
    public Model getAllUsersOnPage(Model model) {
        model.addAttribute("userList", userRep.findAll());
        return null;
    }

    @Override
    public Page<User> getPaginatedPageUser(int pageNo, int pageSize, String sortDir, String sortField) {
        return userRep.findAll(getPageable(pageNo, pageSize, sortDir, sortField));
    }

    @Override
    public Page<Product> getPaginatedPageProduct(int pageNo, int pageSize, String sortDir, String sortField) {
        return productRep.findAll(getPageable(pageNo, pageSize, sortDir, sortField));
    }

    @Override
    public Page<Category> getPaginatedPageCategory(int pageNo, int pageSize, String sortDir, String sortField) {
        return categoryRep.findAll(getPageable(pageNo, pageSize, sortDir, sortField));
    }

    @Override
    public Page<Brand> getPaginatedPageBrand(int pageNo, int pageSize, String sortDir, String sortField) {
        return brandRep.findAll(getPageable(pageNo, pageSize, sortDir, sortField));
    }

    private Pageable getPageable(int pageNo, int pageSize, String sortDir, String sortField) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        return PageRequest.of(pageNo - 1, pageSize, sort);
    }
}
