package az.ingressunibank.bookstoreapp.mapper;

import az.ingressunibank.bookstoreapp.model.dto.UserDto;
import az.ingressunibank.bookstoreapp.model.entity.Role;
import az.ingressunibank.bookstoreapp.model.entity.User;
import az.ingressunibank.bookstoreapp.model.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final RoleMapper roleMapper;
    private final BookMapper bookMapper;

    public User toEntity(RegisterRequest request, String password, Set<Role> roles) {
        if (request == null) return null;
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(password)
                .enabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .roles(roles)
                .build();
    }


    public UserDto toDto(User entity) {
        if (entity == null) return null;
        return new UserDto(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getEnabled(),
                entity.getAccountNonLocked(),
                entity.getAccountNonExpired(),
                entity.getCredentialsNonExpired(),
                bookMapper.toDtoList(entity.getBooks()),
                roleMapper.toDtoSet(entity.getRoles()),
                entity.getCreatedDate()
        );
    }
}
