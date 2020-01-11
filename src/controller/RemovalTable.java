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
import myclass.Removal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RemovalTable {

    @FXML
    private MenuBar menubar;

    Intervention intervent;
    Stage window;
    TableView<Removal> removaltable;

    public void start(List<Removal> removal) throws IOException
    {
        Removal rem=new Removal();
        Stage stage = new Stage();
        window = stage;
        window.setTitle("View Removal Records");


        ObservableList<Removal> removalist = FXCollections.observableArrayList(removal);

        TableColumn<Removal, Number>num= new TableColumn<>("Idx");
        num.setMinWidth(200);
        num.setCellValueFactory(new PropertyValueFactory<>("idx"));

        TableColumn<Removal, String>condition = new TableColumn<>("Animalcondition");
        condition.setMinWidth(200);
        condition.setCellValueFactory(new PropertyValueFactory<>("animalcondition"));


        TableColumn<Removal, String> outcome = new TableColumn<>("Outcome");
        outcome.setMinWidth(200);
        outcome.setCellValueFactory(new PropertyValueFactory<>("outcome"));

        TableColumn<Removal, String> dmade = new TableColumn<>("Datemade");
        dmade.setMinWidth(200);
        dmade.setCellValueFactory(new PropertyValueFactory<>("datemade"));



        TableColumn<Removal, String> fname = new TableColumn<>("Fname");
        fname.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Removal, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Removal, String> data) {
                return data.getValue().getClientinfo().fnameProperty();

            }
        });
        TableColumn<Removal, String> lname = new TableColumn<>("Lname");
        lname.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Removal, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Removal, String> data) {
                return data.getValue().getClientinfo().lnameProperty();

            }
        });
        TableColumn<Removal, String> pay = new TableColumn<>("Payoption");
        pay.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Removal, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Removal, String> data) {
                return data.getValue().getClientinfo().payoptionProperty();

            }
        });
        TableColumn<Removal, Number> acode = new TableColumn<>("Areacode");
        acode.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Removal, Number>, ObservableValue<Number>>()
        {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Removal, Number> data) {
                return data.getValue().getClientinfo().getClientel().areacodeProperty();

            }
        });
        TableColumn<Removal, Number> xchange = new TableColumn<>("Exchange");
        xchange.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Removal, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Removal, Number> data) {
                return data.getValue().getClientinfo().getClientel().exchangeProperty();

            }
        });

        TableColumn<Removal, Number> line = new TableColumn<>("Line");
        line.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Removal, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Removal, Number> data) {
                return data.getValue().getClientinfo().getClientel().lineProperty();
            }
        });
        TableColumn<Removal, String> street = new TableColumn<>("Street");
        street.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Removal,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Removal, String> data) {
                return data.getValue().getAddress().streetProperty();

            }
        });
        TableColumn<Removal, String> town = new TableColumn<>("Town");
        town.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Removal, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Removal, String> data) {
                return data.getValue().getAddress().townProperty();

            }
        });
        TableColumn<Removal, String>parish = new TableColumn<>("Parish");
        parish.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Removal, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Removal, String> data) {
                return data.getValue().getAddress().parishProperty();
            }
        });


        removaltable = new TableView<>();
        removaltable.setItems(removalist);

        removaltable.getColumns().addAll(num,dmade,condition,outcome,fname,lname,pay,acode,xchange,line,street,town,parish);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(removaltable);
        Scene scene = new Scene(vbox);
        window.setScene(scene);
        window.show();

    }
}