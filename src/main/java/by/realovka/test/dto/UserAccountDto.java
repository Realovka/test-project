package by.realovka.test.dto;

import by.realovka.test.entity.Role;
import by.realovka.test.entity.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAccountDto {
    private Long idAccountDto;
    @NotBlank
    @NotEmpty
    @Size(min = 3, max = 16)
    private String usernameAccountDto;
    @NotBlank
    @NotEmpty
    @Size(min = 1, max = 16)
    private String firstNameAccountDto;
    @NotBlank
    @NotEmpty
    @Size(min = 1, max = 16)
    private String lastNameAccountDto;
    private Role role;
    private UserStatus userStatus;
}
