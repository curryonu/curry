package com.curry.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.curry.Util.HtmlUtils;
import com.curry.pojo.AllBookLists;
import com.curry.pojo.Book;
import com.curry.pojo.BookList;
import com.curry.pojo.Page;
import com.curry.saveData.ISaveData;
import com.curry.saveData.SaveToExcle;

public class DoHtmlThread extends Thread{
	AllBookLists allbList =AllBookLists.getAllBookLists();  //用来储存所有符合条件的book
	private List<Page> pages = new ArrayList<Page>();// 用来储存所有含有Book的html文件
	private String url;                             //种子url
	private String keywords;                        //搜索关键字
	protected ExecutorService pool = null;  //管理处理Page的线程池

	public DoHtmlThread(){}
	public DoHtmlThread(String url, String keywords) {
		this.url = url;
		this.keywords = keywords;
	}

	@Override
	public void run() {
		//加载第一页
		Document doc = null;
		try {
			doc = HtmlUtils.getHtml(getURL());
		} catch (IOException e) {
			//logger.debug();
			e.printStackTrace();
		}
		Page page = new Page(doc);
		page.setUrl(getURL());
		pages.add(page);
		
		//从第一页开始逐页加载所有的page,这里实际上可以进一步优化
		//因为每次建立连接都是非常耗时间的，
		//每一页加载一次消耗资源和时间
        while(page.hasNextPage()){
        	page = page.getNextPage();
        	pages.add(page);
        }
        System.out.println("所有页初始化完成！");
        //启动多个线程同时对 得到的page进行处理，获得Page的bookList
         //修改在线程内添加最终的bList
        pool = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(pages.size());//
        for(int i = 0, len = pages.size();i<len;i++){
        	Thread  t= new SetPageThread(pages.get(i),latch);
             t.start();
        }
        
        try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
//        for(int i = 0,len=pages.size();i<len;i++){
//        	AllBookLists.addBookList(pages.get(i).getbList().getBooks());
//        	System.out.println("add one bookList!");
//        }
        //按条件筛选出bList中的前一百名
		ArrayList<Book> l = (ArrayList<Book>) AllBookLists.oder();
		//在上面线程执行完成之后，重新唤醒主线程往下执行      
		//将最终的bList传递给saveData进行保存}
		ISaveData savetoExcel = new SaveToExcle();
		savetoExcel.add(l);
	}
	private String getURL(){//根据前面输入的url以及key拼装新的url这里直接用输入url替代
		return url;
	}

}
