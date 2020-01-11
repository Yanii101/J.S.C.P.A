package myclass;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Client
{
	private StringProperty fname;
	private StringProperty lname;
	private StringProperty payoption;
	private Telnum clientel;
	private int offset = (2*20)+(2*20)+(2*20)+(3*4);

	public Client()
	{
		fname=new SimpleStringProperty("");
		lname=new SimpleStringProperty("");
		payoption=new SimpleStringProperty("");
		clientel=new Telnum();
	}
	public Client(String fname, String lname, String payoption,Telnum clientel,int hours, int minutes, int seconds)
	{
		this.fname = new SimpleStringProperty(fname);
		this.lname = new SimpleStringProperty(lname);
		this.payoption = new SimpleStringProperty(payoption);
		this.clientel =new Telnum(hours,minutes,seconds);
	}
	public Client(String fname, String lname, String payoption,Telnum clientel)
	{
		this.fname = new SimpleStringProperty(fname);
		this.lname =new SimpleStringProperty(lname);
		this.payoption =new SimpleStringProperty(payoption);
		this.clientel = clientel;
	}

	public Client(Client client)
	{
		this.fname=client.fname;
		this.lname=client.lname;
		this.payoption=client.payoption;
		this.clientel=client.clientel;
	}

	public String getFname() {
		return fname.get();
	}

	public void setFname(String fname) {
		this.fname.set(fname);
	}

	public String getLname() {
		return lname.get();
	}

	public void setLname(String lname) {
		this.lname.set(lname);
	}

	public String getPayoption() {
		return payoption.get();
	}

	public StringProperty fnameProperty() {
		return fname;
	}

	public StringProperty lnameProperty() {
		return lname;
	}

	public StringProperty payoptionProperty() {
		return payoption;
	}

	public void setPayoption(String payoption) {
		this.payoption.set(payoption);
	}

	public Telnum getClientel() {
		return clientel;
	}

	public void setClientel(Telnum clientel) {
		this.clientel = clientel;
	}

	public void display()
	{
		System.out.println("the first name is: "+fname);
		System.out.println("the lastname is: "+lname);
		System.out.println("the payoption is: "+payoption);
		this.clientel.display();
	}
	public void initialize()
	{
		RandomAccessFile f=null;
		try
		{
			f=new RandomAccessFile(new File("client.dat"),"rw");
			for(int idx = 17100; idx <= 17399; idx++)
			{
				f.seek((idx - 17100) * offset);
				f.writeInt(0);
				f.writeUTF("");
				f.writeUTF("");
				f.writeUTF("");
				f.writeInt(0);
				f.writeInt(0);
				f.writeInt(0);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (f != null) {
					f.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public Client retrieve(int id) {
		RandomAccessFile file = null;

		try {
			file = new RandomAccessFile("client.dat", "rw");
			file.seek((id - 1) * offset);
			setFname(file.readUTF());
			setLname(file.readUTF());
			setPayoption(file.readUTF());

			return this;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (file != null) {
					file.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
