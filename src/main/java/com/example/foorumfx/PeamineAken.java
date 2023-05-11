package com.example.foorumfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class PeamineAken extends Application {

    private String nimi;
    private List<Arutelud> Arutelude_nimekiri;
    public PeamineAken(String nimi) {
        this.nimi = nimi; //kasutaja nimi
        this.Arutelude_nimekiri = new ArrayList<Arutelud>();
        File[] failid = new File("arutelud").listFiles();
        for (File fail : failid) {
            if (fail.isFile()) {
                try {
                    Arutelude_nimekiri.add(new Arutelud(fail));
                } catch (FileNotFoundException e) {
                    VeaTeade veaTeade = new VeaTeade("Arutelu faili ei leitud!");
                    veaTeade.start(new Stage());
                } catch (IOException e) {
                    VeaTeade veaTeade = new VeaTeade("IOException");
                    veaTeade.start(new Stage());
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

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
                    if (teema.getaruteluNimi().equals(aruteluValik.getText())){
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
