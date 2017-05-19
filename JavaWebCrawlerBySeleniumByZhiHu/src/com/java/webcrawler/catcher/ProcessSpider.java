package com.java.webcrawler.catcher;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ProcessSpider implements Runnable{
	
	private MyWebSpider myWebSpider1=null;
	
	public ProcessSpider()
	{
		
	}
	
	public ProcessSpider(MyWebSpider myWebSpider1)
	{
		this.myWebSpider1=myWebSpider1;
	}
	
	public void myprocess()
	{
		//
		//
		//
		//
		//
		int i=0;
		int j=0;
		//
		//
		
		//mywebspider1.myRequest();
		//String str=new String("");
		//mywebspider1.myRequestSSL("www.zhihu.com/people/ken-lan-46/answers?page=2");
		WebDriver driver1=myWebSpider1.getWebdriver1();
		//登陆
		myWebSpider1.login(driver1);
		//进入个人信息页面
		myWebSpider1.enterUserInfo(driver1);
		//进入我关注的收藏夹
		myWebSpider1.enterMyCollectionsPage(driver1);
		//获得每个收藏夹的URL
		myWebSpider1.getCollectionsUrl(driver1);
		//
		//
		//
		//MyThreadSleep.sleep2s();
		//driver1.navigate().to(myWebSpider1.getStrqueueCollectionUrlToRequest1().remove());
		//搜寻每个收藏夹的问题
		//mywebspider1.getQuesionUrlWithoutAnswerUrlInSpecifiedPage(driver1);
		//myWebSpider1.getQuesionUrlWithAnswerUrlInSpecifiedPage(driver1);
		//
		//
		//
		//遍历每个收藏夹
		MyThreadSleep.sleep2s();
		for(i=0;i<=myWebSpider1.getUrlWarehouse1().sizeStrqueueCollectionUrlToRequest1()-1;i++)
		{
			
			driver1.navigate().to(myWebSpider1.getUrlWarehouse1().pollStrqueueCollectionUrlToRequest1());
			MyThreadSleep.sleep2s();
			//搜寻每个收藏夹的答案(路径包括问题)
			//mywebspider1.getQuesionUrlWithoutAnswerUrlInSpecifiedPage(driver1);
			myWebSpider1.getQuesionUrlWithAnswerUrlInSpecifiedPage(driver1);
		}
		//
		//enter answer page
		MyThreadSleep.sleep1s();
		System.out.println("test getStrqueueAnswerUrlToRequest1().size()"+myWebSpider1.getUrlWarehouse1().sizeStrqueueAnswerUrlToRequest1());
		for(i=0;i<=myWebSpider1.getUrlWarehouse1().sizeStrqueueAnswerUrlToRequest1()-1;i++)
		{
			driver1.navigate().to(myWebSpider1.getUrlWarehouse1().pollStrqueueAnswerUrlToRequest1());
			//
			MyThreadSleep.sleep1s();
			//
			int scrollLength=0;
			//
			for(j=0;j<=100-1;j++)
			{
				JavascriptExecutor jsDriverTemp = (JavascriptExecutor) driver1;
				scrollLength=scrollLength+200;
				String js="document.body.scrollTop ="+scrollLength;
				jsDriverTemp.executeScript(js);
				//
				MyThreadSleep.sleep100ms();
				
			}

			//get pic url in specified page
			myWebSpider1.getSpecifiedAnswerContent2(driver1);
			//
			//
		}
		System.out.println("ooooooooooooooooooooooooooooooooooooooooooooooooottttttttttttttttttttt");
		//
		//
		//
		//
		

		//
		//

		//
		//
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
/*		for(int i=0;i<100;i++)
		{
			MyThreadSleep.sleep1s();
			this.myWebSpider1.setA(i);
			
		}*/
		myprocess();
	}
}
