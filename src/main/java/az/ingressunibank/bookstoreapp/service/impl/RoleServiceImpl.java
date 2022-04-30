package az.ingressunibank.bookstoreapp.service.impl;

import az.ingressunibank.bookstoreapp.exception.ResourceExistsException;
import az.ingressunibank.bookstoreapp.exception.ResourceNotFoundException;
import az.ingressunibank.bookstoreapp.mapper.RoleMapper;
import az.ingressunibank.bookstoreapp.model.dto.RoleDto;
import az.ingressunibank.bookstoreapp.model.entity.Role;
import az.ingressunibank.bookstoreapp.model.enums.RoleName;
import az.ingressunibank.bookstoreapp.model.request.CreateRoleRequest;
import az.ingressunibank.bookstoreapp.repository.RoleRepository;
import az.ingressunibank.bookstoreapp.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;
    private final RoleMapper mapper;

    @Override
    public RoleDto createRole(CreateRoleRequest request) {
        if (doesExistBy(request.getRoleName()))
            throw new ResourceExistsException("Role", "roleName", request.getRoleName());
        Role role = repository.save(mapper.toEntity(request));
        return mapper.toDto(role);
    }

    @Override
    public Role findByRoleName(RoleName roleName) {
        return repository.findByRoleName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "roleName", roleName));
    }

    @Override
    public List<RoleDto> getRoles() {
        return mapper.toDtoList(repository.findAll());
    }

    private Boolean doesExistBy(RoleName authority) {
        return repository.existsByRoleName(authority);
    }
}
