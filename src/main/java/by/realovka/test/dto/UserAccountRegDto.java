package by.realovka.test.dto;

import by.realovka.test.entity.Role;
import by.realovka.test.entity.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountRegDto {
    @NotBlank
    @NotEmpty
    @Size(min = 3, max = 16)
    private String usernameReg;
    @NotBlank
    @NotEmpty
    @Size(min = 3, max = 16)
    private String passwordReg;
    @NotBlank
    @NotEmpty
    @Size(min = 1, max = 16)
    private String firstNameReg;
    @NotBlank
    @NotEmpty
    @Size(min = 1, max = 16)
    private String lastNameReg;
    private Role role;
    private UserStatus userStatus;
}
