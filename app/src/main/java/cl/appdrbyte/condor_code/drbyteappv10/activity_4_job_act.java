package cl.appdrbyte.condor_code.drbyteappv10;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_4_job_act extends AppCompatActivity {
    TextView txt_cli_text;
    TextView txt_cli_nom;
    TextView txt_cli_cel;
    TextView txt_cli_correo;
    EditText ed_txt_nom;
    EditText ed_txt_cel;
    EditText ed_txt_correo;

    DatabaseReference database_perfil;

    ImageButton btnpic;
    private static final int CAM_REQUEST=1313;

    CheckBox chk_box;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4_job_act);
        chk_box = (CheckBox) findViewById (R.id.checkBoxCli);
        chk_box.setChecked(false);
        //instancia de la base de datos
        database_perfil = FirebaseDatabase.getInstance().getReference("cliente");

        btnpic = (ImageButton) findViewById(R.id.img_btn_1);

        btnpic.setOnClickListener(new btnTakePhotoClicker());




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_2);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null ){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        txt_cli_text = (TextView) findViewById(R.id.txt_dat_cliente);
        txt_cli_nom = (TextView) findViewById(R.id.txt_nom_cli);
        txt_cli_cel = (TextView) findViewById(R.id.txt_cel_cli);
        txt_cli_correo = (TextView) findViewById(R.id.txt_correo_cli);
        ed_txt_nom = (EditText) findViewById(R.id.txt_ed_nom_cli);
        ed_txt_cel = (EditText) findViewById(R.id.txt_ed_cel_cli);
        ed_txt_correo = (EditText) findViewById(R.id.txt_ed_correo_cli);



        chk_box.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    //Case 1
                    txt_cli_text.setVisibility(View.VISIBLE);
                    txt_cli_nom.setVisibility(View.VISIBLE);
                    txt_cli_cel.setVisibility(View.VISIBLE);
                    txt_cli_correo.setVisibility(View.VISIBLE);
                    ed_txt_nom.setVisibility(View.VISIBLE);
                    ed_txt_cel.setVisibility(View.VISIBLE);
                    ed_txt_correo.setVisibility(View.VISIBLE);

                    chk_box.setChecked(true);


                }
                else {
                    //case 2
                    txt_cli_text.setVisibility(View.GONE);
                    txt_cli_nom.setVisibility(View.GONE);
                    txt_cli_cel.setVisibility(View.GONE);
                    txt_cli_correo.setVisibility(View.GONE);
                    ed_txt_nom.setVisibility(View.GONE);
                    ed_txt_cel.setVisibility(View.GONE);
                    ed_txt_correo.setVisibility(View.GONE);



                }


            }
        });

        Button btn_job = (Button) findViewById(R.id.button_job_1);
        btn_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chk_box.isChecked()) {

                    Button btn_job = (Button) findViewById(R.id.button_job_1);
                    btn_job.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            addProfile();
                        }
                    });

                } else {

                    Button btn_job = (Button) findViewById(R.id.button_job_1);
                    btn_job.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(activity_4_job_act.this, activity_3_drawer_menu.class);
                            startActivity(intent);
                        }
                    });

                }

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAM_REQUEST){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            btnpic.setImageBitmap(bitmap);
        }
    }

    class btnTakePhotoClicker implements  Button.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,CAM_REQUEST);
        }
    }


    private void addProfile() {

        String nameCli = ed_txt_nom.getText().toString().trim();
        String celCli = ed_txt_cel.getText().toString().trim();
        String mailCli = ed_txt_correo.getText().toString().trim();


        if (!TextUtils.isEmpty(nameCli) || !TextUtils.isEmpty(celCli)
                || !TextUtils.isEmpty(mailCli) ) {

            String UId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            String id = database_perfil.push().getKey();

            Cliente cli = new Cliente(UId, nameCli, celCli, mailCli);

            database_perfil.child(id).setValue(cli);

            Toast.makeText(this,"Cliente agregado", Toast.LENGTH_SHORT).show();

            //+++++++++++++++++++++++++++++ AQUI NOS VAMOS A LA 3era ACTITVITY++++++++++++++++++++++
            Intent intent = new Intent(activity_4_job_act.this, activity_3_drawer_menu.class);
            startActivity(intent);


        }else {
            Toast.makeText(this, "Ingrese datos", Toast.LENGTH_SHORT).show();
        }

    }


}
