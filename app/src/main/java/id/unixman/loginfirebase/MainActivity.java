package id.unixman.loginfirebase;

import android.app.ProgressDialog;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegister;
    private EditText edtEmail;
    private EditText edtPassword;
    private TextView textLogin;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        btnRegister = (Button) findViewById(R.id.btRegister);
        edtEmail = (EditText) findViewById(R.id.edEmail);
        edtPassword = (EditText) findViewById(R.id.edPassword);
        textLogin = (TextView) findViewById(R.id.txtLogin);

        btnRegister.setOnClickListener(this);
        textLogin.setOnClickListener(this);

    }

    private void registerUser(){
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Ent er password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering User ....");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //user sukses register
                         Toast.makeText(MainActivity.this, "Register Berhasil", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }else{
                            Toast.makeText(MainActivity.this, "Register Gagal.. coba lagi....", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        if(v == btnRegister){
            registerUser();
        }
        if(v == textLogin){
            //methosd login
        }
    }
}
