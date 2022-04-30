package az.ingressunibank.bookstoreapp.model.request;

import az.ingressunibank.bookstoreapp.annotation.MatchPassword;
import az.ingressunibank.bookstoreapp.model.enums.RoleName;
import az.ingressunibank.bookstoreapp.util.PatternUtil;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static az.ingressunibank.bookstoreapp.util.PatternUtil.EMAIL_PATTERN;

@Data
public class RegisterRequest {
    @NotNull @NotEmpty @NotBlank
    private String firstName;
    @NotNull @NotEmpty @NotBlank
    private String lastName;
    @NotNull @NotEmpty @NotBlank
    private String username;
    @Email @NotNull
    private String email;
    @MatchPassword
    private String password;
    @NotNull
    private RoleName roleName;
}
