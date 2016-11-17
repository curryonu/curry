package com.curry.saveData;

import java.util.ArrayList;

import com.curry.pojo.Book;

public interface ISaveData {
	void check();
   // void add();
    void delete();
    void change();
	void add(ArrayList<Book> books);
}
