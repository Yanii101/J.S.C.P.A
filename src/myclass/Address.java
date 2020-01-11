package myclass;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Address
{
   private StringProperty street;
   private StringProperty town;
   private StringProperty parish;
   
   public Address() 
   {
	   street=new SimpleStringProperty("");
	   town=new SimpleStringProperty("");
	   parish=new SimpleStringProperty("");
   }

   public Address(String street, String town, String parish)
   {
		this.street = new SimpleStringProperty(street);
		this.town = new SimpleStringProperty(town);
		this.parish = new SimpleStringProperty(parish);
   }
   public Address(Address address)
   {
	   this.street=address.street;
	   this.town=address.town;
	   this.parish=address.parish;
   }
	public String getStreet()
	{
		return street.get();
	}
	
	public void setStreet(String street)
	{
		this.street.set(street);
	}
	
	public String getTown() 
	{
		return town.get();
	}
	
	public void setTown(String town) 
	{
		this.town.set(town);
	}
	
	public String getParish() 
	{
		return parish.get();
	}

	public StringProperty streetProperty() {
		return street;
	}

	public StringProperty townProperty() {
		return town;
	}

	public StringProperty parishProperty() {
		return parish;
	}

	public void setParish(String parish)
	{
		this.parish.set(parish);
	}
	public void display()
	{
	   	System.out.println("the street is: "+street);
	   	System.out.println("the town is: "+town);
	   	System.out.println("the parish is: "+parish);
	} 
   
   
}
