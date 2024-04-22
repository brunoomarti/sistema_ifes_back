package com.sistemaifes.sistemaifes.dto.response;

import java.time.LocalTime;
import java.util.Date;

import com.sistemaifes.sistemaifes.model.Allocation;
import com.sistemaifes.sistemaifes.model.History;

public record HistoryResponseDTO(
    Long _id,
    Date date,
    String action,
    String authorName,
    LocalTime time,
    String oldContent,
    String newContent,
    Allocation allocation
) {
    public HistoryResponseDTO(History history){
        this(
            history.get_id(),
            history.getDate(),
            history.getAction(),
            history.getAuthorName(),
            history.getTime(),
            history.getOldContent(),
            history.getNewContent(),
            history.getAllocation()
        );
    }
}
