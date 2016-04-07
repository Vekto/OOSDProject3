package travelexperts.util;

import javafx.util.Pair;

public class ComboPair extends Pair<Object,Object>
{

	public ComboPair(Object key, Object value)
	{
		super(key, value);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString()
	{
		

		String myString = this.getValue().toString();
		return myString;
	}

}
