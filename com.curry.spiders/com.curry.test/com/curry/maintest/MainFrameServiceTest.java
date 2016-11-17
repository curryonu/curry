package com.curry.maintest;
import com.curry.service.MainFrameServiceImpl;


public class MainFrameServiceTest {
     public static void main(String[] args0){
    	 MainFrameServiceImpl mf= new MainFrameServiceImpl();
    	 mf.strat("https://book.douban.com/subject_search?search_text=%E4%BA%92%E8%81%94%E7%BD%91%2B%E7%BC%96%E7%A8%8B%2B%E7%AE%97%E6%B3%95&cat=1001", "互联网+编程+算法");
     }
}
