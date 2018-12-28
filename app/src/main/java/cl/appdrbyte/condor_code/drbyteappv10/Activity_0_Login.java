package cl.appdrbyte.condor_code.drbyteappv10;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class Activity_0_Login extends AppCompatActivity {
    TextView btn_txt;
    EditText mPassWd;
    EditText mCorreo;
    Button btn_login;

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_0__login);

        mAuth = FirebaseAuth.getInstance();

        btn_txt = (TextView) findViewById(R.id.btn_crear_user);
        btn_login = (Button) findViewById(R.id.btn_login);


        mCorreo = (EditText) findViewById(R.id.log_mail);
        mPassWd = (EditText) findViewById(R.id.log_clave);

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() != null){
                    startActivity(new Intent(Activity_0_Login.this, activity_3_drawer_menu.class));

                }

            }
        };

        btn_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_0_Login.this, activity_1_log_in.class));

            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startSignIn();
            }
        });




    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    private void startSignIn(){


        String mail = mCorreo.getText().toString();
        String passWd = mPassWd.getText().toString();

        if (TextUtils.isEmpty(mail) || TextUtils.isEmpty(passWd)){
            Toast.makeText(Activity_0_Login.this, "Ingrese datos...", Toast.LENGTH_SHORT).show();

        } else {

            mAuth.signInWithEmailAndPassword(mail, passWd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Intent i = new Intent(Activity_0_Login.this, ActivityCrash.class);
                        startActivity(i);
                        finish();
                    }
                }
            });
        }

    }
}
