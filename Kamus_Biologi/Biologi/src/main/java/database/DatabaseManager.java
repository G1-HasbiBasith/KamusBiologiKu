package database;

import java.sql.SQLException;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;


public class DatabaseManager {

	private static final String ROW_ID = "_id";
	private static final String ROW_ISTILAH = "istilah";
	private static final String ROW_ARTI = "arti";
    //client
    private static final String ID_CLIENT = "id_client";
    private static final String NAMA_CLIENT = "nama_client";
    private static final String PASS_CLIENT = "password_client";


	private static final String NAMA_DB = "kamusbiologi";// nama database
	private static final String NAMA_TABEL = "tb_kamus_biologi";// nama tabel
    private static final String TABEL_CLIENT = "tb_client";
	private static final int DB_VERSION = 1;

	private static final String CREATE_TABLE = "create table " + NAMA_TABEL
			+ " (" + ROW_ID + " integer PRIMARY KEY autoincrement, " + ROW_ISTILAH
			+ " text," + ROW_ARTI + " text)";
    //buat tabel client
    private  static final String CREATE_TABEL_CLIENT = "create table"+TABEL_CLIENT
            + "("+ID_CLIENT+" integer PRIMARY KEY autoincrement, "+NAMA_CLIENT
            +" text, "+PASS_CLIENT+" text)";



	private final Context context;
	private DatabaseOpenHelper dbHelper;
	private SQLiteDatabase db;

	public DatabaseManager(Context ctx) {
		this.context = ctx;
		dbHelper = new DatabaseOpenHelper(ctx);
		db = dbHelper.getWritableDatabase();
	}

	private static class DatabaseOpenHelper extends SQLiteOpenHelper {

		public DatabaseOpenHelper(Context context) {
			super(context, NAMA_DB, null, DB_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(CREATE_TABLE);
			ContentValues values = new ContentValues();
			values.put(ROW_ISTILAH, "Abiotik");
			values.put(ROW_ARTI, "Benda Mati");
			db.insert(NAMA_TABEL, null, values);
			values.put(ROW_ISTILAH, "Abisal");
			values.put(ROW_ARTI, "Pembagian bioma air laut dengan kedalaman daerah lebih dari 2000 meter");
			db.insert(NAMA_TABEL, null, values);
			values.put(ROW_ISTILAH, "Adaptasi");
			values.put(ROW_ARTI, "Penyesuaian diri suatu organisme terhadap lingkungan tempat hidupnya");
			db.insert(NAMA_TABEL, null, values);
            values.put(ROW_ISTILAH, "Aerobik");
            values.put(ROW_ARTI, "Bakteri (dan sel lain) yang melakukan pernafasan dengan menggunakan oksigen bebas");
            db.insert(NAMA_TABEL, null, values);
            values.put(ROW_ISTILAH, "Agen");
            values.put(ROW_ARTI, "Bibit Penyakit");
            db.insert(NAMA_TABEL, null, values);
            values.put(ROW_ISTILAH, "Akinet");
            values.put(ROW_ARTI, "Sel yang mengalami penebalan dinding, " +
                    "ukurannya membesar dan didalamnya terdapat spora (endospora)");
            db.insert(NAMA_TABEL, null, values);

            //dataclient
            db.execSQL(CREATE_TABEL_CLIENT);
            ContentValues values_client =new ContentValues();
            values_client.put(ID_CLIENT, "00000");
            values_client.put(NAMA_CLIENT, "user");
            values_client.put(PASS_CLIENT, "pasword");
            db.insert(TABEL_CLIENT, null, values_client);

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + NAMA_DB);
			onCreate(db);
		}
	}

