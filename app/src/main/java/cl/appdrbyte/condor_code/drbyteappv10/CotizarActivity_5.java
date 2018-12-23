package cl.appdrbyte.condor_code.drbyteappv10;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CotizarActivity_5 extends AppCompatActivity {

    ArrayList<String> addItems = new ArrayList<>();
    ArrayList<String> keepnum = new ArrayList<>();
    ListView show;
    int suma = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cotizar_5);

        ListView lista_items = (ListView) findViewById(R.id.lv_cotiz_spinner);
        show = (ListView) findViewById(R.id.lv_almacen_1);


        final TextView txt_total = (TextView) findViewById(R.id.txt_view_resultado_1);

        Button btn_gen_cotiz = (Button) findViewById(R.id.btn_cot_1);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_4);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null ){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        TextView nombre_cliente = (TextView) findViewById(R.id.txt_coti_nom_cli);
        nombre_cliente.setText(getIntent().getStringExtra("nom_cliente"));
        final String nom_cli = getIntent().getStringExtra("nom_cliente");

        CotizaDatos datos_cot1 = new CotizaDatos("Formateo computador", "10000");
        CotizaDatos datos_cot2 = new CotizaDatos("Respaldo Und. Alamcen.", "15000");
        CotizaDatos datos_cot3 = new CotizaDatos("Limpieza basica chasis", "15000");
        CotizaDatos datos_cot4 = new CotizaDatos("Limpieza compl. chasis", "20000");
        CotizaDatos datos_cot5 = new CotizaDatos("Inst. soft. básico", "10000");
        CotizaDatos datos_cot6 = new CotizaDatos("Inst. soft. diseño", "15000");
        CotizaDatos datos_cot7 = new CotizaDatos("Limpieza sist. archivos", "15000");
        CotizaDatos datos_cot8 = new CotizaDatos("Gestión de compra", "17750");

        final ArrayList<CotizaDatos> items_coti = new ArrayList<>();

        items_coti.add(datos_cot1);
        items_coti.add(datos_cot2);
        items_coti.add(datos_cot3);
        items_coti.add(datos_cot4);
        items_coti.add(datos_cot5);
        items_coti.add(datos_cot6);
        items_coti.add(datos_cot7);
        items_coti.add(datos_cot8);


        CotizListAdapter adaptador = new CotizListAdapter(CotizarActivity_5.this, R.layout.adapter_list_cotiza, items_coti);
        lista_items.setAdapter(adaptador);


        lista_items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Animation animation1 = new AlphaAnimation(0.4f, 1.0f);
                animation1.setDuration(800);
                view.startAnimation(animation1);

                String get_input = items_coti.get(position).serv_valor;
                String get_txt = items_coti.get(position).serv_item;


                //Toast.makeText(CotizarActivity_5.this, "Item: "
                 //       + items_coti.get(position).serv_valor, Toast.LENGTH_SHORT).show();

                if (get_input == null || get_input.trim().equals("")) {

                    Toast.makeText(CotizarActivity_5.this, "Item: vacio!", Toast.LENGTH_SHORT).show();

                }else {

                    addItems.add(get_txt + ": $" + get_input);
                    keepnum.add(get_input);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(CotizarActivity_5.this,android.R.layout.simple_list_item_1, addItems);
                    show.setAdapter(adapter);
                    suma += Integer.parseInt(get_input);
                    txt_total.setText("$" + String.valueOf(suma) + ".-");
                }

            }
        });

        show.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Animation animation1 = new AlphaAnimation(0.4f, 1.0f);
                animation1.setDuration(800);
                view.startAnimation(animation1);

                //Toast.makeText(CotizarActivity_5.this, show.getAdapter().getItem(position).toString(), Toast.LENGTH_SHORT).show();

                String get_input = items_coti.get(position).serv_valor;

                if (get_input == null || get_input.trim().equals("")) {

                    Toast.makeText(CotizarActivity_5.this, "Item: vacio!", Toast.LENGTH_SHORT).show();

                }else {

                    suma -= Integer.parseInt(keepnum.get(position).toString());
                    txt_total.setText("$" + String.valueOf(suma) + ".-");
                    addItems.remove(position);
                    keepnum.remove(position);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(CotizarActivity_5.this, android.R.layout.simple_list_item_1, addItems);
                    adapter.notifyDataSetChanged();
                    show.setAdapter(adapter);

                }


            }
        });

        btn_gen_cotiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(DetalleActividad.this, CotizarActivity_5.class));
                Toast.makeText(CotizarActivity_5.this, "Generando...", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(CotizarActivity_5.this, GenerarCotiza_6.class);

                //intent.putStringArrayListExtra("val_lista", keepnum);
                intent.putStringArrayListExtra("val_text", addItems);
                intent.putExtra("nom_cliente", nom_cli);
                intent.putExtra("suma_lista", String.valueOf(suma));
                startActivity(intent);

            }
        });
    }

    //++++++++++++++++Boton HOME++++++++++++++++++++++++++++++++++++++++

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }


        return super.onOptionsItemSelected(item);

    }





}
