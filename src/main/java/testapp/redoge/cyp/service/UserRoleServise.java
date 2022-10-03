package testapp.redoge.cyp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testapp.redoge.cyp.entity.UserRole;
import testapp.redoge.cyp.repository.UserRoleRepository;

import java.util.List;
import java.util.Set;

@Service
public class UserRoleServise {

    @Autowired
    UserRoleRepository userRoleRepository;

    public List<UserRole> getAll(){
        return userRoleRepository.findAll();
    }

    public UserRole getById(Long id){return userRoleRepository.findById(id).orElse(null);}

    public UserRole getByName(String name){return userRoleRepository.findByName(name).orElse(null);}
}
