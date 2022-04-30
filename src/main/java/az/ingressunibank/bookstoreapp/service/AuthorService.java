package az.ingressunibank.bookstoreapp.service;

import az.ingressunibank.bookstoreapp.model.dto.AuthorDto;
import az.ingressunibank.bookstoreapp.model.request.CreateAuthorRequest;

import java.util.List;

public interface AuthorService {
    AuthorDto createAuthor(CreateAuthorRequest request);

    List<AuthorDto> getAuthors();
}
