package travelexperts.model;


import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;

import travelexperts.util.ComboPair;




public class Customer
{
	
	private String CustFirstName;
	private String CustLastName;
	private String CustAddress;
	private String CustCity;
	private String CustProv;
	private String CustPostal;
	private String CustCountry;
	private String CustHomePhone;
	private String CustBusPhone;
	private String CustEmail;
	private int AgentId;
	private int CustomerId;
	
	private final static String TABLE = "Customers";
	private static final String PRIMARYKEY = "CustomerId";
	private static final String[] UPDATE_COLUMNS = 
			{ 
					"CustFirstName",
					"CustLastName",
					"CustAddress",
					"CustCity",
					"CustProv",
					"CustPostal",
					"CustCountry",
					"CustHomePhone",
					"CustBusPhone",
					"CustEmail",
					"AgentId",
					"CustomerId"
		    };
	
	//Constructors
//****************************************************************************************************	
	public Customer (){}
	
	/**
	 * @param custFirstName
	 * @param custLastName
	 * @param custAddress
	 * @param custCity
	 * @param custProv
	 * @param custPostal
	 * @param custCountry
	 * @param custHomePhone
	 * @param custBusPhone
	 * @param custEmail
	 * @param agentId
	 * @param customerId
	 */
	public Customer(String custFirstName, String custLastName, String custAddress, String custCity, String custProv,
	        String custPostal, String custCountry, String custHomePhone, String custBusPhone, String custEmail,
	        int agentId, int customerId)
	{
		super();
		CustFirstName = custFirstName;
		CustLastName = custLastName;
		CustAddress = custAddress;
		CustCity = custCity;
		CustProv = custProv;
		CustPostal = custPostal;
		CustCountry = custCountry;
		CustHomePhone = custHomePhone;
		CustBusPhone = custBusPhone;
		CustEmail = custEmail;
		AgentId = agentId;
		CustomerId = customerId;
	}
	//Methods
//****************************************************************************************************
	/**
	 * Retrieves an ArrayList of all Customers
	 * @return -ArrayList<Customer>
	 * @throws SQLException
	 */
	public static ArrayList<Customer> getCustomers()
	{		
			ArrayList<Customer> myList = new ArrayList<Customer>();
			try
			{
				for (Object object : DataBase.getTable(TABLE,Customer.class))
				{
					myList.add((Customer)object);
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
	 * Retrieves a Customer
	 * @param Id -Id of the Customer you wish to retrieve
	 * @return Customer
	 * @throws SQLException
	 */
	public static Customer getCustomerById(int Id)
	{
		Customer myCustomer= null;
		try
		{
			myCustomer = (Customer)DataBase.getById(TABLE,PRIMARYKEY,Id,Customer.class);;
		}
		catch (IllegalArgumentException | SecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myCustomer;		
	}
	
	/**
	 * Retrieves Customer id's and FirstNames for display in a DropDown or ComboBox
	 * @return HashMap<Integer,String>
	 * @throws SQLException
	 */
	public static ArrayList<ComboPair> getCustomerComboList()
	{
		ArrayList<ComboPair> myCustCombo= null;
		try
		{
			myCustCombo = DataBase.getComboList(TABLE,PRIMARYKEY,"CustFirstName");;
		}
		catch (IllegalArgumentException | SecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myCustCombo;
	}
	
	public static ArrayList<Customer> getAllAgentCustomers(int Id)
	{
		//DataBase.getMultiById(TABLE, "CustomerId" , Id, Booking.class);
		ArrayList<Customer> myList = new ArrayList<Customer>();
			for (Object object : DataBase.getMultiById(TABLE, "AgentId" , Id, Customer.class))
			{
				myList.add((Customer)object);
			}
		return myList;
		
	}
	public static int updateCustomer(Customer newCustomer, Customer oldCustomer) 
	{
		int count = 0;
		
		
		
			count = DataBase.updateEntity(TABLE, UPDATE_COLUMNS, PRIMARYKEY, newCustomer.getCustomerId(), newCustomer, oldCustomer);
		
		
		
		return count;
	}
	
	public static int insertCustomer(Customer myCustomer) 
	{
		int count = 0;
		
		
		
			count = DataBase.insertEntity(TABLE, UPDATE_COLUMNS, PRIMARYKEY, myCustomer.CustomerId, myCustomer);
		
		
		return count;
	}
	
	//Overrides
//****************************************************************************************************
	@Override
	public String toString()
	{
		String returnString = "CustomerId: " + this.CustomerId 
				+ ", First Name: " + this.CustFirstName
				+ ", Last Name: " + this.CustLastName
				+ ", Address: " + this.CustAddress 
				+ ", City: " + this.CustCity
				+ ", Province: "+ this.CustProv
				+ ", Country: " + this.CustCountry 
				+ ", Postal: " + this.CustPostal 
				+ ", Email: " + this.CustEmail
				+ ", Buisness Phone: " + this.CustBusPhone
				+ ", Home Phone: " + this.CustHomePhone
				+ ", AgentId: " + this.AgentId;
		return returnString;
	}
	
	//Getters and Setters
//****************************************************************************************************
	public String getCustFirstName()
	{
		return CustFirstName;
	}
	public void setCustFirstName(String custFirstName)
	{
		CustFirstName = custFirstName;
	}
	public String getCustLastName()
	{
		return CustLastName;
	}
	public void setCustLastName(String custLastName)
	{
		CustLastName = custLastName;
	}
	public String getCustAddress()
	{
		return CustAddress;
	}
	public void setCustAddress(String custAddress)
	{
		CustAddress = custAddress;
	}
	public String getCustCity()
	{
		return CustCity;
	}
	public void setCustCity(String custCity)
	{
		CustCity = custCity;
	}
	public String getCustProv()
	{
		return CustProv;
	}
	public void setCustProv(String custProv)
	{
		CustProv = custProv;
	}
	public String getCustPostal()
	{
		return CustPostal;
	}
	public void setCustPostal(String custPostal)
	{
		CustPostal = custPostal;
	}
	public String getCustCountry()
	{
		return CustCountry;
	}
	public void setCustCountry(String custCountry)
	{
		CustCountry = custCountry;
	}
	public String getCustHomePhone()
	{
		return CustHomePhone;
	}
	public void setCustHomePhone(String custHomePhone)
	{
		CustHomePhone = custHomePhone;
	}
	public String getCustBusPhone()
	{
		return CustBusPhone;
	}
	public void setCustBusPhone(String custBusPhone)
	{
		CustBusPhone = custBusPhone;
	}
	public String getCustEmail()
	{
		return CustEmail;
	}
	public void setCustEmail(String custEmail)
	{
		CustEmail = custEmail;
	}
	public int getAgentId()
	{
		return AgentId;
	}
	public void setAgentId(int agentId)
	{
		AgentId = agentId;
	}
	public int getCustomerId()
	{
		return CustomerId;
	}
	public void setCustomerId(int custId)
	{
		CustomerId = custId;
	}
}
