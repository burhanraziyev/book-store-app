package az.ingressunibank.bookstoreapp.controller;

import az.ingressunibank.bookstoreapp.model.dto.UserDto;
import az.ingressunibank.bookstoreapp.model.request.RegisterRequest;
import az.ingressunibank.bookstoreapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getByUserId(@PathVariable @Min(1) Long userId){
        return new ResponseEntity<>(userService.getByUserId(userId), OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserDto> signUp(@RequestBody @Valid RegisterRequest request){
        return new ResponseEntity<>(userService.register(request), CREATED);
    }
}
