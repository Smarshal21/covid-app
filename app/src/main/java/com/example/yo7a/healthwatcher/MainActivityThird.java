package com.example.yo7a.healthwatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class MainActivityThird extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_third);
        CheckBox diabetes = findViewById(R.id.diabetes);
        CheckBox cholestrol = findViewById(R.id.cholestrol);
        CheckBox x = findViewById(R.id.x);
        CheckBox y = findViewById(R.id.y);
        CheckBox z = findViewById(R.id.z);

        boolean Diabetes = diabetes.isChecked();
        boolean Cholestrol = cholestrol.isChecked();
        boolean X = x.isChecked();
        boolean Y = y.isChecked();
        boolean Z = z.isChecked();

        Button submit2 = findViewById(R.id.submit2);

        Retrofit retrofit = RetrofitClient.getInstance("https://jsonplaceholder.typicode.com/");
        InterfaceThird api = retrofit.create(InterfaceThird.class);
        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataThird data = new DataThird(Diabetes,Cholestrol,X,Y,Z);
                api.postData(data).enqueue(new Callback<ResponseThird>() {
                    @Override
                    public void onResponse(Call<ResponseThird> call, retrofit2.Response<ResponseThird> response) {

                        Toast.makeText(MainActivityThird.this, "Data Added", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseThird> call, Throwable t) {
                        Toast.makeText(MainActivityThird.this, "Data Adding Failed", Toast.LENGTH_LONG).show();

                    }
                });
            }
        });

    }
}