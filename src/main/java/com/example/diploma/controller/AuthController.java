package com.example.diploma.controller;

import com.example.diploma.config.jwt.JwtUtils;
import com.example.diploma.entity.Roles;
import com.example.diploma.entity.User;
import com.example.diploma.pojo.JwtResponse;
import com.example.diploma.pojo.LoginRequest;
import com.example.diploma.pojo.SignupRequest;
import com.example.diploma.repas.IRolesRep;
import com.example.diploma.repas.IUserRep;
import com.example.diploma.service.AuthServiceIml;
import com.example.diploma.service.MainServiceImpl;
import com.example.diploma.service.UserDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    MainServiceImpl mainService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthServiceIml authServiceIml;

    @GetMapping("/signin")
    public String getPageSigninUser(Model model) {
        model = mainService.addCategoriesOnPage(model);
        return "signin";
    }

    @PostMapping("/signin")
    public String authUser(@RequestParam(required = false) String login,
                           @RequestParam(required = false) String password,
                           Model model) {
        model = mainService.addCategoriesOnPage(model);
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            login,
                            password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();
            model.addAttribute("user", userDetails);
            return "userCabinetManagePanel";
        } catch (BadCredentialsException e){
            e.printStackTrace();
            model.addAttribute("error", "???????????????? ?????????? ?????? ????????????");
            return "signin";
        } catch (LockedException e){
            e.printStackTrace();
            model.addAttribute("error", "???????????????????????? ????????????????????????");
            return "signin";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "?????????????????????? ????????????");
            return "signin";
        }


    }

    @GetMapping("/signup")
    public String getPageRegisterUser(@ModelAttribute User user,Model model) {
        model = mainService.addCategoriesOnPage(model);
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUser(@Valid @ModelAttribute  User user,
                               BindingResult bindingResult,
                               @RequestParam(required = false) String passRepeat,
                               Model model) {
        model = mainService.addCategoriesOnPage(model);

        if (authServiceIml.isValidUserLogin(user.getLogin())) {
            bindingResult.addError( new FieldError("user", "login", "?????????? ?????????? ?????? ????????????????????"));
        }
        if (authServiceIml.isValidUserEmail(user.getEmail())) {
            bindingResult.addError( new FieldError("user", "email", "???????????????????????? ?? ?????????? email ?????? ????????????????????"));
        }
        if (!user.getPassword().equals(passRepeat)){
            bindingResult.addError( new FieldError("user", "password", "???????????? ???? ??????????????????"));
        }
        if (!authServiceIml.isValidUserPassword(user.getPassword())){
            bindingResult.addError( new FieldError("user", "password", "???????????? ?????????????? ???????? ???????????? 6 ????????????????"));
        }
        if (bindingResult.hasErrors())
            return "/signup";

        try {
            authServiceIml.registerUser(user);
        } catch (Exception e){
            e.printStackTrace();
            model.addAttribute("message", "???????????? ?????????? ????????????, ???????????????????? ?? ????????????????????????????");
            return "/signup";
        }
        model.addAttribute("message", "???????????????????????? ??????????????????????????????");
        return "redirect:/auth/signin";
    }
}
