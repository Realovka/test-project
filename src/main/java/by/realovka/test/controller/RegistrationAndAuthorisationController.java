package by.realovka.test.controller;

import by.realovka.test.dto.UserAccountAuthDto;
import by.realovka.test.dto.UserAccountRegDto;
import by.realovka.test.entity.UserAccount;
import by.realovka.test.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
@RequestMapping(path = "/home")
@RequiredArgsConstructor
@Slf4j
public class RegistrationAndAuthorisationController {

    private final UserAccountService userAccountService;

    @GetMapping(path = "/reg")
    public ModelAndView getRegistrationPage(ModelAndView modelAndView) {
        modelAndView.addObject("userReg", new UserAccountRegDto());
        modelAndView.setViewName("reg");
        return modelAndView;
    }

    @PostMapping(path = "/reg")
    public ModelAndView postRegistrationUserAccount(@ModelAttribute("userReg") @Valid UserAccountRegDto userAccountRegDto, BindingResult bindingResult,
                                             ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("reg");
        } else {
            UserAccount userAccount = userAccountService.registrationUserAccount(userAccountRegDto);
            if (userAccount.equals(new UserAccount())) {
                modelAndView.setViewName("index");
            } else {
                modelAndView.addObject("suchUserNameAlreadyExists", "Such username already exists");
                modelAndView.setViewName("reg");
            }
        }
        log.info("Registration userAccount {}", userAccountRegDto);
        return modelAndView;
    }


    @GetMapping(path = "/login")
    public ModelAndView getAuthPage(ModelAndView modelAndView) {
        modelAndView.addObject("userAuth", new UserAccountAuthDto());
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
