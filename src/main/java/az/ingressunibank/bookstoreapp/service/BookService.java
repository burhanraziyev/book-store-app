package az.ingressunibank.bookstoreapp.service;

import az.ingressunibank.bookstoreapp.model.dto.BookDto;
import az.ingressunibank.bookstoreapp.model.request.CreateBookRequest;
import az.ingressunibank.bookstoreapp.util.PageFilter;
import az.ingressunibank.bookstoreapp.util.PageItem;

public interface BookService {
    BookDto createBook(CreateBookRequest request);
    PageItem<BookDto> getBooks(PageFilter filter);
    PageItem<BookDto> getBooksByPublisherId(Long userId,PageFilter filter);
}
