package myclass;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Telnum
{
    private IntegerProperty areacode;
    private IntegerProperty exchange;
    private IntegerProperty line;

	public IntegerProperty areacodeProperty() {
		return areacode;
	}

	public IntegerProperty exchangeProperty() {
		return exchange;
	}

	public IntegerProperty lineProperty() {
		return line;
	}

	public Telnum()
	{
		areacode=new SimpleIntegerProperty(0);
		exchange=new SimpleIntegerProperty(0);
		line=new SimpleIntegerProperty(0);
	}
	public Telnum(int areacode, int exchange, int line) 
	{
		this.areacode = new SimpleIntegerProperty(areacode);
		this.exchange = new SimpleIntegerProperty(exchange);
		this.line = new SimpleIntegerProperty(line);
	}
	public Telnum(Telnum telnum)
	{
		this.areacode=telnum.areacode;
		this.exchange=telnum.exchange;
		this.line=telnum.line;
	}
	public int getAreacode() {
		return areacode.get();
	}
	public void setAreacode(int areacode) {
		this.areacode.set(areacode);
	}
	public int getExchange() {
		return exchange.get();
	}
	public void setExchange(int exchange) {
		this.exchange.set(exchange);
	}
	public int getLine() {
		return line.get();
	}
	public void setLine(int line) {
		this.line.set(line);
	}
	public void display()
	{
	   	System.out.println("the areacode is: "+areacode);
	   	System.out.println("the exchange is: "+exchange);
	   	System.out.println("the line is: "+line);
	}       
}
