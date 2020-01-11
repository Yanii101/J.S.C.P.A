package myclass;

import controller.Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Removal extends Intervention
{
	private StringProperty animalcondition;
	private StringProperty outcome;
	private Address address;
	private int offset = (50 + (25 * 2) + (25 * 2) + (3 * 4) + (3 + 4) + (25 * 2) + (25 * 2) + (25 * 2) + (1 * 4));
    Controller c=new Controller();
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public Removal()
	{
		animalcondition=new SimpleStringProperty("");
		outcome=new SimpleStringProperty("");
		address=new Address();
	}
	public Removal(String animalcondition, String outcome, Address address,Client clientinfo,String datemade,int idx,
				   String street,String town,String parish)
	{
		super(clientinfo,datemade,idx);
		this.animalcondition = new SimpleStringProperty(animalcondition);
		this.outcome =new SimpleStringProperty(outcome);
		this.address = new Address(street,town,parish);
	}
	public Removal(String animalcondition, String outcome, Address address,Client clientinfo,String datemade,int idx)
	{
		super(clientinfo,datemade,idx);
		this.animalcondition = new SimpleStringProperty(animalcondition);
		this.outcome =new SimpleStringProperty(outcome);
		this.address = address;
	}
	public Removal(Removal removal) throws IOException {
		super(removal.getClientinfo(),removal.getDatemade(),removal.getIdx());
		this.animalcondition=removal.animalcondition;
		this.outcome=removal.outcome;
		this.address=removal.address;
	}
	public String getAnimalcondition()
	{
		return animalcondition.get();
	}
	public void setAnimalcondition(String animalcondition)
	{
		this.animalcondition.set(animalcondition);
	}
	public String getOutcome()
	{
		return outcome.get();
	}

	public StringProperty animalconditionProperty() {
		return animalcondition;
	}

	public StringProperty outcomeProperty() {
		return outcome;
	}

	public void setOutcome(String outcome)
	{
		this.outcome.set(outcome);
	}
	public Address getAddress()
	{
		return address;
	}
	public void setAddress(Address address) 
	{
		this.address = address;
	}
	 public void display()
	{
		super.display();
     	System.out.println("the animal condition is: "+animalcondition);
     	System.out.println("the outcome is: "+outcome);
     	this.address.display();
	}

	public void initialize() {
		RandomAccessFile f = null;
		try {
			f = new RandomAccessFile(new File("visit.dat"), "rw");
			for (int idx = 1; idx <= 1000; idx++) {
				f.seek((idx - 1000) * offset);
				f.writeInt(0);
				f.writeUTF("");
				f.writeUTF("");
				f.writeUTF("");
				f.writeInt(0);
				f.writeInt(0);
				f.writeInt(0);
				f.writeUTF("");
				f.writeUTF("");
				f.writeUTF("");
				f.writeUTF("");
				f.writeUTF("");
				f.writeUTF("");
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

	public void addremoval(Removal r) throws Exception {
		try {
			updateIdx(getIdx());
		} catch (IOException e) {
			e.printStackTrace();
		}
		RandomAccessFile raf = null;
        /*DateFormat dmade = new SimpleDateFormat("MMM dd yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String datemade = dmade.format(today);
        raf.writeUTF(datemade);*/
		try {
			raf = new RandomAccessFile("removal.dat", "rw");
			raf.seek((getIdx() - 1) * offset);
			raf.writeInt(getIdx());
			raf.writeUTF(r.getDatemade());
			raf.writeUTF(r.getClientinfo().getFname());
			raf.writeUTF(r.getClientinfo().getLname());
			raf.writeUTF(r.getClientinfo().getPayoption());
			raf.writeInt(r.getClientinfo().getClientel().getAreacode());
			raf.writeInt(r.getClientinfo().getClientel().getExchange());
			raf.writeInt(r.getClientinfo().getClientel().getLine());
			raf.writeUTF(r.getAnimalcondition());
			raf.writeUTF(r.getOutcome());
			raf.writeUTF(r.getAddress().getStreet());
			raf.writeUTF(r.getAddress().getTown());
			raf.writeUTF(r.getAddress().getParish());
			System.out.println("writing");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (raf != null) {
					raf.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public List<Removal> viewremoval() throws IOException {
		Removal removal = new Removal();
		List<Removal> removalall = new ArrayList<>();
		RandomAccessFile remfile = new RandomAccessFile("removal.dat", "rw");
		try {
			for (int idx = 1; idx <= 1000; idx++) {
				remfile.seek((idx - 1) * offset);
				int id = remfile.readInt();
				String dmade=remfile.readUTF();
				String firstname = remfile.readUTF();
				String lastname = remfile.readUTF();
				String payoption = remfile.readUTF();
				int areacode = remfile.readInt();
				int exchange = remfile.readInt();
				int line=remfile.readInt();
				String acondition = remfile.readUTF();
				String outcom = remfile.readUTF();
				String street = remfile.readUTF();
				String twn = remfile.readUTF();
				String parish = remfile.readUTF();
				Address add = new Address(street,twn,parish);
				Telnum tel = new Telnum(areacode, exchange, line);
				Client client = new Client(firstname, lastname, payoption, tel);
				removal = new Removal(acondition, outcom, add, client,dmade,id);
				removalall.add(removal);
			}
		} catch (EOFException e) {
			System.out.println("eof");
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (Iterator<Removal> itr = removalall.iterator(); itr.hasNext(); ) {
			Removal rem = itr.next();

			rem.display();
			System.out.println("--------------------------------");
		}
		//removal.display();
//         visit.display();
		return removalall;
	}
	public List<Removal> singleremoval(int num)throws IOException
	{
		Removal rem = new Removal();
		List<Removal>onerem = new ArrayList<>();
		RandomAccessFile remfile = null;

		try {
			remfile = new RandomAccessFile("removal.dat", "rw");
			remfile.seek((num - 1) * getOffset());
			int id = remfile.readInt();
			String datemade = remfile.readUTF();
			String firstname = remfile.readUTF();
			String lastname = remfile.readUTF();
			String payoption = remfile.readUTF();
			int areacode = remfile.readInt();
			int exchange = remfile.readInt();
			int line = remfile.readInt();
			String acondition = remfile.readUTF();
			String outcom = remfile.readUTF();
			String street = remfile.readUTF();
			String twn = remfile.readUTF();
			String parish = remfile.readUTF();
			Address add = new Address(street, twn, parish);
			Telnum tel = new Telnum(areacode, exchange, line);
			Client client = new Client(firstname, lastname, payoption, tel);
			rem = new Removal(acondition, outcom, add, client, datemade, num);
			onerem.add(rem);
		}catch (Exception e)
		{
			e.printStackTrace();
		}
			for (Iterator<Removal> itr = onerem.iterator(); itr.hasNext(); ) {
				Removal removal = itr.next();

				removal.display();
				System.out.println("--------------------------------");
			}
			//removal.display();
//         visit.display();
			return onerem;
		}
	public boolean delremoval(int num) throws IOException

	{
		Removal rem=new Removal();
		RandomAccessFile raf=null;
		try{
			raf = new RandomAccessFile("removal.dat", "rw");
			raf.seek((num - 1) * offset);
			raf.writeInt(0);
			raf.writeUTF(rem.getClientinfo().getFname());
			raf.writeUTF(rem.getClientinfo().getLname());
			raf.writeUTF(rem.getClientinfo().getPayoption());
			raf.writeInt(rem.getClientinfo().getClientel().getAreacode());
			raf.writeInt(rem.getClientinfo().getClientel().getExchange());
			raf.writeInt(rem.getClientinfo().getClientel().getLine());
			raf.writeUTF(rem.getAnimalcondition());
			raf.writeUTF(rem.getOutcome());
			raf.writeUTF(rem.getAddress().getStreet());
			raf.writeUTF(rem.getAddress().getTown());
			raf.writeUTF(rem.getAddress().getParish());
			c.success();
            return true;
		} catch (FileNotFoundException e) {
			System.out.println("not in file");
			return false;
		}finally {
			try {
				if (raf != null) {
					raf.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public boolean updaterem(int num,Removal r) throws IOException {
      RandomAccessFile raf=null;
      try
	  {
		  raf = new RandomAccessFile("removal.dat", "rw");
		  raf.seek((num - 1) * getOffset());
		  raf.writeInt(num);
		  raf.writeUTF(r.getDatemade());
		  raf.writeUTF(r.getClientinfo().getFname());
		  raf.writeUTF(r.getClientinfo().getLname());
		  raf.writeUTF(r.getClientinfo().getPayoption());
		  raf.writeInt(r.getClientinfo().getClientel().getAreacode());
		  raf.writeInt(r.getClientinfo().getClientel().getExchange());
		  raf.writeInt(r.getClientinfo().getClientel().getLine());
		  raf.writeUTF(r.getAnimalcondition());
		  raf.writeUTF(r.getOutcome());
		  raf.writeUTF(r.getAddress().getStreet());
		  raf.writeUTF(r.getAddress().getTown());
		  raf.writeUTF(r.getAddress().getParish());
		  c.success();
		  return true;
	  } catch (FileNotFoundException e) {
		  e.printStackTrace();
		  return false;
	  }
	  finally {
		  try {
			  if (raf != null) {
				  raf.close();
			  }
		  } catch (IOException e) {
			  e.printStackTrace();
		  }
	  }
	}

	public List<Removal> location(String p) throws IOException {
		Removal removal = new Removal();
		List<Removal> locationlist = new ArrayList<>();
		RandomAccessFile remfile = new RandomAccessFile("removal.dat", "rw");
		try {
			for (int idx = 1; idx <= 1000; idx++)
			{
				remfile.seek((idx-1)*offset);
				int id = remfile.readInt();
				String datemade = remfile.readUTF();
				String firstname = remfile.readUTF();
				String lastname = remfile.readUTF();
				String payoption = remfile.readUTF();
				int areacode = remfile.readInt();
				int exchange = remfile.readInt();
				int line = remfile.readInt();
				String acondition = remfile.readUTF();
				String outcom = remfile.readUTF();
				String street = remfile.readUTF();
				String twn = remfile.readUTF();
				String parish = remfile.readUTF();
				if(parish.equals(p))
				{
					Address add = new Address(street, twn, parish);
					Telnum tel = new Telnum(areacode, exchange, line);
					Client client = new Client(firstname, lastname, payoption, tel);
					removal = new Removal(acondition, outcom, add, client,datemade,id);
					locationlist.add(removal);
				}
			}
		} catch (EOFException e) {
			System.out.println("End of file");
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (Iterator<Removal> itr = locationlist.iterator(); itr.hasNext(); ) {
			Removal rem = itr.next();

			rem.display();
			System.out.println("--------------------------------");
		}
		//removal.display();
//         visit.display();
		return locationlist;
	}
	public List<Removal> outcomerep(String o) throws IOException {
		Removal removal = new Removal();
		List<Removal> outcomerep = new ArrayList<>();
		RandomAccessFile remfile = new RandomAccessFile("removal.dat", "rw");
		try {
			for (int idx = 1; idx <= 1000; idx++)
			{
				remfile.seek((idx-1)*offset);
				int id = remfile.readInt();
				String datemade = remfile.readUTF();
				String firstname = remfile.readUTF();
				String lastname = remfile.readUTF();
				String payoption = remfile.readUTF();
				int areacode = remfile.readInt();
				int exchange = remfile.readInt();
				int line = remfile.readInt();
				String acondition = remfile.readUTF();
				String outcom = remfile.readUTF();
				String street = remfile.readUTF();
				String twn = remfile.readUTF();
				String parish = remfile.readUTF();
				if(outcom.equals(o))
				{
					Address add = new Address(street, twn, parish);
					Telnum tel = new Telnum(areacode, exchange, line);
					Client client = new Client(firstname, lastname, payoption, tel);
					removal = new Removal(acondition, outcom, add, client,datemade,id);
					removal.setIdx(id);
					outcomerep.add(removal);
				}
			}
		} catch (EOFException e) {
			System.out.println("end of file");
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (Iterator<Removal> itr = outcomerep.iterator(); itr.hasNext(); ) {
			Removal rem = itr.next();

			rem.display();
			System.out.println("--------------------------------");
		}
		//removal.display();
//         visit.display();
		return outcomerep;
	}

}
