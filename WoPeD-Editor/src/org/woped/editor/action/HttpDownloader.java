package org.woped.editor.action;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;


public class HttpDownloader {

	public static String download() {
    	
		String version=GetVersionWebservice.getVersionString();
		String osName=System.getProperty("os.name").toLowerCase();
		String filename="",url="";

		if(osName.contains("win")){
			filename="WoPeD-install-windows-"+version+".zip";
			url="http://woped.dhbw-karlsruhe.de/woped/wp-content/uploads/2017/06/WoPeD-install-windows-"+version+".zip";
		}else if(osName.contains("nix") || osName.contains("nux") || osName.contains("aix")){
			filename="WoPeD-install-linux-"+version+".tgz";
			url="http://woped.dhbw-karlsruhe.de/woped/wp-content/uploads/2017/06/WoPeD-install-linux-"+version+".tgz";
		}else if(osName.contains("mac") ){
			filename="WoPeD-install-macos-"+version+".dmg";
			url="http://woped.dhbw-karlsruhe.de/woped/wp-content/uploads/2017/06/WoPeD-install-macos-"+version+".dmg";
		}
		
        String saveDir = new File("").getAbsolutePath();
		try {
			HttpDownloadUtility.downloadFile(url, saveDir,filename);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return filename;
	}
}