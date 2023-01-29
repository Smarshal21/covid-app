package com.example.yo7a.healthwatcher;

import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterfaceFirst {
    @POST("/posts")
    retrofit2.Call<Response> postData(@Body DataFirst data);
}
