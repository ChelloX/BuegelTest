package org.woped.editor.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import org.woped.gui.translations.Messages;

public class AutoUpdaterCall extends WoPeDAction{
	 public static boolean getUpdateVerfuegbarBoolean(){
		 return compareVersion();	 
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
	    {	    }

	 public static boolean compareVersion(){
	    	boolean homepageVersionIsLonger=false;
	    	ArrayList<Integer> homepageVersion=GetVersionWebservice.getVersion();
	    	
	    	//Provisorische Arraylist, da die Homepageversion erst bei einer 
	    	//deployten Anwendung möglich ist

	    	ArrayList<Integer> applicationVersion=new ArrayList<Integer>();
	    	try{
	    		GetVersionWebservice.seperateVersion(Messages.getString("Application.Version"));
	    	}
	    	catch(Exception e){
		    	applicationVersion.add(5);
		    	applicationVersion.add(0);
		    	applicationVersion.add(0);
	    	}
	    	int shorterVersionNumber;
	    	if(homepageVersion.size()<=applicationVersion.size())
	    	{
	    		shorterVersionNumber=homepageVersion.size();
	    	}else{
	    		homepageVersionIsLonger=true;
	    		shorterVersionNumber=applicationVersion.size();
	    	}
	    		for(int i=0;i<shorterVersionNumber;i++)
	    			{
	    			if(homepageVersion.get(i)>applicationVersion.get(i)){return true;}
	    			}
	    		// Dieser Fall tritt ein, wenn die Werte der for-Schleife gleich sind,
	    		// aber die HP-Version eine weitere Versionsendung besitzt
	    		if(homepageVersionIsLonger){
	    			return true;
	    		}
	    		return false;
	    	
	    }
    }


