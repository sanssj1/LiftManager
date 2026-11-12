package ar.com.liftmanager.model;

import jakarta.persistence.*;

@Entity @Table(name="app_users", indexes=@Index(name="idx_user_email", columnList="email", unique=true))
public class AppUser {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, length=120) private String name;
    @Column(nullable=false, unique=true, length=180) private String email;
    @Column(nullable=false, length=100) private String passwordHash;
    @Enumerated(EnumType.STRING) @Column(nullable=false, length=30) private Role role;
    @Column(nullable=false) private boolean active=true;
    @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="building_id") private Building building;
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getName(){return name;} public void setName(String name){this.name=name;}
    public String getEmail(){return email;} public void setEmail(String email){this.email=email;}
    public String getPasswordHash(){return passwordHash;} public void setPasswordHash(String passwordHash){this.passwordHash=passwordHash;}
    public Role getRole(){return role;} public void setRole(Role role){this.role=role;}
    public boolean isActive(){return active;} public void setActive(boolean active){this.active=active;}
    public Building getBuilding(){return building;} public void setBuilding(Building building){this.building=building;}
}
