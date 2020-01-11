package myclass;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class IDate
{
   private IntegerProperty day;
   private IntegerProperty month;
   private IntegerProperty year;
    private StringProperty time;

    public StringProperty timeProperty()
    {
        return time;
    }

    public IntegerProperty dayProperty() {
        return day;
    }

    public IntegerProperty monthProperty() {
        return month;
    }

    public IntegerProperty yearProperty() {
        return year;
    }

    public IDate()
   {
	day= new SimpleIntegerProperty(0);
	month=new SimpleIntegerProperty(0);
	year=new SimpleIntegerProperty(0);
	time=new SimpleStringProperty("");
   }
   	public IDate(int day, int month, int year,String time)
   	{
	this.day = new SimpleIntegerProperty(day);
	this.month = new SimpleIntegerProperty(month);
	this.year = new SimpleIntegerProperty(year);
	this.time=new SimpleStringProperty(time);
    }

    public IDate(IDate date)
    {
      this.day=date.day;
      this.month=date.month;
      this.year=date.year;
      this.time=date.time;
    }
    public int getDay() {
        return day.get();
    }

    public void setDay(int day) {
        this.day.set(day);
    }

    public String getTime() {
        return time.get();
    }
    public void setTime(String time) {
        this.time.set(time);
    }
    public int getMonth() {
        return month.get();
    }

    public void setMonth(int month) {
        this.month.set(month);
    }

    public int getYear() {

        return year.get();
    }

    public void setYear(int year) {
        this.year.set(year);
    }
    public void display()
    {
    	System.out.println("the day is: "+day);
    	System.out.println("the month is: "+month);
    	System.out.println("the year is: "+year);
        System.out.println("the time is: "+time);
    }


}
