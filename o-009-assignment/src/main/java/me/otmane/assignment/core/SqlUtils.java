package me.otmane.assignment.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class SqlUtils {

    private static final String SELECT_SQL_TEMPLATE = "select %s from %s order by %s;";
    private static final String SELECT_BY_PK_SQL_TEMPLATE = "select %s from %s where %s = ?;";
    private static final String COUNT_SQL_TEMPLATE = "select count(1) from %s;";
    private static final String INSERT_SQL_TEMPLATE = "insert into %s (%s) values (%s) returning *;";
    private static final String UPDATE_SQL_TEMPLATE = "update %s set %s where %s = ?;";
    private static final String DELETE_SQL_TEMPLATE = "delete from %s where %s = ?;";

    public static String getSelectQuery(Class<? extends Entity> entityClass) {
        return getSelectQuery(entityClass, null);
    }

    public static String getSelectQuery(Class<? extends Entity> entityClass, String primaryKey) {
        Table table = entityClass.getAnnotation(Table.class);

        ArrayList<String> selectFields = new ArrayList<>();

        ArrayList<Field> declaredFields = new ArrayList<>();
        getAllFields(declaredFields, entityClass);

        StringBuilder tableName = new StringBuilder(table.name());

        for (Field field : declaredFields) {
            Column fieldAnnotation = field.getAnnotation(Column.class);

            selectFields.add("%s.%s".formatted(table.name(), fieldAnnotation.name()));
            if (primaryKey == null || primaryKey.isEmpty())
                if (fieldAnnotation.primaryKey())
                    primaryKey = "%s.%s".formatted(table.name(), fieldAnnotation.name());

            if (fieldAnnotation.references() != void.class) {
                String joinTableName = fieldAnnotation.references().getAnnotation(Table.class).name();

                tableName.append(" left join %s ".formatted(joinTableName));

                ArrayList<Field> joinDeclaredFields = new ArrayList<>();
                getAllFields(joinDeclaredFields, fieldAnnotation.references());

                for (Field joinField : joinDeclaredFields) {
                    selectFields.add("%s.%s %1$s_%2$s".formatted(joinTableName, joinField.getName()));

                    Column joinFieldAnnotation = joinField.getAnnotation(Column.class);
                    if (joinFieldAnnotation.primaryKey())
                        tableName.append("on %s.%s = %s.%s".formatted(table.name(), fieldAnnotation.name(), joinTableName, joinFieldAnnotation.name()));
                }
            }
        }

        return SELECT_SQL_TEMPLATE.formatted(String.join(", ", selectFields), tableName, primaryKey);
    }

    public static String getSelectByPKQuery(Class<? extends Entity> entityClass) {
        return getSelectByPKQuery(entityClass, null);
    }

    public static String getSelectByPKQuery(Class<? extends Entity> entityClass, String primaryKey) {
        Table table = entityClass.getAnnotation(Table.class);

        ArrayList<String> selectFields = new ArrayList<>();

        ArrayList<Field> declaredFields = new ArrayList<>();
        getAllFields(declaredFields, entityClass);

        StringBuilder tableName = new StringBuilder(table.name());

        for (Field field : declaredFields) {
            Column fieldAnnotation = field.getAnnotation(Column.class);

            selectFields.add("%s.%s".formatted(table.name(), fieldAnnotation.name()));

            if (fieldAnnotation.references() != void.class) {
                String joinTableName = fieldAnnotation.references().getAnnotation(Table.class).name();

                tableName.append(" left join %s ".formatted(joinTableName));

                ArrayList<Field> joinDeclaredFields = new ArrayList<>();
                getAllFields(joinDeclaredFields, fieldAnnotation.references());

                for (Field joinField : joinDeclaredFields) {
                    selectFields.add("%s.%s %1$s_%2$s".formatted(joinTableName, joinField.getName()));

                    Column joinFieldAnnotation = joinField.getAnnotation(Column.class);
                    if (joinFieldAnnotation.primaryKey())
                        tableName.append("on %s.%s = %s.%s".formatted(table.name(), fieldAnnotation.name(), joinTableName, joinFieldAnnotation.name()));
                }
            }

            if (primaryKey == null || primaryKey.isEmpty())
                if (fieldAnnotation.primaryKey())
                    primaryKey = "%s.%s".formatted(table.name(), fieldAnnotation.name());
        }

        return SELECT_BY_PK_SQL_TEMPLATE.formatted(String.join(", ", selectFields), tableName, primaryKey);
    }

    public static String getInsertQuery(Class<? extends Entity> entityClass) {
        Table table = entityClass.getAnnotation(Table.class);

        ArrayList<String> insertFields = new ArrayList<>();

        ArrayList<Field> declaredFields = new ArrayList<>();
        getAllFields(declaredFields, entityClass);

        for (Field field : declaredFields) {
            Column fieldAnnotation = field.getAnnotation(Column.class);

            if (fieldAnnotation.insertable())
                insertFields.add(fieldAnnotation.name());
        }

        List<String> params = IntStream.range(0, insertFields.size()).mapToObj(v -> "?").toList();
        return INSERT_SQL_TEMPLATE.formatted(table.name(), String.join(", ", insertFields), String.join(", ", params));
    }

    public static String getCountQuery(Class<? extends Entity> entityClass) {
        Table table = entityClass.getAnnotation(Table.class);

        return COUNT_SQL_TEMPLATE.formatted(table.name());
    }

    public static String getUpdateByPKQuery(Class<? extends Entity> entityClass) {
        return getUpdateByPKQuery(entityClass, null);
    }

    public static String getUpdateByPKQuery(Class<? extends Entity> entityClass, String primaryKey) {
        Table table = entityClass.getAnnotation(Table.class);

        ArrayList<String> updateFields = new ArrayList<>();

        ArrayList<Field> declaredFields = new ArrayList<>();
        getAllFields(declaredFields, entityClass);

        for (Field field : declaredFields) {
            Column fieldAnnotation = field.getAnnotation(Column.class);

            if (fieldAnnotation.updatable())
                updateFields.add("%s = ?".formatted(fieldAnnotation.name()));

            if (primaryKey == null || primaryKey.isEmpty())
                if (fieldAnnotation.primaryKey())
                    primaryKey = fieldAnnotation.name();
        }

        return UPDATE_SQL_TEMPLATE.formatted(table.name(), String.join(", ", updateFields), primaryKey);
    }

    public static String getDeleteByPKQuery(Class<? extends Entity> entityClass) {
        return getDeleteByPKQuery(entityClass, null);
    }

    public static String getDeleteByPKQuery(Class<? extends Entity> entityClass, String primaryKey) {
        Table table = entityClass.getAnnotation(Table.class);

        ArrayList<Field> declaredFields = new ArrayList<>();
        getAllFields(declaredFields, entityClass);

        for (Field field : declaredFields) {
            Column fieldAnnotation = field.getAnnotation(Column.class);

            if (primaryKey == null || primaryKey.isEmpty())
                if (fieldAnnotation.primaryKey())
                    primaryKey = fieldAnnotation.name();
        }

        return DELETE_SQL_TEMPLATE.formatted(table.name(), primaryKey);
    }

    public static void getAllFields(ArrayList<Field> fields, Class<?> entityClass) {
        if (entityClass.getSuperclass() != null)
            getAllFields(fields, entityClass.getSuperclass());

        fields.addAll(Arrays.asList(entityClass.getDeclaredFields()));
    }
}
