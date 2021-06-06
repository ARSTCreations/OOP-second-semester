package com.sample;

public class TabelBuku {

    int id;
    String judul,penulis,penerbit,jumlahEksemplar,edisi;
    String kategori,bentuk;

    public TabelBuku(int id, String judul, String penulis, String penerbit, String jumlahEksemplar, String edisi, String kategori, String bentuk) {
        this.id = id;
        this.judul = judul;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.jumlahEksemplar = jumlahEksemplar;
        this.edisi = edisi;
        this.kategori = kategori;
        this.bentuk = bentuk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getJumlahEksemplar() {
        return jumlahEksemplar;
    }

    public void setJumlahEksemplar(String jumlahEksemplar) {
        this.jumlahEksemplar = jumlahEksemplar;
    }

    public String getEdisi() {
        return edisi;
    }

    public void setEdisi(String edisi) {
        this.edisi = edisi;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getBentuk() {
        return bentuk;
    }

    public void setBentuk(String bentuk) {
        this.bentuk = bentuk;
    }
}
