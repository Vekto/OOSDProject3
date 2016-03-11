package travelexperts.controller;

import java.sql.SQLException;

import travelexperts.model.Agent;
import travelexperts.model.TravelPackage;

public class MainApp
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Agent myAgent = null;
		TravelPackage myPackage = null;
		
		try
		{
			for(Agent agent : Agent.getAgents())
			{
				System.out.println(agent.toString());
				
			}
			myAgent = Agent.getAgentById(2);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(myAgent);
		
		try
		{
			for(TravelPackage pack : TravelPackage.getPackages())
			{
				System.out.println(pack.toString());
				
			}
			myPackage = TravelPackage.getPackageById(4);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(myPackage.toString());
	}
	


}
