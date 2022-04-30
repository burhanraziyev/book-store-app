package az.ingressunibank.bookstoreapp.service;

import az.ingressunibank.bookstoreapp.model.dto.RoleDto;
import az.ingressunibank.bookstoreapp.model.entity.Role;
import az.ingressunibank.bookstoreapp.model.enums.RoleName;
import az.ingressunibank.bookstoreapp.model.request.CreateRoleRequest;

import java.util.List;

public interface RoleService {
    RoleDto createRole(CreateRoleRequest request);
    Role findByRoleName(RoleName authority);
    List<RoleDto> getRoles();
}
