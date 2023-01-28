package com.example.yo7a.healthwatcher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class O2Result extends AppCompatActivity {

    private String user, Date;
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    Date today = Calendar.getInstance().getTime();
    int O2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o2_result);

        Date = df.format(today);
        TextView RO2 = this.findViewById(R.id.O2R);
        ImageButton SO2 = this.findViewById(R.id.SendO2);
        Button submit = this.findViewById(R.id.submit);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            O2 = bundle.getInt("O2R");
            user = bundle.getString("Usr");
            RO2.setText(String.valueOf(O2));
        }
 submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Retrofit retrofit = RetrofitClient.getInstance("https://jsonplaceholder.typicode.com/");
        Interface api = retrofit.create(Interface.class);

        Data data = new Data(RO2);
        api.postData(data).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Toast.makeText(O2Result.this, "Data Added", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(O2Result.this, "Data Not Added", Toast.LENGTH_LONG).show();
            }
        });
    }
});
        SO2.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{"recipient@example.com"});
            i.putExtra(Intent.EXTRA_SUBJECT, "Health Watcher");
            i.putExtra(Intent.EXTRA_TEXT, user + "'s Oxygen Saturation Level " + "\n" + " at " + Date + " is :   " + O2);
            try {
                startActivity(Intent.createChooser(i, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(O2Result.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {

        Intent i = new Intent(O2Result.this, Primary.class);
        i.putExtra("Usr", user);
        startActivity(i);
        finish();
        super.onBackPressed();

    }
}
