package org.woped.editor.action;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
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

public class AutoUpdaterCall extends WoPeDAction{
	 private static boolean  updateVerfuegbarBoolean=false;
	 public static boolean getUpdateVerfuegbarBoolean(){
		 updateVerfuegbarBoolean=true;
		 System.out.println("UpdateVerfügbarboolean ist "+updateVerfuegbarBoolean);
		 return updateVerfuegbarBoolean;	 
	 }
	 public AutoUpdaterCall(String propertiesPrefix)
	    {
	        super(propertiesPrefix);
	    }

	    public AutoUpdaterCall()
	    {
	        super("Window.Dispose");
	    }
	    public void actionPerformed(ActionEvent arg0)
	    {
	    	compareVersion();
	        /*Component source = (Component) arg0.getSource();
	        while (source != null && !(source instanceof Window))
	        {
	            source = source.getParent();
	        }
	        if (source instanceof Window)
	        {
	            ((Window) source).dispose();
	        }*/
	    }
	    //public static boolean performAutoUpdate(){
		//System.out.println("AutoUpdater ist aktiviert");
		//return true;
	    public void compareVersion(){
	    	int[] homepageVersion=getHomepageVersion();

	    	String version= Messages.getString("Application.Version");
	    	// Aufteilen der Version in Int
	    	int ver1=3,ver2=5,ver3=3;
	    	if(ver1>homepageVersion[0]){
	    		updateVerfuegbarBoolean=true;
	    	}else if(ver2>homepageVersion[1]){
	    		updateVerfuegbarBoolean=true;
	    	}else if(ver3>homepageVersion[2]){
	    		updateVerfuegbarBoolean=true;
	    	}
	    	
	    	//System.out.println("Version von HP:" +ausgabe);
	    	System.out.println("Version von Woped:"+ version);
	    }
	    public int[] getHomepageVersion(){
	          
	    	final String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; de; rv:1.8.1.12) Gecko/20080201 Firefox/2.0.0.12";
	            String[] strArray=new String[10];
	            try {
	                URL url = new URL("http://woped.dhbw-karlsruhe.de/woped/?page_id=22");
	                URLConnection conn = url.openConnection();
	                conn.addRequestProperty("User-Agent", userAgent);               
	                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	                String str;
	                StringBuilder builder = new StringBuilder(1024);
	                while ((str = in.readLine()) != null) {
	                   builder.append(str);
	                   builder.append("\n");
	                }
	                in.close();
	                str= builder.toString();

	                // REGEX anwenden: http://regexr.com/ (?:Current Release \()(\d\1)\.?(\d\2)?\.?(\d\3)?
	                String reExTest="Current Release";
	                System.out.println(str.indexOf(reExTest));
	                int ver3=0,ver2=0,ver1=Integer.parseInt(str.substring((str.indexOf(reExTest)+reExTest.length()+2),str.indexOf(reExTest)+reExTest.length()+3));
	                System.out.println("Var1 ist:"+ver1);
	                try{
	                	ver2=Integer.parseInt(str.substring((str.indexOf(reExTest)+reExTest.length()+4),str.indexOf(reExTest)+reExTest.length()+5));
	                	 System.out.println("Var1 ist:"+ver2);
	                }catch(Exception e){}
	                try{
	                	ver3=Integer.parseInt(str.substring((str.indexOf(reExTest)+reExTest.length()+6),str.indexOf(reExTest)+reExTest.length()+7));
	                	System.out.println("Var1 ist:"+ver3);
	                }catch(Exception e){}
            	   
	                return new int []{ver1,ver2,ver3};
	            	}
	            	catch (MalformedURLException e)
	                {   System.out.println( e.getMessage());
	                return new int[]{0,0,0};
	                }
	                catch (IOException e)
	                {   System.out.println( e.getMessage());
	                return new int[]{0,0,0};
	                }
	    }

	}

