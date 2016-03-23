package travelexperts.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Reward
{
	private String type;
	private int CustomerId;
	private int RewardId;
	private String RwdNumber;
	private static final String TABLE = "customers_rewards";
	private static final String PRIMARYKEY = "CustomerId";
	private static final String[] UPDATE_COLUMNS = 
			{ 
				"CustomerId",
		    	"RewardId", 
		    	"RwdNumber",
		    };
	public Reward(){};
	public Reward(String n,String t, int i)
	{
		this.setRwdNumber(n);
		this.setType(t);
		this.setRewardId(i);

	}
	public static ObservableList<Reward> getCustomerRewards(int id)
	{
		ObservableList<Reward> myList = FXCollections.observableArrayList();
		Reward reward = null;
		try
		{
		
		Connection conn = DataBase.getConnection();
		Statement statement = conn.createStatement();
		String sqlString = "select RwdNumber,RwdName, r.rewardId "
						+ "from customers_rewards cr "
						+ "INNER JOIN rewards r "
						+ "on cr.rewardId = r.rewardId "
						+ "where CustomerId = "+ id;
		System.out.print(sqlString);
		ResultSet rs = statement.executeQuery(sqlString);
		
		while(rs.next())
		{
	        reward = new Reward(rs.getString(1),rs.getString(2),rs.getInt(3));
	        myList.add(reward);
		}
		return myList;
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static void insertReward(Reward newReward)
	{
		DataBase.insertEntity(TABLE, UPDATE_COLUMNS, PRIMARYKEY,newReward.getCustomerId(),newReward);
	}
	
	public static void deleteReward(Reward reward)
	{
		if(DataBase.concurrencyCheck(reward, TABLE, UPDATE_COLUMNS, "RwdNumber", reward.getRwdNumber()))
		{
		DataBase.deleteById(TABLE, "RwdNumber", reward.getRwdNumber(), reward);
		}
	}
	
	
	
	@Override
	public String toString()
	{
		String myString = this.getType() + " - " + this.getRwdNumber() + " - " + this.getRewardId();
		return myString;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public int getCustomerId()
	{
		return CustomerId;
	}
	public void setCustomerId(int customerId)
	{
		CustomerId = customerId;
	}
	public int getRewardId()
	{
		return RewardId;
	}
	public void setRewardId(int rewardId)
	{
		RewardId = rewardId;
	}
	public String getRwdNumber()
	{
		return RwdNumber;
	}
	public void setRwdNumber(String rwdNumber)
	{
		RwdNumber = rwdNumber;
	}

	
	
}
