package cl.appdrbyte.condor_code.drbyteappv10;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class activity_3_perfil_creado_validar extends AppCompatActivity {

    //private static final String TAG = "activity_3";


    DatabaseReference ref;
    ListView lista_1;
    ArrayList items_lista;
    //ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3_main_perfil_creado);
        //Log.d(TAG, "onCreate: Started.");

        final String id_profile = getIntent().getStringExtra("id_user");

        setTitle(null);

        Toolbar tool_b = (Toolbar) findViewById(R.id.app_bar_1);
        setSupportActionBar(tool_b);

        lista_1 = (ListView) findViewById(R.id.lista_perfil_1);

        items_lista = new ArrayList<clientes_datos_list>();

        ref = FirebaseDatabase.getInstance().getReference("/perfiles");

        Toast.makeText(activity_3_perfil_creado_validar.this, id_profile, Toast.LENGTH_LONG).show();


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                items_lista.clear();

                //String user_name_1 = dataSnapshot.child(id_profile).child("perfil_nombre").getValue(String.class);
                //String user_cel_1 = dataSnapshot.child(id_profile).child("perfil_cel").getValue(String.class);
                //String user_correo_1 = dataSnapshot.child(id_profile).child("perfil_correo").getValue(String.class);
                //String user_profe_1 = dataSnapshot.child(id_profile).child("perfil_profe").getValue(String.class);


                clientes_datos_list user_name = dataSnapshot.child(id_profile).child("perfil_nombre").getValue(clientes_datos_list.class);
                clientes_datos_list user_cel = dataSnapshot.child(id_profile).child("perfil_nombre").getValue(clientes_datos_list.class);
                clientes_datos_list user_correo = dataSnapshot.child(id_profile).child("perfil_nombre").getValue(clientes_datos_list.class);
                clientes_datos_list user_profe = dataSnapshot.child(id_profile).child("perfil_nombre").getValue(clientes_datos_list.class);


                //clientes_datos_list user_name = new clientes_datos_list("nombre:", user_name_1);
                //clientes_datos_list user_cel = new clientes_datos_list("celular:", user_cel_1);
                //clientes_datos_list user_correo = new clientes_datos_list("correo:", user_correo_1);
                //clientes_datos_list user_profe = new clientes_datos_list("profesión:", user_profe_1);


                items_lista.add(user_name);
                items_lista.add(user_cel);
                items_lista.add(user_correo);
                items_lista.add(user_profe);

                //adapter = new ArrayAdapter<>(activity_3_perfil_creado_validar.this, android.R.layout.simple_list_item_1, items_lista);
                //lista_1.setAdapter(adapter);

                ClientesListAdapter adapter = new ClientesListAdapter(activity_3_perfil_creado_validar.this, R.layout.adapter_list_view, items_lista);
                lista_1.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(activity_3_perfil_creado_validar.this, "Error de conexion...", Toast.LENGTH_SHORT).show();

            }
        });








    }
}
