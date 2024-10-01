package com.example.zistock;

public class Classecontenu {

    private String nom;
    private int quant;
    private int total;
    //private String num;
    private int prixunit;
    private String date;

    public Classecontenu(String nom, int prixunit, int quant, int total, String date) {
        this.nom= nom;
        this.prixunit = prixunit;
        this.quant= quant;
        this.total = total;
        this.date = date;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPrixunit() {
        return prixunit;
    }

    public void setPrixunit(int prixunit) {
        this.prixunit = prixunit;
    }




}
