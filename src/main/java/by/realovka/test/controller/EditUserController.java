package by.realovka.test.controller;

import by.realovka.test.dto.UserAccountDto;
import by.realovka.test.dto.UserAccountEditFirstNameDto;
import by.realovka.test.dto.UserAccountEditLastNameDto;
import by.realovka.test.dto.UserAccountEditUsernameDto;
import by.realovka.test.entity.UserAccount;
import by.realovka.test.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/account")
@RequiredArgsConstructor
@Slf4j
public class EditUserController {

    private final UserAccountService userAccountService;

    @GetMapping(path = "/user/{id}/username")
    public ModelAndView editUsernameGetFormPage(@PathVariable("id") Long id, ModelAndView modelAndView) {
        UserAccountDto userAccountDto = userAccountService.getUserAccountDto(id);
        modelAndView.addObject("userEdit", new UserAccountEditUsernameDto());
        modelAndView.addObject("user", userAccountDto);
        modelAndView.setViewName("editusername");
        return modelAndView;
    }

    @PostMapping(path = "/user/{id}/editUsername")
    public ModelAndView editUsername(@AuthenticationPrincipal UserAccount userAccount,
                                     @ModelAttribute("userEdit") @Valid UserAccountEditUsernameDto userAccountEditUserNameDto,
                                     BindingResult bindingResult, @PathVariable("id") Long id, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            UserAccountDto userAccountDbDto = userAccountService.getUserAccountDto(id);
            modelAndView.addObject("user", userAccountDbDto);
            modelAndView.setViewName("editusername");
        } else {
            userAccountService.editUsername(id, userAccountEditUserNameDto.getUsernameEdit());
            addObjectsToModelAndView(userAccount, id, modelAndView);
        }
        log.info("Edit userAccount by username{}", userAccountEditUserNameDto);
        return modelAndView;
    }

    @GetMapping(path = "/user/{id}/firstName")
    public ModelAndView editFirstNameGetFormPage(@PathVariable("id") Long id, ModelAndView modelAndView) {
        UserAccountDto userAccountDto = userAccountService.getUserAccountDto(id);
        modelAndView.addObject("userEdit", new UserAccountEditFirstNameDto());
        modelAndView.addObject("user", userAccountDto);
        modelAndView.setViewName("editfirstname");
        return modelAndView;
    }

    @PostMapping(path = "/user/{id}/editFirstName")
    public ModelAndView editFirstName(@AuthenticationPrincipal UserAccount userAccount,
                                      @ModelAttribute("userEdit") @Valid UserAccountEditFirstNameDto userAccountEditFirstNameDto,
                                      BindingResult bindingResult, @PathVariable("id") Long id, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            UserAccountDto userAccountDbDto = userAccountService.getUserAccountDto(id);
            modelAndView.addObject("user", userAccountDbDto);
            modelAndView.setViewName("editfirstname");
        } else {
            userAccountService.editFirstName(id, userAccountEditFirstNameDto.getFirstNameEdit());
            addObjectsToModelAndView(userAccount, id, modelAndView);
        }
        log.info("Edit userAccount by firstName{}", userAccountEditFirstNameDto);
        return modelAndView;
    }

    @GetMapping(path = "/user/{id}/lastName")
    public ModelAndView editLastNameGetFormPage(@PathVariable("id") Long id, ModelAndView modelAndView) {
        UserAccountDto userAccountDto = userAccountService.getUserAccountDto(id);
        modelAndView.addObject("userEdit", new UserAccountEditLastNameDto());
        modelAndView.addObject("user", userAccountDto);
        modelAndView.setViewName("editlastname");
        return modelAndView;
    }

    @PostMapping(path = "/user/{id}/editLastName")
    public ModelAndView editLastNAme(@AuthenticationPrincipal UserAccount userAccount,
                                     @ModelAttribute("userEdit") @Valid UserAccountEditLastNameDto userAccountEditLastNameDto,
                                     BindingResult bindingResult, @PathVariable("id") Long id, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            UserAccountDto userAccountDbDto = userAccountService.getUserAccountDto(id);
            modelAndView.addObject("user", userAccountDbDto);
            modelAndView.setViewName("editlastname");
        } else {
            userAccountService.editLastName(id, userAccountEditLastNameDto.getLastNameEdit());
            addObjectsToModelAndView(userAccount, id, modelAndView);
        }
        log.info("Edit userAccount by lastName{}", userAccountEditLastNameDto);
        return modelAndView;
    }

    @GetMapping(path = "/user/{id}/status")
    public ModelAndView changeStatus(@AuthenticationPrincipal UserAccount userAccount, @PathVariable("id") Long id, ModelAndView modelAndView) {
        userAccountService.changeStatus(id);
        addObjectsToModelAndView(userAccount, id, modelAndView);
        return modelAndView;
    }

    private void addObjectsToModelAndView(UserAccount userAccount, Long id, ModelAndView modelAndView) {
        UserAccountDto userAccountDbDto = userAccountService.getUserAccountDto(id);
        UserAccountDto userAccountAuthDto = userAccountService.getUserAccountDto(userAccount.getId());
        modelAndView.addObject("userEdit", new UserAccountDto());
        modelAndView.addObject("userAuth", userAccountAuthDto);
        modelAndView.addObject("user", userAccountDbDto);
        modelAndView.setViewName("view");
    }
}
