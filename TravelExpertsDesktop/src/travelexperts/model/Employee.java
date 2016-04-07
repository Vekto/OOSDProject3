package travelexperts.model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Employee
{
	
	private Integer EmpId;
	private String EmpFirstName;
	private String EmpLastName;
	private String EmpMiddleInitial;
	private String EmpPosition;
	private String EmpBusPhone;
	private String EmpEmail;
	private Double EmpSalary;
	private BigDecimal EmpAvgHours;
	private String EmpWorkStatus;
	private boolean IsAgent;
	private static final String[] UPDATE_COLUMNS = 
			{ 
					"EmpFirstName",
					"EmpLastName",
					"EmpMiddleInitial",
					"EmpPosition",
					"EmpBusPhone",
					"EmpEmail",
					"EmpId",
					"EmpSalary",
					"EmpAvgHours",
					"EmpWorkStatus",
					"IsAgent"
		    };
	
	private final static String TABLE = "employees";
	private final static String PRIMARYKEY = "EmpId";
	
	public static ArrayList<Employee> getEmployees()
	{
			ArrayList<Employee> myList = new ArrayList<Employee>();
			try
			{
				for (Object object : DataBase.getTable(TABLE,Employee.class))
				{
					myList.add((Employee)object);
					System.out.println(("djk"+ (Employee)object).toString());
				}
			}
			catch (IllegalArgumentException | SecurityException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return myList;		
	}
	public static Employee getEmployeeById(int Id)
	{
		Employee myEmployee = null;
		try
		{
			myEmployee = (Employee)DataBase.getById(TABLE,PRIMARYKEY,Id,Employee.class);
		}
		catch (IllegalArgumentException | SecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myEmployee;	
	}
	
	public static int updateEmployee(Employee myEmployee, Employee myOldEmployee) 
	{
		int count = 0;
		
		
		
			count = DataBase.updateEntity(TABLE, UPDATE_COLUMNS, PRIMARYKEY, myEmployee.EmpId, myEmployee, myOldEmployee);
		
		
		
		return count;
	}
	
	@Override
	public String toString()
	{
		String returnString = "EmpId: " + this.EmpId + ",   First Name: " + this.EmpFirstName + ", Last Name: " + this.EmpLastName+ ", MiddleIntial: " + this.EmpMiddleInitial+ ", BusPhone: " + this.EmpBusPhone + ", Email: " + this.EmpEmail + ", Position: " + this.EmpPosition + ", avgHours: " + this.EmpAvgHours + ", WorkStatus: " + this.EmpWorkStatus + ", Salary: " +this.EmpSalary + ", Is Agent:" + this.IsAgent;
		return returnString;
	}
	
	public Integer getEmpId()
	{
		return EmpId;
	}
	public void setEmpId(Integer empId)
	{
		EmpId = empId;
	}
	public String getEmpFirstName()
	{
		return EmpFirstName;
	}
	public void setEmpFirstName(String empFirstName)
	{
		EmpFirstName = empFirstName;
	}
	public String getEmpLastName()
	{
		return EmpLastName;
	}
	public void setEmpLastName(String empLastName)
	{
		EmpLastName = empLastName;
	}
	public String getEmpMiddleInitial()
	{
		return EmpMiddleInitial;
	}
	public void setEmpMiddleInitial(String empMiddleInitial)
	{
		EmpMiddleInitial = empMiddleInitial;
	}
	public String getEmpPosition()
	{
		return EmpPosition;
	}
	public void setEmpPosition(String empPosition)
	{
		EmpPosition = empPosition;
	}
	public String getEmpBusPhone()
	{
		return EmpBusPhone;
	}
	public void setEmpBusPhone(String empBusPhone)
	{
		EmpBusPhone = empBusPhone;
	}
	public String getEmpEmail()
	{
		return EmpEmail;
	}
	public void setEmpEmail(String empEmail)
	{
		EmpEmail = empEmail;
	}
	public Double getEmpSalary()
	{
		return EmpSalary;
	}
	public void setEmpSalary(Double empSalary)
	{
		EmpSalary = empSalary;
	}
	public BigDecimal getEmpAvgHours()
	{
		return EmpAvgHours;
	}
	public void setEmpAvgHours(BigDecimal empAvgHours)
	{
		EmpAvgHours = empAvgHours;
	}
	public String getEmpWorkStatus()
	{
		return EmpWorkStatus;
	}
	public void setEmpWorkStatus(String empWorkStatus)
	{
		EmpWorkStatus = empWorkStatus;
	}
	public boolean IsAgent()
	{
		return IsAgent;
	}
	public void setAgent(boolean isAgent)
	{
		this.IsAgent = isAgent;
	}
	
	
}
