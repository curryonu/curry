package com.curry.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.curry.controller.DoHtmlThread;

public class MainFrameServiceImpl implements IMainFrameService{
	public static final String TYPE= "fix";
	public static final String NAME= "Spiders";//给这种线程起一个标示
	private static final HashMap<String, DoHtmlThread> map = new HashMap<String, DoHtmlThread>();
	private static final Logger logger = (Logger) LoggerFactory.getLogger(MainFrameServiceImpl.class.getName());

	public void strat(String url, String keywords) {//得到html，并启动处理线程
		DoHtmlThread t = map.get(NAME);        //?  宸茬粡鍚姩浜唂ix绾跨▼锛�		
		if(t != null){
			logger.warn("Can't start " + NAME + " again.");
		}else {
			t = new DoHtmlThread(url, keywords);
			map.put(NAME,t);
			t.start();
		}

	}

	public void stop() {
		DoHtmlThread t = map.get(NAME);
		if(t==null){
			logger.warn("No " + NAME + " to stop.");
			return;
		}
		map.remove(NAME);
	}
}

