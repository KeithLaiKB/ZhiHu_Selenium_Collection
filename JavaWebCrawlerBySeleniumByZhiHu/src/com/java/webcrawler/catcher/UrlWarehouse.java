package com.java.webcrawler.catcher;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class UrlWarehouse {
	
	private Semaphore smphoreStrqueueCollectionUrlToRequest1=new Semaphore(1);
	private Semaphore smphoreStrqueueAnswerUrlToRequest1=new Semaphore(1);
	private Semaphore smphoreStrqueueImgUrlToRequest1=new Semaphore(1);
	
	
	// queue
	private volatile Queue<String> strqueueCollectionUrlToRequest1 = new LinkedList<String>();
	
	private volatile Queue<String> strqueueAnswerUrlToRequest1 = new LinkedList<String>();
	private volatile Set<String> strsetAnswerUrlVisited=new HashSet<String>();

	private volatile Queue<String> strqueueImgUrlToRequest1=new LinkedList<String>();
	private volatile Set<String> strsetImgUrlVisited=new HashSet<String>();
	
    //
	//
	//
	//
	public UrlWarehouse()
	{
		
		
	}
	//
	//
	//check exits in queue to request
	public boolean checkExistedByStrqueue(Queue<String> queue1,String strUrl)
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
	
	// check exits in set , visited
	public boolean checkVisitedByStrset(Set<String> set1,String strUrl)
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
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * strqueueCollectionUrlToRequest1
	 * 
	 * 
	 * @param value
	 */
	
	public void addStrqueueCollectionUrlToRequest1(String value)
	{
		try {
			this.smphoreStrqueueCollectionUrlToRequest1.acquire();
			//
			//critical resource
			strqueueCollectionUrlToRequest1.add(value);
			//
			//
			this.smphoreStrqueueCollectionUrlToRequest1.release();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	public String peekStrqueueCollectionUrlToRequest1()
	{
		String value=null;
		try {
			this.smphoreStrqueueCollectionUrlToRequest1.acquire();
			//
			//critical resource
			value=strqueueCollectionUrlToRequest1.peek();
			//
			//
			this.smphoreStrqueueCollectionUrlToRequest1.release();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return value;
	}
	
	//contains remove
	public String pollStrqueueCollectionUrlToRequest1()
	{
		String value=null;
		try {
			this.smphoreStrqueueCollectionUrlToRequest1.acquire();
			//critical resource
			//
			value=strqueueCollectionUrlToRequest1.poll();
			//
			//
			this.smphoreStrqueueCollectionUrlToRequest1.release();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return value;
	}
	
	
	public int sizeStrqueueCollectionUrlToRequest1()
	{
		int value=0;
		try {
			this.smphoreStrqueueCollectionUrlToRequest1.acquire();
			//critical resource
			//
			value=strqueueCollectionUrlToRequest1.size();
			//
			//
			this.smphoreStrqueueCollectionUrlToRequest1.release();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return value;
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * strqueueAnswerUrlToRequest1
	 * 
	 */
	
	
	public void addStrqueueAnswerUrlToRequest1(String value)
	{
		try {
			this.smphoreStrqueueAnswerUrlToRequest1.acquire();
			//
			//critical resource
			strqueueAnswerUrlToRequest1.add(value);
			//
			//
			this.smphoreStrqueueAnswerUrlToRequest1.release();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	public String peekStrqueueAnswerUrlToRequest1()
	{
		String value=null;
		try {
			this.smphoreStrqueueAnswerUrlToRequest1.acquire();
			//
			//critical resource
			value=strqueueAnswerUrlToRequest1.peek();
			//
			//
			this.smphoreStrqueueAnswerUrlToRequest1.release();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return value;
	}
	
	//contains remove
	public String pollStrqueueAnswerUrlToRequest1()
	{
		String value=null;
		try {
			this.smphoreStrqueueAnswerUrlToRequest1.acquire();
			//critical resource
			//
			value=strqueueAnswerUrlToRequest1.poll();
			//
			//
			this.smphoreStrqueueAnswerUrlToRequest1.release();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return value;
	}
	
	
	public int sizeStrqueueAnswerUrlToRequest1()
	{
		int value=0;
		try {
			this.smphoreStrqueueAnswerUrlToRequest1.acquire();
			//critical resource
			//
			value=strqueueAnswerUrlToRequest1.size();
			//
			//
			this.smphoreStrqueueAnswerUrlToRequest1.release();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return value;
	}	
	
	
	
	
	
	public boolean checkStrqueueAnswerUrlToRequest1ExsitdStr(String str)
	{
		return this.checkExistedByStrqueue(this.strqueueAnswerUrlToRequest1, str);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * StrqueueImgUrlToRequest1
	 * 
	 * 
	 * @param value
	 */
	public void addStrqueueImgUrlToRequest1(String value)
	{
		try {
			this.smphoreStrqueueImgUrlToRequest1.acquire();
			//
			//critical resource
			strqueueImgUrlToRequest1.add(value);
			//
			//
			this.smphoreStrqueueImgUrlToRequest1.release();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	public String peekStrqueueImgUrlToRequest1()
	{
		String value=null;
		try {
			this.smphoreStrqueueImgUrlToRequest1.acquire();
			//
			//critical resource
			value=strqueueImgUrlToRequest1.peek();
			//
			//
			this.smphoreStrqueueImgUrlToRequest1.release();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return value;
	}
	
	//contains remove
	public String pollStrqueueImgUrlToRequest1()
	{
		String value=null;
		try {
			this.smphoreStrqueueImgUrlToRequest1.acquire();
			//critical resource
			//
			value=strqueueImgUrlToRequest1.poll();
			//
			//
			this.smphoreStrqueueImgUrlToRequest1.release();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return value;
	}
	
	
	public int sizeStrqueueImgUrlToRequest1()
	{
		int value=0;
		try {
			this.smphoreStrqueueImgUrlToRequest1.acquire();
			//critical resource
			//
			value=strqueueImgUrlToRequest1.size();
			//
			//
			this.smphoreStrqueueImgUrlToRequest1.release();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return value;
	}	
	
	
	

	//check url to request existed
	public boolean checkStrqueueImgUrlToRequest1ExsitdStr(String str)
	{
		return this.checkExistedByStrqueue(this.strqueueImgUrlToRequest1, str);
		
		
	}
	
	
	
	
	/**
	 * 
	 * 
	 * strsetAnswerUrlVisited check visited
	 * 
	 */
	public boolean checkStrsetAnswerUrlVisitedExistedStr(String str)
	{
		return this.checkVisitedByStrset(this.strsetAnswerUrlVisited, str);
		
		
	}
	public int addStrsetAnswerUrlVisitedExistedStr(String str)
	{
		//
		int judge=0;
		//
		if(strsetAnswerUrlVisited.add(str)==true)
		{
			return 1;
			
		}
		else
		{
			return -1;
			
		}	
	}
	
	
	/**
	 * 
	 * strsetImgUrlVisited check visited
	 * 
	 * 
	 */

	public boolean checkStrsetImgUrlVisitedExistedStr(String str)
	{
		return this.checkVisitedByStrset(this.strsetAnswerUrlVisited, str);
		
		
	}
	
	
	
	
	
	
	
	
	
}
