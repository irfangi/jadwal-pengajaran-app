package com.ero.jadwalpengajaran.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResultJadwal {
    @SerializedName("data")
    ArrayList<DataListJadwal> data;

    public ArrayList<DataListJadwal> getData() {
        return data;
    }

    public void setData(ArrayList<DataListJadwal> data) {
        this.data = data;
    }
}
