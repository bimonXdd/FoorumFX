package com.example.foorumfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AruteluSisu extends Application {
    private Arutelud arutelu;
    private String nimi;

    public AruteluSisu(Arutelud arutelu, String nimi) {
        this.arutelu = arutelu;
        this.nimi = nimi;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        BorderPane layout = new BorderPane();
        Group grupp = new Group();
        Group kommentaaridTekst = new Group();

        TextField uusKommentaar = new TextField();
        uusKommentaar.setLayoutX(150);
        uusKommentaar.setPrefSize(700, 40);
        Button tagasi = new Button("Tagasi");
        tagasi.setLayoutX(30);


//-------------------------Enter nupp-----------------------------------
        Button enter = new Button("Enter");
        enter.setLayoutX(800);
        enter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                arutelu.getArutelu_kommentaarid().add(new Kommentaar(nimi, uusKommentaar.getText())); //lisab uue kommentaari
                VäljastaKommentaar(kommentaaridTekst);

            }
        });
 //-----------------------------------------------------------------------


//-------------------------------tagasi nupp-----------------------------------------
        tagasi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PeamineAken uus = new PeamineAken(nimi);
                uus.start(stage);
            }
        });
//------------------------------------------------------------------------------------



//---------------------------Ekraani yleval olev info---------------------------
        Text pealkiri = new Text(arutelu.getArutelu_nimi());
        pealkiri.setId("tekst");

        Text algataja = new Text("Algataja: " + arutelu.getAlgataja_nimi());
        algataja.setId("tekst");
        algataja.setLayoutX(140);

        Text loomiseAlgus = new Text(arutelu.getArutelu_loomise_algus().toString());
        loomiseAlgus.setId("tekst");
        loomiseAlgus.setLayoutX(340);
//------------------------------------------------------------------------------


        Group valikud = new Group();
        valikud.getChildren().addAll(tagasi, uusKommentaar, enter);

        VäljastaKommentaar(kommentaaridTekst);//valjastab algul koik olemas olevad kommentaarid

        grupp.getChildren().addAll(pealkiri, algataja, loomiseAlgus);
        layout.setTop(grupp);
        layout.setLeft(kommentaaridTekst);
        layout.setBottom(valikud);


        Scene scene = new Scene(layout, 900, 540);
        scene.getStylesheets().add("kujundus.css");// see lihtsalt css kujundus, mis asub resources failis ja teeb lahedaks asju
        stage.setTitle("Foorum");

        stage.isResizable();
        stage.setScene(scene);

        stage.show();

    }

    private Group VäljastaKommentaar(Group kommentaaridTekst) {
        StringBuilder info = new StringBuilder();
        List<Kommentaar> kommentaarid = new ArrayList<>(arutelu.getArutelu_kommentaarid());
        for (Kommentaar x : kommentaarid) {
            info.append("\n" + x.getKommentaari_loomise_aeg() + " " + x.getKommentaari_autor() + ": \t " + x.getKommentaari_sisu() + "\n");
        }
        Text kommentaar = new Text(info.toString());
        kommentaar.setId("kommentaar");
        kommentaaridTekst.getChildren().add(kommentaar);
        return kommentaaridTekst;
    } // see peaks valjastama koik kommentaarid
}
