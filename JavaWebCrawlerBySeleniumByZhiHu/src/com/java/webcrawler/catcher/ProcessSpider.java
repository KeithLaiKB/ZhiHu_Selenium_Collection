package com.java.webcrawler.catcher;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ProcessSpider implements Runnable{
	// driver would be initial in constructor
	private WebDriver webDriver1=null;
	private MyAbstractWebSpider myWebSpider1=null;
	
	
	


	public ProcessSpider()
	{
		System.setProperty("webdriver.chrome.driver", "./doc/chromedriver_win32/chromedriver.exe");
		webDriver1 = new ChromeDriver();
		webDriver1.get("https://www.zhihu.com/");
	}
	
	public ProcessSpider(MyAbstractWebSpider myWebSpider1)
	{
		System.setProperty("webdriver.chrome.driver", "./doc/chromedriver_win32/chromedriver.exe");
		webDriver1 = new ChromeDriver();
		webDriver1.get("https://www.zhihu.com/");
		this.myWebSpider1=myWebSpider1;
		//webDriver1=MyAbstractWebSpider.getWebdriver1();
	}
	
	
	
	
	
	
	public WebDriver getWebDriver1() {
		return webDriver1;
	}

	public void setWebDriver1(WebDriver webDriver1) {
		this.webDriver1 = webDriver1;
	}
	
	


	public MyAbstractWebSpider getMyWebSpider1() {
		return myWebSpider1;
	}

	public void setMyWebSpider1(MyAbstractWebSpider myWebSpider1) {
		this.myWebSpider1 = myWebSpider1;
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
		//
		//mywebspider1.myRequest();
		//String str=new String("");
		//mywebspider1.myRequestSSL("www.zhihu.com/people/ken-lan-46/answers?page=2");
		
		//登陆
		myWebSpider1.login(webDriver1);
		//进入个人信息页面
		myWebSpider1.enterUserInfo(webDriver1);
		//进入我关注的收藏夹
		myWebSpider1.enterMyCollectionsPage(webDriver1);
		//获得每个收藏夹的URL
		myWebSpider1.getCollectionsUrl(webDriver1);
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
		//因为如果其他线程也在做同样的事情时候，有可能此时的sizeStrqueueCollectionUrlToRequest1已经被另外一个线程正在用，还没来得及增加下一页的collection页面,他会以为已经完成而提前结束
		//但是我希望这个能够作为一个主流每一个阶段都走，所以这样并不算太大问题，反正也有其他线程正在做这个事，如果觉得变慢了，就再加一个这个模块的线程就好了
		for(i=0;i<=myWebSpider1.getUrlWarehouse1().sizeStrqueueCollectionUrlToRequest1()-1;i++)
		{
			
			webDriver1.navigate().to(myWebSpider1.getUrlWarehouse1().pollStrqueueCollectionUrlToRequest1());
			MyThreadSleep.sleep2s();
			//搜寻每个收藏夹的答案(路径包括问题)
			//mywebspider1.getQuesionUrlWithoutAnswerUrlInSpecifiedPage(driver1);
			myWebSpider1.getQuesionUrlWithAnswerUrlInSpecifiedPage(webDriver1);
			//function to get the next page url of the collection and put into urlwarehouse
			myWebSpider1.getUrlToTurnToNextPageInInSpecifiedPage(webDriver1);
		}
		//
		//enter answer page
		MyThreadSleep.sleep1s();
		System.out.println("test getStrqueueAnswerUrlToRequest1().size()"+myWebSpider1.getUrlWarehouse1().sizeStrqueueAnswerUrlToRequest1());
		for(i=0;i<=myWebSpider1.getUrlWarehouse1().sizeStrqueueAnswerUrlToRequest1()-1;i++)
		{
			webDriver1.navigate().to(myWebSpider1.getUrlWarehouse1().pollStrqueueAnswerUrlToRequest1());
			//
			MyThreadSleep.sleep1s();
			//
			int scrollLength=0;
			//
			for(j=0;j<=100-1;j++)
			{
				JavascriptExecutor jsDriverTemp = (JavascriptExecutor) webDriver1;
				scrollLength=scrollLength+200;
				String js="document.body.scrollTop ="+scrollLength;
				jsDriverTemp.executeScript(js);
				//
				MyThreadSleep.sleep100ms();
				
			}

			//get pic url in specified page
			myWebSpider1.getSpecifiedAnswerContent2(webDriver1);
			//
			//

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
