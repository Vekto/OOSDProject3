package travelexperts.model;

import java.util.ArrayList;

public class TripClass
{
	private String ClassId;
	private String ClassName;
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
	
	private static final String TABLE = "classes";
	
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
}
