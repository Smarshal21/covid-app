package com.example.yo7a.healthwatcher;

import android.provider.ContactsContract;
import android.telecom.Call;

import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Interface {
    @POST("/posts")
    retrofit2.Call<Response> postData(@Body Data data);

}
