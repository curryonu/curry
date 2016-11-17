package com.curry.Util;

import java.util.Comparator;

import com.curry.pojo.Book;

public class ObjectSortByString implements Comparator<Book> {

	public int compare(Book u1, Book u2) {
		float score1 = Float.parseFloat(u1.getCommonLerver());
		float score2 = Float.parseFloat(u2.getCommonLerver());

		if (score1 == score2) {
			return 0;
		} else if (score1 < score2) {
			return 1;
		} else {
			return -1;
		}
	}
//	public static void main(String[] args) {
//		Book b1 = new Book();
//		
//	}

}