package travelexperts.model;

import java.util.ArrayList;
import java.util.Date;


public class Booking
{
private Integer BookingId;
private Date BookingDate;
private String BookingNo;
private Integer TravelerCount;
private Integer CustomerId;
private String TripTypeId;
private Integer PackageId;

private final static String TABLE = "Bookings";
private static final String PRIMARYKEY = "BookingId";

public static ArrayList<Booking> getAllCustBookings(int Id)
{
	//DataBase.getMultiById(TABLE, "CustomerId" , Id, Booking.class);
	ArrayList<Booking> myList = new ArrayList<Booking>();
		for (Object object : DataBase.getMultiById(TABLE, "CustomerId" , Id, Booking.class))
		{
			myList.add((Booking)object);
		}
	return myList;
	
}








public Integer getBookingId()
{
	return BookingId;
}
public void setBookingId(int bookingId)
{
	BookingId = bookingId;
}
public Date getBookingDate()
{
	return BookingDate;
}
public void setBookingDate(Date bookingDate)
{
	BookingDate = bookingDate;
}
public String getBookingNo()
{
	return BookingNo;
}
public void setBookingNo(String bookingNo)
{
	BookingNo = bookingNo;
}
public Integer getTravelerCount()
{
	return TravelerCount;
}
public void setTravelerCount(int travelerCount)
{
	TravelerCount = travelerCount;
}
public Integer getCustomerId()
{
	return CustomerId;
}
public void setCustomerId(int customerId)
{
	CustomerId = customerId;
}
public String getTripTypeId()
{
	return TripTypeId;
}
public void setTripTypeId(String tripTypeId)
{
	TripTypeId = tripTypeId;
}
public Integer getPackageId()
{
	//if(PackageId != null)
	return PackageId;
	//else
	//	return 0;
}
public void setPackageId(int packageId)
{
	
	PackageId = packageId;
}


}
