package com.netWork.backend.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String company;

    private String position;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean currentlyWorking;

    private boolean isPublic;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
}
