package com.curry.pojo;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.curry.Util.HtmlUtils;

public class Page {
	//private int index;  //页码
	private BookList bList;//本页内所有的book
	private Page nextPage;  //下一页
	private Document doc;  //本页的html
	private String url;    //本页的地址
	Page(){
	}
	public Page(Document doc) {
		this.doc = doc;
	}
	public boolean hasNextPage(){  //判断并设置下一页
		Elements paginator = doc.getElementsByClass("paginator");
		bList =  new BookList();
		Element nextPageE = null;
		for(Element element : paginator){
			Elements thispage = element.getElementsByClass("thispage");
			for (Element sss : thispage) {
				nextPageE = sss.nextElementSibling();
				//System.out.println(sss.nextElementSibling().text());		
			}
		}
		if(nextPageE!=null && nextPageE.hasAttr("href")){
			String nextUrl = nextPageE.attr("href");
			System.out.println("nextUrl:"+nextUrl);
			String nurl = "https://book.douban.com"+nextUrl;//get next page url
			System.out.println("nurl:"+nurl);
			try {
				nextPage = new Page(HtmlUtils.getHtml(nurl));
				nextPage.setUrl(nurl);
			} catch (IOException e) {
				// 打印异常
				e.printStackTrace();
			}
			//System.out.println(nextPage.attr("href"));//.nextElementSibling().text());
			return true;
		}else{
				//logger.debug("");
			    System.out.println("donot have next page,should be the end of the pages !");
				return false;
		}
		
	}

	public Page getNextPage() {
		return nextPage;
	}
	//判断有页码之后，设置下一个页码
	//	public void setNextPage() {
	//		this.nextPage = nextPage;//
	//	}

	//	public int getIndex() {
	//		return index;
	//	}
	//	public void setIndex(int index) {
	//		this.index = index;
	//	}

	public BookList getbList() {
		if(bList==null) return null;
		return bList;
	}
	public void setbList() {//根据doc解析获得blistt
		if(doc != null){
			Elements subjectItem = doc.getElementsByClass("subject-item");
			if(bList == null) bList =  new BookList();
			System.out.println(subjectItem.size());
			for (Element element : subjectItem) {
				
				Elements info = element.getElementsByClass("info");
			
				//System.out.println(info.size());
				for (Element infoItem : info) {
					Elements rating_nums = infoItem.getElementsByClass("rating_nums");
					Elements pl = infoItem.getElementsByClass("pl");
					Elements a = infoItem.getElementsByTag("a");
					StringBuffer spl = new StringBuffer(pl.text()); 
					int plnum = getPL(spl);
					//System.out.println(plnum);
					if(rating_nums.text() == null || plnum == 0){
						//不对没有评价和评论的书进行统计
					}else{
						Book book = new Book();
						book.setCommonLerver(rating_nums.text());
						book.setCommonSize(plnum);
						book.setTitle(a.text());
						book.setUrl("https://book.douban.com"+a.attr("href"));
						System.out.println(book.toString());
						bList.addBook(book);
					}
				}
			}
			//logger.debug("bList"+bList.size());
		}else{
			System.out.println("doc is null!");
		}
	}
	public Document getDoc() {
		return doc;
	}
	public void setDoc(Document doc) {
		this.doc = doc;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
    private int getPL(StringBuffer sb){//(274人评价)
    	if(sb == null){
    		System.out.println("0");
    		return  0;		
    	}else if(sb.indexOf("少于10")>0){
    		System.out.println("少于10");
    		return 0;
    	}else if(sb.indexOf("目前无")>0){
    		System.out.println("目前无");
    		return 0;
    	}else if(sb.indexOf("人评价")>0){
    		int end = sb.indexOf("人评价)");
//    		if(end <11){
//    			System.out.println("<11");
//    			return 0;
//    		}
    		String tmp = sb.substring(1, end);
    		System.out.println("tmp"+tmp);
    		return Integer.valueOf(tmp);
    	}else{
    		return 0;
    	}
    }
}
