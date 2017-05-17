package com.java.webcrawler.catcher;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.java.webcrawler.personalinfo.PersonalInfo;

public class MyWebSpider {

	// queue
	private Queue<String> queueUrlToRequest1 = new LinkedList<String>();

	public Queue<String> getQueueUrlToRequest1() {
		return queueUrlToRequest1;
	}



	public void setQueueUrlToRequest1(Queue<String> queueUrlToRequest1) {
		this.queueUrlToRequest1 = queueUrlToRequest1;
	}





	private static WebDriver driver=null;
	
	public static WebDriver getDriver() {
		return driver;
	}



	public static void setDriver(WebDriver driver) {
		MyWebSpider.driver = driver;
	}



	public MyWebSpider()
	{
		System.setProperty("webdriver.chrome.driver", "./doc/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.zhihu.com/");
		
	}
	
	
	
	public void login(WebDriver driver)
	{

		//
        // Find the text input element by its name
       // WebElement element = driver.findElement(By.name("wd"));
        //WebElement myUserInfoElementInOrgWeb1=driver.findElement(By.id(":0"));
        
        // Enter something to search for
		MyThreadSleep.sleep2s();
        WebElement loginElementInOrgWeb1=driver.findElement(By.linkText("登录"));
        loginElementInOrgWeb1.click();
        //enter account
        MyThreadSleep.sleep50ms();
        WebElement loginAccountElementInOrgWeb1=driver.findElement(By.name("account"));
        loginAccountElementInOrgWeb1.sendKeys(PersonalInfo.account);
        //enter password
        MyThreadSleep.sleep10ms();
        WebElement loginPasswordElementInOrgWeb1=driver.findElement(By.name("password"));
        loginPasswordElementInOrgWeb1.sendKeys(PersonalInfo.password);
        //
        //submit
        MyThreadSleep.sleep5s();
       // WebElement loginSubmitElementInOrgWeb1=driver.findElement(By.tagName("input"));
        List<WebElement> allInputs = driver.findElements(By.tagName("button"));
        if(allInputs.size()>1)
        {
        	System.out.println("saonima");
        	
        }
        //只打印所有文本框的值
        for(WebElement e: allInputs)
        {
        	if(e.getAttribute("type").equals("submit"))
        	{
        		System.out.println("damn");
        		e.click();  
        		break;
        	}
 
        }
        MyThreadSleep.sleep3s();
	}
	
	
	//enter user info page
	public void enterUserInfo(WebDriver driver)
	{
		MyThreadSleep.sleep3s();
		WebElement myUserInfoElementInHomePage1=driver.findElement(By.id(":0"));
		myUserInfoElementInHomePage1.click();
	}
	
	//enter my collections 
	public void enterMyCollectionsPage(WebDriver driver)
	{
		MyThreadSleep.sleep1s();
        WebElement collectionsEntranceInUserInfoWeb1=driver.findElement(By.partialLinkText("收藏夹"));
        collectionsEntranceInUserInfoWeb1.click();
	}
	
	
	//get each url of collections
	public void getCollectionsUrl(WebDriver driver)
	{
		MyThreadSleep.sleep1s();
        List<WebElement> allA = driver.findElements(By.tagName("a"));
        if(allA.size()>1)
        {
        	System.out.println("saonima");
        	
        }
        for(WebElement e: allA)
        {
        	//  collection/...
        	if(e.getAttribute("href").contains("collection/"))
        	{
        		//直接获得的是绝对路径
        		System.out.println("damn:"+e.getAttribute("href")+e.getText());
        		//
        		//
        		queueUrlToRequest1.add(e.getAttribute("href"));
        		//
        	}
 
        }
		
        
        System.out.println(driver.getPageSource());
        
	}
	
	//
	//
	//
	//
	//
	//进入收藏夹后，只拿问题的URL，(因为会有答案的一些摘要，所以要除去)    每一个driver过来可能就代表一个页面
	public void getQuesionUrlWithoutAnswerUrlInSpecifiedPage(WebDriver driver)
	{
		MyThreadSleep.sleep1s();
        List<WebElement> allA = driver.findElements(By.tagName("a"));
        if(allA.size()>1)
        {
        	System.out.println("size:"+allA.size());
        	
        }
        for(WebElement e: allA)
        {
        	if(e.getAttribute("href")!=null)
        	{
        		if(e.getAttribute("href").contains("question")==true&&e.getAttribute("href").contains("answer")==false)
            	{
            		//直接获得的是绝对路径
            		System.out.println("damn:"+e.getAttribute("href")+e.getText());
            		//
            		//
            		queueUrlToRequest1.add(e.getAttribute("href"));
            		//
            	}
     
        		
        		
        	}
        	
        }
		
		
	}
	
	
	
	
	
	public void testChrome() {

        //account
        //
        //
        
        //
        //
        //
        //myUserInfoElementInOrgWeb1.click();
        // Now submit the form. WebDriver will find the form for us from the element
        //element.submit();

/*        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());
        
        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        WebDriverWait webDriverWait1=new WebDriverWait(driver,5);
        webDriverWait1.until(new ExpectedCondition<Boolean>() {
        	@Override
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheese!");
            }
        });
*/
        // Should see: "cheese! - Google Search"
       // System.out.println("Page title is: " + driver.getTitle());
        
        //Close the browser
       // driver.quit();
	}

}
