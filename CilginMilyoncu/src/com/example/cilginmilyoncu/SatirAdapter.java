package com.example.cilginmilyoncu;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SatirAdapter extends BaseAdapter {

	private LayoutInflater myInflater;
	private List<Urun> myList;
	
	public SatirAdapter(Activity activity,List<Urun> urunler)
	{
		myInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		myList=urunler;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return myList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return myList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View satirView;
		satirView = myInflater.inflate(R.layout.satir, null);
        TextView txtAd =(TextView) satirView.findViewById(R.id.urunAdi); 
        TextView txtFiyat =(TextView) satirView.findViewById(R.id.urunFiyati); 
        TextView txtSite =(TextView) satirView.findViewById(R.id.siteAdi); 
        ImageView imageView =(ImageView) satirView.findViewById(R.id.resim);
        Button btn=(Button)satirView.findViewById(R.id.siteyeGit);
 
        Urun _urun=myList.get(position);
 
        txtAd.setText(_urun.getAd());
        txtFiyat.setText(_urun.fiyat());
        txtSite.setText(_urun.getSite());
       
        imageView.setImageResource(R.drawable.simge1);

        return satirView;
	}

}
