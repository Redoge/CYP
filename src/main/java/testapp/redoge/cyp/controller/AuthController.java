package testapp.redoge.cyp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import testapp.redoge.cyp.config.jwt.JwtUtils;
import testapp.redoge.cyp.entity.User;
import testapp.redoge.cyp.entity.UserRole;
import testapp.redoge.cyp.pojo.JwtResponse;
import testapp.redoge.cyp.pojo.LoginRequest;
import testapp.redoge.cyp.pojo.MessageResponse;
import testapp.redoge.cyp.pojo.SignupRequest;
import testapp.redoge.cyp.service.UserDetailsImpl;
import testapp.redoge.cyp.service.UserRoleService;
import testapp.redoge.cyp.service.UserService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles
                ));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest){
        if(userService.existsByUsername(signupRequest.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is exist"));
        }
        if(userService.existsByEmail(signupRequest.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is exist"));
        }
        if(userService.existsByPhoneNumber(signupRequest.getPhoneNumber())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Phone number is exist"));
        }

        User user = new User(signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()),
                signupRequest.getFirstName(),
                signupRequest.getLastName(),
                signupRequest.getPhoneNumber(),
                0);

        Set<String> reqRoles = signupRequest.getRoles();
        Set<UserRole> roles = new HashSet<>();
        for(String rolesName: reqRoles){
            UserRole role = userRoleService.getByName(rolesName);
            if(role != null){
                roles.add(role);
            }else{
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Role " + rolesName + " not found"));
            }
        }
        user.setRoles(roles);
        user.setRegistered(Timestamp.valueOf(LocalDateTime.now()));
        user.setEnabled(true);
        userService.save(user);
        return ResponseEntity.ok(new MessageResponse("User "+user.getUsername()+" CREATED"));
    }

}
