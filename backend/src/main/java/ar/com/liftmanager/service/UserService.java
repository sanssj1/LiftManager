package ar.com.liftmanager.service;
import ar.com.liftmanager.dto.ApiDtos.*;import ar.com.liftmanager.model.*;import ar.com.liftmanager.repository.*;import org.springframework.security.crypto.password.PasswordEncoder;import org.springframework.stereotype.Service;import java.util.*;
@Service public class UserService {
 private final UserRepository users;private final BuildingRepository buildings;private final PasswordEncoder encoder;private final CurrentUserService current;
 public UserService(UserRepository u,BuildingRepository b,PasswordEncoder e,CurrentUserService c){users=u;buildings=b;encoder=e;current=c;}
 public List<UserView> list(){current.requireAdmin();return users.findAll().stream().map(this::view).toList();}
 public UserView create(UserRequest r){current.requireAdmin();if(users.findByEmailIgnoreCase(r.email()).isPresent())throw BusinessException.conflict("Ya existe un usuario con ese email");if(r.role()==Role.ADMIN)throw BusinessException.badRequest("No se pueden crear administradores desde esta pantalla");AppUser u=new AppUser();u.setName(r.name());u.setEmail(r.email().trim().toLowerCase());u.setPasswordHash(encoder.encode(r.password()));u.setRole(r.role());u.setActive(true);if(r.role()==Role.BUILDING_MANAGER){if(r.buildingId()==null)throw BusinessException.badRequest("El administrador del edificio necesita un edificio asignado");u.setBuilding(buildings.findById(r.buildingId()).orElseThrow(()->BusinessException.notFound("Edificio no encontrado")));}else if(r.buildingId()!=null)throw BusinessException.badRequest("Los técnicos no se asocian a un único edificio");return view(users.save(u));}
 private UserView view(AppUser u){return new UserView(u.getId(),u.getName(),u.getEmail(),u.getRole(),u.isActive(),u.getBuilding()==null?null:u.getBuilding().getId());}
}
