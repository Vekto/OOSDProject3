package travelexperts.model;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

public class Fee
{
	private String FeeId;
	private String FeeName;
	private BigDecimal FeeAmt;
	private String FeeDesc;
	
	private static final String TABLE = "fees";
	private static final String PRIMARYKEY = "FeeId";
	


	
	/**
	 * 
	 */
	public Fee()
	{
		super();
	}


	/**
	 * @param feeId
	 * @param feeName
	 * @param feeAmt
	 * @param feeDesc
	 */
	public Fee(String feeId, String feeName, BigDecimal feeAmt, String feeDesc)
	{
		super();
		FeeId = feeId;
		FeeName = feeName;
		FeeAmt = feeAmt;
		FeeDesc = feeDesc;
	}


	public static ArrayList<Fee> getAllFees()
	{
		ArrayList<Fee> myList = new ArrayList<Fee>();
		try
		{
			for (Object object : DataBase.getTable(TABLE,Fee.class))
			{
				myList.add((Fee)object);
			}
		}
		catch (IllegalArgumentException | SecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return(myList);
	}
	public static Fee getFeeById(String Id) throws SQLException
	{
		Fee myFee= null;
		try
		{
			myFee = (Fee)DataBase.getById(TABLE,PRIMARYKEY,Id,Fee.class);;
		}
		catch (IllegalArgumentException | SecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myFee;		
	}
	
	
	public String getFeeId()
	{
		return FeeId;
	}
	public void setFeeId(String feeId)
	{
		FeeId = feeId;
	}
	public String getFeeName()
	{
		return FeeName;
	}
	public void setFeeName(String feeName)
	{
		FeeName = feeName;
	}
	public BigDecimal getFeeAmt()
	{
		return FeeAmt;
	}
	public void setFeeAmt(BigDecimal feeAmt)
	{
		FeeAmt = feeAmt;
	}
	public String getFeeDesc()
	{
		return FeeDesc;
	}
	public void setFeeDesc(String feeDesc)
	{
		FeeDesc = feeDesc;
	}
}
