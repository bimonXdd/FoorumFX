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
import java.util.ArrayList;


public class PeamineAken extends Application {
    private String nimi;
    public PeamineAken(String nimi){
        this.nimi = nimi; // kasutaja nimi
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        ArrayList<Arutelud> Arutelude_nimekiri = new ArrayList<Arutelud>();
        Arutelud Poliitika = new Arutelud("Poliitika", "Simon");        // see on suvaline testimiseks lisatud arutelu
        Arutelude_nimekiri.add(Poliitika);
        Poliitika.getArutelu_kommentaarid().add(0,new Kommentaar("Simpson ","uououou mis siisn toimub"));

//--------------------Tervitav tekst----------------------------------
        StackPane layout =new StackPane();
        Text tere = new Text("Tere, "+nimi);
        tere.setId("tekst");
        tere.setLayoutY(-400);
        tere.setLayoutX(-225);

//-----------------------------Lisa uus arutelu nupp--------------------
        Button Lisa = new Button("Lisa uus arutelu");// pole veel midagi lisanud
//------------------------------------------------------------------------------------


//------------Loe nupp, millega saab arutelude lugemis aknasse ( ehk klass AruteluSisu)---------
        Button Loe = new Button("Loe: ");
        TextField aruteluValik = new TextField();
        aruteluValik.setPrefSize(230,20);
        aruteluValik.setLayoutX(60);

        Loe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (Arutelud teema : Arutelude_nimekiri) {
                    if (teema.getArutelu_nimi().equals(aruteluValik.getText())){
                        AruteluSisu uus = new AruteluSisu(teema,nimi);
                        uus.start(stage); // teeb uue akna n-o

                    }else{

                    }
                }
            }
        });
//---------------------------------------------------------------------------------------------

        Lisa.setLayoutX(-200);

        Group grupp = new Group();

//-----------------Valjastab koik arutelud-----------------------------------

        StringBuilder teemad = new StringBuilder();
        for (Arutelud teema : Arutelude_nimekiri) {
            teemad.append(teema+"\n");
        }
        Text aruteludenimekirjaText = new Text(teemad.toString());
        aruteludenimekirjaText.setId("nimekiri");
        aruteludenimekirjaText.setLayoutY(-300);
        aruteludenimekirjaText.setLayoutX(-225);

//----------------------------------------------------------------------------


        grupp.getChildren().addAll(aruteludenimekirjaText,tere,Loe,Lisa,aruteluValik);
        layout.getChildren().add(grupp);
        Scene scene = new Scene(layout, 900, 540);
        scene.getStylesheets().add("kujundus.css");// see lihtsalt css kujundus, mis asub resources failis ja teeb lahedaks asju
        stage.setTitle("Foorum");

        stage.isResizable();
        stage.setScene(scene);

        stage.show();

    }

}
