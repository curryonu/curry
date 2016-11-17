package com.curry.controller;

import java.util.concurrent.CountDownLatch;

import com.curry.pojo.AllBookLists;
import com.curry.pojo.Page;
import com.sun.media.jfxmedia.logging.Logger;

public class SetPageThread extends Thread {//处理页面的线程
//	private static final Logger logger = (Logger) LoggerFactory.getLogger(SetPageThread.class.getName());
	private Page page;
	private CountDownLatch latch;
	public SetPageThread(Page page,CountDownLatch latch){
		this.page = page;
		this.latch = latch;
	}
	public void run(){
		if(page.getDoc() != null){
		   page.setbList();
		   if(page.getbList() != null) AllBookLists.addBookList(page.getbList().getBooks());
		   System.out.println("a thread has stoped !"+ page.getbList().getBooks().size());
		   latch.countDown();
		}else{
			//logger.DEBUG;
		}
	}
}
