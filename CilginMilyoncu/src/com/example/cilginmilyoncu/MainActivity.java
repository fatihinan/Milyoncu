package com.example.cilginmilyoncu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class MainActivity extends Activity {

	private String[] menu={"1 TL'lik Ürünler", "1-5 TL Arası Ürünler","5-10 TL Arası Ürünler","10-20 TL Arası Ürünler"};
	
	public static Context icerik;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// UNIVERSAL IMAGE LOADER SETUP
				DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
						.cacheOnDisc(true).cacheInMemory(true)
						.imageScaleType(ImageScaleType.EXACTLY)
						.displayer(new FadeInBitmapDisplayer(300)).build();

				ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
						getApplicationContext())
						.defaultDisplayImageOptions(defaultOptions)
						.memoryCache(new WeakMemoryCache())
						.discCacheSize(100 * 1024 * 1024).build();

				ImageLoader.getInstance().init(config);
				// END - UNIVERSAL IMAGE LOADER SETUP
				
				AdView adView = (AdView) this.findViewById(R.id.adView);
		        AdRequest adRequest = new AdRequest.Builder().build();
		        adView.loadAd(adRequest);

		
		icerik = MainActivity.this;

		ListView listele=(ListView) findViewById(R.id.liste);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, menu);
		listele.setAdapter(adapter);
		
		listele.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String str_tablo_adi = null;
				if(position==0)
				{
					str_tablo_adi = "bir";
				}
				else if(position==1)
				{
					str_tablo_adi = "bir_bes";
				}
				else if(position==2)
				{
					str_tablo_adi = "bes_on";
				}
				else if(position==3)
				{
					str_tablo_adi = "on_yirmi";
				}
				
				Intent myIntent=new Intent(MainActivity.this,UrunSayfasi.class);
				myIntent.putExtra("tablo_adi", str_tablo_adi);
				startActivity(myIntent);
			}
		});
		
	}

}
