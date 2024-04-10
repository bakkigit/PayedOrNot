package gui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Person;

import javax.sound.sampled.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;


public class GuiListView1 extends Application {

    public GuiListView1() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    }

    @Override
    public void start(Stage stage) throws LineUnavailableException, IOException {
        stage.setTitle("ListView Demo1");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane, 650, 320);
        stage.setScene(scene);
        stage.show();
    }

    // -------------------------------------------------------------------------


    private ArrayList<Person> liste = new ArrayList<>();

    private ListView<Person> lvw = new ListView<>();
    private Button btnopret = new Button("Opret");
    private Button btnSlet = new Button("Slet");
    private Button btnSøg = new Button("Søg");
    private TextField txtNavn = new TextField();
    private TextField txtNr = new TextField();
    private TextField txtSøg = new TextField();
    private CheckBox ja = new CheckBox("Ja");
    private CheckBox nej = new CheckBox("Nej");

    static final String driver = "com.mysql.cj.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost:3306/masjid";
    static final String username = "root";
    static final String password = "NoNeed4That";


    private void initContent(GridPane pane) {
        // show or hide grid lines
        pane.setGridLinesVisible(false);
        // set padding of the pane
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);

        Label usynligt = new Label();
        pane.add(usynligt, 5, 5);

        Label lblOversigt = new Label("Oversigt");
        pane.add(lblOversigt, 1, 2);
        pane.add(lvw, 1, 3, 1, 5);
        lvw.setPrefWidth(450);
        lvw.setPrefHeight(250);

        pane.add(btnSøg, 3, 2);
        GridPane.setHalignment(btnSøg, HPos.LEFT);
        btnSøg.setOnAction(event -> {
            try {
                this.btnSearch();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        pane.add(txtSøg, 4, 2);
        GridPane.setHalignment(txtSøg, HPos.LEFT);
        txtSøg.setPrefWidth(3);


        btnopret.setPrefWidth(100);
        pane.add(btnopret, 3, 7);
        GridPane.setValignment(btnopret, VPos.BOTTOM);
        btnopret.setOnAction(event -> this.btnCreate());

        pane.add(btnSlet, 4, 7);
        btnSlet.setPrefWidth(70);
        GridPane.setValignment(btnSlet, VPos.BOTTOM);
        btnSlet.setOnAction(event -> this.btnsletAktion());

        Label lblNavn = new Label("Navn: ");
        pane.add(lblNavn, 3, 3);
        lblNavn.setPrefWidth(100);

        pane.add(txtNavn, 4, 3);
        txtNavn.setPrefWidth(150);


        Label lblNr = new Label("Nummer: ");
        pane.add(lblNr, 3, 5);
        lblNr.setPrefWidth(100);

        pane.add(txtNr, 4, 5);
        txtNr.setPrefWidth(150);

        Label lblBetalt = new Label("Betalt?: ");
        pane.add(lblBetalt, 3, 7);
        GridPane.setValignment(lblBetalt, VPos.TOP);


        pane.add(ja, 4, 7);
        GridPane.setValignment(ja, VPos.TOP);

        pane.add(nej, 4, 7);
        GridPane.setHalignment(nej, HPos.CENTER);
        GridPane.setValignment(nej, VPos.TOP);


    }


    public static Connection getConnection() throws Exception {
        try {
            Class.forName(driver);
            String url1 = "jdbc:mysql://localhost:3306/masjid";
            String username = "root";
            String password = "NoNeed4That";
            Connection conn = DriverManager.getConnection(url1, username, password);
            return conn;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;


    }


    public void btnCreate() {

        if (ja.isSelected()) {
            String navn = txtNavn.getText().trim();
            String nr = txtNr.getText().trim();
            String betalt = ja.getText();
            Person person = new Person(navn, nr, betalt);
            liste.add(person);
            lvw.getItems().setAll(liste);
            ja.setSelected(false);
            txtNr.clear();
            txtNavn.clear();
            Connection con = null;
            try {
                con = getConnection();
                PreparedStatement posted = con.prepareStatement("INSERT INTO bruger (navn,nr,betalt) VALUES ('" + person.getName() + "','" + person.getNr() + "','" + person.getBetalt() + "')");
                posted.executeUpdate();

            } catch (Exception e) {
                System.out.println("Det er under ja " + e);
            }

        } else if (nej.isSelected()) {
            String navn = txtNavn.getText().trim();
            String nr = txtNr.getText().trim();
            String betalt = nej.getText();
            Person person = new Person(navn, nr, betalt);
            liste.add(person);
            lvw.getItems().setAll(liste);
            nej.setSelected(false);
            txtNr.clear();
            txtNavn.clear();
            Connection con = null;
            try {
                con = getConnection();
                PreparedStatement posted = con.prepareStatement("INSERT INTO bruger (navn,nr,betalt) VALUES ('" + person.getName() + "','" + person.getNr() + "','" + person.getBetalt() + "')");
                posted.executeUpdate();

            } catch (Exception e) {
                System.out.println("Det er under nej " + e);
            }


        }

    }

    public void btnsletAktion() {
        Person selected = lvw.getSelectionModel().getSelectedItem();
        liste.remove(selected);
        lvw.getItems().removeAll();
        lvw.getItems().setAll(liste);
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();

            String sql = "DELETE FROM bruger WHERE navn ='" + selected.getName() + "'";
            stmt.executeUpdate(sql);
            System.out.println("Brugeren er blevet slettet!");
        } catch (Exception e) {
            System.out.println("Dette er under SLET " + e);
        }
    }

    public void btnSearch() throws ClassNotFoundException {
        for (Person p : liste) {
            if (txtSøg.getText().trim().equalsIgnoreCase(p.getName().trim())) {
                lvw.getItems().setAll(p);
            }
        }
            if (txtSøg.getText().isEmpty()) {
                lvw.getItems().removeAll();
                liste.clear();
                String sql = "SELECT navn, nr, betalt " +
                        "FROM bruger";
                try (Connection conn = getConnection();
                     Statement stmt  = conn.createStatement();
                     ResultSet rs    = stmt.executeQuery(sql)) {

                    // loop through the result set
                    while (rs.next()) {
                            String navn = rs.getString("navn");
                            String nr = rs.getString("nr");
                            String betalt = rs.getString("betalt");
                            Person person = new Person(navn,nr,betalt);
                            liste.add(person);
                        }

                } catch (Exception e) {
                    System.out.println(e);
                }
                lvw.getItems().removeAll();
                lvw.getItems().setAll(liste);
                System.out.println(liste.size());


            }
    }
}




//    public void btnSearch(){
//        for (Person list : liste){
//            ArrayList<Person>ny = new ArrayList<>();
//            if (list.getName().equals(txtSøg.getText().trim())){
////                lvw.getItems().removeAll(liste);
//                ny.add(list);
//                lvw.getItems().setAll(ny);
//            }else if (txtSøg.getText().trim().isEmpty()){
//                lvw.getItems().removeAll();
//                Statement stmt = null;
//                Connection conn = null;
//                try {
//                    Class.forName(driver);
//                    conn = getConnection();
//
//                    Statement st = conn.createStatement();
//                    ResultSet rs = st.executeQuery("SELECT * FROM bruger");
//                    while (rs.next())
//                    {
//                        System.out.println("Navn "+rs.getString(2)+" Nr "+rs.getString(3)+" Betalt "+rs.getString(4));
//                        String navn = "Navn "+rs.getString(2);
//                        String nr = " Nr "+rs.getString(3);
//                        String betalt = " Betalt "+rs.getString(4);
//                        Person person = new Person(navn,nr,betalt);
//                        liste.add(person);
//
//                    }
//                    lvw.getItems().setAll(liste);
//                }catch (Exception e){
//                    System.out.println("Fejlen i Retrive"+e);
//                }
//            }
//
//            }
//        }


