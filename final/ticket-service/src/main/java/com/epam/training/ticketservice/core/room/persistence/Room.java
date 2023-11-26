package com.epam.training.ticketservice.core.room.persistence;

import com.epam.training.ticketservice.core.room.model.RoomDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class Room {

    @Column(unique = true)
    private String name;
    private int row;
    private int col;

    public Room(RoomDto roomDto) {
        this.name = roomDto.getName();
        this.row = roomDto.getRow();
        this.col = roomDto.getCol();
    }

    public Room(String name, Integer row, Integer col) {
        this.name = name;
        this.row = row;
        this.col = col;
    }
}
