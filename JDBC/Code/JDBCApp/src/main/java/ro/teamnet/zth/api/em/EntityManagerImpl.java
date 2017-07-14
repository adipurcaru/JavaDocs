package ro.teamnet.zth.api.em;

import oracle.jdbc.proxy.annotation.Pre;
import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.database.DBManager;
import ro.teamnet.zth.appl.domain.Employee;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ro.teamnet.zth.api.em.EntityUtils.*;

/**
 * Created by Adrian.Purcaru on 13/07/2017.
 */
public class EntityManagerImpl implements EntityManager {
    
    
    @Override
    public <T> T findById(Class<T> entityClass, Long id) throws ClassNotFoundException {
        Connection conn = DBManager.getConnection();
        String tableName = getTableName(entityClass);
        List<ColumnInfo> columns = getColumns(entityClass);
        List<Field> fields = getFieldsByAnnotations(entityClass, Column.class);
        
        Condition condition = new Condition();
        
        for (ColumnInfo column : columns) {
            if (column.isId()) {
                condition.setColumnName(column.getDbColumnName());
                condition.setValue(id);
            }
        }
        
        QueryBuilder qb = new QueryBuilder();
        qb.setTableName(getTableName(entityClass))
                .setQueryType(QueryType.SELECT)
                .addQueryColumns(columns)
                .addCondition(condition);
        
        String query = qb.createQuery();
        
        ResultSet rs = null;
        try {
            rs = conn.prepareStatement(query).executeQuery();
            T obj = entityClass.newInstance();
            
            while (rs.next()) {
                for (ColumnInfo col : columns) {
                    Field field = obj.getClass().getDeclaredField(col.getColumnName());
                    field.setAccessible(true);
                    field.set(obj, castFromSqlType(rs.getObject(col.getDbColumnName()), col.getColumnType()));
                }
            }
            
            return obj;
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    @Override
    public long getNextIdVal(String tableName, String columnIdName) throws ClassNotFoundException {
        
        Connection conn = DBManager.getConnection();
        Statement statement = null;
        String sql = "SELECT MAX(" + columnIdName + ")+1 FROM " + tableName;
        
        try {
            ResultSet rs = conn.prepareStatement(sql).executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            } else {
                return - 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return - 1;
    }
    
    @Override
    public <T> Object insert(T entity) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, SQLException {
        Connection conn = DBManager.getConnection();
        
        String tableName = getTableName(entity.getClass());
        List<ColumnInfo> columns = getColumns(entity.getClass());
        long id = 0;
        
        for (ColumnInfo column : columns) {
            if (column.isId()) {
                column.setValue(getNextIdVal(tableName, column.getDbColumnName()));
                id = getNextIdVal(tableName, column.getDbColumnName());
            } else {
                Field field = entity.getClass().getDeclaredField(column.getColumnName());
                field.setAccessible(true);
                column.setValue(field.get(entity));
            }
        }
        
        QueryBuilder qb = new QueryBuilder();
        String querry = qb.setQueryType(QueryType.INSERT)
                .setTableName(tableName)
                .addQueryColumns(columns)
                .createQuery();
        
        conn.createStatement().executeUpdate(querry);
        
        T returnedEnt = null;
        if (id != 0) {
            returnedEnt = (T) findById(entity.getClass(), id);
        }
        
        return returnedEnt;
    }
    
    @Override
    public <T> List<T> findAll(Class<T> entityClass) throws ClassNotFoundException, SQLException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        Connection conn = DBManager.getConnection();
        
        String tableName = getTableName(entityClass);
        List<ColumnInfo> columns = getColumns(entityClass);
        
        QueryBuilder qb = new QueryBuilder();
        String querry = qb.setTableName(tableName)
                .setQueryType(QueryType.SELECT)
                .addQueryColumns(columns)
                .createQuery();
        
        ResultSet rs = conn.prepareStatement(querry).executeQuery();
        
        List<T> list = new ArrayList<T>();
        while (rs.next()) {
            T obj = entityClass.newInstance();
            for (ColumnInfo column : columns) {
                Field field = obj.getClass().getDeclaredField(column.getColumnName());
                field.setAccessible(true);
                field.set(obj, EntityUtils.castFromSqlType(rs.getObject(column.getDbColumnName()), column.getColumnType()));
            }
            list.add(obj);
        }
        
        return list;
    }
    
    public ColumnInfo getIdColumn(List<ColumnInfo> columns){
        for(ColumnInfo column : columns) {
            if (column.isId()) {
                return column;
            }
        }
    
        return null;
    }
    @Override
    public <T> T update(T entity) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, SQLException {
        Connection conn = DBManager.getConnection();
        String tableName = getTableName(entity.getClass());
        List<ColumnInfo> columns = getColumns(entity.getClass());

        
        for (ColumnInfo column : columns) {
            Field field = entity.getClass().getDeclaredField(column.getColumnName());
            field.setAccessible(true);
            column.setValue(field.get(entity));
        }
        
        ColumnInfo column = getIdColumn(columns);
        Condition condition = new Condition();
        condition.setColumnName(column.getDbColumnName());
        
        Field f = entity.getClass().getDeclaredField(column.getColumnName());
        f.setAccessible(true);
        condition.setValue(f.get(entity));
        
        QueryBuilder qb = new QueryBuilder();
        String query = qb.setTableName(tableName)
                .setQueryType(QueryType.UPDATE)
                .addQueryColumns(columns)
                .addCondition(condition)
                .createQuery();
        
        conn.prepareStatement(query).executeUpdate();
        
        return entity;
    }
    
    @Override
    public void delete(Object entity) throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException, SQLException {
        Connection conn = DBManager.getConnection();
        String tableName = getTableName(entity.getClass());
        List<ColumnInfo> columns = getColumns(entity.getClass());
        
        for(ColumnInfo columnInfo : columns){
            Field field = entity.getClass().getDeclaredField(columnInfo.getColumnName());
            field.setAccessible(true);
            columnInfo.setValue(field.get(entity));
        }
        
        ColumnInfo column = getIdColumn(columns);
        Condition condition = new Condition();
        Field f = entity.getClass().getDeclaredField(column.getColumnName());
        f.setAccessible(true);
        condition.setValue(f.get(entity));
        condition.setColumnName(column.getDbColumnName());
        
        QueryBuilder qb = new QueryBuilder();
        String query = qb.setTableName(tableName)
                .setQueryType(QueryType.DELETE)
                .addQueryColumns(columns)
                .addCondition(condition)
                .createQuery();
    
        conn.prepareStatement(query).executeUpdate();
    }
    
    @Override
    public <T> List<T> findByParams(Class<T> entityClass, Map<String, Object> params) throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException, SQLException, InstantiationException {
        Connection conn = DBManager.getConnection();
        String tableName = getTableName(entityClass);
        List<ColumnInfo> columns = getColumns(entityClass);
        
        
        ColumnInfo column = getIdColumn(columns);

        
        Field f = entityClass.getDeclaredField(column.getColumnName());
        f.setAccessible(true);
    
        QueryBuilder qb = new QueryBuilder();
        for(Map.Entry<String , Object> item: params.entrySet()){
            Condition condition = new Condition();
            condition.setValue(item.getValue());
            condition.setColumnName(item.getKey());
            qb.addCondition(condition);
        }
        
    
      
        String query = qb.setTableName(tableName)
                .setQueryType(QueryType.SELECT)
                .addQueryColumns(columns)
                .createQuery();
        
        ResultSet resultSet = conn.prepareStatement(query).executeQuery();
        List<T> list = new ArrayList<T>();
        while(resultSet.next()){
            T obj = entityClass.newInstance();
            for(ColumnInfo columnInfo : columns){
                Field field = entityClass.getDeclaredField(columnInfo.getColumnName());
                field.setAccessible(true);
                field.set(obj, EntityUtils.castFromSqlType(resultSet.getObject(columnInfo.getDbColumnName()), columnInfo.getColumnType()));
            }
            list.add((T) obj);
        }
        return list;
    }

    
    public List<Employee> getEmployees(String str) throws ClassNotFoundException, SQLException {
    Connection conn = DBManager.getConnection();
    PreparedStatement ps = null;
    
    String sql = "select employee_id, first_name, last_name, departments.department_name from employees \n" +
            "inner join departments on employees.department_id = departments.department_id\n" +
            "where UPPER(department_name) LIKE '%' || UPPER('" + str + "') || '%'";

    List<Employee> selectedEmployees = new ArrayList<>();
    ps = conn.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();
    
    while(rs.next()){
        Employee e = new Employee();
        e.setEmployeeId(rs.getLong("Employee_id"));
        e.setFirstName(rs.getString("First_Name"));
        e.setLastName(rs.getString("Last_Name"));
        selectedEmployees.add(e);
    }
    
    for(Employee e: selectedEmployees){
        System.out.println(e.getFirstName());
    }
        return selectedEmployees;
    }
    
    public <T> void insertAll(List<T> entities) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, SQLException {
        Connection conn = DBManager.getConnection();
        conn.setAutoCommit(false);
        
        for(T entity : entities) {
            String tableName = getTableName(entity.getClass());
            List<ColumnInfo> columns = getColumns(entity.getClass());
            long id = 0;
    
    
            for (ColumnInfo column : columns) {
                if (column.isId()) {
                    column.setValue(getNextIdVal(tableName, column.getDbColumnName()));
                    id = getNextIdVal(tableName, column.getDbColumnName());
                } else {
                    Field field = entity.getClass().getDeclaredField(column.getColumnName());
                    field.setAccessible(true);
                    column.setValue(field.get(entity));
                }
            }
    
            QueryBuilder qb = new QueryBuilder();
            String querry = qb.setQueryType(QueryType.INSERT)
                    .setTableName(tableName)
                    .addQueryColumns(columns)
                    .createQuery();
    
            conn.createStatement().executeUpdate(querry);
        }
        conn.close();
    }
    
}
