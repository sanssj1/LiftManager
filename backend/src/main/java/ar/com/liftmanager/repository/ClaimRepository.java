package ar.com.liftmanager.repository;
import ar.com.liftmanager.model.Claim; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface ClaimRepository extends JpaRepository<Claim,Long> { List<Claim> findByAssignedToId(Long id); List<Claim> findByElevatorBuildingId(Long buildingId); }
