package me.otmane.demo.springboot.entities;

import lombok.Data;
import lombok.ToString;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Base64;
import java.util.Objects;

@Data
@ToString(exclude = {"nationality"})
public class User {
    private Long pk;

    private String username;

    private String password;

    private Timestamp createdAt = Timestamp.from(Instant.now());

    private Nationality nationality;

    private void prePersist() {
        password = Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(pk, user.pk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk);
    }
}
