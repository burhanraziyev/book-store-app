package az.ingressunibank.bookstoreapp.model.dto;

import az.ingressunibank.bookstoreapp.model.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private Long id;
    private RoleName roleName;
}
