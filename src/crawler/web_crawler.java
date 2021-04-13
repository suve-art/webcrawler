package crawler;

import java.util.ArrayList;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
public class web_crawler 
{
	public static void main(String[] args) 
	{
			String url = "https://www.mobicip.com";
			crawl(1, url, new ArrayList<String>());
	}
	private static void crawl(int depth,String url, ArrayList<String> visited ) 
	{
		if(depth<=2) 
		{
			Document doc = request (url, visited); 
			if (doc!=null) 
			{                  
				for(Element link : doc.select("a[href]")) 
				{
					String next_link = link.absUrl("href"); 
					if (visited.contains(next_link)==false) 
					{
						crawl(depth++,next_link,visited ); 
					}
				}
			}
		}
	}
	private static Document request(String url, ArrayList<String> v) 
	{
		try {
			Connection con = Jsoup.connect(url);
			
			Document doc = con.get();
			
			if(con.response().statusCode()==200) {
				System.out.println("Link: *"+url);
				System.out.print("title:"+ doc.title()+ "\n\n\n"); 
				v.add(url);  
				return doc;
			}
			return null;
		}
		catch(IOException e) { 
			return null;
		}
	}
}
