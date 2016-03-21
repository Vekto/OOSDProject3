package travelexperts.model;


import java.util.ArrayList;

public class Region
{



	private String RegionId;
	private String RegionName;
	
	private static final String TABLE = "regions";

	/**
	 * 
	 */
	public Region()
	{
		super();
	}
	/**
	 * @param regionId
	 * @param regionName
	 */
	public Region(String regionId, String regionName)
	{
		super();
		RegionId = regionId;
		RegionName = regionName;
	}
	public static ArrayList<Region> getAllRegions()
	{
		ArrayList<Region> myList = new ArrayList<Region>();
		try
		{
			for (Object object : DataBase.getTable(TABLE,Region.class))
			{
				myList.add((Region)object);
			}
		}
		catch (IllegalArgumentException | SecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return(myList);
	}

	public String getRegionId()
	{
		return RegionId;
	}

	public void setRegionId(String regionId)
	{
		RegionId = regionId;
	}

	public String getRegionName()
	{
		return RegionName;
	}

	public void setRegionName(String regionName)
	{
		RegionName = regionName;
	}
}
