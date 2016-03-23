package travelexperts.model;

import java.sql.SQLException;
import java.util.ArrayList;

public class TripClass
{
	private String ClassId;
	private String ClassName;
	
	private static final String PRIMARYKEY = "ClassId";
	private static final String TABLE = "classes";
	/**
	 * 
	 */
	public TripClass()
	{
		super();
	}

	/**
	 * @param classId
	 * @param className
	 * @param classDesc
	 */
	public TripClass(String classId, String className, String classDesc)
	{
		super();
		ClassId = classId;
		ClassName = className;
		ClassDesc = classDesc;
	}

	private String ClassDesc;
	
	
	
	public static ArrayList<TripClass> getAllClasses()
	{
		ArrayList<TripClass> myList = new ArrayList<TripClass>();
		try
		{
			for (Object object : DataBase.getTable(TABLE,TripClass.class))
			{
				myList.add((TripClass)object);
			}
		}
		catch (IllegalArgumentException | SecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return(myList);
	}
	
	public static TripClass getTripClassById(String Id) throws SQLException
	{
		TripClass myClass= null;
		try
		{
			myClass = (TripClass)DataBase.getById(TABLE,PRIMARYKEY,Id,TripClass.class);;
		}
		catch (IllegalArgumentException | SecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myClass;		
	}
	
	@Override
	public String toString()
	{
		String myString = this.ClassName;
		return myString;
	}
}
