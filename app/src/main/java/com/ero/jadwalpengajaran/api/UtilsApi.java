package com.ero.jadwalpengajaran.api;

public class UtilsApi {
    public static final String BASE_URL_API = "http://192.168.1.3/jadwal_pengajaran/";

    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }

}
