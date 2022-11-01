package testapp.redoge.cyp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import testapp.redoge.cyp.entity.User;
import testapp.redoge.cyp.entity.UserRole;
import testapp.redoge.cyp.pojo.ChangeActiveStatusRequest;
import testapp.redoge.cyp.pojo.MessageResponse;
import testapp.redoge.cyp.service.UserDetailsImpl;
import testapp.redoge.cyp.service.UserRoleService;
import testapp.redoge.cyp.service.UserService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("/api/v1/admin")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @GetMapping(value = "/users", produces = APPLICATION_JSON_VALUE)
    public List<User> allUser(){return userService.getAll();}

    @PostMapping("/roles")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addRole(@RequestBody UserRole userRole){
        if(userRoleService.existsByName(userRole.getName())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: User role is exist"));
        }
        userRoleService.addRole(userRole);
        return ResponseEntity.ok(userRole);
    }

    @PostMapping("/users/enabled")
    @Transactional
    public ResponseEntity<?> bannedUser(@RequestBody ChangeActiveStatusRequest changeActiveStatusRequest) {
        if(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername().equals(changeActiveStatusRequest.getUsername())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: You can't banned yourself"));
        }
        userService.changeActiveStatus(changeActiveStatusRequest);
        return ResponseEntity.ok(changeActiveStatusRequest);
    }

}
