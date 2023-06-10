package me.otmane.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Base64;

@Data
@Entity
@Table(name = "users")
@ToString(exclude = {"nationality"})
@NamedQuery(name = "models.User.all", query = "from User u order by u.createdAt DESC")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long pk;

    @Column(unique = true, updatable = false)
    private String username;

    @Column()
    private String password;

    @Column(updatable = false)
    private Timestamp createdAt = Timestamp.from(Instant.now());

    @ManyToOne()
    @JoinColumn(name = "nationality")
    private Nationality nationality;

    @PrePersist
    private void prePersist() {
        password = Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8));
    }
}
