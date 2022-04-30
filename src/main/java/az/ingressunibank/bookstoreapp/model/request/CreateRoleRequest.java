package az.ingressunibank.bookstoreapp.model.request;

import az.ingressunibank.bookstoreapp.model.enums.RoleName;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateRoleRequest {
    @NotNull
    private RoleName roleName;
}
