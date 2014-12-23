package com.example.cilginmilyoncu;

public class Urun {
	private String urunAdi;
	private String fiyat;
	private String siteAdi;
	private String dosyaYolu;
	private String tarih;
	
	public Urun()
	{
		super();
	}
	public Urun (String _ad,String _fiyat,String _site, String _dosyaYolu, String _tarih)
	{
		super();
		this.urunAdi=_ad;
		this.fiyat=_fiyat;
		this.siteAdi=_site;
		this.dosyaYolu = _dosyaYolu;
		this.tarih = _tarih;
	}
	public String getAd()
	{
		return urunAdi;
	}
	public String getDosyaYolu()
	{
		return dosyaYolu;
	}
	public void setAd(String ad)
	{
		this.urunAdi=ad;
	}
	public String getFiyat()
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
