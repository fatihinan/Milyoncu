package com.example.cilginmilyoncu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {

	private String[] menu={"1 TL'lik Ürünler", "1-5 TL Arasý Ürünler","5-10 TL Arasý Ürünler","10-20 TL Arasý Ürünler"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView listele=(ListView) findViewById(R.id.liste);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, menu);
		listele.setAdapter(adapter);
		
		listele.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent myIntent=new Intent(MainActivity.this,UrunSayfasi.class);
				startActivity(myIntent);
			}
		});
		
	}

}
