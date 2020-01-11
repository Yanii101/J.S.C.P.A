package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import myclass.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Addvisit implements Initializable
{
   Controller c=new Controller();
   Mainmenu m=new Mainmenu();
   Visit visit=new Visit();

    @FXML
    private AnchorPane clientadd;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField line;

    @FXML
    private TextField exchange;

    @FXML
    private TextField areacode;

    @FXML
    private ChoiceBox<String> animaltype;

    @FXML
    private TextField breed;

    @FXML
    private TextField age;

    @FXML
    private ChoiceBox<String> paymenttype;

    @FXML
    private ChoiceBox<String> gender;

    @FXML
    private TextField vreason;

    @FXML
    private TextField interventionnumber;

    @FXML
    private ChoiceBox<String> clinictype;

    @FXML
    private TextField month;

    @FXML
    private TextField day;

    @FXML
    private TextField year;
    @FXML
    private ChoiceBox<String> timeint;

    @FXML
    private DatePicker dateintend;

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
        clinictype.setValue("Winchester Road");
        clinictype.setItems(ctype);
        animaltype.setValue("Dog");
        animaltype.setItems(alist);
        paymenttype.setValue("Full Payment");
        paymenttype.setItems(pList);
        gender.setValue("Male");
        gender.setItems(glist);
        timeint.setValue("00:00");
        timeint.setItems(timeList);
    }

    @FXML
    void logout(MouseEvent event) throws IOException {
       m.logout(event);
    }

    @FXML
    void makeapp(ActionEvent event)throws IOException {
        Scanner scanner = new Scanner(new File("idx.txt"));
        int intnum = scanner.nextInt();

        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.DATE, 1);
        SimpleDateFormat format1 = new SimpleDateFormat("dd MMM yyyy hh:mm:ss");
        String datemade = format1.format(cal.getTime());
        try {
            String firstname = fname.getText();
            String lastname = lname.getText();
            int acode = Integer.parseInt(areacode.getText());
            int xchange = Integer.parseInt(exchange.getText());
            int pline = Integer.parseInt(line.getText());
            String abreed = breed.getText();
            int anage = Integer.parseInt(age.getText());
            String selectedanimal = animaltype.getValue();
            String selectedpayment = paymenttype.getValue();
            String agender = gender.getValue();
            String ctype = clinictype.getValue();
            String visitreason = vreason.getText();
            int mointend = Integer.parseInt(month.getText());
            int dintend = Integer.parseInt(day.getText());
            int yintend = Integer.parseInt(year.getText());

            if (mointend < 1 || mointend > 12 || dintend < 1 || dintend > 31 || yintend < 2017 || yintend > 2027) {
                Alert a1 = new Alert(Alert.AlertType.ERROR);
                a1.setTitle("Date Range");
                a1.setContentText("The date range is incorrect,\nPlease check the range and try again");
                a1.setHeaderText(null);
                a1.showAndWait();
            } else {


                String timereq = timeint.getValue();
                Animal animal = new Animal(agender, abreed, selectedanimal, anage);
                IDate intendate = new IDate(mointend, dintend, yintend, timereq);
                Telnum tel = new Telnum(acode, xchange, pline);
                Client client = new Client(firstname, lastname, selectedpayment, tel);
                visit = new Visit(visitreason, ctype, intendate, animal, client,datemade,intnum);
                visit.addvisit(visit);

                scanner = new Scanner(new File("idx.txt"));
                int num = scanner.nextInt();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Successfully added appointment");
                alert.setHeaderText("Congrats on making a Visit \n Your" +
                        "Intervention number is " + num);
                alert.setContentText("chooose an option");
                ButtonType main = new ButtonType("Main Menu");
                ButtonType cxl = new ButtonType("Exit program", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(main, cxl);

                //if they submit again just make sure its not dublicate

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == main) {
                    System.out.println("call main");
                } else if (result.get() == cxl) {
                    System.out.println("cxl");

                } else {
                    event.consume();
                }
                c.stafflogin(event);
            }
        }catch (NumberFormatException e)
        {
            c.invalidinput();
        }
            catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void newanimal(ActionEvent event) {

    }

    public void daterange(int m,int y,int d)
    {

    }

}
