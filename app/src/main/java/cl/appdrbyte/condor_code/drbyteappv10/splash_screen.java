package cl.appdrbyte.condor_code.drbyteappv10;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class splash_screen extends AppCompatActivity {

    private ImageView logo;
    private static int splashTimeout = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo = (ImageView) findViewById(R.id.logo_byte);

        Animation my_anim = AnimationUtils.loadAnimation(this, R.anim.animation);

        logo.startAnimation(my_anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(splash_screen.this, activity_1_log_in.class);
                startActivity(i);
                finish();

            }
        }, splashTimeout);
    }
}
