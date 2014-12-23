package com.example.cilginmilyoncu;

import java.util.ArrayList;
import java.util.List;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

public class WebServis extends AsyncTask<Void, Void, Void> 
{
	List<Urun> liste_magazalar = new ArrayList<Urun>(); 
	
	private final String NAMESPACE = "http://tempuri.org/";
	private final String URL = "http://www.e-birge.com/milyoncu_web_servis.asmx";
	private final String METOT_MAGAZA_BILGILERINI_GETIR = "VeritabanindanGetir";
	
	String str_tablo_adi;
	ListView listView;
	Context icerik;
	
	public WebServis(String str_tablo_adi, ListView listView, Context icerik)
	{
		this.str_tablo_adi = str_tablo_adi;
		this.listView = listView;
		this.icerik = icerik;
	}
	
	@Override
	protected Void doInBackground(Void... arg0) {
		// TODO Auto-generated method stub
		VerileriAl();
		return null;
	}
	
	@Override
    protected void onPostExecute(Void result) 
    {
    	SatirAdapter adaptor = new SatirAdapter(icerik, liste_magazalar);
    	 
    	listView.setAdapter(adaptor);
    }
	
	
	private void VerileriAl()
	{
		//Create request
        SoapObject request = new SoapObject(NAMESPACE, METOT_MAGAZA_BILGILERINI_GETIR);
        
      //Property which holds input parameters
        PropertyInfo pi_avm_id = new PropertyInfo();
        //Set Name
        pi_avm_id.setName("str_tablo_adi");
        //Set Value
        pi_avm_id.setValue(str_tablo_adi);
        //Set dataType
        pi_avm_id.setType(String.class);
        //Add the property to request object
        request.addProperty(pi_avm_id);
        
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        //Set output SOAP object
        envelope.setOutputSoapObject(request);
        //Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        
        try {
            //Invole web service
            androidHttpTransport.call(NAMESPACE + METOT_MAGAZA_BILGILERINI_GETIR, envelope);
            //Get the response
            SoapObject yanit = (SoapObject) envelope.getResponse();

            int i_sayac = yanit.getPropertyCount();
            
            for(int i=0; i<i_sayac; i++)
            {
            	SoapObject gec_avm_objesi = (SoapObject) yanit.getProperty(i);
            	
            	String str_urun_adi = gec_avm_objesi.getPropertyAsString(0);
            	String str_urun_fiyati = gec_avm_objesi.getPropertyAsString(1);
            	String str_dosya_yolu= gec_avm_objesi.getPropertyAsString(2);
            	String str_link = gec_avm_objesi.getPropertyAsString(3);
            	String str_tarih = gec_avm_objesi.getPropertyAsString(4);
            	
            	
            	Urun gec_urun = new Urun(str_urun_adi, str_urun_fiyati, str_link, str_dosya_yolu, str_tarih);
            	            	
            	liste_magazalar.add(gec_urun);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
	}

}
