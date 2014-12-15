package com.example.cilginmilyoncu;

public class Urun {
	private String urunAdi;
	private String fiyat;
	private String siteAdi;
	
	public Urun()
	{
		super();
	}
	public Urun (String _ad,String _fiyat,String _site)
	{
		super();
		this.urunAdi=_ad;
		this.fiyat=_fiyat;
		this.siteAdi=_site;
	}
	public String getAd()
	{
		return urunAdi;
	}
	public void setAd(String ad)
	{
		this.urunAdi=ad;
	}
	public String fiyat()
	{
		return fiyat;
	}
	public void setFiyat(String fiyat)
	{
		this.fiyat=fiyat;
	}
	public String getSite()
	{
		return siteAdi;
	}
	public void setSite(String site)
	{
		this.siteAdi=site;
	}
}
