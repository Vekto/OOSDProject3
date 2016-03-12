package travelexperts.model;

import java.sql.SQLException;
import java.util.ArrayList;

public class Agency
{
	
	public int getAgencyId()
	{
		return AgencyId;
	}
	public void setAgencyId(int agencyId)
	{
		AgencyId = agencyId;
	}
	public String getAgncyAddress()
	{
		return AgncyAddress;
	}
	public void setAgncyAddress(String agncyAddress)
	{
		AgncyAddress = agncyAddress;
	}
	public String getAgncyCity()
	{
		return AgncyCity;
	}
	public void setAgncyCity(String agncyCity)
	{
		AgncyCity = agncyCity;
	}
	public String getAgncyProv()
	{
		return AgncyProv;
	}
	public void setAgncyProv(String agncyProv)
	{
		AgncyProv = agncyProv;
	}
	public String getAgncyPostal()
	{
		return AgncyPostal;
	}
	public void setAgncyPostal(String agncyPostal)
	{
		AgncyPostal = agncyPostal;
	}
	public String getAgncyCountry()
	{
		return AgncyCountry;
	}
	public void setAgncyCountry(String agncyCountry)
	{
		AgncyCountry = agncyCountry;
	}
	public String getAgncyPhone()
	{
		return AgncyPhone;
	}
	public void setAgncyPhone(String agncyPhone)
	{
		AgncyPhone = agncyPhone;
	}
	public String getAgncyFax()
	{
		return AgncyFax;
	}
	public void setAgncyFax(String agncyFax)
	{
		AgncyFax = agncyFax;
	}
	private int AgencyId; 
	private String AgncyAddress; 
	private String AgncyCity;
	private String AgncyProv;
	private String AgncyPostal;
	private String AgncyCountry; 
	private String AgncyPhone; 
	private String AgncyFax;
	
	private static final String TABLE = "Agencies";
	private static final String PRIMARYKEY = "AgencyId";
	
	public static ArrayList<Agency> getAgencys() throws SQLException
	{
		

			ArrayList<Agency> myList = new ArrayList<Agency>();
			try
			{
				for (Object object : DataBase.getTable(TABLE,Agency.class))
				{
					myList.add((Agency)object);
				}
			}
			catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
		
		return myList;
		
	}
	
	public static Agency getAgencyById(int Id) throws SQLException
	{
		Agency myAgency = null;
		try
		{
			myAgency = (Agency)DataBase.getById(TABLE,PRIMARYKEY,Id,Agency.class);
		}
		catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return myAgency;
		
	}
	@Override
	public String toString()
	{
		String returnString = "AgencyId: " + this.AgencyId
				+ ", Address: " + this.AgncyAddress
				+ ", City: " + this.AgncyCity
				+ ", Province: " + this.AgncyProv
				+ ", Country: " + this.AgncyCountry
				+ ", Postal: " + this.AgncyPostal
				+ ", Phone: " + this.AgncyPhone
				+ ", Fax: " + this.AgncyFax;
		return returnString;
	}
}
