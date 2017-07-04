package org.woped.editor.action;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.woped.gui.translations.Messages;

public class ExecuteUpdate extends WoPeDAction{
	 private static boolean  updateVerfuegbarBoolean=false;
	 public static boolean getUpdateVerfuegbarBoolean(){
		 return updateVerfuegbarBoolean;
	 }
	 public ExecuteUpdate(String propertiesPrefix)
	    {
	        super(propertiesPrefix);
	    }

	    public ExecuteUpdate()
	    {
	        super("Window.Dispose");
	    }
	    public void actionPerformed(ActionEvent arg0)
	    {
	    	String fileName = HttpDownloader.download();

	    	ProcessBuilder builder = new ProcessBuilder("java","-jar","woped_updater.jar",fileName);
	    	String path = new File("").getAbsolutePath();
	    	builder.directory(new File(path+"\\"));
	    	try {
				builder.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	
	    	System.exit( 0 );
	    }
	   
	    public void compareVersion(){
	    	
	    }

	}

