package travelexperts.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil
{
	
	public static LocalDate DateToLocalDate(Date date)
	{
    Instant instant = Instant.ofEpochMilli(date.getDate());
    LocalDate result = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
    return result;
	}
}
