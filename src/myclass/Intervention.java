package myclass;

import java.io.*;
import java.util.Date;
import java.util.Scanner;

public abstract class Intervention implements Serializable{
    protected String datemade;
    protected Client clientinfo;
    protected int idx = 0;

    public Intervention()
    {
        super();
        idx=0;
        clientinfo = new Client();
        datemade="";
    }

    public Intervention(String fname, String lname, String payoption, Telnum clientel,String datemade,int idx)
    {
        this.clientinfo = new Client(fname, lname, payoption, clientel);
        this.datemade=datemade;
        this.idx=idx;
    }

    public Intervention(Client clientinfo,String datemade,int idx)
    {
        this.clientinfo = clientinfo;
        this.datemade=datemade;
        this.idx=idx;
    }

    public Intervention(Intervention intervention)
    {
        this.clientinfo = intervention.clientinfo;
        this.idx=intervention.idx;
        this.datemade=intervention.datemade;
    }

    public Client getClientinfo()
    {
        return clientinfo;
    }

    public String getDatemade()
    {
        return datemade;
    }

    public void setDatemade(String datemade) {
        this.datemade = datemade;
    }

    public void setClientinfo(Client clientinfo) {
        this.clientinfo = clientinfo;
    }

    public void setIdx(int idx)
    {
        this.idx = idx;
    }

    public int getIdx() throws IOException {
        File file = new File("idx.txt");
        initIdxFile();

        try {
            Scanner in = new Scanner(new FileReader(file));
            if (in.hasNextInt()) {
                idx = in.nextInt();
            }

            return idx;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }


    public void updateIdx(int idx) {
        BufferedWriter writer = null;
        File file = new File("idx.txt");

        try {
            writer = new BufferedWriter(new FileWriter(file));

            writer.write(idx + 1 + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void initIdxFile() {
        BufferedWriter writer = null;
        File file = new File("idx.txt");

        if (!file.exists()) {
            try {
                writer = new BufferedWriter(new FileWriter(file));

                writer.write(1 + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public void display()
    {
        System.out.println("the int num is: "+idx);
        System.out.println("date made: "+datemade);
        this.clientinfo.display();
    }
}
