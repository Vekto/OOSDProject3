package travelexperts.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;



import travelexperts.util.ComboPair;

public class Agency
{
	
	private int AgencyId; 
	private String AgncyAddress; 
	private String AgncyCity;
	private String AgncyProv;
	private String AgncyPostal;
	private String AgncyCountry; 
	private String AgncyPhone; 
	private String AgncyFax;
	private static final String TABLE = "agencies";
	private static final String PRIMARYKEY = "AgencyId";
	private static final String[] UPDATE_COLUMNS = 
			{ 
				"AgncyAddress",
		    	"AgncyCity", 
		    	"AgncyProv",
		    	"AgncyPostal",
		    	"AgncyCountry", 
		    	"AgncyPhone", 
		    	"AgncyFax"
		    };

	//Constructors
//****************************************************************************************************
	public Agency(){}
	
	
	/**
	 * @param agencyId
	 * @param agncyAddress
	 * @param agncyCity
	 * @param agncyProv
	 * @param agncyPostal
	 * @param agncyCountry
	 * @param agncyPhone
	 * @param agncyFax
	 */
	public Agency(int agencyId, String agncyAddress, String agncyCity, String agncyProv, String agncyPostal,
	        String agncyCountry, String agncyPhone, String agncyFax)
	{
		super();
		AgencyId = agencyId;
		AgncyAddress = agncyAddress;
		AgncyCity = agncyCity;
		AgncyProv = agncyProv;
		AgncyPostal = agncyPostal;
		AgncyCountry = agncyCountry;
		AgncyPhone = agncyPhone;
		AgncyFax = agncyFax;
	}


	//Methods
//****************************************************************************************************	
	/**
	 * Retrieves an ArrayList of all Agencies
	 * @return -ArrayList<Agency>
	 * @throws SQLException
	 */
	public static ArrayList<Agency> getAgencies() throws SQLException
	{
			ArrayList<Agency> myList = new ArrayList<Agency>();
			try
			{
				for (Object object : DataBase.getTable(TABLE,Agency.class))
				{
					myList.add((Agency)object);
				}
			}
			catch (IllegalArgumentException | SecurityException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		return myList;	
	}
	
	/**
	 * Retrieves an Agency
	 * @param Id -Id of the agency you wish to retrieve
	 * @return Agency
	 * @throws SQLException
	 */
	public static Agency getAgencyById(int Id) throws SQLException
	{
		Agency myAgency = null;
		try
		{
			myAgency = (Agency)DataBase.getById(TABLE,PRIMARYKEY,Id,Agency.class);
		}
		catch (IllegalArgumentException | SecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myAgency;
	}
	/**
	 * Retrieves Agency id's and Address's for display in a DropDown or ComboBox
	 * @return HashMap<Integer,String>
	 * @throws SQLException
	 */
	public static ArrayList<ComboPair> getAgencyComboList() throws SQLException
	{
		ArrayList<ComboPair> myAgencyCombo= null;
		try
		{
			myAgencyCombo = DataBase.getComboList(TABLE,PRIMARYKEY,"AgncyAddress");;
		}
		catch (IllegalArgumentException | SecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myAgencyCombo;
	}
	
		public static int updateAgency(Agency myAgency, Agency myOldAgency) 
		{
			int count = 0;
			
			
			
				count = DataBase.updateEntity(TABLE, UPDATE_COLUMNS, PRIMARYKEY, myAgency.AgencyId, myAgency, myOldAgency);
			
			
			
			return count;
		}
		
		public static int insertAgency(Agency myAgency) 
		{
			int count = 0;
			
			
			
				count = DataBase.insertEntity(TABLE, UPDATE_COLUMNS, PRIMARYKEY, myAgency.AgencyId, myAgency);
			
			
			return count;
		}
	

	//Overrides
//****************************************************************************************************
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
	//Getters and Setters
//****************************************************************************************************
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
}
