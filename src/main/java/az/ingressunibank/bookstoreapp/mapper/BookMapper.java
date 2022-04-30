package az.ingressunibank.bookstoreapp.mapper;

import az.ingressunibank.bookstoreapp.model.dto.BookDto;
import az.ingressunibank.bookstoreapp.model.dto.BooksAuthorDto;
import az.ingressunibank.bookstoreapp.model.entity.Author;
import az.ingressunibank.bookstoreapp.model.entity.Book;
import az.ingressunibank.bookstoreapp.model.entity.User;
import az.ingressunibank.bookstoreapp.model.request.CreateBookRequest;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public BookDto toDto(Book entity) {
        if (entity == null) return null;
        return new BookDto(
                entity.getId(),
                entity.getTitle(),
                entity.getQuantity(),
                entity.getPrice(),
                entity.getUser().getId(),
                getBookAuthors(entity.getAuthors()),
                entity.getPublishedDate()
        );
    }

    private Set<BooksAuthorDto> getBookAuthors(Set<Author> authors) {
        if (authors == null) return Collections.emptySet();
        return authors.stream()
                .map(author -> new BooksAuthorDto(
                        author.getId(),
                        author.getFullName(),
                        author.getBirthOfDate()))
                .collect(Collectors.toSet());
    }

    public List<BookDto> toDtoList(List<Book> entityList) {
        if (entityList == null) return Collections.emptyList();
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Book toEntity(CreateBookRequest request, Set<Author> authors, User user) {
        if (request == null) return null;
        return Book.builder()
                .authors(authors)
                .user(user)
                .quantity(request.getQuantity())
                .title(request.getTitle())
                .price(request.getPrice())
                .build();
    }
}
