package edit;

import java.util.ArrayList;

import adapter_dan_entitas.EntitasKamus;
import adapter_dan_entitas.KamusBaseAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import com.wilom.kamus_biologi.biologi.R;
import com.wilom.kamus_biologi.biologi.Utama_Kamus;
import database.DatabaseManager;

public class TambahActivity extends Activity implements OnClickListener {
	EditText inIstilah, inArti;
	ImageButton bSimpan;
    ImageButton bBack;
    ImageButton bDelete;
	ListView lv;
	DatabaseManager dm;
	EntitasKamus komponenkamus;
	ArrayList<EntitasKamus> isikamus = new ArrayList<EntitasKamus>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tambah_activity);
		dm = new DatabaseManager(this);
		inIstilah = (EditText) findViewById(R.id.addIstilah);
		inArti = (EditText) findViewById(R.id.addArti);
		bSimpan = (ImageButton) findViewById(R.id.btnSimpanTambah);
        bBack = (ImageButton) findViewById(R.id.btnBackTambah);
        bDelete = (ImageButton) findViewById(R.id.btnDeleteTambah);
		lv = (ListView) findViewById(R.id.listDataKamus);
		bSimpan.setOnClickListener(this);
        bBack.setOnClickListener(this);
		tampilKamus();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
        switch (v.getId()){
         case R.id.btnSimpanTambah :
             String istilah = inIstilah.getText().toString();
             String arti = inArti.getText().toString();
             dm.addKamus(istilah, arti);
             tampilKamus();
             inIstilah.setText("");
             inArti.setText("");
             break;
         case R.id.btnBackTambah :
             //finish();
             Intent i = new Intent(TambahActivity.this, Utama_Kamus.class);
             i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
             startActivity(i);
             break;
         case R.id.btnDeleteTambah :

             break;
        }
	}

	private void tampilKamus() {
		// TODO Auto-generated method stub
		isikamus.clear();
		ArrayList<ArrayList<Object>> data = dm.ambilSemuaBaris();//
		for (int p = 0; p < data.size(); p++) {
			komponenkamus = new EntitasKamus();
			ArrayList<Object> baris = data.get(p);
			Log.e("baris", baris.get(0).toString());
			Log.e("baris", baris.get(1).toString());
			komponenkamus.setArti(baris.get(0).toString());
			komponenkamus.setIstilah(baris.get(1).toString());
			isikamus.add(komponenkamus);
		}
		KamusBaseAdapter datakamus = new KamusBaseAdapter(TambahActivity.this,
				isikamus);
		lv.setAdapter(datakamus);
	}
}
