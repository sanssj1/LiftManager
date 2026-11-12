package ar.com.liftmanager.service;
import ar.com.liftmanager.dto.ApiDtos.*; import ar.com.liftmanager.model.*; import ar.com.liftmanager.repository.*; import org.springframework.stereotype.Service; import java.util.*;
@Service public class BuildingService {
 private final BuildingRepository buildings; private final CurrentUserService current;
 public BuildingService(BuildingRepository b,CurrentUserService c){buildings=b;current=c;}
 public List<BuildingView> list(){AppUser u=current.get();List<Building> list=u.getRole()==Role.BUILDING_MANAGER?(u.getBuilding()==null?List.of():List.of(u.getBuilding())):buildings.findAll();return list.stream().map(this::view).toList();}
 public BuildingView create(BuildingRequest r){current.requireAdmin();Building b=new Building();b.setName(r.name());b.setAddress(r.address());b.setCity(r.city());b.setContactName(r.contactName());b.setContactPhone(r.contactPhone());return view(buildings.save(b));}
 public Building get(Long id){AppUser u=current.get();Building b=buildings.findById(id).orElseThrow(()->BusinessException.notFound("Edificio no encontrado"));if(u.getRole()==Role.BUILDING_MANAGER&&(u.getBuilding()==null||!u.getBuilding().getId().equals(id)))throw BusinessException.forbidden("No podés acceder a este edificio");return b;}
 private BuildingView view(Building b){return new BuildingView(b.getId(),b.getName(),b.getAddress(),b.getCity(),b.getContactName(),b.getContactPhone());}
}
