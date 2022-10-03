package testapp.redoge.cyp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import testapp.redoge.cyp.entity.UserRole;
import testapp.redoge.cyp.service.UserRoleServise;

import java.util.List;


@RestController()
public class MainTestController {

    @Autowired
    UserRoleServise userRoleServise;

    @GetMapping("/test/roles")
    List<UserRole> getRoles(){
        return userRoleServise.getAll();
    }

}
