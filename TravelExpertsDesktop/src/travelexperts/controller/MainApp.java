package travelexperts.controller;

import java.sql.SQLException;

import travelexperts.model.Agency;
import travelexperts.model.Agent;
import travelexperts.model.Customer;
import travelexperts.model.TravelPackage;

public class MainApp
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Agent myAgent = null;
		TravelPackage myPackage = null;
		Customer myCust = null;
		Agency myAgency = null;
		
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
		
		try
		{
			for(Customer cust : Customer.getCustomers())
			{
				System.out.println(cust.toString());
				
			}
			myCust = Customer.getCustomerById(105);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			System.out.println("Fucked up!");
		}
		System.out.println(myCust.toString());
		try
		{
			for(Agency agency : Agency.getAgencys())
			{
				System.out.println(agency.toString());
				
			}
			myAgency = Agency.getAgencyById(1);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			System.out.println("Fucked up!");
		}
		System.out.println(myAgency.toString());
	}
	


}
