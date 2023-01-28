package com.example.yo7a.healthwatcher;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//public class RetrofitClient {
//
//}
public class RetrofitClient {
    private static Retrofit retrofit;

    public static Retrofit getInstance(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
