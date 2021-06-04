package by.realovka.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountAuthDto {
    @NotBlank
    @NotEmpty
    private String usernameAuth;
    @NotBlank
    @NotEmpty
    private String passwordAuth;
}