	public void close() {
		dbHelper.close();
	}
    public void open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
    }

	public void addKamus(String nama, String hobi) {

		ContentValues values = new ContentValues();
		values.put(ROW_ISTILAH, nama);
		values.put(ROW_ARTI, hobi);
		try {
			db.insert(NAMA_TABEL, null, values);
		} catch (Exception e) {
			Log.e("DB ERROR", e.toString());
			e.printStackTrace();
		}
	}

    public ArrayList<ArrayList<Object>> ambilSemuaBarisClient() {//toas

        ArrayList<ArrayList<Object>> dataArray = new ArrayList<ArrayList<Object>>();
        Cursor cur;
        try {
            cur = db.query(TABEL_CLIENT, new String[] { ID_CLIENT, NAMA_CLIENT, PASS_CLIENT },
                    null, null, null, null, null);
            cur.moveToFirst();
            if (!cur.isAfterLast()) {
                do {
                    ArrayList<Object> dataList = new ArrayList<Object>();

                    dataList.add(cur.getString(0));
                    dataList.add(cur.getString(1));

                    dataArray.add(dataList);
                    Log.e("data", cur.getString(0));
                    Log.e("data", cur.getString(1));
                } while (cur.moveToNext());

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("DEBE ERROR", e.toString());
            Toast.makeText(context, "gagal ambil semua baris:" + e.toString(),
                    Toast.LENGTH_SHORT).show();
        }
        return dataArray;
    }
    //end


    public ArrayList<ArrayList<Object>> ambilSemuaBaris() {//toas

		ArrayList<ArrayList<Object>> dataArray = new ArrayList<ArrayList<Object>>();
		Cursor cur;
		try {
			cur = db.query(NAMA_TABEL, new String[] { ROW_ISTILAH, ROW_ARTI },
					null, null, null, null, null);
			cur.moveToFirst();
			if (!cur.isAfterLast()) {
				do {
					ArrayList<Object> dataList = new ArrayList<Object>();

					dataList.add(cur.getString(0));
					dataList.add(cur.getString(1));

					dataArray.add(dataList);
					Log.e("data", cur.getString(0));
					Log.e("data", cur.getString(1));
				} while (cur.moveToNext());

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("DEBE ERROR", e.toString());
			Toast.makeText(context, "gagal ambil semua baris:" + e.toString(),
					Toast.LENGTH_SHORT).show();
		}
		return dataArray;
	}

	public ArrayList<Object> terjemahkan(String in) {//carinya
		ArrayList<Object> arrbaris = new ArrayList<Object>();
		Cursor cursor;
		try {
			cursor = db.rawQuery("Select * from " + NAMA_TABEL + " WHERE "
					+ ROW_ISTILAH + " LIKE '%" + in + "%'", null);
			if (cursor != null) {
				Log.e("error","aaa");
			}else{

				Log.e("error","bbb");
			}
			cursor.moveToFirst();
			if (!cursor.isAfterLast()) {
				do {
					arrbaris.add(cursor.getLong(0));
					arrbaris.add(cursor.getString(1));
					arrbaris.add(cursor.getString(2));
				} while (cursor.moveToNext());
				String r = String.valueOf(arrbaris);
				Toast.makeText(context, "haha" + r, Toast.LENGTH_SHORT).show();
			}
			cursor.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("error", e.toString());
			Toast.makeText(context, "hhii" + e.toString(), Toast.LENGTH_SHORT)
					.show();
		}

		return arrbaris;
	}

    //addClien
    public void addClient(Integer idC, String namaC, String passC){
        ContentValues values_client = new ContentValues();
        values_client.put(ID_CLIENT, idC);
        values_client.put(NAMA_CLIENT, namaC);
        values_client.put(PASS_CLIENT, passC);
        try{
            db.insert(TABEL_CLIENT, null, values_client);
        }catch (Exception e){
            Log.e("DB CLIENT ERROR", e.toString());
            e.printStackTrace();
        }
    } //endaddClient

    //start
    public boolean Register(String username, String password) throws SQLException
    {

        Cursor mCursor = db.rawQuery("insert into " + TABEL_CLIENT + "(nama_client, password_client) values (?,?)", new String[]{username,password});
        if (mCursor != null) {
            if(mCursor.getCount() > 0)
            {
                return true;
            }
        }
        return false;
    }

    public boolean Login(String username, String password) throws SQLException
    {
        Cursor mCursor = db.rawQuery("SELECT * FROM " + TABEL_CLIENT + " WHERE nama_client=? AND password_client=?", new String[]{username,password});
        if (mCursor != null) {
            if(mCursor.getCount() > 0)
            {
                return true;
            }
        }
        return false;
    }
}
