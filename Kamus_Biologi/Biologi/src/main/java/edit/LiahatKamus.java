package edit;

import adapter_dan_entitas.EntitasKamus;
import adapter_dan_entitas.KamusBaseAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.wilom.kamus_biologi.biologi.R;
import database.DatabaseManager;

import java.util.ArrayList;

/**
 * Created by HP on 12/03/2015.
 */
public class LiahatKamus extends Activity implements View.OnClickListener {
    DatabaseManager dm;
    ListView lvLihat;
    EntitasKamus komponenkamusLihat;
    Button kembali;

    ArrayList<EntitasKamus> isikamusLihat = new ArrayList<EntitasKamus>();
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.lihat_kaus_activity);
        dm = new DatabaseManager(this);
        lvLihat = (ListView)findViewById(R.id.listLiahatKamus);
        kembali = (Button)findViewById(R.id.btnKembaliLihat);
        kembali.setOnClickListener(this);

        tampilKamus();

    }

    @Override
    public void onClick(View v) {

    switch (v.getId()){
        case R.id.btnKembaliLihat :
            finish();
            break;
    }
    }

    private void tampilKamus() {
        // TODO Auto-generated method stub
        isikamusLihat.clear();
        ArrayList<ArrayList<Object>> data = dm.ambilSemuaBaris();//
        for (int p = 0; p < data.size(); p++) {
            komponenkamusLihat = new EntitasKamus();
            ArrayList<Object> baris = data.get(p);
            Log.e("baris", baris.get(0).toString());
            Log.e("baris", baris.get(1).toString());
            komponenkamusLihat.setArti(baris.get(0).toString());
            komponenkamusLihat.setIstilah(baris.get(1).toString());
            isikamusLihat.add(komponenkamusLihat);
        }
        KamusBaseAdapter datakamus = new KamusBaseAdapter(LiahatKamus.this,
                isikamusLihat);
        lvLihat.setAdapter(datakamus);
    }
}
