package com.java.webcrawler;

import java.net.MalformedURLException;
import java.net.URL;

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
		//
		//
		MyThreadSleep.sleep2s();
		driver1.navigate().to(mywebspider1.getQueueCollectionUrlToRequest1().remove());
		//搜寻每个收藏夹的问题
		//mywebspider1.getQuesionUrlWithoutAnswerUrlInSpecifiedPage(driver1);
		mywebspider1.getQuesionUrlWithAnswerUrlInSpecifiedPage(driver1);
		//
		//
		//
/*		//遍历每个收藏夹
		for(i=0;i<=mywebspider1.getQueueCollectionUrlToRequest1().size()-1;i++)
		{
			MyThreadSleep.sleep2s();
			driver1.navigate().to(mywebspider1.getQueueCollectionUrlToRequest1().remove());
			//搜寻每个收藏夹的问题
			//mywebspider1.getQuesionUrlWithoutAnswerUrlInSpecifiedPage(driver1);
			mywebspider1.getQuesionUrlWithAnswerUrlInSpecifiedPage(driver1);
		}*/
		//
		//enter answer page
/*		for(i=0;i<=mywebspider1.getQueueAnswerUrlToRequest1().size()-1;i++)
		{
			MyThreadSleep.sleep2s();
			driver1.navigate().to(mywebspider1.getQueueAnswerUrlToRequest1().remove());
			//搜寻每个收藏夹的问题
			//mywebspider1.getQuesionUrlWithoutAnswerUrlInSpecifiedPage(driver1);
			mywebspider1.getQuesionUrlWithAnswerUrlInSpecifiedPage(driver1);
		}*/
		//
		//
		//enter answer page
		MyThreadSleep.sleep2s();
		driver1.navigate().to(mywebspider1.getQueueAnswerUrlToRequest1().remove());
		for(i=0;i<=mywebspider1.getQueueAnswerUrlToRequest1().size()-1;i++)
		{
			MyThreadSleep.sleep5s();
			mywebspider1.getSpecifiedAnswerContent2(driver1);

			break;
		}
		//
		//
		for(i=0;i<=mywebspider1.getQueueImgUrlToRequest1().size()-1;i++)
		{
			//MyThreadSleep.sleep500ms();
			try {
				mywebspider1.downloadPicByURL(new URL(mywebspider1.getQueueImgUrlToRequest1().peek()));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		}
		//
		//
	}

}
