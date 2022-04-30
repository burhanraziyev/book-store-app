package az.ingressunibank.bookstoreapp.repository;

import az.ingressunibank.bookstoreapp.model.entity.Role;
import az.ingressunibank.bookstoreapp.model.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Boolean existsByRoleName(RoleName roleName);
    Optional<Role> findByRoleName(RoleName authority);
}