package cl.appdrbyte.condor_code.drbyteappv10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_1);

        setTitle(null);

        Toolbar tool_b = (Toolbar) findViewById(R.id.app_bar_1);
        setSupportActionBar(tool_b);

        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        //getSupportActionBar().setIcon(R.drawable.dr_byte_icon_5);

        btn_next();
    }

    public void btn_next() {

        Button boton_next_1 = (Button) findViewById(R.id.button_perfil);
        boton_next_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity_1.this, activity_2.class));
                finish();
            }


        });

    }
}
