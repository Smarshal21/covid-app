package com.example.yo7a.healthwatcher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class BloodPressureResult extends AppCompatActivity {

    private String user, Date;
    int SP, DP;
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    Date today = Calendar.getInstance().getTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure_result);

        Date = df.format(today);
        TextView TBP = this.findViewById(R.id.BPT);
        ImageButton SBP = this.findViewById(R.id.SendBP);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            SP = bundle.getInt("SP");
            DP = bundle.getInt("DP");
            user = bundle.getString("Usr");
            TBP.setText(SP + " / " + DP);
            Retrofit retrofit = RetrofitClient.getInstance("https://jsonplaceholder.typicode.com/");
            Interface api = retrofit.create(Interface.class);

            Data data = new Data(TBP);
            api.postData(data).enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    Toast.makeText(BloodPressureResult.this, "Data Added", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    Toast.makeText(BloodPressureResult.this, "Data Not Added", Toast.LENGTH_LONG).show();
                }
            });
        }




        SBP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"recipient@example.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Health Watcher");
                i.putExtra(Intent.EXTRA_TEXT, user + "'s Blood Pressure " + "\n" + " at " + Date + " is :    " + SP + " / " + DP);
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(BloodPressureResult.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(BloodPressureResult.this, Primary.class);
        i.putExtra("Usr", user);
        startActivity(i);
        finish();
        super.onBackPressed();
    }

}
