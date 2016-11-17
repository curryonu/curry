package com.curry.pojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.LoggerFactory;

import com.sun.media.jfxmedia.logging.Logger;
public class BookList {
	//考虑到前面代码可能对此list频繁操作，对其添加方法用锁来限制；
	//List的查询效率较高
	//private static final Logger logger = (Logger) LoggerFactory.getLogger(BookList.class.getName());
	private List<Book> books = new ArrayList<Book>();

//	public List<Book> oder(){  //返回按照规则排序后的books
//		List<Book> tmp100 = new ArrayList<Book>();
//		for(int i = 0,len = books.size();i<len;i++){	
//			for(int j = 0;j<i;j++){
//				Book mb = books.get(i);
//				if(mb.compare(books.get(j))) {
//					books.add(i,books.get(j));
//					books.add(j,mb);
//				}
//			}
//		}
//		if(books.size()>100){
//			tmp100 = books.subList(0, 99);
//		}else{
//			tmp100 = books;
//		}
//		return tmp100;
//	}


	public ArrayList<Book> getBooks() {
		if(books == null) return null;
		return (ArrayList<Book>) books;
	}   
	//	public void setBooks(List<Book> books) {
	//		this.books = books;
	//	}
	public void addBook(Book book){   
		if(books == null) books = new ArrayList<Book>();
		books.add(book);
	}
	public void deleteBook(Book book){  //

	}


//	public synchronized void  addBookList( ArrayList<Book> bookList) {
//		books.addAll(bookList);
//		//添加打印debug
//	}
}
