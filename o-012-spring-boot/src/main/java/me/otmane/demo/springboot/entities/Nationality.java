package me.otmane.demo.springboot.entities;


import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Data
@ToString(exclude = {"users"})
public class Nationality {
    private Long pk;

    private String name;

    private Timestamp createdAt = Timestamp.from(Instant.now());

    private List<User> users;
}
