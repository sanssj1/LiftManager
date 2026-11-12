package ar.com.liftmanager.model;
import jakarta.persistence.*;
@Entity @Table(name="buildings")
public class Building {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false,length=140) private String name;
    @Column(nullable=false,length=240) private String address;
    @Column(nullable=false,length=100) private String city;
    @Column(length=120) private String contactName;
    @Column(length=40) private String contactPhone;
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getName(){return name;} public void setName(String name){this.name=name;}
    public String getAddress(){return address;} public void setAddress(String address){this.address=address;}
    public String getCity(){return city;} public void setCity(String city){this.city=city;}
    public String getContactName(){return contactName;} public void setContactName(String contactName){this.contactName=contactName;}
    public String getContactPhone(){return contactPhone;} public void setContactPhone(String contactPhone){this.contactPhone=contactPhone;}
}
