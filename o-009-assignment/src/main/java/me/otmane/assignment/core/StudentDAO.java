package me.otmane.assignment.core;

import me.otmane.assignment.models.Student;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO extends DatabaseDAO<Student, Long> {
    public StudentDAO(Class<Student> klass) throws SQLException {
        super(klass);
    }

    public List<Student> byBranch(Long pk) throws SQLException {
        PreparedStatement st = cnx.prepareStatement(SqlUtils.getSelectByParamQuery(klass, "students.branch_pk"));
        st.setObject(1, pk);

        st.execute();
        ResultSet rs = st.getResultSet();

        ArrayList<Student> records = new ArrayList<>();

        try {
            Method map = klass.getDeclaredMethod("map", ResultSet.class);

            while (rs.next()) {
                records.add((Student) map.invoke(null, rs));
            }

            rs.close();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        return records;
    }
}
