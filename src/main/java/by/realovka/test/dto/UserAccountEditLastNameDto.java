package by.realovka.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountEditLastNameDto {
    @NotBlank
    @NotEmpty
    @Size(min = 1, max = 16)
    private String lastNameEdit;
}
