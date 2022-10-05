package testapp.redoge.cyp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testapp.redoge.cyp.entity.User;
import testapp.redoge.cyp.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void save(User user) {userRepository.save(user);}
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
    public Boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }
    public Boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }
    public Boolean existsByPhoneNumber(String phoneNumber){return userRepository.existsByPhoneNumber(phoneNumber);}
    public List<User> getAll(){return userRepository.findAll();}
}
