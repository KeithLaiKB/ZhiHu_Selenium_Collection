package com.java.webcrawler.catcher;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.java.webcrawler.personalinfo.PersonalInfo;

public class MyWebSpider {

	// queue
	private Queue<String> queueCollectionUrlToRequest1 = new LinkedList<String>();
	
	private Queue<String> queueAnswerUrlToRequest1 = new LinkedList<String>();
	private Set<String> queueAnswerUrlVisited=new HashSet<String>();

	private Queue<String> queueImgUrlToRequest1=new LinkedList<String>();
	private Set<String> queueImgUrlVisited=new HashSet<String>();
	
	
	

	public Queue<String> getQueueImgUrlToRequest1() {
		return queueImgUrlToRequest1;
	}



	public void setQueueImgUrlToRequest1(Queue<String> queueImgUrlToRequest1) {
		this.queueImgUrlToRequest1 = queueImgUrlToRequest1;
	}



	public Queue<String> getQueueAnswerUrlToRequest1() {
		return queueAnswerUrlToRequest1;
	}



	public void setQueueAnswerUrlToRequest1(Queue<String> queueAnswerUrlToRequest1) {
		this.queueAnswerUrlToRequest1 = queueAnswerUrlToRequest1;
	}



	public Queue<String> getQueueCollectionUrlToRequest1() {
		return queueCollectionUrlToRequest1;
	}



