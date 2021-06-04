package by.realovka.test.service;

import by.realovka.test.dto.UserAccountDto;
import by.realovka.test.dto.UserAccountRegDto;
import by.realovka.test.entity.UserAccount;

import java.util.List;

public interface UserAccountService {
    UserAccount registrationUserAccount(UserAccountRegDto userAccountRegDto);
    List<UserAccountDto> getAllUsersAccountDto();
    UserAccountDto getUserAccountDto(Long id);
    void editUsername(Long id, String username);
    void editFirstName(Long id, String firstName);
    void editLastName(Long id, String lastName);
    void changeStatus(Long id);
}
