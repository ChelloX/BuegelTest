package org.woped.editor.action;

import java.io.File;
import java.io.IOException;
import org.woped.core.utilities.Platform;

public class HttpDownloader {

	public static void download() throws Exception {
		
		String url = "http://woped.dhbw-karlsruhe.de/woped/wp-content/uploads/";
		String saveDir = new File("").getAbsolutePath();
		String fileName = null;
		String saveName = null;
		
		if (Platform.isWindows())
			fileName = saveName = "WoPeD.zip";
		if (Platform.isUnix())
			fileName = saveName = "WoPeD.tgz";
		if (Platform.isMac()) {
			fileName = "WoPeD.app_.zip";
			saveName = "WoPeD.app";
		}
		
		try {
			HttpDownloadUtility.downloadFile(url + fileName, saveDir, saveName);

			ProcessBuilder builder = new ProcessBuilder("java", "-jar", "woped_updater.jar", saveName);
			builder.directory(new File(saveDir + File.separator));
			builder.start();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return;

	}
}