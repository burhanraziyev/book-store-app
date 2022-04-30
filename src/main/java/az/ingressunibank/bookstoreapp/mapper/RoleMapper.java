package az.ingressunibank.bookstoreapp.mapper;

import az.ingressunibank.bookstoreapp.model.dto.RoleDto;
import az.ingressunibank.bookstoreapp.model.entity.Role;
import az.ingressunibank.bookstoreapp.model.request.CreateRoleRequest;
import liquibase.pro.packaged.S;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleMapper {

    public RoleDto toDto(Role entity) {
        if (entity == null) return null;
        return new RoleDto(entity.getId(), entity.getRoleName());
    }

    public Set<RoleDto> toDtoSet(Set<Role> entitySet) {
        if (entitySet == null) return Collections.emptySet();
        return entitySet.stream().map(this::toDto).collect(Collectors.toSet());
    }

    public List<RoleDto> toDtoList(List<Role> entityList) {
        if (entityList == null) return Collections.emptyList();
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Role toEntity(CreateRoleRequest request) {
        if (request == null) return null;
        return Role.builder().roleName(request.getRoleName()).build();
    }
}
