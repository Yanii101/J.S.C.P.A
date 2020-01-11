package myclass;

import controller.Controller;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Visit extends Intervention {
    private String reason;
    private String clinicname;
    private IDate intendate;
    private Animal animal;
    Controller c=new Controller();
    private int offset = (50 + (25 * 2) + (25 * 2) + (3 * 4) + (3 + 4) + (25 * 2) + (25 * 2) + (25 * 2) + (1 * 4));
    //offset = (2 * 20) + (2 * 20) + (2 * 20) + (3 * 4) + (3 * 4) + (4 * 20);


    public Visit() {
        super();
        reason = "";
        clinicname = "";
        intendate = new IDate();
        animal = new Animal();
    }

    public Visit(String reason, String clinicname, IDate intendate, Animal animal, Client clientinfo,String datemade,int idx) throws IOException {
        super(clientinfo,datemade,idx);
        this.reason = reason;
        this.clinicname = clinicname;
        this.intendate = intendate;
        this.animal = animal;
    }

    public Visit(Visit visit) throws IOException {
        super(visit.getClientinfo(),visit.getDatemade(),visit.getIdx());
        this.reason = visit.reason;
        this.clinicname = visit.clinicname;
        this.intendate = visit.intendate;
        this.animal = visit.animal;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getClinicname() {
        return clinicname;
    }

    public void setClinicname(String clinicname) {
        this.clinicname = clinicname;
    }

    public IDate getIntendate() {
        return intendate;
    }

    public void setIntendate(IDate intendate) {
        this.intendate = intendate;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void display() {
        System.out.println("the reason is: " + reason);
        System.out.println("the clinic name is: " + clinicname);
        this.intendate.display();
        this.animal.display();
        super.display();
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(reason);
        buffer.append("\n");
        buffer.append(clinicname);
        buffer.append("\n");
        buffer.append(animal);
        buffer.append("\n");

        return buffer.toString();
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
                f.writeUTF("");
                f.writeInt(0);
                f.writeInt(0);
                f.writeInt(0);
                f.writeUTF("");
                f.writeUTF("");
                f.writeUTF("");
                f.writeInt(0);
                f.writeInt(0);
                f.writeInt(0);
                f.writeInt(0);
                f.writeInt(0);
                f.writeInt(0);
                f.writeUTF("");
                f.writeUTF("");
                f.writeUTF("");
                f.writeUTF("");
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

    public void addvisit(Visit v) throws Exception {
        try {
            updateIdx(getIdx());
        } catch (IOException e) {
            e.printStackTrace();
        }
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile("visit.dat", "rw");
            raf.seek((v.getIdx() - 1) * offset);
            raf.writeInt(v.getIdx());
            raf.writeUTF(v.getClientinfo().getFname());
            raf.writeUTF(v.getClientinfo().getLname());
            raf.writeUTF(v.getClientinfo().getPayoption());
            raf.writeInt(v.getClientinfo().getClientel().getAreacode());
            raf.writeInt(v.getClientinfo().getClientel().getExchange());
            raf.writeInt(v.getClientinfo().getClientel().getLine());
            raf.writeUTF(v.getDatemade());
            raf.writeUTF(v.getReason());
            raf.writeUTF(v.getClinicname());
            raf.writeInt(v.getIntendate().getYear());
            raf.writeInt(v.getIntendate().getMonth());
            raf.writeInt(v.getIntendate().getDay());
            raf.writeUTF(v.getIntendate().getTime());
            raf.writeUTF(v.getAnimal().getGender());
            raf.writeUTF(v.getAnimal().getBreed());
            raf.writeUTF(v.getAnimal().getType());
            raf.writeInt(v.getAnimal().getAge());
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

    public List<Visit> readvisit() throws IOException {
        Visit visit = new Visit();
        List<Visit> allvisit = new ArrayList<>();
        RandomAccessFile visitfile = new RandomAccessFile("visit.dat", "rw");
        try {
            for (int idx = 1; idx <= 1000; idx++) {
                visitfile.seek((idx - 1) * offset);
                int id = visitfile.readInt();
                String firstname = visitfile.readUTF();
                String lastname = visitfile.readUTF();
                String payoption = visitfile.readUTF();
                int areacode = visitfile.readInt();
                int exchange = visitfile.readInt();
                int line = visitfile.readInt();
                String dmade=visitfile.readUTF();
                String reason = visitfile.readUTF();
                String clinic = visitfile.readUTF();
                int year = visitfile.readInt();
                int month = visitfile.readInt();
                int day = visitfile.readInt();
                String time=visitfile.readUTF();
                String gender = visitfile.readUTF();
                String breed = visitfile.readUTF();
                String type = visitfile.readUTF();
                int age = visitfile.readInt();
                Animal animal = new Animal(gender, breed, type, age);
                IDate intendate = new IDate(month, day, year,time);
                Telnum tel = new Telnum(areacode, exchange, line);
                Client client = new Client(firstname, lastname, payoption, tel);
                visit.setIdx(id);
                visit = new Visit(reason, clinic, intendate, animal,client,dmade,id);
                allvisit.add(visit);
            }
        } catch (EOFException e) {
            System.out.println("eof");
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Iterator<Visit> itr = allvisit.iterator(); itr.hasNext(); ) {
            Visit visit1 = itr.next();

            visit1.display();
            System.out.println("--------------------------------");
        }

//         visit.display();
        return allvisit;
    }
    public List<Visit> viewsinglerecord(int num) {
        Visit visit = new Visit();
        List<Visit> svisit = new ArrayList<>();
        RandomAccessFile visitfile = null;
        try {
            visitfile = new RandomAccessFile("visit.dat", "rw");
            visitfile.seek((num - 1) * getOffset());
            int id = visitfile.readInt();
            String firstname = visitfile.readUTF();
            String lastname = visitfile.readUTF();
            String payoption = visitfile.readUTF();
            int areacode = visitfile.readInt();
            int exchange = visitfile.readInt();
            int line = visitfile.readInt();
            String dmade=visitfile.readUTF();
            String reason = visitfile.readUTF();
            String clinic = visitfile.readUTF();
            int year = visitfile.readInt();
            int month = visitfile.readInt();
            int day=visitfile.readInt();
            String time=visitfile.readUTF();
            String gender = visitfile.readUTF();
            String breed = visitfile.readUTF();
            String type = visitfile.readUTF();
            int age = visitfile.readInt();
            Animal animal = new Animal(gender, breed, type, age);
            IDate intendate = new IDate(month, day, year,time);
            Telnum tel = new Telnum(areacode, exchange, line);
            Client client = new Client(firstname, lastname, payoption, tel);
            visit = new Visit(reason, clinic, intendate, animal, client,dmade,num);
            svisit.add(visit);
        } catch (FileNotFoundException e)
        {
            c.notfound();
        }catch (IOException w)
        {
            c.outofrange();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        for (Iterator<Visit> itr = svisit.iterator(); itr.hasNext(); ) {
            Visit visit1 = itr.next();

            visit1.display();
            System.out.println("--------------------------------");
        }
        return svisit;
    }

    public boolean deleteitervent(int num,Visit v)throws IOException {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile("visit.dat", "rw");
            raf.seek((num - 1) * offset);
            raf.writeInt(0);
            raf.writeUTF(v.getClientinfo().getFname());
            raf.writeUTF(v.getClientinfo().getLname());
            raf.writeUTF(v.getClientinfo().getPayoption());
            raf.writeInt(v.getClientinfo().getClientel().getAreacode());
            raf.writeInt(v.getClientinfo().getClientel().getExchange());
            raf.writeInt(v.getClientinfo().getClientel().getLine());
            raf.writeUTF(v.getDatemade());
            raf.writeUTF(v.getReason());
            raf.writeUTF(v.getClinicname());
            raf.writeInt(v.getIntendate().getYear());
            raf.writeInt(v.getIntendate().getMonth());
            raf.writeInt(v.getIntendate().getDay());
            raf.writeUTF(v.getIntendate().getTime());
            raf.writeUTF(v.getAnimal().getGender());
            raf.writeUTF(v.getAnimal().getBreed());
            raf.writeUTF(v.getAnimal().getType());
            raf.writeInt(v.getAnimal().getAge());
            c.success();
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
        return false;
    }

    public boolean updaterec(int num, Visit v) throws IOException {
        RandomAccessFile raf = null;
        try {
           raf = new RandomAccessFile("visit.dat", "rw");
            raf.seek((num - 1) * getOffset());
            raf.writeInt(num);
            raf.writeUTF(v.getClientinfo().getFname());
            raf.writeUTF(v.getClientinfo().getLname());
            raf.writeUTF(v.getClientinfo().getPayoption());
            raf.writeInt(v.getClientinfo().getClientel().getAreacode());
            raf.writeInt(v.getClientinfo().getClientel().getExchange());
            raf.writeInt(v.getClientinfo().getClientel().getLine());
            raf.writeUTF(v.getDatemade());
            raf.writeUTF(v.getReason());
            raf.writeUTF(v.getClinicname());
            raf.writeInt(v.getIntendate().getYear());
            raf.writeInt(v.getIntendate().getMonth());
            raf.writeInt(v.getIntendate().getDay());
            raf.writeUTF(v.getIntendate().getTime());
            raf.writeUTF(v.getAnimal().getGender());
            raf.writeUTF(v.getAnimal().getBreed());
            raf.writeUTF(v.getAnimal().getType());
            raf.writeInt(v.getAnimal().getAge());
            return true;
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
        return false;
    }
    public List<Visit> clinicloationrep(String cname) throws Exception{
        Visit visit = new Visit();
        List<Visit> locationlist = new ArrayList<>();
        RandomAccessFile visitfile = new RandomAccessFile("visit.dat", "rw");
        try {
            for (int idx = 1; idx <= 1000; idx++) {
                visitfile.seek((idx - 1) * offset);
                int id = visitfile.readInt();
                String firstname = visitfile.readUTF();
                String lastname = visitfile.readUTF();
                String payoption = visitfile.readUTF();
                int areacode = visitfile.readInt();
                int exchange = visitfile.readInt();
                int line = visitfile.readInt();
                String dmade=visitfile.readUTF();
                String reason = visitfile.readUTF();
                String clinic = visitfile.readUTF();
                int year = visitfile.readInt();
                int month = visitfile.readInt();
                int day = visitfile.readInt();
                String time=visitfile.readUTF();
                String gender = visitfile.readUTF();
                String breed = visitfile.readUTF();
                String type = visitfile.readUTF();
                int age = visitfile.readInt();
                if(clinic.equals(cname))
                {
                    Animal animal = new Animal(gender, breed, type, age);
                    IDate intendate = new IDate(month, day, year,time);
                    Telnum tel = new Telnum(areacode, exchange, line);
                    Client client = new Client(firstname, lastname, payoption, tel);
                    visit = new Visit(reason, clinic, intendate, animal, client,dmade,idx);
                    visit.setIdx(id);
                    visit.setDatemade(dmade);
                    locationlist.add(visit);
                }
            }
        } catch (EOFException e) {
            System.out.println("eof");
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Iterator<Visit> itr = locationlist.iterator(); itr.hasNext(); ) {
            Visit visit1 = itr.next();

            visit1.display();
            System.out.println("--------------------------------");
        }

//         visit.display();
        return locationlist;
    }



    public List<Visit> animaltypereport(String aname) throws Exception{
        Visit visit = new Visit();
        List<Visit> alist = new ArrayList<>();
        RandomAccessFile visitfile = new RandomAccessFile("visit.dat", "rw");
        try {
            for (int idx = 1; idx <= 1000; idx++) {
                visitfile.seek((idx - 1) * offset);
                int id = visitfile.readInt();
                String firstname = visitfile.readUTF();
                String lastname = visitfile.readUTF();
                String payoption = visitfile.readUTF();
                int areacode = visitfile.readInt();
                int exchange = visitfile.readInt();
                int line = visitfile.readInt();
                String dmade=visitfile.readUTF();
                String reason = visitfile.readUTF();
                String clinic = visitfile.readUTF();
                int year = visitfile.readInt();
                int month = visitfile.readInt();
                int day = visitfile.readInt();
                String time=visitfile.readUTF();
                String gender = visitfile.readUTF();
                String breed = visitfile.readUTF();
                String type = visitfile.readUTF();
                int age = visitfile.readInt();
                if(type.equals(aname))
                {
                    Animal animal = new Animal(gender, breed, type, age);
                    IDate intendate = new IDate(month, day, year,time);
                    Telnum tel = new Telnum(areacode, exchange, line);
                    Client client = new Client(firstname, lastname, payoption, tel);
                    visit = new Visit(reason, clinic, intendate, animal, client,dmade,idx);
                    alist.add(visit);
                }
            }
        } catch (EOFException e) {
            System.out.println("eof");
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Iterator<Visit> itr = alist.iterator(); itr.hasNext(); ) {
            Visit visit1 = itr.next();

            visit1.display();
            System.out.println("--------------------------------");
        }

//         visit.display();
        return alist;
    }



    public List<Visit> vdatereport(int smonth,int sday,int syear) throws IOException {
        Visit visit = new Visit();
        List<Visit> datelist = new ArrayList<>();
        RandomAccessFile visitfile = new RandomAccessFile("visit.dat", "rw");
        try {
            for (int idx = 1; idx <= 1000; idx++) {
                visitfile.seek((idx - 1) * offset);
                int id = visitfile.readInt();
                String firstname = visitfile.readUTF();
                String lastname = visitfile.readUTF();
                String payoption = visitfile.readUTF();
                int areacode = visitfile.readInt();
                int exchange = visitfile.readInt();
                int line = visitfile.readInt();
                String dmade = visitfile.readUTF();
                String reason = visitfile.readUTF();
                String clinic = visitfile.readUTF();
                int year = visitfile.readInt();
                int month = visitfile.readInt();
                int day = visitfile.readInt();
                String time = visitfile.readUTF();
                String gender = visitfile.readUTF();
                String breed = visitfile.readUTF();
                String type = visitfile.readUTF();
                int age = visitfile.readInt();
                System.out.println("CUrrent month " + month + " user month " + smonth);
                System.out.println("CUrrent dya " + day + "U DAy " + sday);
                System.out.println("CUrrent tyera " + year + " useryerah " + syear);
                if (month == smonth) {
                         if (day == sday) {
                              if (year == syear)
                              {
                            Animal animal = new Animal(gender, breed, type, age);
                            IDate intendate = new IDate(month, day, year, time);
                            Telnum tel = new Telnum(areacode, exchange, line);
                            Client client = new Client(firstname, lastname, payoption, tel);
                            visit = new Visit(reason, clinic, intendate, animal, client, dmade, idx);
                            datelist.add(visit);
                        }
                    }
                }
            }
        } catch (EOFException e)
        {
           System.out.println("End of file");
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Iterator<Visit> itr = datelist.iterator(); itr.hasNext(); ) {
            Visit visit1 = itr.next();

            visit1.display();
            System.out.println("--------------------------------");
        }

//         visit.display();
        return datelist;
    }
}






