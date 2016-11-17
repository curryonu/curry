package com.curry.pojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.curry.Util.ObjectSortByString;

public class AllBookLists {
	private static AllBookLists ab =new AllBookLists();
	private AllBookLists(){	
	}
	public static AllBookLists getAllBookLists(){
		return ab;
	}
	
	private static List<Book> books = new ArrayList<Book>();
	
	public static  List<Book> oder(){  //返回按照规则排序后的books	
		 Collections.sort(books,new ObjectSortByString());
		 return books;
	}


	public static ArrayList<Book> getBooks() {
		return (ArrayList<Book>) books;
	}   


	public static synchronized void  addBookList( ArrayList<Book> bookList) {
		if(bookList!=null)
		books.addAll(bookList);
		//添加打印debug
	}
}
