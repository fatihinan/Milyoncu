using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Net;
using System.Xml;
using System.Xml.Linq;
using HtmlAgilityPack;
using System.Text;
using MySql.Data.MySqlClient;
using System.Data;
using System.Text.RegularExpressions;

/// <summary>
/// Summary description for milyoncu_web_servis
/// </summary>
[WebService(Namespace = "http://tempuri.org/")]
[WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
// To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
// [System.Web.Script.Services.ScriptService]
public class milyoncu_web_servis : System.Web.Services.WebService {

    IM_MySQLIslemleri my_sql_islemleri = new IM_MySQLIslemleri();

    public milyoncu_web_servis () {

        //Uncomment the following line if using designed components 
        //InitializeComponent(); 
    }

    [WebMethod]
    public string HelloWorld() 
    {
        
        return "Hello World";
    }

    [WebMethod]
    public void VeritabaninaYaz()
    {
        my_sql_islemleri.IM_MySQLBaglatiAc("Server=94.73.151.238;Database=milyoncu;Uid=hikmetyucel.net;Pwd=Odtu1997; pooling=false;");

        my_sql_islemleri.IM_MySSQLSorguCalistir("DELETE FROM bir");
        my_sql_islemleri.IM_MySSQLSorguCalistir("ALTER TABLE bir AUTO_INCREMENT=1;");
        my_sql_islemleri.IM_MySSQLSorguCalistir("DELETE FROM bir_bes");
        my_sql_islemleri.IM_MySSQLSorguCalistir("ALTER TABLE bir_bes AUTO_INCREMENT=1;");
        my_sql_islemleri.IM_MySSQLSorguCalistir("DELETE FROM bes_on");
        my_sql_islemleri.IM_MySSQLSorguCalistir("ALTER TABLE bes_on AUTO_INCREMENT=1;");
        my_sql_islemleri.IM_MySSQLSorguCalistir("DELETE FROM on_yirmi");
        my_sql_islemleri.IM_MySSQLSorguCalistir("ALTER TABLE on_yirmi AUTO_INCREMENT=1;");

        int i_offset = 0;

        for (int i = 0; i < 20; i++)
        {
            try
            {
                VerileriYaz("http://www.sahibinden.com/bilgisayar-aksesuarlar?pagingSize=50&sorting=price_asc&pagingOffset=" + i_offset.ToString());
                i_offset += 50;
            }
            catch (Exception e)
            {
                //break;
            }
        }
        
        my_sql_islemleri.IM_MySQLBaglantiKapat();
    }


    public string VerileriYaz(string str_url)
    {
        WebClient client = new WebClient();

        client.Encoding = System.Text.Encoding.UTF8;

        Uri url = new Uri(str_url);

        string html = client.DownloadString(url);

        HtmlAgilityPack.HtmlDocument dokuman = new HtmlAgilityPack.HtmlDocument();

        dokuman.LoadHtml(html);

        HtmlNodeCollection linkler = dokuman.DocumentNode.SelectNodes("//td[@class='searchResultsSmallThumbnail']");
        HtmlNodeCollection urun_adi = dokuman.DocumentNode.SelectNodes("//td[@class='searchResultsTitleValue']");
        HtmlNodeCollection urun_fiyati = dokuman.DocumentNode.SelectNodes("//td[@class='searchResultsPriceValue']");
        HtmlNodeCollection ilan_tarihi = dokuman.DocumentNode.SelectNodes("//td[@class='searchResultsDateValue']");

        string str_link = "";
        string str_dosya_yolu = "";
        string str_urun_adi = "";
        string str_urun_fiyati = "";
        string str_ilan_tarihi = "";
        string[] str_double_fiyat;
        double d_fiyat;
        string str_tablo_ismi = "";
        bool b_yirmiden_kucuk = false;


        for (int i = 0; i < linkler.Count; i++)
        {
            //link
            str_link = "www.sahibinden.com" + linkler[i].ChildNodes[1].Attributes["href"].Value;

            //fotoğraf yolu
            str_dosya_yolu = linkler[i].ChildNodes[1].ChildNodes[1].Attributes["src"].Value;

            //ürün adı
            str_urun_adi = urun_adi[i].ChildNodes[1].InnerText;
            str_urun_adi = str_urun_adi.Replace("&#039;", "'");
            str_urun_adi = str_urun_adi.Replace("&quot;", "\"");
            str_urun_adi = str_urun_adi.Replace("'", "");

            //ürün fiyatı
            str_urun_fiyati = urun_fiyati[i].InnerText;

            //ilan tarihi
            str_ilan_tarihi = ilan_tarihi[i].InnerText;
            str_ilan_tarihi = str_ilan_tarihi.Replace("&#039;", "'");
            str_ilan_tarihi = str_ilan_tarihi.Replace("&quot;", "\"");
            str_ilan_tarihi = str_ilan_tarihi.Replace("'", "");

            b_yirmiden_kucuk = true;

            if (str_urun_fiyati.Contains("TL"))
            {
                str_double_fiyat = Regex.Split(str_urun_fiyati, "TL");
                d_fiyat = Convert.ToDouble(str_double_fiyat[0]);

                if (d_fiyat <= 1.0)
                {
                    str_tablo_ismi = "bir";
                }
                else if (d_fiyat > 1.0 && d_fiyat<=5.00)
                {
                    str_tablo_ismi = "bir_bes";
                }
                else if (d_fiyat > 5.0 && d_fiyat <= 10.00)
                {
                    str_tablo_ismi = "bes_on";
                }
                else if (d_fiyat > 10.0 && d_fiyat <= 20.00)
                {
                    str_tablo_ismi = "on_yirmi";
                }
                else
                {
                    b_yirmiden_kucuk = false;
                }

                if (b_yirmiden_kucuk)
                {
                    string str_sorgu = "INSERT INTO " + str_tablo_ismi + " (urun_adi, urun_fiyati, dosya_yolu, link, tarih) VALUES ('" + str_urun_adi + "'," + d_fiyat.ToString().Replace(",",".") +",'" + str_dosya_yolu + "','" + str_link + "','" + str_ilan_tarihi + "')";
                    my_sql_islemleri.IM_MySSQLSorguCalistir(str_sorgu);
                }
            }
        }
        

        return html;
    }


    protected bool CheckUrlExists(string url)
    {
        // If the url does not contain Http. Add it.
        if (!url.Contains("http://"))
        {
            url = "http://" + url;
        }
        try
        {
            var request = WebRequest.Create(url) as HttpWebRequest;
            request.Method = "HEAD";
            using (var response = (HttpWebResponse)request.GetResponse())
            {
                return response.StatusCode == HttpStatusCode.OK;
            }
        }
        catch
        {
            return false;
        }
    }
    
}
