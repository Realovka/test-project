package by.realovka.test.service;

import by.realovka.test.dto.UserAccountDto;
import by.realovka.test.dto.UserAccountRegDto;
import by.realovka.test.entity.UserAccount;
import by.realovka.test.entity.UserStatus;
import by.realovka.test.repository.UserAccountRepository;
import by.realovka.test.service.exception.NoSuchUserAccountException;
import by.realovka.test.service.exception.SuchUsernameExistsException;
import by.realovka.test.service.exception.UserAccountIsInactiveException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAccountServiceImpl implements UserAccountService, UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String name) {
        userAccountRepository.findByUsername(name).ifPresent(
                userAccount -> {
                    if (userAccount.getUserStatus().equals(UserStatus.INACTIVE)) {
                        log.info("Identification userAccount {}", userAccount);
                        throw new UserAccountIsInactiveException();
                    }
                });
        return userAccountRepository.findByUsername(name).orElseThrow(NoSuchUserAccountException::new);
    }

    @Override
    public UserAccount registrationUserAccount(UserAccountRegDto userAccountRegDto) {
        Optional<UserAccount> userAccountFromDB = userAccountRepository.findByUsername(userAccountRegDto.getUsernameReg());
        log.info("Find userAccount by username in DB {}", userAccountFromDB);
        if (userAccountFromDB.isEmpty()) {
            UserAccount userAccount = new UserAccount();
            userAccount.setUsername(userAccountRegDto.getUsernameReg());
            userAccount.setPassword(passwordEncoder.encode(userAccountRegDto.getPasswordReg()));
            userAccount.setFirstName(userAccountRegDto.getFirstNameReg());
            userAccount.setLastName(userAccountRegDto.getLastNameReg());
            userAccount.setRoles(Collections.singleton(userAccountRegDto.getRole()));
            userAccount.setUserStatus((userAccountRegDto.getUserStatus()));
            userAccount.setLocalDateTime(LocalDateTime.now());
            log.info("Registration userAccount {}", userAccountRegDto);
            userAccountRepository.save(userAccount);
        }
        return userAccountFromDB.orElse(new UserAccount());
    }

    @Override
    public List<UserAccountDto> getAllUsersAccountDto() {
        List<UserAccount> usersAccount = userAccountRepository.findAll();
        return usersAccount.stream().map(
                userAccount -> UserAccountDto.builder()
                        .idAccountDto(userAccount.getId())
                        .usernameAccountDto(userAccount.getUsername())
                        .firstNameAccountDto(userAccount.getFirstName())
                        .lastNameAccountDto(userAccount.getLastName())
                        .role(userAccount.getRoles().stream().findFirst().get())
                        .userStatus(userAccount.getUserStatus())
                        .build()).collect(Collectors.toList());

    }

    @Override
    public UserAccountDto getUserAccountDto(Long id) {
        UserAccount userAccount = userAccountRepository.getById(id);
        log.info("UserAccount from DB {}", userAccount);
        return UserAccountDto.builder()
                .idAccountDto(userAccount.getId())
                .usernameAccountDto(userAccount.getUsername())
                .firstNameAccountDto(userAccount.getFirstName())
                .lastNameAccountDto(userAccount.getLastName())
                .role(userAccount.getRoles().stream().findFirst().get())
                .userStatus(userAccount.getUserStatus())
                .build();
    }

    @Override
    public void editUsername(Long id, String username) {
        if (userAccountRepository.findByUsername(username).isPresent()) {
          throw new SuchUsernameExistsException();
        }
        UserAccount userAccount = userAccountRepository.findById(id).orElseThrow(NoSuchUserAccountException::new);
        userAccount.setUsername(username);
        log.info("Edit userAccount by username {}", userAccount);
        userAccountRepository.save(userAccount);
    }

    @Override
    public void editFirstName(Long id, String firstName) {
        UserAccount userAccount = userAccountRepository.findById(id).orElseThrow(NoSuchUserAccountException::new);
        userAccount.setFirstName(firstName);
        log.info("Edit userAccount by firstName {}", userAccount);
        userAccountRepository.save(userAccount);
    }

    @Override
    public void editLastName(Long id, String lastName) {
        UserAccount userAccount = userAccountRepository.findById(id).orElseThrow(NoSuchUserAccountException::new);
        userAccount.setLastName(lastName);
        log.info("Edit userAccount by lastName {}", userAccount);
        userAccountRepository.save(userAccount);
    }

    @Override
    public void changeStatus(Long id) {
        UserAccount userAccount = userAccountRepository.findById(id).orElseThrow(NoSuchUserAccountException::new);
        if (userAccount.getUserStatus().equals(UserStatus.ACTIVE)) {
            userAccount.setUserStatus(UserStatus.INACTIVE);
        } else {
            userAccount.setUserStatus(UserStatus.ACTIVE);
        }
        log.info("Edit userAccount by userStatus {}", userAccount);
        userAccountRepository.save(userAccount);
    }
}
