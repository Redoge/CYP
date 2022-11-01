package testapp.redoge.cyp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import testapp.redoge.cyp.entity.User;
import testapp.redoge.cyp.entity.UserRole;
import testapp.redoge.cyp.service.UserRoleService;
import testapp.redoge.cyp.service.UserService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@RequestMapping("/api/v1/fs")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)

public class FirstStartController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @GetMapping("/fs")
    public ResponseEntity<?> firstStart(){
        userRoleService.addRole(new UserRole("ROLE_ADMIN"));
        userRoleService.addRole(new UserRole("ROLE_CUSTOMER"));
        userRoleService.addRole(new UserRole("ROLE_EMPLOYEE"));

        User userRedoge = new User("Redoge", "mail.redoge@gmail.com", passwordEncoder.encode("redoge"), "Danylo", "Shchetinin", "+380931806738", 0 );
        Set<UserRole> roles = new HashSet<>();
        roles.add(userRoleService.getByName("ROLE_ADMIN"));
        userRedoge.setRoles(roles);
        userRedoge.setRegistered(Timestamp.valueOf(LocalDateTime.now()));
        userRedoge.setEnabled(true);
        userService.save(userRedoge);

        User userSasha = new User("Sasha", "sasha@gmail.com", passwordEncoder.encode("sasha"), "Olexandr", "Prodyvus", "+380977372325", 0 );
        userSasha.setRoles(roles);
        userSasha.setRegistered(Timestamp.valueOf(LocalDateTime.now()));
        userSasha.setEnabled(true);
        userService.save(userSasha);

        return ResponseEntity.ok("Added roles[ROLE_ADMIN, ROLE_CUSTOMER, ROLE_EMPLOYEE]|||\n" +
                "Added user [Redoge-redoge; Sasha-sasha]");
    }
}
