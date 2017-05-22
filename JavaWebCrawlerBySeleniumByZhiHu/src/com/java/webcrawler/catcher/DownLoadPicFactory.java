package com.java.webcrawler.catcher;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownLoadPicFactory implements Runnable{

	private MyAbstractWebSpider myWebSpider1=null;
	
	
	
	
	public DownLoadPicFactory()
	{
		
	}
	
	public DownLoadPicFactory(MyAbstractWebSpider myWebSpider1)
	{
		this.myWebSpider1=myWebSpider1;
	}
	
	
	
	//
	//down
	public int downloadPicByURL(URL url)
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
	        myWebSpider1.getUrlWarehouse1().addStrsetAnswerUrlVisitedExistedStr(url.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("fail to down load");
			return -1;
		}    

              
        System.out.println(" read picture success:"+fileName); 
        return 1;
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
	
	
	
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
/*		for(int i=0;i<1000;i++)
		{
			MyThreadSleep.sleep1s();
			MyThreadSleep.sleep1s();
			System.out.println(this.myWebSpider1.getA());
			
		}*/
		MyThreadSleep.sleep10s();
		MyThreadSleep.sleep10s();
		MyThreadSleep.sleep10s();
		MyThreadSleep.sleep10s();
		MyThreadSleep.sleep10s();
		MyThreadSleep.sleep10s();
		MyThreadSleep.sleep10s();
		MyThreadSleep.sleep10s();
		//
		//
		//
		for(;myWebSpider1.getUrlWarehouse1().sizeStrqueueImgUrlToRequest1()>=0;)
		{
			System.out.println("getStrqueueImgUrlToRequest1 size!!!!!!!!!!!!!!!!:"+myWebSpider1.getUrlWarehouse1().sizeStrqueueImgUrlToRequest1() );
			if(myWebSpider1.getUrlWarehouse1().sizeStrqueueImgUrlToRequest1()<=0)
			{
				MyThreadSleep.sleep5s();
				continue;
				
			}
			try 
			{
				System.out.println("getStrqueueImgUrlToRequest1 size@@@@@@@@@@@@@@@@@@@@@@@@@@:"+myWebSpider1.getUrlWarehouse1().sizeStrqueueImgUrlToRequest1() );
				if(this.downloadPicByURL(new URL(myWebSpider1.getUrlWarehouse1().peekStrqueueImgUrlToRequest1()))==1)
				{
					//remove
					myWebSpider1.getUrlWarehouse1().pollStrqueueImgUrlToRequest1();
				}
				else
				{
					
					
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