	public void setQueueCollectionUrlToRequest1(Queue<String> queueCollectionUrlToRequest1) {
		this.queueCollectionUrlToRequest1 = queueCollectionUrlToRequest1;
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
        		queueCollectionUrlToRequest1.add(e.getAttribute("href"));
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
            		//已存在准备列表中
            		if(checkExistedByStrQueue(queueAnswerUrlToRequest1,e.getAttribute("href"))==true)
            		{
            			//do nothing because it exists
            			System.out.println(e.getAttribute("href")+" exsits to request");
            		}
            		else
            		{
            			//已存在已看过的列表中
            			if(checkVisitedByStrSet(this.queueAnswerUrlVisited,e.getAttribute("href"))==true)
                		{
                			//do nothing because it is visited
                			System.out.println(e.getAttribute("href")+" has been visited");
                		}
                		else
                		{
                			queueAnswerUrlToRequest1.add(e.getAttribute("href"));
                		}
            			
            		}
            		//
            	}
     
        		
        		
        	}
        	
        }
		
		
	}
	
	
	//进入收藏夹后，只拿问题答案的URL，(这些答案都是精选的)    每一个driver过来可能就代表一个页面
	public void getQuesionUrlWithAnswerUrlInSpecifiedPage(WebDriver driver)
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
        		if(e.getAttribute("href").contains("question")==true&&e.getAttribute("href").contains("answer")==true)
            	{
            		//直接获得的是绝对路径
            		System.out.println("damn:"+e.getAttribute("href")+e.getText());
            		//
            		//
            		//
            		//已存在准备列表中
            		if(checkExistedByStrQueue(queueAnswerUrlToRequest1,e.getAttribute("href"))==true)
            		{
            			//do nothing because it exists
            			System.out.println(e.getAttribute("href")+" exsits to request");
            		}
            		else
            		{
            			//已存在已看过的列表中
            			if(checkVisitedByStrSet(this.queueAnswerUrlVisited,e.getAttribute("href"))==true)
                		{
                			//do nothing because it is visited
                			System.out.println(e.getAttribute("href")+" has been visited");
                		}
                		else
                		{
                			queueAnswerUrlToRequest1.add(e.getAttribute("href"));
                		}
            			
            		}
            		//
            		//
            		//
/*            		if(checkVisitedBySet(this.queueAnswerUrlVisited,e.getAttribute("href"))==true)
            		{
            			//do nothing because it is visited
            			System.out.println(e.getAttribute("href")+" exsits");
            		}
            		else
            		{
            			queueAnswerUrlToRequest1.add(e.getAttribute("href"));
            		}*/
            		//
            		
            		//
            	}	
        		
        	}
        	
        }
		
		
	}
	
	
	//just get the specified answer content which does not include the content in top bar
	public void getSpecifiedAnswerContent(WebDriver driver)
	{
		//寻找 QuestionAnswer-content 里面的图片
		
		WebElement webElement1= driver.findElement(By.xpath("//div[contains(@class,'QuestionAnswer-content')]"));
		System.out.println(webElement1.getText());
		WebElement webElement2= driver.findElement(By.xpath("//div[contains(@class,'QuestionAnswer-content')]/img"));
		System.out.println(webElement1.getAttribute("src"));
		List<WebElement> allImg = driver.findElements(By.xpath("//div[contains(@class,'QuestionAnswer-content')]/img"));
        if(allImg.size()>=1)
        {
        	System.out.println("size:"+allImg.size());
        	
        }
        for(WebElement e: allImg)
        {
        	if(e.getAttribute("data-original")!=null)
        	{
        		System.out.println("data-original"+e.getAttribute("data-original"));
        		
        	}
        	
        }
	}
	
	
	public void getSpecifiedAnswerContent2(WebDriver driver)
	{
		 //System.out.println(driver.getPageSource());
		//WebElement webElement1= driver.findElement(By.xpath("div[class='QuestionAnswer-content']>noscript"));
		//System.out.println(webElement1.getText());
		List<WebElement> allImg = driver.findElements(By.cssSelector("img"));
		//寻找 QuestionAnswer-content 里面的图片
		//List<WebElement> allImg = driver.findElements(By.xpath("//div[contains(@class,'QuestionAnswer-content')]/img"));
        if(allImg.size()>=1)
        {
        	System.out.println("size:"+allImg.size());
        	
        }
        else
        {
        	System.out.println("size:"+allImg.size());
        	
        }
        for(WebElement e: allImg)
        {
        	if(e.getAttribute("data-original")!=null)
        	{
        		//
        		System.out.println("data-original:"+e.getAttribute("data-original"));
        		//已存在准备列表中
        		if(checkExistedByStrQueue(this.queueImgUrlToRequest1,e.getAttribute("data-original"))==true)
        		{
        			//do nothing because it exists
        			System.out.println(e.getAttribute("data-original")+" exsits to request");
        		}
        		else
        		{
        			//已存在已看过的列表中
        			if(checkVisitedByStrSet(this.queueImgUrlVisited,e.getAttribute("data-original"))==true)
            		{
            			//do nothing because it is visited
            			System.out.println(e.getAttribute("data-original")+" has been visited");
            		}
            		else
            		{
            			queueImgUrlToRequest1.add(e.getAttribute("data-original"));
            		}
        			
        		}
        		//
        		//
        	}
        	
        }
	}
	
	
	public void getPicUrl()
	{
		
		
	}
	
	//
	//down
	public void downloadPicByURL(URL url)
	{
		String fileName=new String("");
		try {
			HttpURLConnection conn = (HttpURLConnection)url.openConnection(); 
	        InputStream inputStream = conn.getInputStream();   //通过输入流获得图片数据 
	        byte[] getData = myreadInputStream(inputStream);     //获得图片的二进制数据 
	              
	        //
	        fileName+=url.toString();
	        fileName=fileName.replace(":",""); 
	        fileName=fileName.replace("/",""); 
	        fileName=fileName.replace(".",""); 
	        fileName=fileName.replace("-","");
	        //
	        fileName+=".jpg";
	        //
	        File imageFile = new File("mypic/"+fileName);   
	        FileOutputStream fos;
			fos = new FileOutputStream(imageFile);
	        fos.write(getData); 
	        fos.close(); 
	        queueImgUrlVisited.add(url.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    

              
        System.out.println(" read picture success:"+fileName); 
    } 
		
    public  byte[] myreadInputStream(InputStream inputStream) throws IOException { 
        byte[] buffer = new byte[1024]; 
        int len = 0; 
        ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
        while((len = inputStream.read(buffer)) != -1) { 
            bos.write(buffer, 0, len); 
        } 
              
        bos.close(); 
        return bos.toByteArray(); 
    } 
    //
    //
    //
    //
    //
	
	
	
	public boolean checkExistedByStrQueue(Queue<String> queue1,String strUrl)
	{
		if(queue1.contains(strUrl)==false)
		{
			return false;
			
		}
		else 
		{
			return true;
			
		}
		
	}
	
	public boolean checkVisitedByStrSet(Set<String> set1,String strUrl)
	{
		if(set1.contains(strUrl)==false)
		{
			return false;
			
		}
		else 
		{
			return true;
			
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
