package adapter_dan_entitas;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.wilom.kamus_biologi.biologi.R;

public class KamusBaseAdapter extends BaseAdapter {
	private static ArrayList<EntitasKamus> searchArrayList;

	private LayoutInflater mInflater;

	Bitmap bm;

	public KamusBaseAdapter(Context context, ArrayList<EntitasKamus> results) {
		searchArrayList = results;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return searchArrayList.size();
	}

	@Override
	public Object getItem(int p) {
		return searchArrayList.get(p);
	}

	@Override
	public long getItemId(int p) {
		return p;
	}

	@Override
	public View getView(int p, View v, ViewGroup parent) {
		ViewHolder holder;

		if (v == null) {
			v = mInflater
					.inflate(R.layout.item_costum_list, null);
			holder = new ViewHolder();
            holder.arti = (TextView) v.findViewById(R.id.outIstilahe);
            holder.istilah = (TextView) v.findViewById(R.id.outArtine);



			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
        holder.arti.setText(searchArrayList.get(p).getArti());
        holder.istilah.setText(searchArrayList.get(p).getIstilah());
		return v;
	}

	static class ViewHolder {
		TextView istilah, arti;

	}

}