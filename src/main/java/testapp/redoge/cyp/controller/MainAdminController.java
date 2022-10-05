package testapp.redoge.cyp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/test")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MainAdminController {

    @GetMapping("/all")
    @PreAuthorize("permitAll()")
    public String getAll(){
        return "public API";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public String getUserApi(){
        return "CUSTOMER API";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAdminApi(){
        return "ADMIN API";
    }

}
