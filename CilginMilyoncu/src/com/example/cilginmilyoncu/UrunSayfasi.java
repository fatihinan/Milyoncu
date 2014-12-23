package com.example.cilginmilyoncu;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

public class UrunSayfasi extends Activity{
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.urun_sayfasi);
		
		AdView adView = (AdView) this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        
		String str_tablo_adi = getIntent().getExtras().getString("tablo_adi");
		ListView myList=(ListView)findViewById(R.id.liste2);

		WebServis ayarlari_guncelle = new WebServis(str_tablo_adi, myList, getApplicationContext());
		ayarlari_guncelle.execute();
        
	}
}
