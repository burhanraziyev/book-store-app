package az.ingressunibank.bookstoreapp.controller;

import az.ingressunibank.bookstoreapp.model.dto.BookDto;
import az.ingressunibank.bookstoreapp.model.request.CreateBookRequest;
import az.ingressunibank.bookstoreapp.service.BookService;
import az.ingressunibank.bookstoreapp.util.PageFilter;
import az.ingressunibank.bookstoreapp.util.PageItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/books")
public class BookController {
    private final BookService bookService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_PUBLISHER')")
    public ResponseEntity<BookDto> createBook(@RequestBody @Valid CreateBookRequest request){
        return new ResponseEntity<>(bookService.createBook(request), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_PUBLISHER','ROLE_USER')")
    public ResponseEntity<PageItem<BookDto>> getBooks(@RequestBody @Valid PageFilter filter){
        return new ResponseEntity<>(bookService.getBooks(filter),HttpStatus.OK);
    }
}
