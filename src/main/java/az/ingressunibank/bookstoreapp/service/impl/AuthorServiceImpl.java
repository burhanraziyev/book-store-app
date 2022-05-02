package az.ingressunibank.bookstoreapp.service.impl;

import az.ingressunibank.bookstoreapp.mapper.AuthorMapper;
import az.ingressunibank.bookstoreapp.model.dto.AuthorDto;
import az.ingressunibank.bookstoreapp.model.entity.Author;
import az.ingressunibank.bookstoreapp.model.request.CreateAuthorRequest;
import az.ingressunibank.bookstoreapp.repository.AuthorRepository;
import az.ingressunibank.bookstoreapp.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper mapper;

    @Override
    public AuthorDto createAuthor(CreateAuthorRequest request) {
        Author author = mapper.toEntity(request);
        return mapper.toDto(authorRepository.saveAndFlush(author));
    }

    @Override
    public List<AuthorDto> getAuthors() {
        return mapper.toDtoList(authorRepository.findAll());
    }
}
