package me.otmane.assignment.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import me.otmane.assignment.core.Column;
import me.otmane.assignment.core.Entity;
import me.otmane.assignment.core.Table;

import java.sql.ResultSet;
import java.sql.SQLException;


@Data
@SuperBuilder
@Table(name = "branches")
@EqualsAndHashCode(callSuper = true)
public class Branch extends Entity {
    @Column(name = "name")
    private String name;

    public static Branch map(ResultSet rs) throws SQLException {
        return builder()
                .pk(rs.getLong("pk"))
                .name(rs.getString("name"))
                .build();
    }
}
