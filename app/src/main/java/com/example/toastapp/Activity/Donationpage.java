package com.example.toastapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.toastapp.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class Donationpage extends AppCompatActivity implements PaymentResultListener {
    EditText name, email, amount, contact;
    AppCompatButton donate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donationpage);

        name = findViewById(R.id.et_donar_name);
        email = findViewById(R.id.et_donar_email);
        amount = findViewById(R.id.et_donation_amount);
        contact = findViewById(R.id.et_donar_contactno);
        donate = findViewById(R.id.btn_download_reciept);


        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });


    }

    private void startPayment() {


        final Activity activity = this;

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "DevComm");
            options.put("description", "Donation for support");
            options.put("send_sms_hash", true);
            options.put("allow_rotation", true);
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://raw.githubusercontent.com/RahulSoni0/RahulSoni0/main/135733646-fccb6edd-50a2-4bff-8ec1-6468a609a040.png");
            options.put("currency", "INR");

            options.put("amount", amount.getText().toString().trim());

            JSONObject preFill = new JSONObject();
            preFill.put("email", email.getText().toString().trim());
            preFill.put("contact", contact.getText().toString().trim());

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {

        try {

            Toast.makeText(this, "Payment Successful: " + s, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,Reciept.class);
            intent.putExtra("name" , name.getText().toString());
            intent.putExtra("email" , email.getText().toString());
            intent.putExtra("contact" , contact.getText().toString());
            intent.putExtra("amount" , amount.getText().toString());
            startActivity(intent);


        } catch (Exception e) {
            Log.e("TAG", "Exception in onPaymentSuccess", e);
        }

    }

    @Override
    public void onPaymentError(int i, String s) {

        try {
            Toast.makeText(this, "Payment failed: " + " Error " , Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("TAG", "Exception in onPaymentError", e);
        }

    }



}