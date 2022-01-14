package com.example.diploma.controller;

import com.example.diploma.entity.exception.ProductNotEnoughException;
import com.example.diploma.entity.User;
import com.example.diploma.service.MainServiceImpl;
import com.example.diploma.service.ProductShopCartService;
import com.example.diploma.service.UserCabinetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.NoSuchElementException;

@Controller
@RequestMapping(value = "/shopCart")
public class ProductShopCartController {

    @Autowired
    ProductShopCartService shopCartService;
    @Autowired
    UserCabinetService userCabinetService;
    @Autowired
    MainServiceImpl mainService;

    @GetMapping("")
    public String getShopCartPage(Model model, Principal principal){
        model = mainService.addCategoriesOnPage(model);
        model.addAttribute("productList",
                shopCartService.getProductList());
        model.addAttribute("shopCartList", shopCartService.getShopCart());
        return "productShopCart";
    }


    @GetMapping("/addProduct/{productId}")
    @ResponseBody
    public Integer addProductToShopCart(@PathVariable Long productId){
        try {
            shopCartService.addProductByIdInShopCart(productId);
            return shopCartService.getShopCart().getShopCartItemSet().size();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return shopCartService.getShopCart().getShopCartItemSet().size();
        }
    }

    @GetMapping("/deleteProduct/{productId}")
    public ResponseEntity deleteProductFromShopCart(@PathVariable Long productId){
        try {
            shopCartService.deleteProductByIdInShopCart(productId);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/updateProductNum/{productId}/{productNum}")
    public ResponseEntity updateProductNum(@PathVariable Long productId,
                                   @PathVariable int productNum,
                                   Model model,
                                   Principal principal){
        try {
            //User user = userCabinetService.getUserByLogin(principal.getName());
            shopCartService.updateNumProductByIdInShopCart(productId, productNum);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/placeOrder")
    public String getProductShopOrderPage(Model model, Principal principal){
        model = mainService.addCategoriesOnPage(model);
        if (principal != null) {
            User user = userCabinetService
                .getUserByLogin(principal.getName());
            model.addAttribute("user", user);
        }

        model.addAttribute("productList", shopCartService.getProductList());
        model.addAttribute("shopCartList", shopCartService.getShopCart());

        return "productShopOrder";
    }

    @PostMapping("/placeOrder/submit")
    public String submitOrder(Model model, Principal principal,
                              @RequestParam(required = false) String customer,
                              @RequestParam String contact,
                              @RequestParam String phone,
                              @RequestParam String email,
                              @RequestParam String address){

        // todo добавить отправитель на почту заказа
        try {
            if (principal != null) {
                User user = userCabinetService
                        .getUserByLogin(principal.getName());
                shopCartService.saveOrderShopCart(user.getId(), contact, phone, email, address);
                model.addAttribute("message", "Ваш заказ сделан");
                model.addAttribute("user", user);
                model = mainService.addCategoriesOnPage(model);
                return "userCabinetManagePanel";
            } else {
                String message = "Уважаемый " + contact + " ваш заказ сделан, продтверждение придет на указаную почту";
                shopCartService.saveOrderShopCart(userCabinetService.getGuestId(), contact, phone, email, address);
                model.addAttribute("message", message);
                model = mainService.addCategoriesOnPage(model);
                return "guestSucseecOrder";
            }
        } catch (ProductNotEnoughException e) {
            model.addAttribute("error", e.getMessage());
            return getShopCartPage(model, principal);
        }



    }
}
