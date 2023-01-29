package com.example.yo7a.healthwatcher;

import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterfaceSecond {
    @POST("/posts")
    retrofit2.Call<ResponseSecond> postData(@Body DataSecond data);
}
