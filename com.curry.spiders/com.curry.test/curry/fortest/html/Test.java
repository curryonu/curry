package curry.fortest.html;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.jsoup.Jsoup;

import com.curry.pojo.Page;

public class Test {
      Page[] pages =new Page[5];
      public Page[] getPages() throws IOException{
    	  pages[0] =  new Page(Jsoup.parse(new FileInputStream("C:\\douban\\html\\001.htm"),"utf-8","url"));
    	  pages[0].setbList();
    	  pages[1] =  new Page(Jsoup.parse(new FileInputStream("C:\\douban\\html\\002.htm"),"utf-8","url"));
    	  pages[1].setbList();
    	  pages[2] =  new Page(Jsoup.parse(new FileInputStream("C:\\douban\\html\\003.htm"),"utf-8","url"));
    	  pages[2].setbList();
    	  pages[3] =  new Page(Jsoup.parse(new FileInputStream("C:\\douban\\html\\004.htm"),"utf-8","url"));
   	      pages[3].setbList();
    	  pages[4] =  new Page(Jsoup.parse(new FileInputStream("C:\\douban\\html\\005.htm"),"utf-8","url"));
    	  pages[4].setbList();
    	  return pages;
      }
}
