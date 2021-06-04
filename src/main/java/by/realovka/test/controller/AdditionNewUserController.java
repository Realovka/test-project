package by.realovka.test.controller;

import by.realovka.test.dto.UserAccountDto;
import by.realovka.test.dto.UserAccountRegDto;
import by.realovka.test.entity.UserAccount;
import by.realovka.test.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/account")
@RequiredArgsConstructor
@Slf4j
public class AdditionNewUserController {

    private final UserAccountService userAccountService;

    @GetMapping(path = "/user/new")
    public ModelAndView getPageForRegistrationNewUser(ModelAndView modelAndView) {
        modelAndView.addObject("userReg", new UserAccountRegDto());
        modelAndView.setViewName("new");
        return modelAndView;
    }

    @PostMapping(path = "/user/new")
    public ModelAndView addNewUser(@AuthenticationPrincipal UserAccount userAccount, @ModelAttribute("userReg") @Valid UserAccountRegDto userAccountRegDto,
                                   BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("new");
        } else {
            UserAccount userAccountDB = userAccountService.registrationUserAccount(userAccountRegDto);
            if (userAccountDB.equals(new UserAccount())) {
                List<UserAccountDto> usersAccountDto = userAccountService.getAllUsersAccountDto();
                UserAccountDto userAccountAuthDto = userAccountService.getUserAccountDto(userAccount.getId());
                modelAndView.addObject("userAuth", userAccountAuthDto);
                modelAndView.addObject("listUsersAccount", usersAccountDto);
                modelAndView.setViewName("user");
            } else {
                modelAndView.addObject("userReg", new UserAccountRegDto());
                modelAndView.addObject("suchUserNameAlreadyExists", "Such username already exists");
                modelAndView.setViewName("new");
            }
        }
        log.info("New userAccount {}", userAccountRegDto);
        return modelAndView;
    }
}
