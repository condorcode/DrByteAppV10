package cl.appdrbyte.condor_code.drbyteappv10;



import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ClientesListAdapter extends ArrayAdapter<clientes_datos_list> {

    //private static final String TAG = "ClientesListAdapter";
    private Context mContext;
    int mResource;

    public ClientesListAdapter(Context context, int resource, ArrayList<clientes_datos_list> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String nom_cliente = getItem(position).getNom_cli();
        String nom_equipo = getItem(position).getNom_equipo();
        String num_serie = getItem(position).getNum_serie();
        String estado = getItem(position).getEstado();
        String fecha = getItem(position).getFecha();

        clientes_datos_list user = new clientes_datos_list(nom_cliente, nom_equipo, num_serie, estado, fecha );

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView nom_cli = (TextView) convertView.findViewById(R.id.lv_txtv_nom_cli);
        TextView nombre_equipo = (TextView) convertView.findViewById(R.id.lv_txtv_nom_equip);
        TextView numero_serie = (TextView) convertView.findViewById(R.id.lv_txtv_num_serie);
        TextView estado_job = (TextView) convertView.findViewById(R.id.lv_txt_estado);
        TextView fecha_ing = (TextView) convertView.findViewById(R.id.lv_txt_fecha);


        if (estado == "confirmar"){

            estado_job.setBackgroundResource(R.drawable.alert_cian);

        } else {

            estado_job.setBackgroundResource(R.drawable.alert_amarillo);
        }

        nom_cli.setText(nom_cliente);
        nombre_equipo.setText(nom_equipo);
        numero_serie.setText(num_serie);
        estado_job.setText(estado);
        fecha_ing.setText(fecha);



        return convertView;

    }
}
