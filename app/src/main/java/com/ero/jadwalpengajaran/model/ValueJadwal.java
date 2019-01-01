package com.ero.jadwalpengajaran.model;

import com.google.gson.annotations.SerializedName;

public class ValueJadwal {
    @SerializedName("tanggal")
    String tanggal;

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

}
