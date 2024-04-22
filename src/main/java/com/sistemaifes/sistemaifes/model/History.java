package com.sistemaifes.sistemaifes.model;

import java.time.LocalTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistemaifes.sistemaifes.dto.request.HistoryRequestDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class History {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @Column
    private Date date;

    @Column(length = 100, nullable = false)
    String action;

    @Column(length = 100, nullable = false)
    String authorName;

    @Column
    LocalTime time;

    @Column(length = 100, nullable = false)
    String oldContent;

    @Column(length = 100, nullable = false)
    String newContent;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_allocation")
    @JsonIgnore
    private Allocation allocation;

    public History(HistoryRequestDTO data) {
        this.date = data.date();
        this.action = data.action();
        this.authorName = data.authorName();
        this.time = data.time();
        this.oldContent = data.oldContent();
        this.newContent = data.newContent();
        this.allocation = data.allocation();
    }

}
