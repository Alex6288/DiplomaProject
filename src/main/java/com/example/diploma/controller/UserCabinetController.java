package com.example.diploma.controller;

import com.example.diploma.entity.OrderProduct;
import com.example.diploma.entity.User;
import com.example.diploma.repas.IUserRep;
import com.example.diploma.service.MainServiceImpl;
import com.example.diploma.service.UserCabinetService;
import com.example.diploma.service.UserDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Set;

@Controller
@RequestMapping(value = "/user")
public class UserCabinetController {

    @Autowired
    MainServiceImpl mainService;

    @Autowired
    UserCabinetService cabinetService;


    @GetMapping("/cabinet")
    public String getUserCabinetPage( Principal principal, Model model){
       try {

        model = mainService.addCategoriesOnPage(model);
        model.addAttribute("user", cabinetService.getUserByLogin(principal.getName()));
        return "userCabinetManagePanel";
       } catch ( Exception e) {
           e.printStackTrace();
           return "userCabinetManagePanel";
       }
    }



    @GetMapping("/info")
    public String getUserInfo(Principal principal, Model model){
        try{
            model = mainService.addCategoriesOnPage(model);
            model.addAttribute("user", cabinetService.getUserByLogin(principal.getName()));
           return "userCabinetPersonalInfo";
        } catch ( Exception e) {
            e.printStackTrace();
            return "userCabinetManagePanel";
        }
    }

    @PostMapping("/updateInfo")
    public String updateInfo(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model){
        try {
            model = mainService.addCategoriesOnPage(model);
            if (bindingResult.hasErrors()){
                return "userCabinetPersonalInfo";
            }

            cabinetService.updateUserInfo(user);
            model.addAttribute("message", "Данные успешно изменены");
            model.addAttribute("user", cabinetService.getUserByLogin(user.getLogin()));
            return "userCabinetPersonalInfo";
        } catch (Exception e){
            model.addAttribute("error", "Ошибка изменения данных");
            e.printStackTrace();
            return "userCabinetPersonalInfo";
        }
    }

    @GetMapping("/orders")
    public String getUserOrdersPage(Model model, Principal principal){
        model = mainService.addCategoriesOnPage(model);
        model.addAttribute("orders",
                cabinetService.getAllOrders(cabinetService.getUserByLogin(principal.getName())));
        return "userCabinetOrders";
    }

    @GetMapping("/showOrderDetails/{numOrder}")
    @ResponseBody
    public Set<OrderProduct> getOrderProduct(@PathVariable Long numOrder){
        System.out.println();
        System.out.println();
        System.out.println(cabinetService.getDetailOrder(numOrder));
        System.out.println();
        System.out.println();
        return cabinetService.getDetailOrder(numOrder);
    }

    @PutMapping("/markPut")
    @ResponseBody
    public String putProductMark(@RequestParam int mark,
                                 @RequestParam Long productId,
                                 @RequestParam Long orderNum){
        try {
            cabinetService.putProductMark(mark, productId, orderNum);
            return "Умпешно";
        } catch (Exception e){
            e.printStackTrace();
            return "Ошибка";
        }
    }

}
