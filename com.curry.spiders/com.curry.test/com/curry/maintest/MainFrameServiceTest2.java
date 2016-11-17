package com.curry.maintest;

import java.io.IOException;
import java.util.ArrayList;

import com.curry.pojo.AllBookLists;
import com.curry.pojo.Book;
import com.curry.pojo.BookList;
import com.curry.pojo.Page;
import com.curry.saveData.ISaveData;
import com.curry.saveData.SaveToExcle;

import curry.fortest.html.Test;

public class MainFrameServiceTest2 {
	public static void main(String[] args){
		Page[] pages = null;
		try {
			pages = new Test().getPages();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("pages"+pages.length);
		for(int i = 0,len=pages.length;i<len;i++){
			Page p = pages[i];
			BookList bl =p.getbList();
			//System.out.println("bl"+bl.getBooks());
			if(bl !=null){
				ArrayList<Book> books = bl.getBooks();
				AllBookLists.addBookList(books);
			}
			
			System.out.println("add one bookList!");
		}
		//按条件筛选出bList中的前一百名
		ArrayList<Book> l = (ArrayList<Book>) AllBookLists.oder();
		//在上面线程执行完成之后，重新唤醒主线程往下执行      
		//将最终的bList传递给saveData进行保存}
		ISaveData savetoExcel = new SaveToExcle();
		savetoExcel.add(l);  
	}
}
