package backend.backend.rest.auth;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trainalyze")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtUtil;

  private final Logger logger = org.slf4j.LoggerFactory.getLogger(AuthController.class);

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody AuthRequest request) {
    logger.info("Login attempt for user: {} with pin: {}", request.getUsername(), request.getPin());
    try {
      Authentication auth = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPin()));
      UserDetails user = (UserDetails) auth.getPrincipal();
      String token = jwtUtil.generateToken(user);
      logger.info("Login successful for user: {}", request.getUsername());
      return ResponseEntity.ok(new AuthResponse(token));
    } catch (BadCredentialsException ex) {
      logger.error("Login failed for user: {}", request.getUsername(), ex);
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falscher Benutzername oder Passwort");
    }
  }
}
