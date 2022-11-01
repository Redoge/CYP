package testapp.redoge.cyp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testapp.redoge.cyp.entity.Order;
import testapp.redoge.cyp.entity.User;
import testapp.redoge.cyp.pojo.ChangeActiveStatusRequest;
import testapp.redoge.cyp.repository.UserRepository;

import java.util.List;
import java.util.Optional;

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

    @Transactional
    public void changeActiveStatus(ChangeActiveStatusRequest changeActiveStatusRequest) {
        Optional<User> optionalUser = userRepository.findByUsername(changeActiveStatusRequest.getUsername());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEnabled(changeActiveStatusRequest.isActive());
            userRepository.save(user);
        }
    }
}
