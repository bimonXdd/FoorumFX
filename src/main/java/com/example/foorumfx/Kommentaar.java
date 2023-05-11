package com.example.foorumfx;

import java.time.LocalDateTime;

public class Kommentaar {
    private String kommentaari_autor;
    private String kommentaari_sisu;
    private LocalDateTime kommentaari_loomise_aeg;

    public Kommentaar(String autor, String sisu) {
        this.kommentaari_autor = autor;
        this.kommentaari_sisu = sisu;
        this.kommentaari_loomise_aeg = LocalDateTime.now();
    }

    public Kommentaar(String kommentaari_autor, String kommentaari_sisu, LocalDateTime kommentaari_loomise_aeg) {
        this.kommentaari_autor = kommentaari_autor;
        this.kommentaari_sisu = kommentaari_sisu;
        this.kommentaari_loomise_aeg = kommentaari_loomise_aeg;
    }

    public String getKommentaari_autor() {
        return kommentaari_autor;
    }
    public String getKommentaari_sisu() {
        return kommentaari_sisu;
    }
    public LocalDateTime getKommentaari_loomise_aeg() {
        return kommentaari_loomise_aeg;
    }

    public String toString() {
        return "\n"+kommentaari_autor + ":"  +kommentaari_loomise_aeg+ "\t\t"+
                kommentaari_sisu;
    }
}
