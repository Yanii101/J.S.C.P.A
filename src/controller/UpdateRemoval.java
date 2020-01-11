package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import myclass.Address;
import myclass.Client;
import myclass.Removal;
import myclass.Telnum;

import java.io.FileNotFoundException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

public class UpdateRemoval implements Initializable
{
    Controller c =new Controller();
    Addremoval addrem=new Addremoval();
    ObservableList<String>parishlist=FXCollections.
            observableArrayList("Kingston", "St. Andrew", "St. Catherine", "Clarendon", "Manchester", "St. Elizabeth","Westmoreland", "Hanover", "St. James", "Trelawny", "St. Ann",
                    "St. Mary", "Portland","St. Thomas");
    ObservableList<String> removalist = FXCollections.
            observableArrayList("Reproduce Indiscrimately", "Animal left to forage for itself",
                    "Overpopulation", "Aggressive  Animal", "Illness","Other");
    ObservableList<String> pList = FXCollections.
            observableArrayList("Full Payment", "Partial Payment", "None");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        condition.setValue("Reproduce Indiscrimately");
        condition.setItems(removalist);
        paymenttype.setValue("Full Payment");
        paymenttype.setItems(pList);
        parish.setValue("Kingston");
        parish.setItems(parishlist);
    }
    @FXML
    private TextField intventnum;
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
    private TextField street;

    @FXML
    private TextField town;

    @FXML
    private ChoiceBox<String> parish;

    @FXML
    private ChoiceBox<String> paymenttype;

    @FXML
    private ChoiceBox<String> condition;

    @FXML
    void updaterem(ActionEvent event)
    {
        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.DATE, 0);
        SimpleDateFormat format1 = new SimpleDateFormat("dd MMM yyyy hh:mm:ss");
        String datemade = format1.format(cal.getTime());
        try {
            int updatenum=Integer.parseInt(intventnum.getText());
            String firstname = fname.getText();
            String lastname = lname.getText();
            int acode = Integer.parseInt(areacode.getText());
            int xchange = Integer.parseInt(exchange.getText());
            int pline = Integer.parseInt(line.getText());
            String selectedpayment = paymenttype.getValue();
            String selectremoval = condition.getValue();
            String rstreet = street.getText();
            String rtown = town.getText();
            String rparish = parish.getValue();
            Address add = new Address(rstreet, rtown, rparish);
            Telnum tel = new Telnum(acode, xchange, pline);
            Client client = new Client(firstname, lastname, selectedpayment, tel);
            String outcome =addrem.check(selectremoval);
            Removal removal = new Removal(selectremoval, outcome,add, client,datemade,updatenum);
            removal.updaterem(updatenum,removal);
            c.stafflogin(event);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception el) {
            el.printStackTrace();
        }
    }

    @FXML
    void verify(MouseEvent event) {

    }

}
