package com.wilom.kamus_biologi.biologi;

import adapter_dan_entitas.EntitasKamus;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import database.DatabaseManager;
import edit.LiahatKamus;
import edit.LoginActivity;
import edit.LoginClient;


import java.util.ArrayList;


public class Utama_Kamus extends ActionBarActivity implements View.OnClickListener {

    DatabaseManager dm;
    EditText edtCari;
    ImageButton btnCari;
    ImageButton btnTambah;
    ImageButton btnKeluar;
    ImageButton btnLihatKamus;
    EditText edtHasil;
    ArrayList<EntitasKamus> isikamus = new ArrayList<EntitasKamus>();
    EntitasKamus komponenkamus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama__kamus);
        dm = new DatabaseManager(this);

        // mencocokkan object/komponen sesuai id nya

        edtCari = (EditText) findViewById(R.id.edtCari);
        edtHasil = (EditText) findViewById(R.id.edtHasil);
        btnTambah = (ImageButton) findViewById(R.id.btnTambahAwal);
        btnCari = (ImageButton) findViewById(R.id.btnCariAwal);
        btnKeluar = (ImageButton)findViewById(R.id.btnExitAwal);
        btnLihatKamus = (ImageButton)findViewById(R.id.btnLihatkamus);

        // method supaya button berfungsi
        btnTambah.setOnClickListener(this);
        btnCari.setOnClickListener(this);
        btnKeluar.setOnClickListener(this);
        btnLihatKamus.setOnClickListener(this);

    }
    protected void fungsiterjemah() {
        // TODO Auto-generated method stub
        ArrayList<Object> baris;
        baris = dm.terjemahkan(edtCari.getText().toString());
        if (baris.size() > 0) {
            edtHasil.setText((String) baris.get(2));
            edtCari.setText("");

        } else {
           edtHasil.setText("tidak ditemukan");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_utama__kamus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //metode klick
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnCariAwal:
                fungsiterjemah();
                break;

            case R.id.btnTambahAwal:
                Intent i = new Intent(Utama_Kamus.this, LoginClient.class);
                startActivity(i);
                break;
            case R.id.btnLihatkamus :
                Intent ilihat =new Intent(Utama_Kamus.this, LiahatKamus.class);
                startActivity(ilihat);
                break;
            case R.id.btnExitAwal:
                close();

                break;
        }
    }
    //keluar
    public void close(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Anda Yakin Ingin Keluar?")
                .setCancelable(false).setPositiveButton
                ("Ya", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // TODO Auto-generated method stub
                        Utama_Kamus.this.finish();
                    }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int id) {
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        }).show();
    }
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK){
            close();
            Intent a = new Intent(this,Utama_Kamus.class);
            //a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(a);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }//keluar

    //konfirmasi
    public void konfirmasi(){

    }
}
