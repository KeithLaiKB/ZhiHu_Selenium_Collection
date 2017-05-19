package com.java.webcrawler.catcher;

import java.util.List;
import java.util.concurrent.Semaphore;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.java.webcrawler.personalinfo.PersonalInfo;

public class MyWebSpider {
	//
	//
	// driver would be initial in constructor
	private static WebDriver webdriver1 = null;

	// each spider own a urlwarehouse
	private UrlWarehouse urlWarehouse1 = null;

	public static WebDriver getWebdriver1() {
		return webdriver1;
	}

	public static void setWebdriver1(WebDriver webdriver1) {
		MyWebSpider.webdriver1 = webdriver1;
	}

	public UrlWarehouse getUrlWarehouse1() {
		return urlWarehouse1;
	}

	public void setUrlWarehouse1(UrlWarehouse urlWarehouse1) {
		this.urlWarehouse1 = urlWarehouse1;
	}

	//
	//
	// constructor
	public MyWebSpider() {
		System.setProperty("webdriver.chrome.driver", "./doc/chromedriver_win32/chromedriver.exe");
		webdriver1 = new ChromeDriver();
		webdriver1.get("https://www.zhihu.com/");
		//
		//
		urlWarehouse1 = new UrlWarehouse();
		//
		//
	}

	public MyWebSpider(UrlWarehouse urlWarehouse1) {
		this.urlWarehouse1 = urlWarehouse1;
	}

	//
	//
	//
	//
	//
	//
	// process
	public void login(WebDriver driver) {

		//
		// Find the text input element by its name
		// WebElement element = driver.findElement(By.name("wd"));
		// WebElement
		// myUserInfoElementInOrgWeb1=driver.findElement(By.id(":0"));

		// Enter something to search for
		MyThreadSleep.sleep2s();
		WebElement loginElementInOrgWeb1 = driver.findElement(By.linkText("登录"));
		loginElementInOrgWeb1.click();
		// enter account
		MyThreadSleep.sleep50ms();
		WebElement loginAccountElementInOrgWeb1 = driver.findElement(By.name("account"));
		loginAccountElementInOrgWeb1.sendKeys(PersonalInfo.account);
		// enter password
		MyThreadSleep.sleep10ms();
		WebElement loginPasswordElementInOrgWeb1 = driver.findElement(By.name("password"));
		loginPasswordElementInOrgWeb1.sendKeys(PersonalInfo.password);
		//
		// submit
		MyThreadSleep.sleep5s();
		// WebElement
		// loginSubmitElementInOrgWeb1=driver.findElement(By.tagName("input"));
		List<WebElement> allInputs = driver.findElements(By.tagName("button"));
		if (allInputs.size() > 1) {
			System.out.println("saonima");

		}
		// 只打印所有文本框的值
		for (WebElement e : allInputs) {
			if (e.getAttribute("type").equals("submit")) {
				System.out.println("damn");
				e.click();
				break;
			}

		}
		MyThreadSleep.sleep3s();
	}

	// enter user info page
	public void enterUserInfo(WebDriver driver) {
		MyThreadSleep.sleep3s();
		WebElement myUserInfoElementInHomePage1 = driver.findElement(By.id(":0"));
		myUserInfoElementInHomePage1.click();
	}

	// enter my collections
	public void enterMyCollectionsPage(WebDriver driver) {
		MyThreadSleep.sleep1s();
		WebElement collectionsEntranceInUserInfoWeb1 = driver.findElement(By.partialLinkText("收藏夹"));
		collectionsEntranceInUserInfoWeb1.click();
	}

