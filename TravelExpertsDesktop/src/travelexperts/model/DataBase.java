package travelexperts.model;


import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



import travelexperts.util.ComboPair;
import travelexperts.util.EncryptionUtil;


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
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	public static ArrayList<Object> getTable(String table, Class<?> entity) 
	{
		try
		{
		Connection conn = getConnection();
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from " + table);
		
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
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return null;
		
	}
	
	public static Object getById(String table, String idField,Object idValue,Class<?> entity) 
	{
		try
		{
		Connection conn = getConnection();
		Statement statement;
			statement = conn.createStatement();
		

			ResultSet rs = statement.executeQuery("select * from " + table + " Where " + idField + "='" + idValue + "'");
			if (rs.next())
			{
				HashMap<String, Object> myHash = new HashMap<String, Object>();
				ResultSetMetaData metaData = rs.getMetaData();
				Integer columnCount = metaData.getColumnCount();

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
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		
	    return null;
	}
	public static ArrayList<Object> getMultiById(String table, String idField,int idValue,Class<?> entity) 
	{
		try
		{
		Connection conn = getConnection();
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from " + table + " where " + idField + " = " + idValue);
		
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
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static ArrayList<ComboPair> getComboList(String table, String idField, String myField)
	{

		try
		{
			Connection conn = getConnection();
			Statement statement = conn.createStatement();
			ResultSet rs;
			rs = statement.executeQuery("select " + idField + "," + myField + " from " + table);
			ArrayList<ComboPair> myList = new ArrayList<ComboPair>();
			
			while (rs.next())
			{
				ComboPair myComboPair = new ComboPair(rs.getInt(1), rs.getString(2));
				myList.add(myComboPair);
			}
			
			rs.close();
			statement.close();
			conn.close();
			return myList;
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static int updateEntity(String table,String[] myColumns, String idField,int idValue, Object myNewEntity, Object myOldEntity)  
	{
		int updateCount = 0;
		DataBase.concurrencyCheck(myOldEntity,table,myColumns,idField,idValue);
		try
		{
			
			int i = 1;
			Connection conn = getConnection();
			String myQuery = "UPDATE " + table + " Set ";
			for (String column : myColumns)
			{

				if (i != myColumns.length)
				{
					myQuery += column + " = ?,";
				}
				else
				{
					myQuery += column + " =? ";
				}
				i++;
			}
			myQuery += "WHERE " + idField + " = " + idValue;
			i = 1;
			PreparedStatement statement;

			statement = conn.prepareStatement(myQuery);

			for (String column : myColumns)
			{
				Field field = myNewEntity.getClass().getDeclaredField(column);
				field.setAccessible(true);
				statement.setObject(i, field.get(myNewEntity));
				i++;
			}
			updateCount = statement.executeUpdate();

		}
		catch (SQLException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e)
		{
			System.out.println(e.getMessage());
			updateCount = 0;
		}
		
		return updateCount;
					
	}
	
	
	public static int insertEntity(String table,String[] myColumns, String idField,int idValue, Object myNewEntity)  
	{
		int updateCount = 0;

		try
		{
			
			int i = 1;
			Connection conn = getConnection();
			String myQuery = "INSERT INTO " + table + " ( ";
			String myValues = "(";
			for (String column : myColumns)
			{
				if (i != myColumns.length)
				{
					myValues += " ?,";
					myQuery += column + ", ";
				}
				else
				{
					myValues +=  " ?) ";
					myQuery += column + ") ";
				}
				i++;

			}
			myQuery += " VALUES " + myValues;

			PreparedStatement statement;
			i=1;
			statement = conn.prepareStatement(myQuery,Statement.RETURN_GENERATED_KEYS);
			for (String column : myColumns)
			{
				Field field = myNewEntity.getClass().getDeclaredField(column);
				field.setAccessible(true);
				statement.setObject(i, field.get(myNewEntity));
				i++;
			}
			statement.executeUpdate();
			ResultSet recordId = statement.getGeneratedKeys();
			recordId.next();
			updateCount=recordId.getInt(1);
			System.out.println(statement.toString());
		}
		catch (SQLException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e)
		{
			System.out.println(e.getMessage());
			updateCount = 0;
		}
		
		return updateCount;
					
	}
	
	public static boolean deleteById(String table, String idField,Object idValue,Object myOldEntity)
	{
		boolean success = false;
		try
		{
		Connection conn = getConnection();
		Statement statement = conn.createStatement();
		String sql = "DELETE FROM " + table + " WHERE " + idField + "='" + idValue + "'";

		success = statement.execute(sql);	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return success;
	}
	
	
	public static boolean concurrencyCheck(Object myEntity,String table,String[] myColumns,String idField, Object idValue) 
	{

		try
		{
			int i=1;
			Connection conn = getConnection();
			Statement statement = conn.createStatement();
			String sqlString = "select " + idField + " from " + table + " where ";
			for (String column : myColumns)
			{
				Field field = myEntity.getClass().getDeclaredField(column);
				field.setAccessible(true);
				if(field.get(myEntity) != null)
				{
				sqlString += column + " = '"+ field.get(myEntity) + "'";
				}
				else
				{
					sqlString += column +  " IS NULL";
				}
				if(i <myColumns.length)
				{
				sqlString += " && ";
				}
				i++;
			}
			//sqlString += idField + " = " + idValue;
			System.out.println(sqlString);

			ResultSet rs = statement.executeQuery(sqlString);
			if (rs.next())
			{
				System.out.println("HELLO!");
				return true;
				
			}
			else
			{
				return false;

			}
		}
		catch (SQLException | IllegalArgumentException | IllegalAccessException | SecurityException | NoSuchFieldException e)
		{
			System.out.println("Failed to update row :" + e.getMessage());
			return false;
		}

	}
	
	
	
	public static Object entityConstructor(HashMap<String,Object> myHash,Class<?> entity)  
	{
		
		Object objectInstance = null;
		try
		{
		 objectInstance = entity.newInstance();
		 

		for(Map.Entry<String, Object> row : myHash.entrySet())
		{
			Field field = entity.getDeclaredField(row.getKey());
			field.setAccessible(true);
			field.set(objectInstance, row.getValue());

		}
		//return objectInstance;
		}
		catch( IllegalAccessException | NoSuchFieldException | SecurityException | InstantiationException e)
		{
			System.out.println(e.getMessage());
		}
		return objectInstance;
	}
	
	public static boolean ValidLogin(String user, String pass)
	{
		boolean verified = false;
	
	try
	{
		Connection conn = getConnection();
		Statement statement = conn.createStatement();
        final String strPssword = "BrownCoat";
        EncryptionUtil.setKey(strPssword);
        EncryptionUtil.encrypt(pass.trim());
        String encryptedPass = EncryptionUtil.getEncryptedString();
		String sqlString = "select AgentId from Agents where AgentId = " + user + " && password = " + encryptedPass;
		//verified = statement.execute(sqlString);
		//return verified;
	}
	catch (Exception e)
	{
		System.out.println(e.getMessage());
	}	
	return false;
	}
}
