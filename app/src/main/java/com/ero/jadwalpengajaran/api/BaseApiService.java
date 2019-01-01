package com.ero.jadwalpengajaran.api;

import com.ero.jadwalpengajaran.model.DataListJadwal;
import com.ero.jadwalpengajaran.model.ResultJadwal;
import com.ero.jadwalpengajaran.model.ValueJadwal;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BaseApiService {
    @POST("lihat_jadwal.php")
    Call<ResultJadwal> lihatJadwal(@Body ValueJadwal valueJadwal);
}
