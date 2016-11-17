package com.curry.pojo;

import java.io.Serializable;

public class Book implements Serializable {
	//private int id;   //书的id
	private String title;  //书名
	private String commonLerver; //评定得分
	private int commonSize; //书评人数
	private String url;     //书籍的详细信息连接
	//private String info;      //其他信息

	Book(){
	}

	Book(int id,String title,String commonLerver,int commonSize,String info){
		//this.id = id;
		this.commonLerver = commonLerver;
		this.commonSize = commonSize;
		//this.info = info;
	}
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCommonLerver() {
		return commonLerver;
	}
	public void setCommonLerver(String commonLerver) {
		this.commonLerver = commonLerver;
	}
	public int getCommonSize() {
		return commonSize;
	}
	public void setCommonSize(int conmmonSize) {
		this.commonSize = conmmonSize;
	}
	public boolean compare(Book b){ //自己大未true
		if(commonLerver == null ){
			return false;//自己小
		}else if(b.commonLerver == null){
			return true;//自己大
		}else{
			float mylever = Float.parseFloat(commonLerver);
			float blever = Float.parseFloat(b.commonLerver);
			if(mylever>blever){
				return true;//自己大
			}else{
				return false;//自己小
			}
		}
	}
//	public String getInfo() {
//		return info;
//	}
//	public void setInfo(String info) {
//		this.info = info;
//	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {

		return "title；" + this.title+ "/" + this.url + "/" + this.commonLerver + "/" + this.commonSize + "/";
	}
}

