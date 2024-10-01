package com.example.zistock;

public class classecommande {
    private String nom;
    private int quant;
    private String alerte;
    private String num;


    public classecommande(String num, String nom, int quant, String alerte) {
        this.nom= nom;
        this.num = num;
        this.quant= quant;
        this.alerte= alerte;

    }

    public int getQuant() {

        return quant;
    }

    public void setQuant(int quant) {

        this.quant = quant;
    }

    public String getNom() {

        return nom;
    }

    public void setNom(String nom) {

        this.nom = nom;
    }

    public String getNum() {

        return num;
    }

    public void setNum(String num) {

        this.num = num;
    }


    public String getAlerte() {
        return alerte;
    }

    public void setAlerte(String alerte) {
        this.alerte = alerte;
    }
}
