package com.example.foorumfx;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Arutelud implements Comparable<Arutelud>{
    private String aruteluNimi;
    private String algatajaNimi; // Mõtlesin, et akki on hea, kui arutelul on huvitavat infot natuke.
    private LocalDateTime aruteluLoomiseAalgus;
    private List<Kommentaar> aruteluKommentaarid;

    public Arutelud(String aruteluNimi, String algatajaNimi){
        this.aruteluNimi=aruteluNimi;
        this.algatajaNimi=Arutelud.this.algatajaNimi;
        this.aruteluLoomiseAalgus = LocalDateTime.now();
        this.aruteluKommentaarid = new ArrayList<Kommentaar>();
    }

    public String getalgatajaNimi() {
        return algatajaNimi;
    }

    public String getaruteluNimi() {
        return aruteluNimi;
    }

    public LocalDateTime getaruteluLoomiseAalgus() {
        return aruteluLoomiseAalgus;
    }

    public List<Kommentaar> getaruteluKommentaarid() { return aruteluKommentaarid; }

    @Override
    public String toString() {

        return "Arutelu teema: "+aruteluNimi+
                "; Arutelu algataja: "+ algatajaNimi+
                "; Arutelu loodi: "+ aruteluLoomiseAalgus;
    }
    //meetod kommentaari lisamiseks
    public void lisaKommentaar(String autor, String sisu) {
        aruteluKommentaarid.add(new Kommentaar(autor, sisu));
    }
    //meetod arutelu ja selle kommentaaride ekraanile väljastamiseks
    public void esitaArutelu() {
        System.out.println(aruteluNimi);
        System.out.println("Algataja: " + algatajaNimi);
        System.out.println("Loomise aeg: " + aruteluLoomiseAalgus);
        System.out.println("Kommentaarid: ");
        for (Kommentaar kommentaar : aruteluKommentaarid) {
            System.out.print(kommentaar);
        }
    }

    public Arutelud(File aruteluFail) throws FileNotFoundException, IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(aruteluFail));
            this.aruteluNimi = br.readLine();
            this.algatajaNimi = br.readLine();
            this.aruteluLoomiseAalgus = LocalDateTime.parse(br.readLine());
            int kommentaarideArv = Integer.parseInt(br.readLine());
            this.aruteluKommentaarid = new ArrayList<Kommentaar>();
            for (int i = 0; i < kommentaarideArv; i++) {
                aruteluKommentaarid.add(new Kommentaar(br.readLine(), br.readLine(), LocalDateTime.parse(br.readLine())));
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new IOException();
        };
    }

    public void kirjutaFaili() throws IOException {
        String failinimi = "arutelud\\" + aruteluNimi + ".txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(failinimi)));
        bw.write(aruteluNimi + "\n");
        bw.write(algatajaNimi + "\n");
        bw.write(aruteluLoomiseAalgus.toString() + "\n");
        bw.write(aruteluKommentaarid.size() + "\n");
        for (int i = 0; i < aruteluKommentaarid.size(); i++) {
            Kommentaar kommentaar = aruteluKommentaarid.get(i);
            bw.write(kommentaar.getKommentaari_autor() + "\n");
            bw.write(kommentaar.getKommentaari_sisu() + "\n");
            bw.write(kommentaar.getKommentaari_loomise_aeg().toString() + "\n");
        }
        bw.close();
    }

    @Override
    public int compareTo(Arutelud o) {
        return this.aruteluNimi.compareTo(o.getaruteluNimi());
    }
}
