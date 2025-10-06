package backend.backend.rest.profile;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

public class RegisterRequest {
    String username;
    String pin;

    public String getUsername() {
        return username;
    }
    public String getPin() {
        return pin;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPin(String pin) {
        this.pin = pin;
    }

    
}
