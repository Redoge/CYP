package testapp.redoge.cyp.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import testapp.redoge.cyp.entity.User;
import testapp.redoge.cyp.repository.UserRepository;

public class UserDatailsServisImpl implements UserDetailsService{
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User NOT FOUND with username" + username));
        return UserDetailsImpl.build(user);
    }
}
