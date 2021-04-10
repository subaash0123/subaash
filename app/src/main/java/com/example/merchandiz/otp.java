package com.example.merchandiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;



import java.util.Objects;
import java.util.concurrent.TimeUnit;

import Buyers.LoginActivity;
import Buyers.RegisterActivity;
import Buyers.SplashScreen;

public class otp extends AppCompatActivity  {
    Button verify,sendotp,resendotp,phoneverify,emailverify;
    TextView phonenum;
    EditText otpnum;
    FirebaseAuth fauth;
    FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore fstore;

    private static final String TAG = "otp";
    PhoneAuthProvider.ForceResendingToken mResendToken;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    String mVerificationId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        fauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

        initsetup();

    }
    private void initsetup(){

        verify=(Button) findViewById(R.id.checkotp);
        phonenum=(TextView) findViewById(R.id.phonenum);
        otpnum=(EditText) findViewById(R.id.otpnum);
        sendotp=(Button) findViewById(R.id.sendotp);
        resendotp=(Button) findViewById(R.id.resendotp);

        Intent intent=getIntent();
        String phoneval=intent.getStringExtra("phonenum");
        phonenum.setText(phoneval);
        //Objects.requireNonNull(getSupportActionBar()).setTitle("Verify Phone Number");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                       "+91" + phoneval,
                        60,
                        TimeUnit.SECONDS,
                        otp.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(otp.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                Intent intentone=new Intent(getApplicationContext(), OtpVerifyScreen.class);
                                intent.putExtra("mobilenumber",phoneval);
                                intent.putExtra("verificationum",s);
                                Log.d("otpnumber", "onCodeSent: "+s);
                                startActivity(intentone);
                            }
                        }

                        );

            }
        });

       /* verify.setOnClickListener(this);
        sendotp.setOnClickListener(this);
        resendotp.setOnClickListener(this);*/

    }



/*
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sendotp:
                getOtp( phonenum.getText().toString());
                sendotp.setVisibility(View.GONE);
                otpnum.setVisibility(View.VISIBLE);
                verify.setVisibility(View.VISIBLE);
                resendotp.setVisibility(View.VISIBLE);
                break;
            case R.id.checkotp:
                String code = otpnum.getText().toString();
                verifyPhoneNumberWithCode(mVerificationId, code);
                if(otpnum.getText().toString().isEmpty() || otpnum.getText().toString().length()< 6 || otpnum.getText().toString().length()>6){
                    otpnum.setError("please enter a valid otp");
                }
                break;
            case R.id.resendotp:
                resendVerificationCode(phonenum.getText().toString(), mResendToken);
                Toast.makeText(otp.this, "OTP has been resent", Toast.LENGTH_SHORT).show();
                break;
        }
    }*/


   /*     private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithCredential:success");

                                FirebaseUser user = task.getResult().getUser();
                                // Update UI
                            } else {
                                // Sign in failed, display a message and update the UI
                                Log.w(TAG, "signInWithCredential:failure", task.getException());
                                if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                    // The verification code entered was invalid
                                }
                            }
                        }
                    });
        }

*/

   /* private void getOtp(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                this,
                mCallbacks);
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        //signInWithPhoneAuthCredential(credential);
    }

    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                this,
                mCallbacks,
                token);
    }*/
}
