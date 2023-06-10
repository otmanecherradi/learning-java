package me.otmane.demo.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Data
@Entity
@ToString(exclude = {"users"})
@Table(name = "nationalities")
@NamedQuery(name = "models.Nationality.all", query = "from Nationality n order by n.createdAt DESC")
//@NamedQuery(name = "models.Nationality.all", query = "from Nationality n order by n.createdAt DESC")
public class Nationality {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long pk;

    @Column(unique = true, updatable = false)
    private String name;

    @Column(updatable = false)
    private Timestamp createdAt = Timestamp.from(Instant.now());

    @OneToMany(mappedBy = "nationality")
    private List<User> users;
}
