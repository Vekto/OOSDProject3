package travelexperts.model;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class BookingDetail
{
 private Integer BookingDetailId;
 private Double ItineraryNo;
 private Date TripStart;
 private Date TripEnd;
 private String Description;
 private String Destination;
 private BigDecimal BasePrice;
 private BigDecimal AgencyCommission;
 private Integer BookingId;
 private String RegionId;
 private String ClassId;
 private String FeeId;
 private Integer ProductSupplierId;

	private final static String TABLE = "bookingdetails";
	private static final String PRIMARYKEY = "BookingDetailId";
	private static final String[] UPDATE_COLUMNS = 
			{ 
					"ItineraryNo",
					"TripStart",
					"TripEnd",
					"Description",
					"BasePrice",
					"AgencyCommission",
					"BookingId",
					"RegionId",
					"ClassId",
					"FeeId",
					"ProductSupplierId"
							    
			};
/**
 * @param bookingDetailId
 * @param itineraryNo
 * @param tripStart
 * @param tripEnd
 * @param description
 * @param destination
 * @param basePrice
 * @param agencyCommission
 * @param bookingId
 * @param regionId
 * @param classId
 * @param feeId
 * @param productSupplierId
 */
public BookingDetail(Integer bookingDetailId, Double itineraryNo, Date tripStart, Date tripEnd, String description,
        String destination, BigDecimal basePrice, BigDecimal agencyCommission, Integer bookingId, String regionId,
        String classId, String feeId, Integer productSupplierId)
{
	super();
	BookingDetailId = bookingDetailId;
	ItineraryNo = itineraryNo;
	TripStart = tripStart;
	TripEnd = tripEnd;
	Description = description;
	Destination = destination;
	BasePrice = basePrice;
	AgencyCommission = agencyCommission;
	BookingId = bookingId;
	RegionId = regionId;
	ClassId = classId;
	FeeId = feeId;
	ProductSupplierId = productSupplierId;
}
/**
 * 
 */
public BookingDetail()
{
	super();
}

/**
 * Retrieves an ArrayList of all Customers
 * @return -ArrayList<Customer>
 * @throws SQLException
 */
public static ArrayList<BookingDetail> getBookingDetails() throws SQLException
{		
		ArrayList<BookingDetail> myList = new ArrayList<BookingDetail>();
		try
		{
			for (Object object : DataBase.getTable(TABLE,BookingDetail.class))
			{
				myList.add((BookingDetail)object);
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
 * Retrieves a Booking Detail
 * @param Id -BookingId of the Detail you wish to retrieve
 * @return BookingDetail
 * @throws SQLException
 */
public static BookingDetail getDetailByBookingId(String Id) throws SQLException
{
	BookingDetail myDetail= null;
	try
	{
		myDetail = (BookingDetail)DataBase.getById(TABLE,"BookingId",Id,BookingDetail.class);;
	}
	catch (IllegalArgumentException | SecurityException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return myDetail;		
}

public static int updateBookingDetail(BookingDetail newDetail, BookingDetail oldDetail) 
{
	int count = 0;
	
	
	
		count = DataBase.updateEntity(TABLE, UPDATE_COLUMNS, PRIMARYKEY, oldDetail.BookingDetailId, newDetail, oldDetail);
	
	
	
	return count;
}

public Integer getBookingDetailId()
{
	return BookingDetailId;
}
public void setBookingDetailId(Integer bookingDetailId)
{
	BookingDetailId = bookingDetailId;
}
public Double getItineraryNo()
{
	return ItineraryNo;
}
public void setItineraryNo(Double itineraryNo)
{
	ItineraryNo = itineraryNo;
}
public Date getTripStart()
{
	return TripStart;
}
public void setTripStart(Date tripStart)
{
	TripStart = tripStart;
}
public Date getTripEnd()
{
	return TripEnd;
}
public void setTripEnd(Date tripEnd)
{
	TripEnd = tripEnd;
}
public String getDescription()
{
	return Description;
}
public void setDescription(String description)
{
	Description = description;
}
public String getDestination()
{
	return Destination;
}
public void setDestination(String destination)
{
	Destination = destination;
}
public BigDecimal getBasePrice()
{
	return BasePrice;
}
public void setBasePrice(BigDecimal basePrice)
{
	BasePrice = basePrice;
}
public BigDecimal getAgencyCommission()
{
	return AgencyCommission;
}
public void setAgencyCommission(BigDecimal agencyCommission)
{
	AgencyCommission = agencyCommission;
}
public Integer getBookingId()
{
	return BookingId;
}
public void setBookingId(Integer bookingId)
{
	BookingId = bookingId;
}
public String getRegionId()
{
	return RegionId;
}
public void setRegionId(String regionId)
{
	RegionId = regionId;
}
public String getClassId()
{
	return ClassId;
}
public void setClassId(String classId)
{
	ClassId = classId;
}
public String getFeeId()
{
	return FeeId;
}
public void setFeeId(String feeId)
{
	FeeId = feeId;
}
public Integer getProductSupplierId()
{
	return ProductSupplierId;
}
public void setProductSupplierId(Integer productSupplierId)
{
	ProductSupplierId = productSupplierId;
}
 
 
}
