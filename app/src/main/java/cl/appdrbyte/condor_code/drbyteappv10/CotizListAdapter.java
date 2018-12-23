package cl.appdrbyte.condor_code.drbyteappv10;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class CotizListAdapter extends ArrayAdapter<CotizaDatos> {



    //private static final String TAG = "ClientesListAdapter";
    private Context mContext;
    int mResource;


    public CotizListAdapter(Context context, int resource, ArrayList<CotizaDatos> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String item_cot = getItem(position).getServ_item();
        String valor_cot = getItem(position).getServ_valor();


        CotizaDatos user = new CotizaDatos(item_cot, valor_cot );

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView val_serv = (TextView) convertView.findViewById(R.id.txt_valor_num);
        TextView item_val = (TextView) convertView.findViewById(R.id.txt_valor_num_2);

        /*
        TextView numero_serie = (TextView) convertView.findViewById(R.id.lv_txtv_num_serie);
        TextView estado_job = (TextView) convertView.findViewById(R.id.lv_txt_estado);
        TextView fecha_ing = (TextView) convertView.findViewById(R.id.lv_txt_fecha);*/



        val_serv.setText(valor_cot);
        item_val.setText(item_cot);

/*
        nombre_equipo.setText(nom_equipo);
        numero_serie.setText(num_serie);
        estado_job.setText(estado);
        fecha_ing.setText(fecha);


*/
        return convertView;

    }
}