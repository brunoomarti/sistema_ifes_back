package com.sistemaifes.sistemaifes.model;
 
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistemaifes.sistemaifes.dto.request.AllocationRequestDTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "allocation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Allocation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @Column
    private String startDate;

    @Column
    private String endDate;

    @Column
    private String startTime;

    @Column
    private String endTime;

    @Column
    private String weekDay;

    @Column
    private String type;

    @Column(length = 100)
    @Length(min = 3, max = 100)
    private String applicant;

    @ManyToOne
    @JoinColumn(name = "id_lesson")
    private Lesson lesson;

    @ManyToOne
    @JoinColumn(name = "id_event")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "id_location")
    private Local location;

    @ManyToOne
    @JoinColumn(name = "id_classe")
    private Classe classe;

    @ManyToMany
    @JoinTable(
        name = "allocation_schedule",
        joinColumns = @JoinColumn(name = "allocation_id"),
        inverseJoinColumns = @JoinColumn(name = "schedule_id")
    )
    private List<Schedule> selectedTimes;

    @OneToMany(mappedBy = "allocation", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<History> histories;

    @Column
    private Boolean active;

    public Allocation(AllocationRequestDTO data) {
        this.startDate = data.startDate();
        this.endDate = data.endDate();
        this.startTime = data.startTime();
        this.endTime = data.endTime();
        this.weekDay = data.weekDay();
        this.type = data.type();
        this.applicant = data.applicant();
        this.lesson = data.lesson();
        this.selectedTimes = data.selectedTimes();
        this.event = data.event();
        this.location = data.location();
        this.classe = data.classe();
        this.active = data.active();
    }

}
