package me.otmane;

import java.sql.SQLException;
import java.util.List;

public interface DatabaseDAO<Type> {
    Type insert(Type type) throws SQLException;

    List<Type> all() throws SQLException;

    Type get(long pk) throws SQLException;

    void update(Type type) throws SQLException;

    void delete(long pk) throws SQLException;
}
