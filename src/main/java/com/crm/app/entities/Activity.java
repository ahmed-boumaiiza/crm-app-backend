package com.crm.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@Table(name="activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date")
    private String date;
    @Column(name = "activity_type")
    private String activityType;

    @Column(name = "subject")
    private String subject;

    @Column(name = "note")
    private String note;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "activities")
    @JsonIgnore
    private Set<Contact> participants = new HashSet<>();

}
