package org.woped.editor.action;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;


public class HttpDownloader {

	public static void download() {
    	
        final String urlWin = "http://woped.dhbw-karlsruhe.de/woped/wp-content/uploads/2013/03/Woped-Logo.png";
        //final String urlLix = "https://sourceforge.net/projects/woped/files/woped/3.5.3/WoPeD-install-linux-3.5.3.tgz/download";
        //final String urlMac = "https://sourceforge.net/projects/woped/files/woped/3.5.3/WoPeD-install-macos-3.5.3.dmg/download";
		String saveDir = "C:/Users/Steffen/Downloads";
		try {
			HttpDownloadUtility.downloadFile(urlWin, saveDir);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}