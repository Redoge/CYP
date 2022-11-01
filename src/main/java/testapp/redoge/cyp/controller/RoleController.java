package testapp.redoge.cyp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import testapp.redoge.cyp.service.UserRoleService;
import testapp.redoge.cyp.service.UserService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/roles")
public class RoleController {
    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("")
    public ResponseEntity<?> getRoles(){
        return ResponseEntity.ok(userRoleService.getAll());
    }
}
