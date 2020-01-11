package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import myclass.Intervention;
import myclass.Visit;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    @FXML
    void stafflogin(ActionEvent event)throws IOException
    {
        Parent rootparent = FXMLLoader.load(getClass().getResource("../view/mainmenu.fxml"));
        Scene rootscene=new Scene(rootparent);
        Stage appstage=(Stage)((Node)event.getSource()).getScene().getWindow();
        appstage.setResizable(false);
        appstage.setScene(rootscene);
        appstage.show();
    }
    @FXML
     void adminlogin(ActionEvent event)throws IOException { }

    public void notfound()
    {
        Alert a1=new Alert(Alert.AlertType.ERROR);
        a1.setTitle("Item not found");
        a1.setContentText("We could not locate any records for that item in the file\nchose another input or add a record of that type");
        a1.setHeaderText(null);
        a1.showAndWait();
    }
    public void invalidinput()
    {
        Alert a1=new Alert(Alert.AlertType.ERROR);
        a1.setTitle("Input Validation Error");
        a1.setContentText("Please check your fields again,one or more of the following errors have occured:\n" +
                "1.You Entered Numbers in a text Field\n2.You entered text in a number field \n3.All the fields are not filled out");
        a1.setHeaderText(null);
        a1.showAndWait();
    }
    public void success()
    {
        Alert a1=new Alert(Alert.AlertType.CONFIRMATION);
        a1.setTitle("Update Success");
        a1.setContentText("Congrats the request was submitted successfullly");
        a1.setHeaderText(null);
        a1.showAndWait();
    }
    public void outofrange()
    {
        Alert a1=new Alert(Alert.AlertType.WARNING);
        a1.setTitle("Number out of range");
        a1.setContentText("The value that you entered is out of range for the search result");
        a1.setHeaderText(null);
        a1.showAndWait();
    }
    public void eof()
    {
        Alert a1=new Alert(Alert.AlertType.ERROR);
        a1.setTitle("File not Found");
        a1.setContentText("We could not locate the record in the file or the file");
        a1.setHeaderText(null);
        a1.showAndWait();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //todo
    }
}
