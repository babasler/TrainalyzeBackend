package backend.backend.rest.auth;

public class AuthRequest {
  private String username;
  private String pin;

  public String getUsername() { return username; }
  public void setUsername(String username) { this.username = username; }

  public String getPin() { return pin; }
  public void setPin(String password) { this.pin = password; }
}
