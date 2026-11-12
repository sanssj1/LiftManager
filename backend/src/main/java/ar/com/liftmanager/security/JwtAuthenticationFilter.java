package ar.com.liftmanager.security;
import ar.com.liftmanager.repository.UserRepository; import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; import org.springframework.security.core.context.SecurityContextHolder; import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component; import org.springframework.web.filter.OncePerRequestFilter; import jakarta.servlet.*; import jakarta.servlet.http.*; import java.io.IOException; import java.util.List;
@Component public class JwtAuthenticationFilter extends OncePerRequestFilter {
 private final JwtService jwt; private final UserRepository users;
 public JwtAuthenticationFilter(JwtService jwt,UserRepository users){this.jwt=jwt;this.users=users;}
 @Override protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain)throws ServletException,IOException{
  String header=request.getHeader("Authorization");
  if(header!=null&&header.startsWith("Bearer "))try{String email=jwt.subject(header.substring(7));var user=users.findByEmailIgnoreCase(email).orElse(null);if(user!=null&&user.isActive()){var principal=User.withUsername(user.getEmail()).password("").authorities("ROLE_"+user.getRole().name()).build();SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(principal,null,principal.getAuthorities()));}}
  catch(Exception ignored){SecurityContextHolder.clearContext();}
  chain.doFilter(request,response);
 }
}
