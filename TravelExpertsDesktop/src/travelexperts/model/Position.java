package travelexperts.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import travelexperts.util.ComboPair;

public class Position
{
	String Position;

	public Position(){}
	
	/**
	 * @param position
	 */
	public Position(String position)
	{
		super();
		Position = position;
	}


	public static ArrayList<Position> getPositionList()
	{
		try
		{
			Connection conn = DataBase.getConnection();
			Statement statement = conn.createStatement();
			ResultSet rs;
			rs = statement.executeQuery("select * from positions");
			ArrayList<Position> myList = new ArrayList<Position>();
			
			while (rs.next())
			{
				Position myPosition = new Position(rs.getString(1));
				myList.add(myPosition);
			}
			
			rs.close();
			statement.close();
			conn.close();
			return myList;
		}
		catch (IllegalArgumentException | SecurityException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	
	

	
	@Override
	public String toString()
	{
		
		return Position;
	}

	public String getPosition()
	{
		return Position;
	}

	public void setPosition(String position)
	{
		Position = position;
	}
}
