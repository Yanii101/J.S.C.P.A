package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import myclass.Visit;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Datepicker implements Initializable
{
    @FXML
    private ChoiceBox<String> day;

    @FXML
    private ChoiceBox<String> year;

    @FXML
    private ChoiceBox<String> month;

    ObservableList<String> dlist= FXCollections.
            observableArrayList("1","2","4","5","7","8","9","10","11","12","13","14","15",
                    "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31");

    ObservableList<String>mlist= FXCollections.
            observableArrayList("1","2","4","5","7","8","9","10","11","12");

    ObservableList<String> ylist= FXCollections.
            observableArrayList("2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027");

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        day.setValue("Date");
        day.setItems(dlist);
        month.setValue("Month");
        month.setItems(mlist);
        year.setValue("Year");
        year.setItems(ylist);
    }
    @FXML
    void search(ActionEvent event) {
        Controller c=new Controller();
        Visit v=new Visit();
        VisitTable rep=new VisitTable();
        try
        {
            int mth=Integer.parseInt(month.getValue());
            int dy=Integer.parseInt(day.getValue());
            int yr=Integer.parseInt(year.getValue());

            List<Visit> daterep = v.vdatereport(mth,dy,yr);
            rep.start(daterep);
            c.stafflogin(event);

        } catch (FileNotFoundException e) {
            c.notfound();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
