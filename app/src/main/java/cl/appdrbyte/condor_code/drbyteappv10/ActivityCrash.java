package cl.appdrbyte.condor_code.drbyteappv10;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityCrash extends AppCompatActivity {
    private static int splashTimeout = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(ActivityCrash.this, Activity_0_Login.class);
                startActivity(i);
                finish();

            }
        }, splashTimeout);
    }
}
