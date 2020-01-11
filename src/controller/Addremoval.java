package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import myclass.Address;
import myclass.Client;
import myclass.Removal;
import myclass.Telnum;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Addremoval implements Initializable
{
    Controller c=new Controller();
    Mainmenu m=new Mainmenu();

    @FXML
    private AnchorPane paytype;

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
    private ChoiceBox<String> paymenttype;

    @FXML
    private TextField street;

    @FXML
    private TextField town;

    @FXML
    private ChoiceBox<String> parish;


    @FXML
    private ChoiceBox<String> condition;

    ObservableList<String> removalist = FXCollections.
            observableArrayList("Reproduce Indiscrimately", "Animal left to forage for itself",
                    "Overpopulation", "Aggressive  Animal", "Illness","Other");
    ObservableList<String> pList = FXCollections.
            observableArrayList("Full Payment", "Partial Payment", "None");
    ObservableList<String>parishlist=FXCollections.
            observableArrayList("Kingston", "St. Andrew", "St. Catherine", "Clarendon", "Manchester", "St. Elizabeth","Westmoreland", "Hanover", "St. James", "Trelawny", "St. Ann",
                    "St. Mary", "Portland","St. Thomas");

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        condition.setValue("Reproduce Indiscrimately");
        condition.setItems(removalist);
        paymenttype.setValue("Full Payment");
        paymenttype.setItems(pList);
        parish.setValue("Kingston");
        parish.setItems(parishlist);
    }

    @FXML
    void logout(MouseEvent event) throws IOException {
       m.logout(event);
    }

    @FXML
    String verify(MouseEvent event) {
        return null;
    }

    @FXML
    void addremoval(ActionEvent event) throws FileNotFoundException
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        SimpleDateFormat format1 = new SimpleDateFormat("dd MMM yyyy hh:mm:ss");
        String datemade = format1.format(cal.getTime());
        Scanner scanner = new Scanner(new File("idx.txt"));
        int intnum = scanner.nextInt();
            try {
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
                String outcome = check(selectremoval);
                Removal removal = new Removal(selectremoval, outcome, add, client,datemade,intnum);
                removal.addremoval(removal);
                scanner = new Scanner(new File("idx.txt"));
                int num = scanner.nextInt();
                Alert b = new Alert(Alert.AlertType.CONFIRMATION);
                b.setTitle("Successfully added removal");
                b.setHeaderText("You have just made a  new remoal \n Your" +
                        "Intervention number is " + num);
                b.setContentText("chooose an option");
                b.showAndWait();
                c.stafflogin(event);
            } catch (NumberFormatException e)
            {
                c.invalidinput();
            }
            catch (FileNotFoundException e) {
                c.eof();
            } catch (Exception el)
            {
                el.printStackTrace();
            }
    }

    String check(String c)
    {
        String out = "";

        if (c.equals("Reproduce Indiscrimately")) {
            out = "Euthanized";
        }
        if (c.equals("Animal left to forage for itself")) {
            out = "Euthanized";
        }
        if (c.equals("Illness")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Illness severity");
            alert.setHeaderText("ILLNESS SEVERITY");
            alert.setContentText("Can the animal illness be easily cured?");
            ButtonType yesbutton = new ButtonType("Yes");
            ButtonType nobutton = new ButtonType("No");
            alert.getButtonTypes().setAll(yesbutton, nobutton);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == yesbutton) {
                out = "Adopted";
            } else if (result.get() == nobutton) {
                out = "Euthanized";
            }
        }
        if (c.equals("Aggressive  Animal"))
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Aggressive  Animal");
            alert.setHeaderText("Aggressive  Animal Query");
            alert.setContentText("Can the animal aggressiveness be fixed?");
            ButtonType yesbutton = new ButtonType("Yes");
            ButtonType nobutton = new ButtonType("No");
            alert.getButtonTypes().setAll(yesbutton, nobutton);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == yesbutton)
            {
                out="Adopted";
            } else if (result.get() == nobutton)
            {
                out = "Euthanized";
            }
        }
        if (c.equals("Other"))
        {
            out = "Adopted";
        }
        return out;
    }
}

