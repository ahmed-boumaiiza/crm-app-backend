package com.crm.app.entities;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@Table(name="contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    @NotBlank(message = "This field should be not empty")
    private String firstName;
    @Column(name = "last_name")
    @NotBlank(message = "This field should be not empty")
    private String lastName;
    @Column(name = "company")
    private String company;
    @Column(name = "job")
    private String job;

    @Column(name = "contact_owner")
    private String contactOwner;
    @Column(name = "email",unique = true)
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            message = "Email format is invalid",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    @Column(name = "phone")
    @Pattern(regexp = "^\\+\\d{3}\\s\\d{8}$",
            message = "Phone number format is invalid")
    private String phone;
    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "state")
    private String state;

    @Column(name = "zip_code")
    private String zipCode;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "contact_activitiy",
            joinColumns = { @JoinColumn(name = "contact_id") },
            inverseJoinColumns = { @JoinColumn(name = "activity_id") })
    private Set<Activity> activities = new HashSet<>();

}