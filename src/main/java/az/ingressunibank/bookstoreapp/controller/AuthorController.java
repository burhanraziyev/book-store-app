package az.ingressunibank.bookstoreapp.controller;

import az.ingressunibank.bookstoreapp.model.dto.AuthorDto;
import az.ingressunibank.bookstoreapp.model.request.CreateAuthorRequest;
import az.ingressunibank.bookstoreapp.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/authors")
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_PUBLISHER')")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody @Valid CreateAuthorRequest request){
        return new ResponseEntity<>(authorService.createAuthor(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAuthors(){
        return new ResponseEntity<>(authorService.getAuthors(),HttpStatus.OK);
    }
}
