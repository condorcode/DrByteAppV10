package cl.appdrbyte.condor_code.drbyteappv10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;


public class activity_1_log_in extends AppCompatActivity {

    Animation alph_anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1_log_in);

        //alph_anim = AnimationUtils.loadAnimation(this,R.anim.anim_alpha);

        btn_next();
    }

    public void btn_next() {

        final Button boton_next_1 = (Button) findViewById(R.id.btn_correo);

        boton_next_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //boton_next_1.setBackgroundTintList(android.content.res.ColorStateList.valueOf(android));
                //v.startAnimation(alph_anim);
                startActivity(new Intent(activity_1_log_in.this, activity_2_log_screen.class));
                //finish();
            }


        });

    }
}
