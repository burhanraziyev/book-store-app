package az.ingressunibank.bookstoreapp.service.impl;

import az.ingressunibank.bookstoreapp.mapper.BookMapper;
import az.ingressunibank.bookstoreapp.model.dto.BookDto;
import az.ingressunibank.bookstoreapp.model.entity.Book;
import az.ingressunibank.bookstoreapp.model.entity.User;
import az.ingressunibank.bookstoreapp.model.request.CreateBookRequest;
import az.ingressunibank.bookstoreapp.repository.BookRepository;
import az.ingressunibank.bookstoreapp.service.BookService;
import az.ingressunibank.bookstoreapp.service.UserService;
import az.ingressunibank.bookstoreapp.util.CustomSpecification;
import az.ingressunibank.bookstoreapp.util.PageFilter;
import az.ingressunibank.bookstoreapp.util.PageItem;
import az.ingressunibank.bookstoreapp.util.PageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper mapper;
    private final UserService publisherService;

    @Override
    public BookDto createBook(CreateBookRequest request) {
        return null;
    }

    @Override
    public PageItem<BookDto> getBooks(PageFilter filter) {
        CustomSpecification<Book> customSpecification = new CustomSpecification<>(filter.getQueryParams());
        Pageable pageRequest = PageUtil.of(filter.getPageInfo());
        Page<Book> bookPage = bookRepository.findAll(customSpecification,pageRequest);
        var books = new PageImpl<>(bookPage.getContent()).map(mapper::toDto);
        return PageUtil.convert(books);
    }

    @Override
    public PageItem<BookDto> getBooksByPublisherId(Long userId, PageFilter filter) {
        User publisher = publisherService.findByUserId(userId);
        CustomSpecification<Book> customSpecification = new CustomSpecification<>(filter.getQueryParams());
        Pageable pageRequest = PageUtil.of(filter.getPageInfo());
        Page<Book> bookPage = bookRepository.findByUser(publisher,customSpecification,pageRequest);
        var books = new PageImpl<>(bookPage.getContent()).map(mapper::toDto);
        return PageUtil.convert(books);
    }
}
