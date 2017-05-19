package com.java.webcrawler;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;

import com.java.webcrawler.catcher.DownLoadPicFactory;
import com.java.webcrawler.catcher.MyThreadSleep;
import com.java.webcrawler.catcher.MyWebSpider;
import com.java.webcrawler.catcher.ProcessSpider;


public class TestMain {
//git rm -r --cached .
	// git add .
	public static void main(String[] args) {
		//
		//
		//
		//
		MyWebSpider mywebspider1=new MyWebSpider();
		Runnable runnableProcessSpider=new ProcessSpider(mywebspider1);
		new Thread(runnableProcessSpider).start();
		//
		//
		Runnable runnableDownLoadPicFactory=new DownLoadPicFactory(mywebspider1);
		new Thread(runnableDownLoadPicFactory).start();
		//
		//
		//
	}
	
	


}
