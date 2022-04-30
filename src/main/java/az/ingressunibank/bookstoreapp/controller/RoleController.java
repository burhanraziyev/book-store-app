package az.ingressunibank.bookstoreapp.controller;

import az.ingressunibank.bookstoreapp.model.dto.RoleDto;
import az.ingressunibank.bookstoreapp.model.request.CreateRoleRequest;
import az.ingressunibank.bookstoreapp.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/roles")
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleDto> createRole(@RequestBody @Valid CreateRoleRequest request){
        return new ResponseEntity<>(roleService.createRole(request), HttpStatus.CREATED);
    }
}
