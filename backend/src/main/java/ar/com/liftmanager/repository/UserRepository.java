package ar.com.liftmanager.repository;
import ar.com.liftmanager.model.*; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface UserRepository extends JpaRepository<AppUser,Long> {
 Optional<AppUser> findByEmailIgnoreCase(String email); List<AppUser> findByRole(Role role); List<AppUser> findByBuildingId(Long buildingId);
}
