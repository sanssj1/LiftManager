package ar.com.liftmanager.service;
import ar.com.liftmanager.model.*; import ar.com.liftmanager.repository.UserRepository; import org.springframework.security.core.context.SecurityContextHolder; import org.springframework.stereotype.Service;
@Service public class CurrentUserService {
 private final UserRepository users; public CurrentUserService(UserRepository users){this.users=users;}
 public AppUser get(){String email=SecurityContextHolder.getContext().getAuthentication().getName();return users.findByEmailIgnoreCase(email).orElseThrow(()->BusinessException.notFound("Usuario no encontrado"));}
 public boolean isAdmin(AppUser u){return u.getRole()==Role.ADMIN;}
 public void requireAdmin(){if(get().getRole()!=Role.ADMIN)throw BusinessException.forbidden("Se requiere rol administrador");}
 public void requireAdminOrTechnician(){if(!ListRoles.ADMIN_TECH.contains(get().getRole()))throw BusinessException.forbidden("No tenés permisos para esta operación");}
 private static final class ListRoles {static final java.util.List<Role> ADMIN_TECH=java.util.List.of(Role.ADMIN,Role.TECHNICIAN);}
}
