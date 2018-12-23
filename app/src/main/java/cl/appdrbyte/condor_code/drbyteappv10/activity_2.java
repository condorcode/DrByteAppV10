package cl.appdrbyte.condor_code.drbyteappv10;

import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class activity_2 extends AppCompatActivity {

    EditText nombre;
    EditText celular;
    EditText correo;
    EditText profesión;
    Button guardar_perfil;
    private int Gallery_intent = 2;
    ImageButton foto_perfil;

    StorageReference img_path;

    String old_path = "";

    StorageReference photoRef;
    StorageReference storage_ref;

    DatabaseReference database_perfil;

    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2_main);

        setTitle(null);

        Toolbar tool_b = (Toolbar) findViewById(R.id.app_bar_1);
        setSupportActionBar(tool_b);

        database_perfil = FirebaseDatabase.getInstance().getReference("perfiles");

        nombre = (EditText) findViewById(R.id.edit_nombre);
        celular = (EditText) findViewById(R.id.edit_celular);
        correo = (EditText) findViewById(R.id.edit_correo);
        profesión = (EditText) findViewById(R.id.edit_profe);

        foto_perfil = (ImageButton) findViewById(R.id.boton_foto_perfil);
        guardar_perfil = (Button) findViewById(R.id.button_guardar);

        storage_ref = FirebaseStorage.getInstance().getReference();
        photoRef = FirebaseStorage.getInstance().getReference();

        guardar_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //************Agrgar Usuario*****************
                if (!flag) {
                    addProfile();
                }else {
                    Toast.makeText(activity_2.this, "Falta foto...", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void addProfile() {
        String name = nombre.getText().toString().trim();
        String cel = celular.getText().toString().trim();
        String mail = correo.getText().toString().trim();
        String profession = profesión.getText().toString().trim();
        String foto_path = img_path.getPath().toString().trim();

        if (!TextUtils.isEmpty(name) || !TextUtils.isEmpty(cel) || !TextUtils.isEmpty(mail)
                || !TextUtils.isEmpty(profession) ) {

            String id = database_perfil.push().getKey();

            perfil_user perfil_new = new perfil_user(id, name, cel, mail, profession, foto_path);

            database_perfil.child(id).setValue(perfil_new);

            Toast.makeText(this,"Perfil agregado", Toast.LENGTH_SHORT).show();

            //+++++++++++++++++++++++++++++ AQUI NOS VAMOS A LA 3era ACTITVITY++++++++++++++++++++++
            Intent intent = new Intent(this, activity_3_perfil_creado_validar.class);
            intent.putExtra("id_user", id);
            startActivity(intent);


        }else {
            Toast.makeText(this, "Ingrese datos", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnFotoPerfil(View view) {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), Gallery_intent);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == Gallery_intent && resultCode == RESULT_OK && null != data) {
            try{
                final Uri uriPath = data.getData();
                final InputStream inputStream = getContentResolver().openInputStream(uriPath);
                final Bitmap imageMap = BitmapFactory.decodeStream(inputStream);

                //compresor de imagenes
                int nh = (int) ( imageMap.getHeight() * (512.0 / imageMap.getWidth()) );
                Bitmap scaled = Bitmap.createScaledBitmap(imageMap, 512, nh, true);
                // insertar foto
                foto_perfil.setImageBitmap(scaled);

                if (flag) {

                    img_path = storage_ref.child("perfiles").child(uriPath.getLastPathSegment());
                    img_path.putFile(uriPath);
                    flag = false;
                    old_path = img_path.getPath().toString().trim();
                    Toast.makeText(this,"subiendo foto...", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(this,"Borrando foto anterior...", Toast.LENGTH_LONG).show();

                    // Create a reference to the file to delete
                    StorageReference desertRef = photoRef.child(old_path);

                    // Delete the file
                    desertRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // File deleted successfully


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Uh-oh, an error occurred!
                        }
                    });

                    img_path = storage_ref.child("perfiles").child(uriPath.getLastPathSegment());
                    img_path.putFile(uriPath);
                    old_path = img_path.getPath().toString().trim();


                }



            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Image was not found", Toast.LENGTH_SHORT).show();
            }

        }




       /* if (requestCode == Gallery_intent && requestCode == RESULT_OK){

            //Uri image_uri = data.getData();
            //foto_perfil.setImageURI(image_uri);

        }*/

    }



    /*/función volver a pantalla inicio------------------------------------------
    public void configurarBoton_volver(){

        Button boton_volver_1 = (Button) findViewById(R.id.buttonVolver_2);
        boton_volver_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }*/

}
