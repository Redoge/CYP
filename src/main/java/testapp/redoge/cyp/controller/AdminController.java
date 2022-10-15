package testapp.redoge.cyp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import testapp.redoge.cyp.entity.User;
import testapp.redoge.cyp.entity.UserRole;
import testapp.redoge.cyp.service.UserRoleService;
import testapp.redoge.cyp.service.UserService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("/api/admin")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @GetMapping(value = "/user", produces = APPLICATION_JSON_VALUE)
    public List<User> allUser(){return userService.getAll();}

    @GetMapping(value = "/role", produces = APPLICATION_JSON_VALUE)
    public List<UserRole> allRole(){return userRoleService.getAll();}

    @PostMapping("/roles")
    public ResponseEntity<?> addRole(@RequestBody UserRole userRole){
        userRoleService.addRole(userRole);
        return ResponseEntity.ok(userRole);
    }

}
