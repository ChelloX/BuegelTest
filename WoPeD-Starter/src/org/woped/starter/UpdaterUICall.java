package org.woped.starter;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
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

import javax.swing.JDialog;
import javax.swing.JFrame;

import org.woped.editor.action.WoPeDAction;
import org.woped.gui.translations.Messages;


public class UpdaterUICall extends WoPeDAction{	 
	 public UpdaterUICall(String propertiesPrefix)
	    {
	        super(propertiesPrefix);
	    }

	    public UpdaterUICall()
	    {
	        super("Window.Dispose");
	    }
	    public void actionPerformed(ActionEvent arg0)
	    {
	    	UpdaterUI updater= new UpdaterUI();

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            updater.setLocation((screenSize.width - updater.getWidth()) / 2, (screenSize.height - updater.getHeight()) / 2);
        
			updater.setVisible(true);
	    }
}


