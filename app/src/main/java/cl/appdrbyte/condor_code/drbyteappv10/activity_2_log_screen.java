package cl.appdrbyte.condor_code.drbyteappv10;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class activity_2_log_screen extends AppCompatActivity {
    String mId;
    EditText mNombre;
    EditText mApellido;
    EditText mCelu;
    EditText mCorreo;
    EditText mClave;
    EditText mClaveConf;
    String mImgPath;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2_log_screen);

        mAuth = FirebaseAuth.getInstance();

        mId = "";
        mNombre  = (EditText) findViewById(R.id.ed_nombre);
        mApellido = (EditText) findViewById(R.id.ed_apellido);
        mCelu = (EditText) findViewById(R.id.ed_celular);
        mCorreo = (EditText) findViewById(R.id.ed_correo);
        mClave = (EditText) findViewById(R.id.ed_clave);
        mClaveConf = (EditText) findViewById(R.id.ed_clave_conf);
        mImgPath = "";








        btn_next();
    }

    public void btn_next() {

        Button boton_next_1 = (Button) findViewById(R.id.btn_crear_perf);
        boton_next_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mCorreo.getText().toString().trim();
                String clave = mClave.getText().toString().trim();

                final String nombre = mNombre.getText().toString().trim();
                final String apellido = mApellido.getText().toString().trim();
                final String celu = mCelu.getText().toString().trim();
                final String correo = mCorreo.getText().toString().trim();
                mAuth.createUserWithEmailAndPassword(email, clave)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    //we will store the aditional files in Firebase db
                                    Usuario user = new Usuario("", nombre, apellido, celu, correo, "");
                                    FirebaseDatabase.getInstance().getReference("perfiles")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText(activity_2_log_screen.this, "Dato registrado!",
                                                        Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(activity_2_log_screen.this, "Error al registrar...",
                                                        Toast.LENGTH_SHORT).show();

                                            }
                                        }
                                    });


                                }else {
                                    Toast.makeText(activity_2_log_screen.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }


        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() != null) {
            //handle all ready logged in user

        }
    }
}
