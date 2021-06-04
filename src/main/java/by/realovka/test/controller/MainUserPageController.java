package by.realovka.test.controller;

import by.realovka.test.dto.UserAccountDto;
import by.realovka.test.entity.UserAccount;
import by.realovka.test.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/account")
@RequiredArgsConstructor
@Slf4j
public class MainUserPageController {

    private final UserAccountService userAccountService;

    @GetMapping(path = "/user")
    public ModelAndView getAuthUserAccountPage(@AuthenticationPrincipal UserAccount userAccount, ModelAndView modelAndView) {
        List<UserAccountDto> usersAccountDto = userAccountService.getAllUsersAccountDto();
        UserAccountDto userAccountAuthDto = userAccountService.getUserAccountDto(userAccount.getId());
        modelAndView.addObject("userAuth", userAccountAuthDto);
        modelAndView.addObject("listUsersAccount", usersAccountDto);
        modelAndView.setViewName("user");
        log.info("Authorization userAccount {}", userAccountAuthDto);
        return modelAndView;
    }

    @GetMapping(path = "/user/{id}")
    public ModelAndView getOtherUserPage(@AuthenticationPrincipal UserAccount userAccount,
                                         @PathVariable Long id, ModelAndView modelAndView) {
        UserAccountDto userAccountDto = userAccountService.getUserAccountDto(id);
        modelAndView.addObject("user", userAccountDto);
        UserAccountDto userAccountAuthDto = userAccountService.getUserAccountDto(userAccount.getId());
        modelAndView.addObject("userAuth", userAccountAuthDto);
        modelAndView.setViewName("view");
        log.info("Other userAccount {}", userAccountDto);
        return modelAndView;
    }
}
