package ar.com.liftmanager.repository;
import ar.com.liftmanager.model.Elevator; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface ElevatorRepository extends JpaRepository<Elevator,Long> { List<Elevator> findByBuildingId(Long buildingId); Optional<Elevator> findByIdAndBuildingId(Long id,Long buildingId); }
