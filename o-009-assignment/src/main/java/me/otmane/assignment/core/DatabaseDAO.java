package me.otmane.assignment.core;

import me.otmane.assignment.models.Gender;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDAO<T extends Entity, PkType extends Long> {
    protected final Connection cnx;
    private final Class<T> klass;

    public DatabaseDAO(Class<T> klass) throws SQLException {
        this.klass = klass;

        cnx = ConnectionFactory.getInstance();
    }

    public T insert(T type) throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        PreparedStatement st = cnx.prepareStatement(SqlUtils.getInsertQuery(klass));

        ArrayList<Field> declaredFields = new ArrayList<>();
        SqlUtils.getAllFields(declaredFields, klass);
        for (int idx = 0; idx < declaredFields.size(); idx++) {
            Column fieldAnnotation = declaredFields.get(idx).getAnnotation(Column.class);

            if (fieldAnnotation.insertable()) {
                declaredFields.get(idx).setAccessible(true);
                var value = declaredFields.get(idx).get(type);

                if (value instanceof String)
                    st.setObject(idx, value);
                else if (value instanceof Gender)
                    st.setObject(idx, ((Gender) value).getValue());
                else if (value instanceof Entity)
                    st.setObject(idx, ((Entity) value).getPk());
                else
                    st.setObject(idx, null);
            }
        }

        st.execute();

        ResultSet rs = st.getResultSet();
        rs.next();
        type = this.get((PkType) Long.valueOf(rs.getLong("pk")));
        rs.close();
        st.close();

        return type;
    }

    public List<T> all() throws SQLException {
        ResultSet rs = cnx.createStatement().executeQuery(SqlUtils.getSelectQuery(klass));

        ArrayList<T> records = new ArrayList<>();

        try {
            Method map = klass.getDeclaredMethod("map", ResultSet.class);

            while (rs.next()) {
                records.add((T) map.invoke(null, rs));
            }

            rs.close();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        return records;
    }

    public T get(PkType pk) throws SQLException {
        PreparedStatement st = cnx.prepareStatement(SqlUtils.getSelectByPKQuery(klass));
        st.setObject(1, pk);

        st.execute();
        ResultSet rs = st.getResultSet();

        T record;

        try {
            Method map = klass.getDeclaredMethod("map", ResultSet.class);

            rs.next();
            record = (T) map.invoke(null, rs);

            rs.close();
            st.close();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        return record;
    }

    public int count() throws SQLException {
        ResultSet rs = cnx.createStatement().executeQuery(SqlUtils.getCountQuery(klass));
        rs.next();

        int count = rs.getInt(1);
        rs.close();

        return count;
    }

    public void update(T type) throws SQLException, IllegalAccessException {
        PreparedStatement st = cnx.prepareStatement(SqlUtils.getUpdateByPKQuery(klass));

        ArrayList<Field> declaredFields = new ArrayList<>();
        SqlUtils.getAllFields(declaredFields, klass);
        for (int idx = 0; idx < declaredFields.size(); idx++) {
            Column fieldAnnotation = declaredFields.get(idx).getAnnotation(Column.class);

            if (fieldAnnotation.updatable()) {
                declaredFields.get(idx).setAccessible(true);
                var value = declaredFields.get(idx).get(type);

                if (value instanceof String)
                    st.setObject(idx, value);
                else if (value instanceof Gender)
                    st.setObject(idx, ((Gender) value).getValue());
                else if (value instanceof Entity)
                    st.setObject(idx, ((Entity) value).getPk());
                else
                    st.setObject(idx, null);
            }
        }

        st.setObject(declaredFields.size(), type.getPk());

        st.execute();
        st.close();
    }

    public void delete(PkType pk) throws SQLException {
        PreparedStatement st = cnx.prepareStatement(SqlUtils.getDeleteByPKQuery(klass));
        st.setObject(1, pk);

        st.execute();
        st.close();
    }
}
