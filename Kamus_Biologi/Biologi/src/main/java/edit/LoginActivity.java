package edit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.wilom.kamus_biologi.biologi.R;

/**
 * Created by HP on 12/03/2015.
 */
public class LoginActivity extends ActionBarActivity implements View.OnClickListener{
    EditText edtUser;
    EditText edtPass;
    Button btnLogin;
    Button btnCancel;
    AlertDialog alert;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_tambah_activity);
        edtUser = (EditText)findViewById(R.id.edtUser);
        edtPass = (EditText)findViewById(R.id.edtPass);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnCancel = (Button)findViewById(R.id.btnCancel);

        btnLogin.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.btnLogin :
            login();
            break;
        case R.id.btnCancel :
            finish();
            break;
    }
    }
    //meodenya
    public  void login(){
        String userQ,passQ;
        String user = edtUser.getText().toString();
        String pass = edtPass.getText().toString();
        userQ="wilom";
        passQ="sukarni";
        if(user.equals(userQ)&&pass.equals(passQ)){
            Intent intent=new Intent(LoginActivity.this,TambahActivity.class);
            startActivity(intent);
        }else{
            AlertDialog.Builder pesan = new AlertDialog.Builder(this);
            pesan.setMessage("USER : "+ user+ " & PASSWORD : " + pass + " Masih Salah")
            .setCancelable(false).setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            edtUser.setText("");
                            edtPass.setText("");
                        }
                    });
            alert = pesan.create();
            alert.show();
        }
    }
}
