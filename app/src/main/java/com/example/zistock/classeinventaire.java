package com.example.zistock;

public class classeinventaire {

        private static String nom;
        private static int prix;
        private static int total;
        private static int quant;
        private String alerte;
        private static String date;



        /*public classeinventaire(String nom, int prix, int quant, int total) {
            this(nom, prix, quant, total, );
        }*/

    public classeinventaire(String nom, int prix, int quant, int total, String date) {
            this.nom= nom;
            this.prix = prix;
            this.total = total;
            this.date = date;
            this.quant= quant;

        }

    public static String getDate() {

        return date;
    }

    public void setDate(String date) {

        this.date = date;
    }


    public static int getQuant() {

            return quant;
        }

        public void setQuant(int quant) {

            this.quant = quant;
        }

        public static String getNom() {

            return nom;
        }

        public void setNom(String nom) {

            this.nom = nom;
        }
    public static int getPrix() {

        return prix;
    }

    public void setPrix(int prix) {

        this.prix = prix;
    }
        public static int getTotal() {

            return total;
        }

        public void setTotal(int total) {

            this.total = total;
        }




    }


