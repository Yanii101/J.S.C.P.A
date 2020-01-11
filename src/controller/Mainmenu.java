package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import myclass.Removal;
import myclass.Visit;
import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class Mainmenu
{
    @FXML
    private MenuBar menubar;

    @FXML
    void addappointment(ActionEvent event)throws Exception
    {
            Parent rootparent =FXMLLoader.load(getClass().getResource("../view/makevisit.fxml"));
            Scene rootscene=new Scene(rootparent);
            Stage appstage=(Stage)menubar.getScene().getWindow();
            appstage.setResizable(false);
            appstage.setScene(rootscene);
            appstage.show();
    }

    @FXML
    void addremoval(ActionEvent event) throws IOException
    {
        Parent rootparent =FXMLLoader.load(getClass().getResource("../view/addremoval.fxml"));
        Scene rootscene=new Scene(rootparent);
        Stage appstage=(Stage)menubar.getScene().getWindow();
        appstage.setResizable(false);
        appstage.setScene(rootscene);
        appstage.show();

    }
    @FXML
    void animalreport(ActionEvent event) throws Exception {
        try {
            String[] animal = new String[6];
            animal[0] = "Dog";
            animal[1] = "Cat";
            animal[2] = "Bird";
            animal[3] = "Horse";
            animal[4] = "Horse";
            animal[5] = "Cow";
            Object selectanimal = JOptionPane.showInputDialog(null,
                    "Chose the animal report that you wish you see", "Animal Name", JOptionPane.QUESTION_MESSAGE, null, animal,
                    "Dog");
            String a = selectanimal.toString();
            System.out.println("Selected option" + selectanimal);
            Visit visit = new Visit();

            List<Visit> animalrep = visit.animaltypereport(a);
            VisitTable v = new VisitTable();
            v.start(animalrep);
        }catch (NumberFormatException e) {
            System.out.println("User Exited alert box");
        }catch (NullPointerException e)
        {
            System.out.println("User exits");
        }
    }

    @FXML
    void clinicdaterep(ActionEvent event) throws IOException
    {
        Parent rootparent = FXMLLoader.load(getClass().getResource("../view/datesearch.fxml"));
        Scene rootscene = new Scene(rootparent);
        Stage appstage = (Stage) menubar.getScene().getWindow();
        appstage.setResizable(false);
        appstage.setScene(rootscene);
        appstage.show();

    }

    @FXML
    void cliniclocationrep(ActionEvent event) throws Exception {
        try
        {
            String[] cname = new String[2];
            cname[0] = "Winchester Road";
            cname[1] = "Caymanas Park";
            Object selectcname = JOptionPane.showInputDialog(null,
                    "Choice the clinic name to view report", "Clinic Name", JOptionPane.QUESTION_MESSAGE, null, cname,
                    "Winchester Road");
            System.out.println("Selected option" + selectcname);
            String c = selectcname.toString();
            System.out.println("" + c);

            Visit visit = new Visit();

            List<Visit> location = visit.clinicloationrep(c);
            VisitTable v = new VisitTable();
            v.start(location);
        }catch (NumberFormatException e) {
            System.out.println("User Exited alert box");
        }
    }

    @FXML
    void deleteapp(ActionEvent event) throws IOException {
        try
        {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Delete Appointment");
            dialog.setHeaderText("Delete Exisiting Appointment from database");
            String result = JOptionPane.showInputDialog("Please enter the intervention you would like to delete");
            int num = Integer.parseInt(result);
            Visit visit = new Visit();
            visit.deleteitervent(num, visit);
        }catch (NumberFormatException e) {
            System.out.println("User Exited alert box");
        }
    }

    @FXML
    void deleteremoval(ActionEvent event) throws IOException {
        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Delete Removal");
            dialog.setHeaderText("Delete Exisiting removal from database");
            //dialog.setContentText("Please enter the intervention you would like to delete");
            String result = JOptionPane.showInputDialog("Please enter the intervention you would like to delete");
            // Optional<String>result=dialog.showAndWait();
            int num = Integer.parseInt(result);
            Removal rem = new Removal();
            rem.delremoval(num);
        }catch (NumberFormatException e) {
            System.out.println("User Exited alert box");
        }

    }
    @FXML
    void logout(MouseEvent event) throws IOException
    {
        Parent rootparent = FXMLLoader.load(getClass().getResource("../view/controller.fxml"));
        Scene rootscene=new Scene(rootparent);
        Stage appstage=(Stage)((Node)event.getSource()).getScene().getWindow();
        appstage.setScene(rootscene);
        appstage.show();
    }

    @FXML
    void outcomereport(ActionEvent event) throws IOException
    {
        try {
            String[] outcome = new String[2];
            outcome[0] = "Euthanized";
            outcome[1] = "Adopted";
            Object selectout = JOptionPane.showInputDialog(null,
                    "Chose the outcome to view the report for", "Outcome Type", JOptionPane.QUESTION_MESSAGE, null, outcome,
                    "Winchester Road");
            String out = selectout.toString();
            Removal rem = new Removal();
            System.out.println("Selected option " + selectout);

            List<Removal> remlist = rem.outcomerep(out);
            RemovalTable r = new RemovalTable();
            r.start(remlist);
        }catch (NumberFormatException e) {
            System.out.println("User Exited alert box");
        }
    }

    @FXML
    void removalocationrep(ActionEvent event) throws IOException
    {
        try {
            String[] location = new String[14];
            location[0] = "Kingston";
            location[1] = "St. Andrew";
            location[2] = "St. Catherine";
            location[3] = "Clarendon";
            location[4] = "Manchester";
            location[5] = "St. Elizabeth";
            location[6] = "Westmoreland";
            location[7] = "Hanover";
            location[8] = "St. James";
            location[9] = "Trelawny";
            location[10] = "St. Ann";
            location[11] = "St. Mary";
            location[12] = "Portland";
            location[13] = "St. Thomas";
            Object selectlocation = JOptionPane.showInputDialog(null,
                    "Chose the outcome to view the report for", "Outcome Type", JOptionPane.QUESTION_MESSAGE, null, location,
                    "Kingston");
            System.out.println("Selected option " + selectlocation);
            String rloc = selectlocation.toString();
            Removal rem = new Removal();

            List<Removal> remlist = rem.location(rloc);
            RemovalTable r = new RemovalTable();
            r.start(remlist);
        } catch (NumberFormatException e) {
            System.out.println("User Exited alert box");
        }
    }
    @FXML
    void requestdatereport(ActionEvent event)
    {

    }

    @FXML
    void updateapp(ActionEvent event) throws IOException
    {
        Parent rootparent =FXMLLoader.load(getClass().getResource("../view/updatevisit.fxml"));
        Scene rootscene=new Scene(rootparent);
        Stage appstage=(Stage)menubar.getScene().getWindow();
        appstage.setResizable(false);
        appstage.setScene(rootscene);
        appstage.show();
    }

    @FXML
    void updaterem(ActionEvent event) throws Exception
    {
        Parent rootparent =FXMLLoader.load(getClass().getResource("../view/updateremoval.fxml"));
        Scene rootscene=new Scene(rootparent);
        Stage appstage=(Stage)menubar.getScene().getWindow();
        appstage.setResizable(false);
        appstage.setScene(rootscene);
        appstage.show();
    }

    @FXML
    void viewallapp(ActionEvent event)throws Exception
    {
        Visit visit=new Visit();

        List<Visit> allvisit = visit.readvisit();
        VisitTable v=new VisitTable();
        v.start(allvisit);
    }

    @FXML
    void viewallremoval(ActionEvent event) throws Exception
    {
        Removal rem=new Removal();

        List<Removal>remlist=rem.viewremoval();
        RemovalTable r=new RemovalTable();
        r.start(remlist);
    }

    @FXML
    void viewsingleapp(ActionEvent event) throws IOException
    {
        TextInputDialog dialog=new TextInputDialog();
        dialog.setTitle("View Single Appointment");
        dialog.setHeaderText("View Exisiting Appointment from database");
        //dialog.setContentText("Please enter the intervention you would like to delete");
        String result= JOptionPane.showInputDialog("Please enter the intervention you would like to view");
        // Optional<String>result=dialog.showAndWait();
        int num=Integer.parseInt(result);
        Visit visit=new Visit();
       try{
           List<Visit> slist = visit.viewsinglerecord(num);
           VisitTable v=new VisitTable();
           v.start(slist);
       }catch (NumberFormatException e)
       {
           System.out.println("User Exited alert box");
       }

    }

    @FXML
    void viewsingleremoval(ActionEvent event) throws IOException {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("View Single Appointment");
        dialog.setHeaderText("View Exisiting Appointment from database");
        //dialog.setContentText("Please enter the intervention you would like to delete");
        String result = JOptionPane.showInputDialog("Please enter the intervention you would like to view");
        // Optional<String>result=dialog.showAndWait();
        int num = Integer.parseInt(result);
        Removal rem = new Removal();
        try {
            List<Removal> oneremoval = rem.singleremoval(num);
            RemovalTable r = new RemovalTable();
            r.start(oneremoval);

            rem.singleremoval(num);
        } catch (NumberFormatException e) {
            System.out.println("User Exited alert box");
        }
    }
}

