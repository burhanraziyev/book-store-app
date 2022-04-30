package az.ingressunibank.bookstoreapp.mapper;

import az.ingressunibank.bookstoreapp.model.dto.AuthorDto;
import az.ingressunibank.bookstoreapp.model.dto.AuthorsBookDto;
import az.ingressunibank.bookstoreapp.model.entity.Author;
import az.ingressunibank.bookstoreapp.model.entity.Book;
import az.ingressunibank.bookstoreapp.model.request.CreateAuthorRequest;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AuthorMapper {

    public AuthorDto toDto(Author entity) {
        if (entity == null) return null;
        return new AuthorDto(
                entity.getId(),
                entity.getFullName(),
                getAuthorsBookSet(entity.getBooks()),
                entity.getBirthOfDate());
    }

    private Set<AuthorsBookDto> getAuthorsBookSet(Set<Book> books) {
        if (books == null) return Collections.emptySet();
        return books.stream()
                .map(book -> new AuthorsBookDto(
                        book.getId(),
                        book.getTitle(),
                        book.getQuantity(),
                        book.getPrice(),
                        book.getUser().getId(),
                        book.getPublishedDate()))
                .collect(Collectors.toSet());
    }

    public List<AuthorDto> toDtoList(List<Author> entityList) {
        if (entityList == null) return Collections.emptyList();
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Author toEntity(CreateAuthorRequest request) {
        if (request == null) return null;
        return Author.builder()
                .fullName(request.getFullName())
                .birthOfDate(request.getBirthOfDate())
                .build();
    }
}
