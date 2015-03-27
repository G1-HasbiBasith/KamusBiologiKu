package edit;

import adapter_dan_entitas.EntitasKamus;
import android.app.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.wilom.kamus_biologi.biologi.R;
import database.DatabaseManager;

import java.util.ArrayList;

/**
 * Created by HP on 18/03/2015.
 */
public class LoginClient extends Activity implements View.OnClickListener {
    Button login;
    Button cancel;
    Button register;
    EditText txtUserC;
    EditText txtPassC;
    DatabaseManager dbC;
    EntitasKamus komponenkamusLihat;
    AlertDialog alert;
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_client_activity);
        dbC = new DatabaseManager(this);
        login=(Button)findViewById(R.id.btnLoginC);
        register=(Button)findViewById(R.id.btnRegisterC);
        cancel=(Button)findViewById(R.id.btnCancelC);
        txtUserC = (EditText)findViewById(R.id.txtUserC);
        txtPassC = (EditText)findViewById(R.id.txtPassC);
        //clicnya
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLoginC :
            loginClient();
                break;
            case R.id.btnRegisterC :
            registerClient();
                break;
            case R.id.btnCancelC :
                break;
        }
    }
    //login
    public void loginClient(){
        String user = txtUserC.getText().toString();
        String pass = txtPassC.getText().toString();
        try{
            if(user.length() > 0 && pass.length() >0)
            {
                DatabaseManager dbUser = new DatabaseManager(LoginClient.this);
                dbUser.open();

                if(dbUser.Login(user, pass))
                {
                    Toast.makeText(LoginClient.this, "Successfully Logged In", Toast.LENGTH_LONG).show();
                    Intent kela = new Intent(LoginClient.this, TambahActivity.class);
                    startActivity(kela);
                }else{
                    AlertDialog.Builder pesan = new AlertDialog.Builder(this);
                    pesan.setMessage("USER : "+ user+ " & PASSWORD : " + pass + " Masih Salah")
                            .setCancelable(false).setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // TODO Auto-generated method stub
                                    txtUserC.setText("");
                                    txtPassC.setText("");
                                }
                            });
                    alert = pesan.create();
                    alert.show();
                }
                dbUser.close();
            }

        }catch(Exception e)
        {
            Toast.makeText(LoginClient.this,e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    //register
    public void registerClient(){
    String user = txtUserC.getText().toString();
    String pass = txtPassC.getText().toString();
    try {
        DatabaseManager dbaUser = new DatabaseManager(LoginClient.this);
        dbaUser.open();
        if(dbaUser.Register(user, pass)){
            Toast.makeText(LoginClient.this,"Create Data", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(LoginClient.this,"Tambah Username/Password", Toast.LENGTH_LONG).show();
        }
        dbaUser.close();
    } catch (Exception e) {
        // TODO: handle exception
        Toast.makeText(LoginClient.this,e.getMessage(), Toast.LENGTH_LONG).show();
    }
    }
}
