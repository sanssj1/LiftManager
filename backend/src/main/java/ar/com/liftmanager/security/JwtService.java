package ar.com.liftmanager.security;
import ar.com.liftmanager.model.AppUser; import io.jsonwebtoken.*; import io.jsonwebtoken.security.Keys; import javax.crypto.SecretKey; import java.nio.charset.StandardCharsets; import java.util.Date;
import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Service;
@Service public class JwtService {
 private final SecretKey key; private final long expirationMs;
 public JwtService(@Value("${app.jwt.secret}") String secret,@Value("${app.jwt.expiration-ms:28800000}") long expirationMs){byte[] bytes=secret.getBytes(StandardCharsets.UTF_8);if(bytes.length<32)throw new IllegalArgumentException("APP_JWT_SECRET debe tener al menos 32 caracteres");this.key=Keys.hmacShaKeyFor(bytes);this.expirationMs=expirationMs;}
 public String generate(AppUser user){Date now=new Date();return Jwts.builder().subject(user.getEmail()).claim("role",user.getRole().name()).issuedAt(now).expiration(new Date(now.getTime()+expirationMs)).signWith(key).compact();}
 public String subject(String token){return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();}
}
