package cl.appdrbyte.condor_code.drbyteappv10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetalleActividad extends AppCompatActivity {

    String estado_trab;
    String nombre_cli;

    TextView txt_1;
    TextView txt_2;
    TextView txt_3;
    TextView txt_4;
    TextView txt_5;

    EditText txt_a;
    EditText txt_b;
    EditText txt_c;

    Button btn_1;

    ImageButton img_btn_pdf;
    CheckBox btn_enviar_cot;
    ImageView img_div;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_actividad);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_3);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null ){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }



        TextView nombre_cliente_det = (TextView) findViewById(R.id.txt_servicio_nom_cli);
        TextView estado_trabajo = (TextView) findViewById(R.id.lv_txt_estado_2);
        estado_trab = getIntent().getStringExtra("estado_img");
        nombre_cli = getIntent().getStringExtra("nom_cliente");
        Button btn_cotizar = (Button) findViewById(R.id.btn_cotizar_1);


        if (estado_trab.equals("confirmar")){

            estado_trabajo.setBackgroundResource(R.drawable.alert_cian);

        } else {

            estado_trabajo.setBackgroundResource(R.drawable.alert_amarillo);
        }

        img_btn_pdf = (ImageButton) findViewById(R.id.img_btn_pdf);
        btn_enviar_cot = (CheckBox) findViewById(R.id.check_pdf_send);



        img_div = (ImageView) findViewById(R.id.img_linea_div_6);

        txt_1 = (TextView) findViewById(R.id.txt_dat_cli_1_a);
        txt_2 = (TextView) findViewById(R.id.txt_nom_cli_a);
        txt_3 = (TextView) findViewById(R.id.txt_correo_cli_b);
        txt_4 = (TextView) findViewById(R.id.txt_cel_cli_c);


        txt_a = (EditText) findViewById(R.id.txt_ed_nom_cli_a);
        txt_b = (EditText) findViewById(R.id.txt_ed_correo_cli_b);
        txt_c = (EditText) findViewById(R.id.txt_ed_cel_cli_c);


        btn_1 = (Button) findViewById(R.id.button_job_1_x);



        CheckBox btn_abrir = (CheckBox) findViewById(R.id.check_enviar_pdf);
        btn_abrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((CheckBox) v).isChecked()) {
                    //Case 1
                    img_btn_pdf.setVisibility(View.VISIBLE);
                    btn_enviar_cot.setVisibility(View.VISIBLE);


                    //Case 1
                    img_div.setVisibility(View.VISIBLE);

                    txt_1.setVisibility(View.VISIBLE);
                    txt_2.setVisibility(View.VISIBLE);
                    txt_3.setVisibility(View.VISIBLE);
                    txt_4.setVisibility(View.VISIBLE);

                    txt_a.setVisibility(View.VISIBLE);
                    txt_b.setVisibility(View.VISIBLE);
                    txt_c.setVisibility(View.VISIBLE);

                    btn_1.setVisibility(View.VISIBLE);


                }
                else {
                    //case 2
                    img_btn_pdf.setVisibility(View.GONE);
                    btn_enviar_cot.setVisibility(View.GONE);

                    //case 2

                    img_div.setVisibility(View.GONE);
                    txt_1.setVisibility(View.GONE);
                    txt_2.setVisibility(View.GONE);
                    txt_3.setVisibility(View.GONE);
                    txt_4.setVisibility(View.GONE);

                    txt_a.setVisibility(View.GONE);
                    txt_b.setVisibility(View.GONE);
                    txt_c.setVisibility(View.GONE);

                    btn_1.setVisibility(View.GONE);


                }

            }
        });










        nombre_cliente_det.setText(getIntent().getStringExtra("nom_cliente"));
        estado_trabajo.setText(getIntent().getStringExtra("estado_img"));

        btn_cotizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(DetalleActividad.this, CotizarActivity_5.class));
                Toast.makeText(DetalleActividad.this, "Cotizar " + nombre_cli, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(DetalleActividad.this, CotizarActivity_5.class);
                intent.putExtra("nom_cliente", nombre_cli);
                intent.putExtra("estado_img", estado_trab);
                startActivity(intent);

            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetalleActividad.this, MailSend.class);
                startActivity(intent);
            }
        });

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }


        return super.onOptionsItemSelected(item);

    }


}
