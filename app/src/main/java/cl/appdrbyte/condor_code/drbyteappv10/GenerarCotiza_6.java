package cl.appdrbyte.condor_code.drbyteappv10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GenerarCotiza_6 extends AppCompatActivity {

    double total = 0;
    double total_lista = 0;
    double total_desc = 0;
    double comision = 0;
    double iva;
    TextView comi;
    TextView txt_iva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_cotiza_6);

        TextView nom_cliente = (TextView) findViewById(R.id.txt_gen_coti_nom_cli);
        nom_cliente.setText(getIntent().getStringExtra("nom_cliente"));

        TextView sub_tot = (TextView) findViewById(R.id.txt_val_subtotal_1);
        sub_tot.setText("$" + getIntent().getStringExtra("suma_lista") + ".-");
        total_lista = Integer.parseInt(getIntent().getStringExtra("suma_lista"));

        comi = (TextView) findViewById(R.id.txt_comision_tec);
        comi.setText(String.valueOf("$" + String.valueOf(comision) + ".-"));

        txt_iva = (TextView) findViewById(R.id.txt_iva_solo);
        txt_iva.setText("$" + String.valueOf(iva) + ".-");



        final TextView sub_tot_desc = (TextView) findViewById(R.id.txt_val_subtotal_desc_1);

        Button btn_calc = (Button) findViewById(R.id.btn_calcular_1);

        Button btn_crea = (Button) findViewById(R.id.btn_crear_coti);





        sub_tot_desc.setText(String.valueOf("$" + String.valueOf(total_desc) + ".-"));


        CheckBox chk_box1 = (CheckBox) findViewById (R.id.checkBox1_desc);
        chk_box1.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               //is chkIos checked?
               if (((CheckBox) v).isChecked()) {
                   //Case 1
                   total_desc += (total_lista * .02);
                   sub_tot_desc.setText(String.valueOf("$" + String.valueOf(total_desc) + ".-"));


               }else {

                   total_desc -= (total_lista * .02);
                   sub_tot_desc.setText(String.valueOf("$" + String.valueOf(total_desc) + ".-"));

               }
           }
        });

        CheckBox chk_box2 = (CheckBox) findViewById (R.id.checkBox2_desc);
        chk_box2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    //Case 1
                    total_desc += (total_lista * .03);
                    sub_tot_desc.setText(String.valueOf("$" + String.valueOf(total_desc) + ".-"));


                }else {

                    total_desc -= (total_lista * .03);
                    sub_tot_desc.setText(String.valueOf("$" + String.valueOf(total_desc) + ".-"));
                }
            }
        });


        final TextView txt_total_final = (TextView) findViewById(R.id.txt_view_resul_1);
        txt_total_final.setText("$" + String.valueOf(total) + ".-");

        ListView lista_val = (ListView) findViewById(R.id.lv_cotizacion_final);

        ArrayList<String> valores = new ArrayList<String>();
        valores = getIntent().getStringArrayListExtra("val_text");





        ArrayAdapter<String> adapter = new ArrayAdapter<String>(GenerarCotiza_6.this,android.R.layout.simple_list_item_1, valores);
        lista_val.setAdapter(adapter);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_4);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null ){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        btn_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iva = ((total_lista - total_desc) * .19);
                comision = (total_lista - total_desc) * .2;
                total = (total_lista - total_desc) + iva;

                comi.setText(String.valueOf("$" + String.valueOf(comision) + ".-"));
                txt_iva.setText("$" + String.valueOf(iva) + ".-");
                txt_total_final.setText("$" + String.valueOf(total) + ".-");

            }
        });

        btn_crea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GenerarCotiza_6.this, activity_3_drawer_menu.class);
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
