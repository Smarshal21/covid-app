package com.example.yo7a.healthwatcher;

import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterfaceThird {
    @POST("/posts")
    retrofit2.Call<ResponseThird> postData(@Body DataThird data);
}
