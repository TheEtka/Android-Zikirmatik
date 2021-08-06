package com.abdullahetkakaradeniz.zikirmatik;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    int number;
    SharedPreferences sharedPreferences;
    int storedNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        sharedPreferences = this.getSharedPreferences("com.abdullahetkakaradeniz.zikirmatik", Context.MODE_PRIVATE);

        storedNumber = sharedPreferences.getInt("storedNumber",0);

        if (storedNumber == 0){
            textView.setText("Zikir: ");
        } else {
            textView.setText("Zikir: " + storedNumber);
        }

    }


    public void start (View view){

        textView.setText("Zikir: " + number);
        number++;
        textView.setText("Zikir: " + number);

        sharedPreferences.edit().putInt("storedNumber",number).apply();

        storedNumber = sharedPreferences.getInt("storedNumber",0);

        if (number != 0){
            sharedPreferences.edit().putInt("storedNumber",number).apply();
            textView.setText("Zikir: " + number);
        }

    }

    public void stop (View view){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("UYARI");
        alert.setMessage("Sıfırlamak istiyor musun?");
        alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                number = 0;
                textView.setText("Zikir: " + number);
                Toast.makeText(MainActivity.this,"Zikir Sıfırlandı",Toast.LENGTH_SHORT).show();

                int storedData = sharedPreferences.getInt("storedNumber",0);

                if (storedData != 0){
                    sharedPreferences.edit().remove("storedNumber").apply();
                    textView.setText("Zikir: ");
                }
            }
        });
        alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();

    }
}