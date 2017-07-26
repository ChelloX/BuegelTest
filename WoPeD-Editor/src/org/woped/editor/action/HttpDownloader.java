package org.woped.editor.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;


public class HttpDownloader {
	static String filename="";
	
	public static String download() throws Exception {
    	
		
		String url="http://woped.dhbw-karlsruhe.de/woped/wp-content/uploads/",fileEnding="";
		fileEnding=checkOS();
		url=url+checkUploadTime(url,fileEnding)+fileEnding;
		
        String saveDir = new File("").getAbsolutePath();
		try {
			System.out.println(url);
			HttpDownloadUtility.downloadFile(url, saveDir,filename);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return filename;
	}
	// Bei Upload eines Files auf der WoPeD-Homepage wird das Jahr und der Monat des Uploads mit abgespeichert. 
	// Mit dieser Methode wird die Monatsnummer bis 2030 angefragt und zurückgegeben:
	public static String checkUploadTime(String url,String fileEnding){
		for(int i=2017;i<2030;i++){
			for(int y=1;y<=12;y++){
			
			InputStream is = null;
		      try {
		    	 if(y<10){
		    		 URL urlTest = new URL(url+i+"/0"+y+fileEnding);
			         URLConnection con = urlTest.openConnection(); 
			         is = con.getInputStream();
			         return ""+i+"/0"+y;
		    	 }else{
		    		 URL urlTest = new URL(url+i+"/"+y+fileEnding);
			         URLConnection con = urlTest.openConnection(); 
			         is = con.getInputStream();
			         return ""+i+"/"+y;
		    	 }		         
		      } catch (Exception e) {
		    	  System.out.println("Error bei checkUploadTime");
		      }finally{
		         if(is!= null)
		            try {is.close();} catch (IOException e){ }
		      }
			}
		}	
		return ""+"bla";
	}
	public static String checkOS(){
		String osName=System.getProperty("os.name").toLowerCase();
		if(osName.contains("win")){
			filename="WoPeD-install-windows.zip";
			return "/WoPeD.zip";
		}else if(osName.contains("nix") || osName.contains("nux") || osName.contains("aix")){
			filename="WoPeD-install-linux.tgz";
			return "/WoPed.tgz";
		}else if(osName.contains("mac") ){
			filename="WoPeD-install-macos.app";
			return "/WoPeD.app_.zip";
		}else{
			return "/WoPeD.zip";}
	
	}
}