package travelexperts.model;

import java.awt.List;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import travelexperts.model.Agent;
import javax.naming.spi.DirStateFactory.Result;

import com.sun.glass.ui.CommonDialogs.Type;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import com.sun.rowset.internal.Row;
import com.sun.xml.internal.stream.Entity;
import com.sun.xml.internal.ws.spi.db.FieldSetter;

import jdk.internal.dynalink.beans.StaticClass;
import jdk.nashorn.internal.objects.annotations.Where;

public class DataBase
{

	public static Connection getConnection()
	{
		Connection conn = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "root", "");
			
		}
		catch (ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public static ArrayList<Object> getTable(String Table, Class<?> entity) throws SQLException, IllegalAccessException, NoSuchFieldException, SecurityException
	{
		Connection conn = getConnection();
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from " + Table);
		
		ArrayList<Object> resultList = new ArrayList<Object>();
	    HashMap<String, Object> row = null;
	    ResultSetMetaData metaData = rs.getMetaData();
	    Integer columnCount = metaData.getColumnCount();
	    Object objectInstance = null;
	    while (rs.next()) 
	    {
	        row = new HashMap<String, Object>();
	        for (int i = 1; i <= columnCount; i++) 
	        {
	            row.put(metaData.getColumnName(i), rs.getObject(i));
	        }
	        objectInstance = entityConstructor(row, entity);
	        resultList.add(objectInstance);
	    }
	    rs.close();
	    statement.close();
	    conn.close();
	    
	    return resultList;
	}
	
	public static Object getById(String Table, String Id,int idValue,Class<?> entity) throws SQLException, IllegalAccessException, NoSuchFieldException, SecurityException
	{
		Connection conn = getConnection();
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from " + Table + " Where " + Id + "=" + idValue  );
		
	   
	    HashMap<String, Object> myHash = new HashMap<String,Object>();
	    ResultSetMetaData metaData = rs.getMetaData();
	    Integer columnCount = metaData.getColumnCount();

	    rs.next();
        for (int i = 1; i <= columnCount; i++) 
        {
            myHash.put(metaData.getColumnName(i), rs.getObject(i));
        }
        rs.close();
        statement.close();
        conn.close();
        
        Object myEntity = entityConstructor(myHash, entity);
        
	    return myEntity;
	}
	
	public static HashMap<Integer,String> getComboList(String Table,String id, String myField) throws SQLException, IllegalAccessException, NoSuchFieldException, SecurityException
	{
		Connection conn = getConnection();
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select " +id + "," + myField + " from " + Table);
		
	   
	    HashMap<Integer, String> myHash = new HashMap<Integer,String>();
	    while (rs.next()) 
	    {
	          myHash.put(rs.getInt(1), rs.getString(2));
	    }
        rs.close();
        statement.close();
        conn.close();
        
        
	    return myHash;
	}
	
	
	
	public static Object entityConstructor(HashMap<String,Object> myHash,Class<?> entity) throws IllegalAccessException, NoSuchFieldException, SecurityException 
	{
		
		Object objectInstance = null;
		try
		{
		 objectInstance = entity.newInstance();
		}
		catch (InstantiationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(Map.Entry<String, Object> row : myHash.entrySet())
		{
			Field field = entity.getDeclaredField(row.getKey());
			field.setAccessible(true);
			field.set(objectInstance, row.getValue());

		}
		return objectInstance;
	}
}
