/*package com.java.webcrawler.catcher;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

public class MyCloseableHttpClientSSL{

	
	private MyCloseableHttpClientSSL()
	{
		
		
	}
	
	private static class SingletonHolder {
		// 静态初始化器，有JVM来保证线程安全
		private static MyCloseableHttpClientSSL instance = new MyCloseableHttpClientSSL();
	}

	
	
	
	public MyCloseableHttpClientSSL getMyInstance() {
		return SingletonHolder.instance;

	}

	
	
	public static CloseableHttpClient createClientDefaultSSL() {
		try {
			// import org.apache.http.conn.ssl.TrustStrategy;
			// import org.apache.http.ssl.SSLContextBuilder;
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有
				// import javax.security.cert.X509Certificate;
				@Override
				public boolean isTrusted(X509Certificate[] chain,
						// import javax.security.cert.CertificateException;
						String authType) {
					return true;
				}
			}).build();
			// import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
			// import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
			// import java.security.KeyManagementException;
		} catch (KeyManagementException e) {
			e.printStackTrace();
			// import java.security.NoSuchAlgorithmException;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			// import java.security.KeyStoreException;
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return HttpClients.createDefault();
	}

}
*/