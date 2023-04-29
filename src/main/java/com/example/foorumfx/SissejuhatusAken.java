package com.example.foorumfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
/*
* Tahtis
*
* Kui teksti ei teki, siis pea meeles, et taust on must, ehk lisa 'mingi tekst'.setId("tekst") nt rida 54 sissejuhatus.setId("tekst");
* See setId on vajalik, et tekst oleks roheline ja selle "tekst" id jargi saab kujundus.css fail aru, mida roheliseks teha.
*
*42-54 on näide, kuidas uue akna loomisega on.
*
*
* setLayout asjad on koik lihtsalt objektide positsioneerimiseks.
*
* */


public class SissejuhatusAken extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //------------------------Nime input--------------------------------------------------
        TextField nimi = new TextField();
        nimi.setPrefSize(230,20);
        //------------------------------------------------------------------------------------

        Button login = new Button("Kasuta seda nime");
        login.setLayoutY(60);                                            //See koik on suht login nupp ja selle kasutus ja positsioneerimine.
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String sisestatudNimi;
                if (nimi.getText().isEmpty()) {sisestatudNimi="NoName";}
                else sisestatudNimi = nimi.getText();
                PeamineAken uus = new PeamineAken(sisestatudNimi);// loob uue akna
              uus.start(stage);

            }
        });


        //------------------------Sissejuhatava teksti ala------------------------------------

        Text sissejuhatus = new Text("*****  Tere, \n*****  Olete jõudnud mingisse ägedasse foorumi,\n*****  kus saate " +
                "uusi arutelusi luua, lisada\n*****  olemasolevatele aruteludele oma kommentaare " +
                "ning näha teiste kommentaare. \n \nPalun valike nimi, millega sooviksite kommenteerida:");
        sissejuhatus.setId("tekst");
        sissejuhatus.setLayoutY(-140);
        //------------------------------------------------------------------------------------


        Group grupp = new Group();
        grupp.getChildren().addAll(login,nimi,sissejuhatus);

        StackPane layout =new StackPane();
        layout.getChildren().add(grupp);


        Scene scene = new Scene(layout, 900, 540);
        scene.getStylesheets().add("kujundus.css");// see lihtsalt css kujundus, mis asub resources failis ja teeb lahedaks asju
        stage.setTitle("Foorum");

        stage.isResizable();
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();

    }
}