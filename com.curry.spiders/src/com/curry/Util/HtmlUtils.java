package com.curry.Util;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class HtmlUtils {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(HtmlUtils.class.getName());
	   /*会存在多个线程同时调用此方法的情况，单例+锁
	    * 
	    */
	private static HtmlUtils htmlUtils = new HtmlUtils();
	private HtmlUtils(){		
	}
	public static HtmlUtils getHtmlUtils(){
		return htmlUtils;
	}
	
	public static synchronized Document getHtml(String url) throws IOException{ //根据url获取html文件
		Connection con= Jsoup.connect(url)
				.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0") // 设置 User-Agent 
				.timeout(30000); // 设置连接超时时间
				/*.ignoreHttpErrors(true)
				.followRedirects(true)*/
//		Document doc = Jsoup.connect(url)
//				.timeout(60000)
//				.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0")
//				.get();
		Connection.Response resp = con.execute();
		Document doc = null; 
		if (resp.statusCode() == 200){ 
		doc = con.get(); 
		}
       return doc;
	}
	
}
