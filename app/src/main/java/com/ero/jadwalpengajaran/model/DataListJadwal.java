package com.ero.jadwalpengajaran.model;

import com.google.gson.annotations.SerializedName;

public class DataListJadwal {
    @SerializedName("id")
    String judul;
    @SerializedName("nama")
    String nama;
    @SerializedName("nim")
    String nim;
    @SerializedName("tanggal")
    String tanggal;

    public DataListJadwal(){

    }

    public DataListJadwal(String judul, String nama, String nim, String tanggal, String waktu, String ruang, String narasumber1, String narasumber2, String narasumber3) {
        this.judul = judul;
        this.nama = nama;
        this.nim = nim;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.ruang = ruang;
        this.narasumber1 = narasumber1;
        this.narasumber2 = narasumber2;
        this.narasumber3 = narasumber3;
    }

    @SerializedName("waktu")
    String waktu;
    @SerializedName("ruang")
    String ruang;
    @SerializedName("narasumber1")
    String narasumber1;
    @SerializedName("narasumber2")
    String narasumber2;
    @SerializedName("narasumber3")
    String narasumber3;

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }

    public String getNarasumber1() {
        return narasumber1;
    }

    public void setNarasumber1(String narasumber1) {
        this.narasumber1 = narasumber1;
    }

    public String getNarasumber2() {
        return narasumber2;
    }

    public void setNarasumber2(String narasumber2) {
        this.narasumber2 = narasumber2;
    }

    public String getNarasumber3() {
        return narasumber3;
    }

    public void setNarasumber3(String narasumber3) {
        this.narasumber3 = narasumber3;
    }
}
