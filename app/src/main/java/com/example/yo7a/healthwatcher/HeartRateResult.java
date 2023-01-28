package com.example.yo7a.healthwatcher;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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


public class HeartRateResult extends AppCompatActivity {

    private String user, Date;
    int HR;
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    Date today = Calendar.getInstance().getTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate_result);

        Date = df.format(today);
        TextView RHR = this.findViewById(R.id.HRR);
        ImageButton SHR = this.findViewById(R.id.SendHR);
        Button submit = this.findViewById(R.id.submit);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            HR = bundle.getInt("bpm");
            user = bundle.getString("Usr");
            Log.d("DEBUG_TAG", "ccccc" + user);
            RHR.setText(String.valueOf(HR));
        }
submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Retrofit retrofit = RetrofitClient.getInstance("https://jsonplaceholder.typicode.com/");
        Interface api = retrofit.create(Interface.class);

        Data data = new Data(RHR);
        api.postData(data).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Toast.makeText(HeartRateResult.this, "Data Added", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(HeartRateResult.this, "Data Not Added", Toast.LENGTH_LONG).show();
            }
        });
    }
});
        SHR.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{"recipient@example.com"});
            i.putExtra(Intent.EXTRA_SUBJECT, "Health Watcher");
            i.putExtra(Intent.EXTRA_TEXT, user + "'s Heart Rate " + "\n" + " at " + Date + " is :    " + HR);
            try {
                startActivity(Intent.createChooser(i, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(HeartRateResult.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(HeartRateResult.this, Primary.class);
        i.putExtra("Usr", user);
        startActivity(i);
        finish();
    }
}