	// get each url of collections
	public void getCollectionsUrl(WebDriver driver) {
		MyThreadSleep.sleep1s();
		List<WebElement> allA = driver.findElements(By.tagName("a"));
		if (allA.size() > 1) {
			System.out.println("saonima");

		}
		for (WebElement e : allA) {
			// collection/...
			if (e.getAttribute("href").contains("collection/")) {
				// 直接获得的是绝对路径
				System.out.println("damn:" + e.getAttribute("href") + e.getText());
				//
				//
				urlWarehouse1.addStrqueueCollectionUrlToRequest1(e.getAttribute("href"));
				// strqueueCollectionUrlToRequest1.add(e.getAttribute("href"));
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
	// 进入收藏夹后，只拿问题的URL，(因为会有答案的一些摘要，所以要除去) 每一个driver过来可能就代表一个页面
	public void getQuesionUrlWithoutAnswerUrlInSpecifiedPage(WebDriver driver) {
		MyThreadSleep.sleep1s();
		List<WebElement> allA = driver.findElements(By.tagName("a"));
		if (allA.size() > 1) {
			System.out.println("size:" + allA.size());

		}
		for (WebElement e : allA) {
			if (e.getAttribute("href") != null) {
				if (e.getAttribute("href").contains("question") == true
						&& e.getAttribute("href").contains("answer") == false) {
					// 直接获得的是绝对路径
					System.out.println("damn:" + e.getAttribute("href") + e.getText());
					//
					//
					// 已存在准备列表中
					if (urlWarehouse1.checkStrqueueAnswerUrlToRequest1ExsitdStr(e.getAttribute("href")) == true) {
						// do nothing because it exists
						System.out.println(e.getAttribute("href") + " exsits to request");
					} else {
						// 已存在已看过的列表中
						if (urlWarehouse1.checkStrsetAnswerUrlVisitedExistedStr(e.getAttribute("href")) == true) {
							// do nothing because it is visited
							System.out.println(e.getAttribute("href") + " has been visited");
						} else {
							urlWarehouse1.addStrqueueAnswerUrlToRequest1(e.getAttribute("href"));
						}

					}
					//
				}

			}

		}

	}

	// 进入收藏夹后，只拿问题答案的URL，(这些答案都是精选的) 每一个driver过来可能就代表一个页面
	public void getQuesionUrlWithAnswerUrlInSpecifiedPage(WebDriver driver) {
		MyThreadSleep.sleep1s();
		List<WebElement> allA = driver.findElements(By.tagName("a"));
		if (allA.size() > 1) {
			System.out.println("size:" + allA.size());

		}
		for (WebElement e : allA) {
			if (e.getAttribute("href") != null) {
				if (e.getAttribute("href").contains("question") == true
						&& e.getAttribute("href").contains("answer") == true) {
					// 直接获得的是绝对路径
					System.out.println("damn:" + e.getAttribute("href") + e.getText());
					//
					//
					//
					// 已存在准备列表中
					if (urlWarehouse1.checkStrqueueAnswerUrlToRequest1ExsitdStr(e.getAttribute("href")) == true) {
						// do nothing because it exists
						System.out.println(e.getAttribute("href") + " exsits to request");
					} else {
						// 已存在已看过的列表中
						if (urlWarehouse1.checkStrsetAnswerUrlVisitedExistedStr(e.getAttribute("href")) == true) {
							// do nothing because it is visited
							System.out.println(e.getAttribute("href") + " has been visited");
						} else {
							urlWarehouse1.addStrqueueAnswerUrlToRequest1(e.getAttribute("href"));
						}

					}
					//
					//
					//
					/*
					 * if(checkVisitedBySet(this.queueAnswerUrlVisited,e.
					 * getAttribute("href"))==true) { //do nothing because it is
					 * visited
					 * System.out.println(e.getAttribute("href")+" exsits"); }
					 * else {
					 * queueAnswerUrlToRequest1.add(e.getAttribute("href")); }
					 */
					//

					//
				}

			}

		}

	}

	public void getSpecifiedAnswerContent2(WebDriver driver) {
		MyThreadSleep.sleep2s();
		// System.out.println(driver.getPageSource());
		// WebElement webElement1=
		// driver.findElement(By.xpath("div[class='QuestionAnswer-content']>noscript"));
		// System.out.println(webElement1.getText());
		List<WebElement> allImg = driver.findElements(By.cssSelector("img"));
		// 寻找 QuestionAnswer-content 里面的图片
		// List<WebElement> allImg =
		// driver.findElements(By.xpath("//div[contains(@class,'QuestionAnswer-content')]/img"));
		if (allImg.size() >= 1) {
			System.out.println("size in getSpecifiedAnswerContent2:" + allImg.size());

		} else {
			System.out.println("size in getSpecifiedAnswerContent2:" + allImg.size());

		}
		for (WebElement e : allImg) {
			if (e.getAttribute("data-original") != null) {
				//
				System.out.println("data-original:" + e.getAttribute("data-original"));
				// 已存在准备列表中
				if (urlWarehouse1.checkStrqueueImgUrlToRequest1ExsitdStr(e.getAttribute("data-original")) == true) {
					// do nothing because it exists
					System.out.println(e.getAttribute("data-original") + " exsits to request");
				} else {
					// 已存在已看过的列表中
					if (urlWarehouse1.checkStrsetImgUrlVisitedExistedStr(e.getAttribute("data-original")) == true) {
						// do nothing because it is visited
						System.out.println(e.getAttribute("data-original") + " has been visited");
					} else {
						// 将img的url放进队列中
						urlWarehouse1.addStrqueueImgUrlToRequest1(e.getAttribute("data-original"));
						System.out.println(urlWarehouse1.sizeStrqueueImgUrlToRequest1());
					}

				}
				//
				//
			} else {
				System.out.println("fkdata-original:" + e.getAttribute("src"));
			}

		}
	}

}
