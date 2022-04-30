package az.ingressunibank.bookstoreapp.service;

import az.ingressunibank.bookstoreapp.model.dto.UserDto;
import az.ingressunibank.bookstoreapp.model.entity.User;
import az.ingressunibank.bookstoreapp.model.request.RegisterRequest;

public interface UserService {
    UserDto register(RegisterRequest request);
    UserDto getByUserId(Long id);
    User findByUserId(Long id);
}
