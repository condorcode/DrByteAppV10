package cl.appdrbyte.condor_code.drbyteappv10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_2_log_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2_log_screen);

        btn_next();
    }

    public void btn_next() {

        Button boton_next_1 = (Button) findViewById(R.id.btn_crear_perf);
        boton_next_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_2_log_screen.this, activity_3_drawer_menu.class));
                //finish();
            }


        });

    }
}
