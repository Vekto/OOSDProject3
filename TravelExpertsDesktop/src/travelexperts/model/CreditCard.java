package travelexperts.model;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


public class CreditCard
{
	private Integer CreditCardId;
	private String CCName;
	private String CCNumber;
	private Date CCExpiry;
	private Integer CustomerId;
	
	private static final String TABLE = "creditcards";
	private static final String PRIMARYKEY = "CreditCardId";
	private static final String[] UPDATE_COLUMNS = 
			{
					"CreditCardId",
					"CCName",
					"CCNumber",
					"CCExpiry",
					"CustomerId"
			};
	
	
	public static ArrayList<CreditCard> getCreditCards(int Id)
	
	{		
			ArrayList<CreditCard> myList = new ArrayList<CreditCard>();
			try
			{
				for (Object object : DataBase.getMultiById(TABLE, "CustomerId", Id, CreditCard.class))
				{
					myList.add((CreditCard)object);
				}
			}
			catch (IllegalArgumentException | SecurityException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return myList;		
	}
	
	public static void insertCreditCard(CreditCard newCard)
	{
		DataBase.insertEntity(TABLE, UPDATE_COLUMNS, PRIMARYKEY,newCard.CreditCardId,newCard);
	}
	
	public static void deleteReward(CreditCard card)
	{
		if(DataBase.concurrencyCheck(card, TABLE, UPDATE_COLUMNS, "CreditCardId", card.getCreditCardId()))
		{
		DataBase.deleteById(TABLE, PRIMARYKEY, card.getCreditCardId(), card);
		}
	}
	
	
	@Override
	public String toString()
	{
		DateFormat df = new SimpleDateFormat("MM-yyyy");
		String subString = this.getCCNumber().substring(this.CCNumber.length()-4);
		String myString=this.getCCName() + " - ****" + subString + " Expiry: " + df.format(this.getExpiryDate());
		return myString;
	}

	public Integer getCreditCardId()
	{
		return CreditCardId;
	}
	public void setCreditCardId(Integer creditCardId)
	{
		CreditCardId = creditCardId;
	}
	public String getCCName()
	{
		return CCName;
	}
	public void setCCName(String cCName)
	{
		CCName = cCName;
	}
	public String getCCNumber()
	{
		return CCNumber;
	}
	public void setCCNumber(String cCnumber)
	{
		CCNumber = cCnumber;
	}
	public Date getExpiryDate()
	{
		return CCExpiry;
	}
	public void setExpiryDate(Date ccExpiry)
	{
		CCExpiry = ccExpiry;
	}
	public Integer getCustomerId()
	{
		return CustomerId;
	}
	public void setCustomerId(Integer customerId)
	{
		CustomerId = customerId;
	}
	
	
}
