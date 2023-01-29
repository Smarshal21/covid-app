package com.example.yo7a.healthwatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;


public class MainActivityFirsty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_firsty);
        EditText nameEditText = findViewById(R.id.name);
        String name = nameEditText.getText().toString();
        EditText ageEditText = findViewById(R.id.age);
        String age = ageEditText.getText().toString();
        EditText sexEditText = findViewById(R.id.sex);
        String sex = sexEditText.getText().toString();
        EditText heightEditText = findViewById(R.id.Height);
        String height = heightEditText.getText().toString();
        EditText weightEditText = findViewById(R.id.Weight);
        String weight = weightEditText.getText().toString();
        Button submitButton = findViewById(R.id.submit);

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://jsonplaceholder.typicode.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();

        Retrofit retrofit = RetrofitClient.getInstance("https://jsonplaceholder.typicode.com/");
        InterfaceFirst api = retrofit.create(InterfaceFirst.class);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataFirst data = new DataFirst(name, age, sex, height, weight);
                api.postData(data).enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        Toast.makeText(MainActivityFirsty.this, "Data Added", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Toast.makeText(MainActivityFirsty.this, "Data Adding Failed", Toast.LENGTH_LONG).show();
                    }
                });
//                api.postData(data).enqueue(new Callback<ResponseFirst>() {
//                    @Override
//                    public void onResponse(Call<ResponseFirst> call, retrofit2.Response<ResponseFirst> response) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseFirst> call, Throwable t) {
//
//                    }
//
//                });
            }
        });

        Button nextButton = findViewById(R.id.next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityFirsty.this, MainActivitySecond.class);
                startActivity(intent);
            }
        });

    }
}
//package com.example.yo7a.healthwatcher;
//
//
//public class MainActivityFirst extends AppCompatActivity {
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_first);
//
//    }
//}
//package com.example.yo7a.healthwatcher;
//
//        import android.annotation.SuppressLint;
//        import android.content.Intent;
//        import androidx.appcompat.app.AppCompatActivity;
//        import android.os.Bundle;
//        import android.widget.Button;
//        import android.widget.EditText;
//        import android.widget.Toast;
//        import retrofit2.Call;
//        import retrofit2.Callback;
//
//public class MainActivityFirst extends AppCompatActivity {
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_first);
//
//    }
//}

