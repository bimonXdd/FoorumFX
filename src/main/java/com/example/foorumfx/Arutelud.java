package com.example.foorumfx;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Arutelud implements Comparable<Arutelud>{
    private String Arutelu_nimi;
    private String Algataja_nimi; // Mõtlesin, et akki on hea, kui arutelul on huvitavat infot natuke.
    private LocalDateTime Arutelu_loomise_algus;
    private List<Kommentaar> Arutelu_kommentaarid;

    private int Vaatamiste_arv;
    public Arutelud(String Arutelu_nimi, String Algataja_nimi){
        this.Arutelu_nimi=Arutelu_nimi;
        this.Algataja_nimi=Algataja_nimi;
        this.Arutelu_loomise_algus = LocalDateTime.now();
        this.Arutelu_kommentaarid = new ArrayList<Kommentaar>();
        this.Vaatamiste_arv = (int) (Math.random() * 1000);
    }

    public String getAlgataja_nimi() {
        return Algataja_nimi;
    }

    public String getArutelu_nimi() {
        return Arutelu_nimi;
    }

    public LocalDateTime getArutelu_loomise_algus() {
        return Arutelu_loomise_algus;
    }

    public List<Kommentaar> getArutelu_kommentaarid() { return Arutelu_kommentaarid; }

    @Override
    public String toString() {

        return "Arutelu teema: "+Arutelu_nimi+
                "; Arutelu algataja: "+ Algataja_nimi+
                "; Arutelu loodi: "+ Arutelu_loomise_algus;
    }


    //meetod kommentaari lisamiseks
    public void lisaKommentaar(Kommentaar kommentaar) throws IOException {
        String autor = AruteluSisu.getNimi();
        String sisu =kommentaar.getKommentaari_sisu();
        try{
        lisaKommentaarFaili("kommentaarid.dat", new Kommentaar(autor, sisu).toString());}
        catch (Exception e) {
            DataOutputStream dataOut = new DataOutputStream(new FileOutputStream("kommentaarid.dat"));
        }



    }


    //meetod arutelu ja selle kommentaaride ekraanile väljastamiseks
    public void esitaArutelu() {
        System.out.println(Arutelu_nimi);
        System.out.println("Algataja: " + Algataja_nimi);
        System.out.println("Loomise aeg: " + Arutelu_loomise_algus);
        System.out.println("Vaatamiste arv: " + Vaatamiste_arv);
        System.out.println("Kommentaarid: ");
        for (Kommentaar kommentaar : Arutelu_kommentaarid) {
            System.out.print(kommentaar);
        }
    }

    @Override
    public int compareTo(Arutelud o) {
        return this.Arutelu_nimi.compareTo(o.getArutelu_nimi());
    }

    public void lisaKommentaarFaili(String failiNimi, String sonum){

        try(FileWriter fw = new FileWriter(failiNimi, true);
            BufferedWriter writer = new BufferedWriter(fw);) {
            writer.write(sonum);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
