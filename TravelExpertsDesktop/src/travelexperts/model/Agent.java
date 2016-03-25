package travelexperts.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import travelexperts.util.ComboPair;



public class Agent
{
	
	private String AgtBusPhone;
	private String AgtEmail;
	private int AgencyId;
	private String AgtLastName;
	private String AgtMiddleInitial;
	private String AgtPosition;
	private int AgentId;
	private String AgtFirstName;
	private boolean Active;
	private static final String[] UPDATE_COLUMNS = 
			{ 
				"AgtBusPhone",
		    	"AgtEmail", 
		    	"AgencyId",
		    	"AgtLastName",
		    	"AgtMiddleInitial", 
		    	"AgtPosition", 
		    	"AgentId",
		    	"AgtFirstName",
		    	"Active"
		    };
	
	private final static String TABLE = "Agents";
	private final static String PRIMARYKEY = "AgentId";
	
	//Constructors
//****************************************************************************************************
	public Agent(){}
	
	/**
	 * Builds a Agent Taking ALL properties as arguments
	 * @param agencyId
	 * @param agtPosition
	 * @param agtEmail
	 * @param agtBusPhone
	 * @param agtMiddleInitial
	 * @param agtLastName
	 * @param agtFirstName
	 * @param agentId
	 * @param active
	 */
	public Agent(int agencyId, String agtPosition, String agtEmail, String agtBusPhone, String agtMiddleInitial,
	        String agtLastName, String agtFirstName, int agentId, boolean active)
	{
		super();
		this.AgencyId = agencyId;
		this.AgtPosition = agtPosition;
		this.AgtEmail = agtEmail;
		this.AgtBusPhone = agtBusPhone;
		this.AgtMiddleInitial = agtMiddleInitial;
		this.AgtLastName = agtLastName;
		this.AgtFirstName = agtFirstName;
		this.AgentId = agentId;
		this.Active = active;
	}
	
	//Methods
//****************************************************************************************************

	/**
	 * Retrieves an ArrayList of all Agents
	 * @return -ArrayList<Agent>
	 * @throws SQLException
	 */
	public static ArrayList<Agent> getAgents() throws SQLException
	{
			ArrayList<Agent> myList = new ArrayList<Agent>();
			try
			{
				for (Object object : DataBase.getTable(TABLE,Agent.class))
				{
					myList.add((Agent)object);
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
	 * Retrieves an Agent
	 * @param Id -Id of the agent you wish to retrieve
	 * @return Agent
	 * @throws SQLException
	 */
	public static Agent getAgentById(int Id) throws SQLException
	{
		Agent myAgent = null;
		try
		{
			myAgent = (Agent)DataBase.getById(TABLE,PRIMARYKEY,Id,Agent.class);
		}
		catch (IllegalArgumentException | SecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myAgent;	
	}
	
	/**
	 * Retrieves Agent id's and FirstNames for display in a DropDown or ComboBox
	 * @return HashMap<Integer,String>
	 * @throws SQLException
	 */
	public static ArrayList<ComboPair> getAgentComboList() throws SQLException
	{
		ArrayList<ComboPair> myAgentCombo= null;
		try
		{
			myAgentCombo = DataBase.getComboList(TABLE,PRIMARYKEY,"AgtFirstName");;
		}
		catch (IllegalArgumentException | SecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myAgentCombo;	
	}
	
	public static ArrayList<ComboPair> getActiveAgentComboList()
	{
		try
		{
			Connection conn = DataBase.getConnection();
			Statement statement = conn.createStatement();
			ResultSet rs;
			rs = statement.executeQuery("select AgentId ,AgtFirstName from agents where Active=1");
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
	
	public static int updateAgent(Agent myAgent, Agent myOldAgent) 
	{
		int count = 0;
		
		
		
			count = DataBase.updateEntity(TABLE, UPDATE_COLUMNS, PRIMARYKEY, myAgent.AgentId, myAgent, myOldAgent);
		
		
		
		return count;
	}
	
	public static int insertAgent(Agent myAgent) 
	{
		int count = 0;
		
		
		
			count = DataBase.insertEntity(TABLE, UPDATE_COLUMNS, PRIMARYKEY, myAgent.AgentId, myAgent);
		
		
		return count;
	}
	
	//Overrides
//****************************************************************************************************
	 
	@Override
	public String toString()
	{
		String returnString = "AgencyId: " + this.AgencyId + ",   First Name: " + this.AgtFirstName + ", Last Name: " + this.AgtLastName+ ", MiddleIntial: " + this.AgtMiddleInitial+ ", BusPhone: " + this.AgtBusPhone + ", Email: " + this.AgtEmail + ", Position: " + this.AgtPosition + ", Agent Id: " + this.AgentId;
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
		this.AgencyId = agencyId;
	}
	public String getAgtPosition() 
	{
		return AgtPosition;
	}
	public void setAgtPosition(String agtPosition) 
	{
		this.AgtPosition = agtPosition;
	}
	public String getAgtEmail() 
	{
		return AgtEmail;
	}
	public void setAgtEmail(String agtEmail) 
	{
		this.AgtEmail = agtEmail;
	}
	public String getAgtBusPhone() 
	{
		return AgtBusPhone;
	}
	public void setAgtBusPhone(String agtBusPhone) 
	{
		this.AgtBusPhone = agtBusPhone;
	}
	public String getAgtMiddleInitial() 
	{
		return AgtMiddleInitial;
	}
	public void setAgtMiddleInitial(String agtMiddleInitial) 
	{
		this.AgtMiddleInitial = agtMiddleInitial;
	}
	public String getAgtLastName() 
	{
		return AgtLastName;
	}
	public void setAgtLastName(String agtLastName) 
	{
		this.AgtLastName = agtLastName;
	}
	public String getAgtFirstName() 
	{
		return AgtFirstName;
	}
	public void setAgtFirstName(String agtFirstName) 
	{
		this.AgtFirstName = agtFirstName;
	}
	public int getAgentId() 
	{
		return AgentId;
	}
	public void setAgentId(int agentId) 
	{
		this.AgentId = agentId;
	}

	public boolean isActive()
	{
		return Active;
	}

	public void setActive(boolean active)
	{
		Active = active;
	}
	
}
