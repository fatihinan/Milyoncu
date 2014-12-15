package com.example.cilginmilyoncu;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class UrunSayfasi extends Activity{
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.urun_sayfasi);
		
		final ListView myList=(ListView)findViewById(R.id.liste2);
		
		final List<Urun> urunler=new ArrayList<Urun>();
		urunler.add(new Urun("Ürün 1","10 TL","www.hizlial.com"));
        
        SatirAdapter adapter=new SatirAdapter(this,urunler);
        myList.setAdapter(adapter);
        
	}
}
