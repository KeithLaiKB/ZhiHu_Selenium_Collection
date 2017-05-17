package com.java.webcrawler;

import org.openqa.selenium.WebDriver;

import com.java.webcrawler.catcher.MyThreadSleep;
import com.java.webcrawler.catcher.MyWebSpider;


public class TestMain {
//git rm -r --cached .
	// git add .
	public static void main(String[] args) {
		myprocess();
	}
	
	
	public static void myprocess()
	{
		//
		int i=0;
		//
		//
		MyWebSpider mywebspider1=new MyWebSpider();
		//mywebspider1.myRequest();
		String str=new String("");
		//mywebspider1.myRequestSSL("www.zhihu.com/people/ken-lan-46/answers?page=2");
		WebDriver driver1=mywebspider1.getDriver();
		mywebspider1.login(driver1);
		mywebspider1.enterUserInfo(driver1);
		mywebspider1.enterMyCollectionsPage(driver1);
		mywebspider1.getCollectionsUrl(driver1);
		//
		for(i=0;i<=mywebspider1.getQueueUrlToRequest1().size()-1;i++)
		{
			MyThreadSleep.sleep2s();
			driver1.navigate().to(mywebspider1.getQueueUrlToRequest1().remove());
			mywebspider1.getQuesionUrlWithoutAnswerUrlInSpecifiedPage(driver1);
		}
		//
		//
	}

}
