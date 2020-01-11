package controller;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import myclass.IDate;
import myclass.Intervention;
import myclass.Visit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VisitTable {

    @FXML
    private MenuBar menubar;

    Intervention intervent;
    Stage window;
    TableView<Visit>visitable;

    public void start(List<Visit> visitt) throws IOException {
        Visit visit=new Visit();
        Stage stage=new Stage();
        window=stage;
        window.setTitle("View Visits Record");


        ObservableList<Visit> visitlist=FXCollections.observableArrayList(visitt);

        TableColumn<Visit,String>reason=new TableColumn<>("Reason");
        reason.setMinWidth(200);
       reason.setCellValueFactory(new PropertyValueFactory<>("reason"));

        TableColumn<Visit,String>cname=new TableColumn<>("Clinicname");
        cname.setMinWidth(200);
        cname.setCellValueFactory(new PropertyValueFactory<>("clinicname"));


        TableColumn<Visit,String>dmade=new TableColumn<>("Datemade");
        dmade.setMinWidth(200);
        dmade.setCellValueFactory(new PropertyValueFactory<>("datemade"));


        /**needs to fix*/
        TableColumn<Visit,Number>idx=new TableColumn<>("Idx");
        idx.setMinWidth(200);
        idx.setCellValueFactory(new PropertyValueFactory<>("idx"));


        TableColumn<Visit,Number>day=new TableColumn<>("Day");
       day.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Visit, Number>, ObservableValue<Number>>()
        {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Visit, Number> data) {
                return data.getValue().getIntendate().dayProperty();

            }
        });
        TableColumn<Visit,Number>month=new TableColumn<>("Month");
        month.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Visit, Number>, ObservableValue<Number>>()
        {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Visit, Number> data) {
                return data.getValue().getIntendate().monthProperty();

            }
        });
        TableColumn<Visit,Number>year=new TableColumn<>("Year");
       year.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Visit, Number>, ObservableValue<Number>>()
        {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Visit, Number> data) {
                return data.getValue().getIntendate().yearProperty();

            }
        });

        TableColumn<Visit,String>time=new TableColumn<>("Time");
        time.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Visit, String>, ObservableValue<String>>()
        {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Visit, String> data) {
                return data.getValue().getIntendate().timeProperty();

            }
        });
        TableColumn<Visit,String>type=new TableColumn<>("Type");
        type.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Visit, String>, ObservableValue<String>>()
        {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Visit, String> data) {
                return data.getValue().getAnimal().typeProperty();

            }
        });
        TableColumn<Visit,String>breed=new TableColumn<>("Breed");
        breed.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Visit, String>, ObservableValue<String>>()
        {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Visit, String> data) {
                return data.getValue().getAnimal().breedProperty();

            }
        });
        TableColumn<Visit,String>gender=new TableColumn<>("Gender");
        gender.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Visit, String>, ObservableValue<String>>()
        {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Visit, String> data) {
                return data.getValue().getAnimal().genderProperty();

            }
        });
        TableColumn<Visit,Number>age=new TableColumn<>("Age");
        age.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Visit, Number>, ObservableValue<Number>>()
        {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Visit, Number> data) {
                return data.getValue().getAnimal().ageProperty();

            }
        });
        TableColumn<Visit,String>fname=new TableColumn<>("Fname");
        fname.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Visit, String>, ObservableValue<String>>()
        {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Visit, String> data) {
                return data.getValue().getClientinfo().fnameProperty();

            }
        });
        TableColumn<Visit,String>lname=new TableColumn<>("Lname");
        lname.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Visit, String>, ObservableValue<String>>()
        {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Visit, String> data) {
                return data.getValue().getClientinfo().lnameProperty();

            }
        });
        TableColumn<Visit,String>pay=new TableColumn<>("Payoption");
        pay.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Visit, String>, ObservableValue<String>>()
        {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Visit, String> data) {
                return data.getValue().getClientinfo().payoptionProperty();

            }
        });
        TableColumn<Visit,Number>acode=new TableColumn<>("Areacode");
        acode.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Visit, Number>, ObservableValue<Number>>()
        {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Visit, Number> data) {
                return data.getValue().getClientinfo().getClientel().areacodeProperty();

            }
        });
        TableColumn<Visit,Number>xchange=new TableColumn<>("Exchange");
        xchange.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Visit, Number>, ObservableValue<Number>>()
        {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Visit, Number> data) {
                return data.getValue().getClientinfo().getClientel().exchangeProperty();

            }
        });
        TableColumn<Visit,Number>line=new TableColumn<>("Line");
        line.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Visit, Number>, ObservableValue<Number>>()
        {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Visit, Number> data) {
                return data.getValue().getClientinfo().getClientel().lineProperty();
            }
        });
        visitable=new TableView<>();
        visitable.setItems(visitlist);

       visitable.getColumns().addAll(reason,cname,time,day,month,year,breed,type,gender,age,fname,lname,pay,
               acode,xchange,line,dmade,idx);

        VBox vbox=new VBox();
        vbox.getChildren().addAll(visitable);
        Scene scene=new Scene(vbox);
        window.setScene(scene);
        window.show();

    }
}