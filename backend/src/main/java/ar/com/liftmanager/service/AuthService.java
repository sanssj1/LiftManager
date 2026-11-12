package ar.com.liftmanager.service;
import ar.com.liftmanager.dto.ApiDtos.*; import ar.com.liftmanager.repository.UserRepository; import ar.com.liftmanager.security.JwtService; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.stereotype.Service;
@Service public class AuthService {
 private final UserRepository users; private final PasswordEncoder encoder; private final JwtService jwt;
 public AuthService(UserRepository users,PasswordEncoder encoder,JwtService jwt){this.users=users;this.encoder=encoder;this.jwt=jwt;}
 public LoginResponse login(LoginRequest req){var user=users.findByEmailIgnoreCase(req.email()).orElseThrow(()->BusinessException.forbidden("Credenciales incorrectas"));if(!user.isActive()||!encoder.matches(req.password(),user.getPasswordHash()))throw BusinessException.forbidden("Credenciales incorrectas");return new LoginResponse(jwt.generate(user),user.getId(),user.getName(),user.getEmail(),user.getRole());}
}
