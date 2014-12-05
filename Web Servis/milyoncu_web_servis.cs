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
    public string HelloWorld() {
        
        return "Hello World";
    }

    [WebMethod]
    public void VeritabaninaYaz()
    {
        my_sql_islemleri.IM_MySQLBaglatiAc("Server=94.73.151.238;Database=milyoncu;Uid=hikmetyucel.net;Pwd=Odtu1997; pooling=false;");

        VerileriYaz("http://www.sahibinden.com/bilgisayar-aksesuarlar?pagingSize=50&sorting=price_asc");

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
        for (int i = 0; i < linkler.Count; i++)
        {
            str_link = "www.sahibinden.com" + linkler[i].ChildNodes[1].Attributes["href"].Value;
            str_dosya_yolu = linkler[i].ChildNodes[1].ChildNodes[1].Attributes["src"].Value;
            str_urun_adi = urun_adi[i].ChildNodes[1].InnerText;
            str_urun_fiyati = urun_fiyati[i].InnerText;
            str_ilan_tarihi = ilan_tarihi[i].InnerText;

            if (str_urun_fiyati.Contains("TL"))
            {
                str_double_fiyat = Regex.Split(str_urun_fiyati, "TL");
                d_fiyat = Convert.ToDouble(str_double_fiyat[0]);
            }
        }
        

        return html;
    }



    
}
