package az.ingressunibank.bookstoreapp.service.impl;

import az.ingressunibank.bookstoreapp.exception.ResourceNotFoundException;
import az.ingressunibank.bookstoreapp.mapper.UserMapper;
import az.ingressunibank.bookstoreapp.model.dto.UserDto;
import az.ingressunibank.bookstoreapp.model.entity.Role;
import az.ingressunibank.bookstoreapp.model.entity.User;
import az.ingressunibank.bookstoreapp.model.request.RegisterRequest;
import az.ingressunibank.bookstoreapp.repository.UserRepository;
import az.ingressunibank.bookstoreapp.service.RoleService;
import az.ingressunibank.bookstoreapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Override
    public UserDto register(RegisterRequest request) {
        String password = passwordEncoder.encode(request.getPassword());
        Role role = roleService.findByRoleName(request.getRoleName());
        User user = mapper.toEntity(request, password, Set.of(role));
        return mapper.toDto(userRepository.saveAndFlush(user));
    }

    @Override
    public UserDto getByUserId(Long id) {
        return mapper.toDto(findByUserId(id));
    }

    @Override
    public User findByUserId(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }
}
