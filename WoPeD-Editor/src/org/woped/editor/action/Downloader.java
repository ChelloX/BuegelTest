package org.woped.editor.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL; 

public class Downloader {
    public static void downloadFile(String url_str, OutputStream os)
        throws IllegalStateException, MalformedURLException,
        ProtocolException, IOException {

    URL url = new URL(url_str.replace(" ", "%20"));

    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    conn.setRequestMethod("GET");
    conn.connect();

    int responseCode = conn.getResponseCode();
    if (responseCode == HttpURLConnection.HTTP_OK) {

        byte tmp_buffer[] = new byte[4096];

        InputStream is = conn.getInputStream();

        int n;

        while ((n = is.read(tmp_buffer)) > 0) {
            os.write(tmp_buffer, 0, n);
            os.flush();
        }

    } else {
        throw new IllegalStateException("HTTP response: " + responseCode);
    }
}

public static void main(String[] args) {
    try {
    	//TODO: Hier muss noch die Versionsnummer als Parameter eingetragen werden:
        final String urlWin = "http://sourceforge.net/projects/woped/files/woped/3.5.3/WoPeD-install-windows-3.5.3.zip/download";
        final String urlMac = "http://sourceforge.net/projects/woped/files/woped/3.5.3/WoPeD-install-macos-3.5.3.dmg/download";
        final String urlLin = "http://sourceforge.net/projects/woped/files/woped/3.5.3/WoPeD-install-linux-3.5.3.tgz/download";

        FileOutputStream fos = new FileOutputStream(System
                .getProperty("user.home")
                + File.separator + "logo.gif");

        downloadFile(urlWin, fos);

        fos.close();
    } catch (Exception e) {

        e.printStackTrace();
    }
}
} 