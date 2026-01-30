package backend.backend.rest.user;

import backend.backend.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    
    public User updateUser(User user) {
        return userRepository.save(user);
    }
    
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

     public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            throw new UnauthorizedException("User is not authenticated");
        }
        return auth.getName();
    }
}
