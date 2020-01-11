package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import myclass.*;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

public class UpdateVisit implements Initializable
{
    Controller c =new Controller();
    Visit visit=new Visit();
    ObservableList<String> alist= FXCollections.
            observableArrayList("Dog","Cat","Bird","Horse","Goat","Cow");
    ObservableList<String>pList= FXCollections.
            observableArrayList("Full Payment","Partial Payment","None");
    ObservableList<String>glist= FXCollections.
            observableArrayList("Female","Male");
    ObservableList<String> ctype= FXCollections.
            observableArrayList("Winchester Road","Caymanas Park");
    ObservableList<String> timeList = FXCollections.
            observableArrayList("8:00", "8:30", "9:00"
                    , "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00",
                    "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00",
                    "17:30", "18:00");


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        clinic.setValue("Winchester Road");
        clinic.setItems(ctype);
        animaltype.setValue("Dog");
        animaltype.setItems(alist);
        payoption.setValue("Full Payment");
        payoption.setItems(pList);
        gender.setValue("Male");
        gender.setItems(glist);
        itime.setValue("00:00");
        itime.setItems(timeList);
    }
    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField areacode;

    @FXML
    private TextField exchange;

    @FXML
    private TextField line;


    @FXML
    private TextField breed;

    @FXML
    private ChoiceBox<String> animaltype;

    @FXML
    private ChoiceBox<String> gender;

    @FXML
    private TextField age;

    @FXML
    private ChoiceBox<String> payoption;

    @FXML
    private ChoiceBox<String> clinic;

    @FXML
    private TextField visitreason;

    @FXML
    private ChoiceBox<String> itime;

    @FXML
    private TextField year;

    @FXML
    private TextField imonth;

    @FXML
    private TextField iday;

    @FXML
    private TextField intnum;

    @FXML
    void updateapp(ActionEvent event)throws Exception
    {
        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.DATE, 1);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String datemade = format1.format(cal.getTime());

        int updatenum=Integer.parseInt(intnum.getText());
        String firstname = fname.getText();
        String lastname = lname.getText();
        int acode = Integer.parseInt(areacode.getText());
        int xchange = Integer.parseInt(exchange.getText());
        int pline = Integer.parseInt(line.getText());
        String abreed = breed.getText();
        int anage = Integer.parseInt(age.getText());
        String selectedanimal = animaltype.getValue();
        String selectedpayment = payoption.getValue();
        String agender = gender.getValue();
        String ctype = clinic.getValue();
        String cvisitreason = visitreason.getText();
        int mointend =  Integer.parseInt(imonth.getText());
        int dintend =  Integer.parseInt(iday.getText());
        int yintend =  Integer.parseInt(year.getText());
        String tintend=itime.getValue();
        Animal animal = new Animal(agender, abreed, selectedanimal, anage);
        IDate intendate = new IDate(mointend, dintend, yintend,tintend);
        Telnum tel = new Telnum(acode, xchange, pline);
        Client client = new Client(firstname, lastname, selectedpayment, tel);
        visit = new Visit(cvisitreason, ctype, intendate, animal, client,datemade,updatenum);
        visit.updaterec(updatenum,visit);
        c.success();
        c.stafflogin(event);
    }

}
