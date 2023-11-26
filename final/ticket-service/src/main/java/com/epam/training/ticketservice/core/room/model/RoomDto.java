package com.epam.training.ticketservice.core.room.model;

import lombok.Value;

@Value
public class RoomDto {

    String name;
    Integer row;
    Integer col;

    public static RoomBuilder builder() {
        return new RoomBuilder();
    }

    public static class RoomBuilder {

        private String name;
        private Integer row;
        private Integer col;

        public RoomBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public RoomBuilder withRow(Integer row) {
            this.row = row;
            return this;
        }

        public RoomBuilder withCol(Integer col) {
            this.col = col;
            return this;
        }

        public RoomDto build() {
            return new RoomDto(name, row, col);
        }
    }
}
