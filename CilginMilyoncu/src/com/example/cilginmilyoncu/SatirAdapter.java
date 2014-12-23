package com.example.cilginmilyoncu;

import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
	
	public SatirAdapter(Context icerik,List<Urun> urunler)
	{
		myInflater = (LayoutInflater) icerik.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        ImageView imageView =(ImageView) satirView.findViewById(R.id.resim);
        Button btn = (Button)satirView.findViewById(R.id.siteyeGit);
        
        final Urun _urun=myList.get(position);
        
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Yonlendir(_urun.getSite());
			}
		});

        txtAd.setText(_urun.getAd());
        txtFiyat.setText(_urun.getFiyat() + " TL");
       
        imageView.setImageResource(R.drawable.simge1);
        
        String url = _urun.getDosyaYolu();

        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).build();
        		
        //download and display image from url
        imageLoader.displayImage(url, imageView, options);

        return satirView;
	}
	
	
	private void Yonlendir(String str_url) 
	{
		Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
		if(!str_url.contains("http"))
		{
			str_url = "http://" + str_url;
		}
        myWebLink.setData(Uri.parse(str_url));
        MainActivity.icerik.startActivity(myWebLink);
            
    }

}
