package com.example.creditcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText rate, period, sum;
    TextView monthlyPayment, totalAmount, overpayment;
    double monthlyRate, totalRate;
    String mPayment, tAmount, oPayment;

    DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        monthlyPayment = findViewById(R.id.monthlyPayment);
        totalAmount = findViewById(R.id.totalAmount);
        overpayment = findViewById(R.id.overpayment);

        rate = findViewById(R.id.rate);
        period = findViewById(R.id.period);
        sum = findViewById(R.id.sum);

    }



    public void calculate(View view) {
        rate = findViewById(R.id.rate);
        period = findViewById(R.id.period);
        sum = findViewById(R.id.sum);

        if (rate.getText().toString().equals("")) {
            Toast toast = Toast.makeText(this,
                    "Введите процентную ставку!", Toast.LENGTH_SHORT);
            toast.show();
        } else if (period.getText().toString().equals("")) {
            Toast toast = Toast.makeText(this,
                    "Введите срок кредитования!", Toast.LENGTH_SHORT);
            toast.show();
        } else if (sum.getText().toString().equals("")) {
            Toast toast = Toast.makeText(this,
                    "Введите сумму кредита!", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            monthlyRate = (Double.parseDouble(rate.getText().toString())) / 12 / 100;
            totalRate = Math.pow((1 + monthlyRate), Integer.parseInt(period.getText().toString()) * 12);
            mPayment = String.valueOf(Double.parseDouble(sum.getText().toString()) * monthlyRate * totalRate / (totalRate - 1));
            oPayment = String.valueOf(Double.parseDouble(mPayment) * (Double.parseDouble(period.getText().toString()) * 12) - Double.parseDouble(sum.getText().toString()));
            tAmount = String.valueOf(Double.parseDouble(oPayment) + Double.parseDouble(sum.getText().toString()));

            monthlyPayment.setText(decimalFormat.format(Double.parseDouble(mPayment)));
            totalAmount.setText(decimalFormat.format(Double.parseDouble(tAmount)));
            overpayment.setText(decimalFormat.format(Double.parseDouble(oPayment)));
        }
    }

    static {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);
    }

    public void clear(View view) {
    rate.setText("");
    rate.requestFocus();
    period.setText("");
    sum.setText("");
    monthlyPayment.setText("");
    totalAmount.setText("");
    overpayment.setText("");
    }
}