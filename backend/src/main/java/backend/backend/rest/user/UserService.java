package backend.backend.rest.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(String username, String pin) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists");
        } else {
            String encodedPin = passwordEncoder.encode(pin);
            User user = new User(username, encodedPin);
            return userRepository.save(user);
        }

    }
}
