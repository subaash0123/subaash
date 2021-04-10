package com.example.merchandiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import Buyers.HomeActivity;
import Buyers.LoginActivity;

public class OtpVerifyScreen extends AppCompatActivity {
EditText otpnum;
String otpnumber;
String Verificationid;
Button checkotp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify_screen);
        otpnum=findViewById(R.id.otpnum);
        checkotp=findViewById(R.id.checkotp);
        otpnumber=otpnum.getText().toString();
        Verificationid=getIntent().getStringExtra("verificationum");
        checkotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(otpnum.getText().toString().isEmpty())
                {
                    Toast.makeText(OtpVerifyScreen.this, "Please enter otp", Toast.LENGTH_SHORT).show();
                }
                else if(otpnum.getText().toString().length()<=5)
                {
                    Toast.makeText(OtpVerifyScreen.this, "Please enter 6 digit otp", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(OtpVerifyScreen.this, "Congratulation ! Registered sucessfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(), LoginActivity.class);

                    startActivity(intent);
                    /*if(Verificationid!=null)
                    {
                        PhoneAuthCredential phoneAuthCredential=PhoneAuthProvider.getCredential(Verificationid,otpnumber);
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful())
                                        {
                                            Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        }
                                        else
                                        {
                                            Toast.makeText(OtpVerifyScreen.this, "verfication code is invalid ", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }*/
                }
            }
        });


    }
}