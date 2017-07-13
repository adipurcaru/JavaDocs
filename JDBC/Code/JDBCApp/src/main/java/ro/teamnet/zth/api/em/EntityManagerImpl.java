package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.database.DBManager;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

        for(ColumnInfo column : columns){
            if(column.isId()){
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
            
            while (rs.next()){
                for(ColumnInfo col : columns){
                    Field field = obj.getClass().getDeclaredField(col.getColumnName());
                    field.setAccessible(true);
                    field.set(obj,castFromSqlType(rs.getObject(col.getDbColumnName()),col.getColumnType()));
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
        String sql = "SELECT MAX("+ columnIdName + ")+1 FROM " + tableName;
    
        try {
            ResultSet rs = conn.prepareStatement(sql).executeQuery();
            if(rs.next()) {
                return rs.getLong(1);
            }else{
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return -1;
    }
    
    @Override
    public <T> Object insert(T entity) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, SQLException {
        Connection conn = DBManager.getConnection();
        
        String tableName = getTableName(entity.getClass());
        List<ColumnInfo> columns = getColumns(entity.getClass());
        long id = 0;
        
        for(ColumnInfo column : columns){
            if(column.isId()){
                column.setValue(getNextIdVal(tableName,column.getDbColumnName()));
                id = getNextIdVal(tableName,column.getDbColumnName());
            }else{
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
        if(id != 0){
            returnedEnt = (T)findById(entity.getClass(), id);
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
    
        ResultSet rs  = conn.prepareStatement(querry).executeQuery();
        
        List<T> list = new ArrayList<T>();
        while(rs.next()){
            T obj = entityClass.newInstance();
            for(ColumnInfo column : columns){
                Field field = obj.getClass().getDeclaredField(column.getColumnName());
                field.setAccessible(true);
                field.set(obj,EntityUtils.castFromSqlType(rs.getObject(column.getDbColumnName()),column.getColumnType()));
            }
            list.add(obj);
        }
        
        return list;
    }
}
