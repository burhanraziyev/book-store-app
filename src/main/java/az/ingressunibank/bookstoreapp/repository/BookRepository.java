package az.ingressunibank.bookstoreapp.repository;

import az.ingressunibank.bookstoreapp.model.entity.Book;
import az.ingressunibank.bookstoreapp.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    Page<Book> findByUser(User publisher, Specification<Book> specification, Pageable pageable);
    Page<Book> findAll(Specification<Book> specification, Pageable pageable);
}