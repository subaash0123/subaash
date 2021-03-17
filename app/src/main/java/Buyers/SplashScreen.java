package Buyers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.merchandiz.MainActivity;
import com.example.merchandiz.R;

public class SplashScreen extends AppCompatActivity {
    private static final long SPLASH_DISPLAY_LENGTH = 3000;
    ImageView logo,appName,splashImg;
    LottieAnimationView lottieAnimationView;
    private static final int NUM_PAGES=3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        splashImg=findViewById(R.id.img);
        lottieAnimationView=findViewById(R.id.lottie);
        //logo.animate().translationX(-1400).setDuration(1000).setStartDelay(4000);
        // appName.animate().translationX(1400).setDuration(1000).setStartDelay(4000);
    //    lottieAnimationView.animate().translationX(1400).setDuration(1000).setStartDelay(4000);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}