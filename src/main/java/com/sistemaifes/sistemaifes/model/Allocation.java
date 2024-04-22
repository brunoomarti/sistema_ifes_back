package com.sistemaifes.sistemaifes.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistemaifes.sistemaifes.dto.request.AllocationRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Date startDate;

    @Column
    private Date endDate;

    @Column(length = 100, nullable = false)
    private String type;

    @OneToMany(mappedBy = "allocation") 
    @JsonIgnore
    private List<Lesson> lessons;

    @OneToMany(mappedBy = "allocation") 
    @JsonIgnore
    private List<Event> events;

    @ManyToOne
    @JoinColumn(name = "id_location")
    private Local location;

    @ManyToOne
    @JoinColumn(name = "id_classe")
    private Classe classe;

    @OneToMany(mappedBy = "allocation") 
    @JsonIgnore
    private List<AllocSchedule> selectedTimes;

    @OneToMany(mappedBy = "allocation") 
    @JsonIgnore
    private List<History> histories;

    public Allocation(AllocationRequestDTO data) {
        this.startDate = data.startDate();
        this.endDate = data.endDate();
        this.type = data.type();
        this.lessons = data.lessons();
        this.events = data.events();
        this.location = data.location();
        this.classe = data.classe();
        this.selectedTimes = data.selectedTimes();
    }

}
