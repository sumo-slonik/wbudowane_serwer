package com.example.wbudowane_serwer.Entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentLocation {
    Employee employee;
    String roomId;

    public CurrentLocation(Employee employee, String roomId) {
        this.employee = employee;
        this.roomId = roomId;
    }
}
