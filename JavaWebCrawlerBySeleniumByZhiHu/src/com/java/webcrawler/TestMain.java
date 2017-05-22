package com.java.webcrawler;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.java.webcrawler.catcher.DownLoadPicFactory;
import com.java.webcrawler.catcher.MyAbstractWebSpider;
import com.java.webcrawler.catcher.MyConcreteWebSpider;
import com.java.webcrawler.catcher.MyThreadSleep;
import com.java.webcrawler.catcher.ProcessSpider;


public class TestMain {
//git rm -r --cached .
	// git add .
	public static void main(String[] args) {
		//
		//
		//
		//
		MyAbstractWebSpider mywebspider1=new MyConcreteWebSpider();
		//
		//
		//
		Runnable runnableProcessSpider=new ProcessSpider(mywebspider1);
		new Thread(runnableProcessSpider).start();
		//
		//
		Runnable runnableDownLoadPicFactory=new DownLoadPicFactory(mywebspider1);
		new Thread(runnableDownLoadPicFactory).start();
		//
		//
		//
		//
		//
		//
		MyThreadSleep.sleep10s();
		MyThreadSleep.sleep10s();
		ProcessSpider runnableProcessSpiderToCatchAnswer1=new ProcessSpider(mywebspider1){
			@Override
			public void myprocess()
			{
				//存在有可能跟其他线程先后进入了同个收藏夹一起进行遍历，此时该收藏夹的搜索就由多个线程一起爬，但是已收入的答案就不会收入url队列里了
				for(;this.getMyWebSpider1().getUrlWarehouse1().sizeStrqueueCollectionUrlToRequest1()>=0;)
				{
					System.out.println("getStrqueueImgUrlToRequest1 size!!!!!!!!!!!!!!!!:"+this.getMyWebSpider1().getUrlWarehouse1().sizeStrqueueImgUrlToRequest1() );
					if(this.getMyWebSpider1().getUrlWarehouse1().sizeStrqueueCollectionUrlToRequest1()<=0)
					{
						MyThreadSleep.sleep5s();
						continue;
						
					}
					MyThreadSleep.sleep2s();
					for(int overI=0;overI<=this.getMyWebSpider1().getUrlWarehouse1().sizeStrqueueCollectionUrlToRequest1()-1;overI++)
					{
						this.getWebDriver1().navigate().to(this.getMyWebSpider1().getUrlWarehouse1().pollStrqueueCollectionUrlToRequest1());
						MyThreadSleep.sleep2s();
						//搜寻每个收藏夹的答案(路径包括问题)
						//mywebspider1.getQuesionUrlWithoutAnswerUrlInSpecifiedPage(driver1);
						this.getMyWebSpider1().getQuesionUrlWithAnswerUrlInSpecifiedPage(this.getWebDriver1());
						this.getMyWebSpider1().getUrlToTurnToNextPageInInSpecifiedPage(this.getWebDriver1());
					}
				}

			}
		};
		new Thread((Runnable)runnableProcessSpiderToCatchAnswer1).start();
		//
		//
/*		MyThreadSleep.sleep10s();
		MyThreadSleep.sleep10s();
		ProcessSpider runnableProcessSpiderToCatchAnswer2=new ProcessSpider(mywebspider1){
			@Override
			public void myprocess()
			{
				//存在有可能跟其他线程先后进入了同个收藏夹一起进行遍历，此时该收藏夹的搜索就由多个线程一起爬，但是已收入的答案就不会收入url队列里了
				for(;this.getMyWebSpider1().getUrlWarehouse1().sizeStrqueueCollectionUrlToRequest1()>=0;)
				{
					System.out.println("getStrqueueImgUrlToRequest1 size!!!!!!!!!!!!!!!!:"+this.getMyWebSpider1().getUrlWarehouse1().sizeStrqueueImgUrlToRequest1() );
					if(this.getMyWebSpider1().getUrlWarehouse1().sizeStrqueueCollectionUrlToRequest1()<=0)
					{
						MyThreadSleep.sleep5s();
						continue;
						
					}
					MyThreadSleep.sleep2s();
					for(int overI=0;overI<=this.getMyWebSpider1().getUrlWarehouse1().sizeStrqueueCollectionUrlToRequest1()-1;overI++)
					{
						this.getWebDriver1().navigate().to(this.getMyWebSpider1().getUrlWarehouse1().pollStrqueueCollectionUrlToRequest1());
						MyThreadSleep.sleep2s();
						//搜寻每个收藏夹的答案(路径包括问题)
						//mywebspider1.getQuesionUrlWithoutAnswerUrlInSpecifiedPage(driver1);
						this.getMyWebSpider1().getQuesionUrlWithAnswerUrlInSpecifiedPage(this.getWebDriver1());
						this.getMyWebSpider1().getUrlToTurnToNextPageInInSpecifiedPage(this.getWebDriver1());
					}
				}

			}
		};
		new Thread((Runnable)runnableProcessSpiderToCatchAnswer2).start();*/
		//
		//
		//
		//
		MyThreadSleep.sleep10s();
		MyThreadSleep.sleep10s();
		ProcessSpider runnableProcessSpiderToCatchContent=new ProcessSpider(mywebspider1){
			@Override
			public void myprocess()
			{
				for(;this.getMyWebSpider1().getUrlWarehouse1().sizeStrqueueAnswerUrlToRequest1()>=0;)
				{
					System.out.println("sizeStrqueueAnswerUrlToRequest1 size!!!!!!!!!!!!!!!!:"+this.getMyWebSpider1().getUrlWarehouse1().sizeStrqueueAnswerUrlToRequest1() );
					if(this.getMyWebSpider1().getUrlWarehouse1().sizeStrqueueAnswerUrlToRequest1()<=0)
					{
						MyThreadSleep.sleep5s();
						continue;
						
					}
					this.getWebDriver1().navigate().to(this.getMyWebSpider1().getUrlWarehouse1().pollStrqueueAnswerUrlToRequest1());
					//
					MyThreadSleep.sleep1s();
					//
					int scrollLength=0;
					//
					for(int overI=0;overI<=100-1;overI++)
					{
						JavascriptExecutor jsDriverTemp = (JavascriptExecutor)this.getWebDriver1();
						scrollLength=scrollLength+200;
						String js="document.body.scrollTop ="+scrollLength;
						jsDriverTemp.executeScript(js);
						//
						MyThreadSleep.sleep100ms();
						
					}

					//get pic url in specified page
					this.getMyWebSpider1().getSpecifiedAnswerContent2(this.getWebDriver1());
					//
					//

					//
					//
				}

			}
		};
		new Thread((Runnable)runnableProcessSpiderToCatchContent).start();
	}
	
	


}
