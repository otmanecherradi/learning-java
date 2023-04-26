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
@Table(name = "students")
@EqualsAndHashCode(callSuper = true)
public class Student extends Entity {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender", type = Gender.class)
    private Gender gender;

    @Column(name = "branch_pk", references = Branch.class)
    private Branch branch;

    public static Student map(ResultSet rs) throws SQLException {
        Branch b = null;

        if (rs.getString("branch_pk") != null) {
            b = Branch.builder()
                    .pk(rs.getLong("branch_pk"))
                    .name(rs.getString("branches_name"))
                    .build();
        }

        return builder()
                .pk(rs.getLong("pk"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .gender(Gender.valueOf(rs.getString("gender")))
                .branch(b)
                .build();
    }
}
