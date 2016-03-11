package travelexperts.model;

import java.awt.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.sun.glass.ui.TouchInputSupport;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import sun.management.resources.agent;

public class Agent
{
	
	public String AgtBusPhone;
	public String AgtEmail;
	public int AgencyId;
	public String AgtLastName;
	public String AgtMiddleInitial;
	public String AgtPosition;
	public int AgentId;
	public String AgtFirstName;
	
	
	
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
	 */
	public Agent(int agencyId, String agtPosition, String agtEmail, String agtBusPhone, String agtMiddleInitial,
	        String agtLastName, String agtFirstName, int agentId)
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
	}
	
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
	
	public static ArrayList<Agent> getAgents() throws SQLException
	{
		

			ArrayList<Agent> myList = new ArrayList<Agent>();
			try
			{
				for (Object object : DataBase.getTable("Agents",Agent.class))
				{
					myList.add((Agent)object);
				}
			}
			catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
		
		return myList;
		
	}
	
	public static Agent getAgentById(int Id) throws SQLException
	{
		Agent myAgent = null;
		try
		{
			myAgent = (Agent)DataBase.getById("Agents","AgentId",Id,Agent.class);
		}
		catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return myAgent;
		
	}
	@Override
	public String toString()
	{
		String returnString = "AgencyId: " + this.AgencyId + ",   First Name: " + this.AgtFirstName + ", Last Name: " + this.AgtLastName+ ", MiddleIntial: " + this.AgtMiddleInitial+ ", BusPhone: " + this.AgtBusPhone + ", Email: " + this.AgtEmail + ", Position: " + this.AgtPosition + ", Agent Id: " + this.AgentId;
		return returnString;
	}
}
