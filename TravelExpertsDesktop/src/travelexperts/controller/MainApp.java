package travelexperts.controller;

import java.sql.SQLException;
import java.util.HashMap;

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
			for (HashMap.Entry<Integer, String> entry : Agent.getAgentComboList().entrySet()) {
			    Integer key = entry.getKey();
			    String value = entry.getValue();
			    System.out.println("Key: " + key + " Value:" + value);
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
			for (HashMap.Entry<Integer, String> entry : TravelPackage.getPackageComboList().entrySet()) {
			    Integer key = entry.getKey();
			    String value = entry.getValue();
			    System.out.println("Key: " + key + " Value:" + value);
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
			for (HashMap.Entry<Integer, String> entry : Customer.getCustomerComboList().entrySet()) {
			    Integer key = entry.getKey();
			    String value = entry.getValue();
			    System.out.println("Key: " + key + " Value:" + value);
			}
			myCust = Customer.getCustomerById(105);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			System.out.println("An Error has occured in retrieving your data");
		}
		System.out.println(myCust.toString());
		try
		{
			for(Agency agency : Agency.getAgencies())
			{
				System.out.println(agency.toString());
				
			}
			for (HashMap.Entry<Integer, String> entry : Agency.getAgencyComboList().entrySet()) {
			    Integer key = entry.getKey();
			    String value = entry.getValue();
			    System.out.println("Key: " + key + " Value:" + value);
			}
			myAgency = Agency.getAgencyById(1);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			System.out.println("An error has occured in retrieving your data");
		}
		System.out.println(myAgency.toString());
		try
		{
			System.out.println(Agency.updateAgent(1, myAgency));
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


}
