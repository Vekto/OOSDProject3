package travelexperts.model;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class TravelPackage
{

	
	private BigDecimal PkgAgencyCommission;
	private BigDecimal PkgBasePrice;
	private String PkgDesc;
	private Date PkgEndDate;
	private Date PkgStartDate;
	private String PkgName;
	private int PackageId;
	private final static String TABLE = "Packages";
	private final static String PRIMARYKEY = "PackageId";
	
	//Constructors
//****************************************************************************************************			
	public TravelPackage(){}
	
	/**
	 * @param pkgAgencyCommission
	 * @param pkgBasePrice
	 * @param pkgDesc
	 * @param pkgEndDate
	 * @param pkgStartDate
	 * @param pkgName
	 * @param packageId
	 */
	public TravelPackage(BigDecimal pkgAgencyCommission, BigDecimal pkgBasePrice, String pkgDesc, Date pkgEndDate,
	        Date pkgStartDate, String pkgName, int packageId)
	{
		super();
		PkgAgencyCommission = pkgAgencyCommission;
		PkgBasePrice = pkgBasePrice;
		PkgDesc = pkgDesc;
		PkgEndDate = pkgEndDate;
		PkgStartDate = pkgStartDate;
		PkgName = pkgName;
		PackageId = packageId;
	}
	//Methods
//****************************************************************************************************	
	/**
	 * Retrieves an ArrayList of all Travel Packages
	 * @return -ArrayList<TravelPackage>
	 * @throws SQLException
	 */
	public static ArrayList<TravelPackage> getPackages() throws SQLException
	{
			ArrayList<TravelPackage> myList = new ArrayList<TravelPackage>();
			try
			{
				for (Object object : DataBase.getTable("Packages",TravelPackage.class))
				{
					myList.add((TravelPackage)object);
				}
			}
			catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		return myList;	
	}
	
	/**
	 * Retrieves a Travel Package
	 * @param Id -Id of the Travel Package you wish to retrieve
	 * @return TravelPackage
	 * @throws SQLException
	 */
	public static TravelPackage getPackageById(int Id) throws SQLException
	{
		TravelPackage myPackage = null;
		try
		{
			myPackage = (TravelPackage)DataBase.getById(TABLE,PRIMARYKEY,Id,TravelPackage.class);;
		}
		catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return myPackage;	
	}
	
	/**
	 * Retrieves Package id's and Names for display in a DropDown or ComboBox
	 * @return HashMap<Integer,String>
	 * @throws SQLException
	 */
	public static HashMap<Integer,String> getPackageComboList() throws SQLException
	{
		HashMap<Integer,String> myPkgCombo= null;
		try
		{
			myPkgCombo = DataBase.getComboList(TABLE,PRIMARYKEY,"PkgName");;
		}
		catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return myPkgCombo;	
	}
	
	//Overrides
//****************************************************************************************************	
	@Override
	public String toString()
	{
		String returnString = "PackageId: " + this.PackageId + ",   Name: " + this.PkgName + ", Description: " + this.PkgDesc + ", Base Price: " + this.PkgBasePrice+ ", StartDate: " + this.PkgStartDate + ", EndDate: " + this.PkgEndDate + ", Agency Commission: " + this.PkgAgencyCommission;
		return returnString;
	}
	
	//Constructors
//****************************************************************************************************	
	public BigDecimal getPkgAgencyCommission()
	{
		return PkgAgencyCommission;
	}
	public void setPkgAgencyCommission(BigDecimal pkgAgencyCommission)
	{
		this.PkgAgencyCommission = pkgAgencyCommission;
	}
	public BigDecimal getPkgBasePrice()
	{
		return PkgBasePrice;
	}
	public void setPkgBasePrice(BigDecimal pkgBasePrice)
	{
		this.PkgBasePrice = pkgBasePrice;
	}
	public String getPkgDesc()
	{
		return PkgDesc;
	}
	public void setPkgDesc(String pkgDesc)
	{
		this.PkgDesc = pkgDesc;
	}
	public Date getPkgEndDate()
	{
		return PkgEndDate;
	}
	public void setPkgEndDate(Date pkgEndDate)
	{
		this.PkgEndDate = pkgEndDate;
	}
	public Date getPkgStartDate()
	{
		return PkgStartDate;
	}
	public void setPkgStartDate(Date pkgStartDate)
	{
		this.PkgStartDate = pkgStartDate;
	}
	public String getPkgName()
	{
		return PkgName;
	}
	public void setPkgName(String pkgName)
	{
		this.PkgName = pkgName;
	}
	public int getPackageId()
	{
		return PackageId;
	}
	public void setPackageId(int packageId)
	{
		this.PackageId = packageId;
	}
}

