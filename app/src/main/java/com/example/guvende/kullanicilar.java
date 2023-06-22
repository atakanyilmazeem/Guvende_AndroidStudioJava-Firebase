package com.example.guvende;

public class kullanicilar {


    String kayitad = "";
    String kayitsoyad = "";
    String kayittc = "";
    String kayitdogum = "";
    String kayittelefon = "";
    String kayitsifre = "";

    String kayityer = "";

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    String key = "";

public  kullanicilar(String kayitad,String kayitsoyad,String kayittc,String kayitdogum,String kayittelefon,String kayitsifre) {

     this.kayitad=kayitad;
     this.kayitsoyad=kayitsoyad;
     this.kayittc=kayittc;
     this.kayitsifre=kayitsifre;

     this.kayitdogum=kayitdogum;
     this.kayittelefon=kayittelefon;


 }
    public kullanicilar(String kayitad, String kayitsoyad,String kayittc, String kayitdogum, String kayittelefon, String kayitsifre,  String kayityer) {
        this.kayitad = kayitad;
        this.kayitsoyad = kayitsoyad;
        this.kayittc=kayittc;
        this.kayitdogum = kayitdogum;
        this.kayittelefon = kayittelefon;
        this.kayitsifre = kayitsifre;

        this.kayityer = kayityer;


    }

    public String getKayitad() {
        return kayitad;
    }

    public void setKayitad(String kayitad) {
        this.kayitad = kayitad;
    }

    public String getKayitsoyad() {
        return kayitsoyad;
    }

    public void setKayitsoyad(String kayitsoyad) {
        this.kayitsoyad = kayitsoyad;
    }

    public String getKayittc() {
        return kayittc;
    }

    public void setKayittc(String kayittc) {
        this.kayittc = kayittc;
    }

    public String getKayitdogum() {
        return kayitdogum;
    }

    public void setKayitdogum(String kayitdogum) {
        this.kayitdogum = kayitdogum;
    }

    public String getKayittelefon() {
        return kayittelefon;
    }

    public void setKayittelefon(String kayittelefon) {
        this.kayittelefon = kayittelefon;
    }

    public String getKayitsifre() {
        return kayitsifre;
    }

    public void setKayitsifre(String kayitsifre) {
        this.kayitsifre = kayitsifre;
    }





    public String getKayityer() {
        return kayityer;
    }

    public void setKayityer(String kayityer) {
        this.kayityer = kayityer;
    }

    public kullanicilar() {
    }

  /* public kullanicilar(String kayitad, String kayitsoyad, String kayittc, String kayitdogum, String kayittelefon, String kayitsifre,  String kayityer) {
        this.kayitad = kayitad;
        this.kayitsoyad = kayitsoyad;
        this.kayittc = kayittc;
        this.kayitdogum = kayitdogum;
        this.kayittelefon = kayittelefon;
        this.kayitsifre = kayitsifre;

        this.kayityer = kayityer;
    }
*/


}