package ar.com.liftmanager.repository;
import ar.com.liftmanager.model.Building; import org.springframework.data.jpa.repository.JpaRepository;
public interface BuildingRepository extends JpaRepository<Building,Long> {}
