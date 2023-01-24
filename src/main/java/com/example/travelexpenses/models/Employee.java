package com.example.travelexpenses.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "tbl_m_employee")
public class Employee {
    @Id
    @Column (name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    @Column(name = "fullname")  
    private String Fullname;

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    @Column (name = "email")
    private String Email;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column (name = "birthdate")
    private LocalDate Birthdate;


    public LocalDate getBirthdate() {
        return Birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        Birthdate = birthdate;
    }

    @Column (name = "address")
    private String Address;

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
