package org.woped.editor.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.Certificate;
import javax.security.cert.X509Certificate;

import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

/**
 * A utility that downloads a file from a URL.
 * @author www.codejava.net
 *
 */
public class HttpDownloadUtility {
	private static final int BUFFER_SIZE = 4096;

	/**
	 * Downloads a file from a URL
	 * param fileURL HTTP URL of the file to be downloaded
	 * param saveDir path of the directory to save the file
	 * throws IOException
	 */
	public static void downloadFile(String fileURL, String saveDir)
			throws IOException {

		URL url = new URL(fileURL);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

		int responseCode = httpConn.getResponseCode();

		// always check HTTP response code first
		if (responseCode == HttpURLConnection.HTTP_OK) {
			String fileName = "";
			String disposition = httpConn.getHeaderField("Content-Disposition");
			String contentType = httpConn.getContentType();
			int contentLength = httpConn.getContentLength();

			if (disposition != null) {
				// extracts file name from header field
				int index = disposition.indexOf("filename=");
				if (index > 0) {
					fileName = disposition.substring(index + 10,
							disposition.length() - 1);
				}
			} else {
				// extracts file name from URL
				//fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,fileURL.length());
				fileName ="icon";
				//fileName ="woped.tgz";
				//fileName ="woped.dmg";
			}

			System.out.println("Content-Type = " + contentType);
			System.out.println("Content-Disposition = " + disposition);
			System.out.println("Content-Length = " + contentLength);
			System.out.println("fileName = " + fileName);

			// opens input stream from the HTTP connection
			InputStream inputStream = httpConn.getInputStream();
			String saveFilePath = saveDir + File.separator + fileName;
			
			// opens an output stream to save into file
			FileOutputStream outputStream = new FileOutputStream(saveFilePath);

			int bytesRead = -1;
			byte[] buffer = new byte[BUFFER_SIZE];
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			outputStream.close();
			inputStream.close();

			System.out.println("File downloaded");
		} else {
			System.out.println("No file to download. Server replied HTTP code: " + responseCode);
		}
		httpConn.disconnect();
	}
	/*public SSLSocketFactory createSslSocketFactory() throws Exception {
			TrustManager[] byPassTrustManagers = new TrustManager[] { new X509TrustManager() {
			 public X509Certificate[] getAcceptedIssuers() {
			 return new X509Certificate[0];
			}
			public void checkClientTrusted(X509Certificate[] chain, String authType) {
			}
			public void checkServerTrusted(X509Certificate[] chain, String authType) {
			 }
			@Override
			public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
					throws CertificateException {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
					throws CertificateException {
				// TODO Auto-generated method stub
				
			}
			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				// TODO Auto-generated method stub
				return null;
			}
			} };
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, byPassTrustManagers, new SecureRandom());return sslContext.getSocketFactory();
				
	}*/
	public void trustMethod(){
		/*
		// Load CAs from an InputStream
		// (could be from a resource or ByteArrayInputStream or ...)
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		// From https://www.washington.edu/itconnect/security/ca/load-der.crt
		InputStream caInput = new BufferedInputStream(new FileInputStream("load-der.crt"));
		@SuppressWarnings("deprecation")
		Certificate ca;
		try {
		    ca =cf.generateCertificate(caInput);
		    System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
		} finally {
		    caInput.close();
		}

		// Create a KeyStore containing our trusted CAs
		String keyStoreType = KeyStore.getDefaultType();
		KeyStore keyStore = KeyStore.getInstance(keyStoreType);
		keyStore.load(null, null);
		keyStore.setCertificateEntry("ca", ca);

		// Create a TrustManager that trusts the CAs in our KeyStore
		String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
		tmf.init(keyStore);

		// Create an SSLContext that uses our TrustManager
		SSLContext context = SSLContext.getInstance("TLS");
		context.init(null, tmf.getTrustManagers(), null);

		// Tell the URLConnection to use a SocketFactory from our SSLContext
		URL url = new URL("https://certs.cac.washington.edu/CAtest/");
		HttpsURLConnection urlConnection =
		    (HttpsURLConnection)url.openConnection();
		urlConnection.setSSLSocketFactory(context.getSocketFactory());
		InputStream in = urlConnection.getInputStream();
		copyInputStreamToOutputStream(in, System.out);
*/
	}
	
}