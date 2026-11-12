package ar.com.liftmanager.model;
import jakarta.persistence.*;
@Entity @Table(name="elevators", uniqueConstraints=@UniqueConstraint(name="uk_building_elevator_code",columnNames={"building_id","code"}))
public class Elevator {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false,length=40) private String code;
    @Column(length=80) private String brand;
    @Column(length=80) private String model;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=30) private ElevatorStatus status=ElevatorStatus.OPERATIONAL;
    @ManyToOne(fetch=FetchType.EAGER,optional=false) @JoinColumn(name="building_id",nullable=false) private Building building;
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getCode(){return code;} public void setCode(String code){this.code=code;}
    public String getBrand(){return brand;} public void setBrand(String brand){this.brand=brand;}
    public String getModel(){return model;} public void setModel(String model){this.model=model;}
    public ElevatorStatus getStatus(){return status;} public void setStatus(ElevatorStatus status){this.status=status;}
    public Building getBuilding(){return building;} public void setBuilding(Building building){this.building=building;}
}
