package com.example.yo7a.healthwatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class MainActivitySecond extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_second);
        CheckBox cough = findViewById(R.id.cough);
        CheckBox cold = findViewById(R.id.cold);
        CheckBox headache = findViewById(R.id.headache);
        CheckBox bodypains = findViewById(R.id.bodypains);
        CheckBox vomiting = findViewById(R.id.vomiting);
        CheckBox smell = findViewById(R.id.smell);

        final boolean Cough = cough.isChecked();
        final boolean Cold = cold.isChecked();
        final boolean Headache = headache.isChecked();
        final boolean Bodypains = bodypains.isChecked();
        final boolean Vomiting = vomiting.isChecked();
        final boolean Smell = smell.isChecked();

        final Button submit1 = findViewById(R.id.submit1);

        Retrofit retrofit = RetrofitClient.getInstance("https://jsonplaceholder.typicode.com/");
         InterfaceSecond api = retrofit.create(InterfaceSecond.class);

        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSecond data = new DataSecond(Cough, Cold, Headache, Bodypains, Vomiting, Smell);
                api.postData(data).enqueue(new Callback<ResponseSecond>() {
                    @Override
                    public void onResponse(Call<ResponseSecond> call, retrofit2.Response<ResponseSecond> response) {
                        Toast.makeText(MainActivitySecond.this, "Data Added", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseSecond> call, Throwable t) {
                        Toast.makeText(MainActivitySecond.this, "Data Adding Failed", Toast.LENGTH_LONG).show();
                    }
                });


                Button next2 = findViewById(R.id.next2);
                next2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivitySecond.this, MainActivityThird.class));
                    }
                });
            }
        });

    }
}