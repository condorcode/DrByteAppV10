package cl.appdrbyte.condor_code.drbyteappv10;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ActivosFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_activos, container, false);
        // Inflate the layout for this fragment

        ListView lista_jobs = (ListView) view.findViewById(R.id.activos_ls);


        clientes_datos_list sam = new clientes_datos_list("Samantha Arenas",
                "Dell", "as8374sd9", "ingresado","20/11/2018"  );

        clientes_datos_list juan = new clientes_datos_list("Juanito Chevere",
                "Hp laptop", "dlf456542", "ingresado","03/09/2018"  );

        clientes_datos_list ale = new clientes_datos_list("Alejandra Uganda",
                "Acer netbook", "as289293", "confirmar","15/10/2018"  );


        final ArrayList<clientes_datos_list> clientes_lista = new ArrayList<>();

        clientes_lista.add(sam);
        clientes_lista.add(juan);
        clientes_lista.add(ale);



        ClientesListAdapter adaptador = new ClientesListAdapter(this.getActivity(), R.layout.adapter_list_view, clientes_lista);
        lista_jobs.setAdapter(adaptador);


        lista_jobs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ActivosFragment.this.getActivity(), "Cliente "
                        + clientes_lista.get(position).nom_cli, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ActivosFragment.this.getActivity(), DetalleActividad.class);
                intent.putExtra("nom_cliente", clientes_lista.get(position).nom_cli);
                intent.putExtra("estado_img", clientes_lista.get(position).estado);
                startActivity(intent);

            }
        });



        return view;


    }
}
