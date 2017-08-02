/*
 * 
 * Copyright (C) 2004-2005, see @author in JavaDoc for the author 
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 * For contact information please visit http://woped.dhbw-karlsruhe.de
 *
 */
package org.woped.starter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.woped.core.config.ConfigurationManager;
import org.woped.editor.action.AutoUpdaterCall;
import org.woped.editor.action.DisposeWindowAction;
import org.woped.editor.action.ExecuteUpdate;
import org.woped.editor.help.action.LaunchDefaultBrowserAction;
import org.woped.gui.lookAndFeel.WopedButton;
import org.woped.gui.translations.Messages;

/**
 * @author Steffen Goehrig </a> <br>
 *         <br>
 *         
 * 15.05.2017
 */

@SuppressWarnings("serial")
public class UpdaterUI extends JDialog
{
    private JLabel              logoLabel       = null;
    private JLabel              updateTextLabel  = null;
    private JLabel              javaTextLabel   = null;
    private JLabel              homepageLabel   = null;
    private JLabel              mailtoLabel     = null;
    private JLabel              sfLabel         = null;
    private JLabel              icLabel         = null;    
    private WopedButton             closeButton     = null;
    private WopedButton             updateButton     = null;
    private WopedButton             changelogButton = null;

    private JScrollPane         updaterPanel      = null;
    private JScrollPane         changeLogPanel  = null;
    private JPanel              buttonPanel     = null;
    private boolean onStart;
    public UpdaterUI(boolean onStart)
    {
        this(null,onStart);
    }

    /**
     * Constructor for UpdaterUI.
     * 
     * @param owner
     * @throws HeadlessException
     */
    public UpdaterUI(Frame owner,boolean onStart) throws HeadlessException
    {
        super(owner, true);
        initialize(onStart);
    }

    /**
     * This method initializes and layouts the about information
     * 
     * @return void
     */
    private void initialize(boolean onStart)
    {
    	this.onStart=onStart;
        this.setVisible(false);
        this.getContentPane().setLayout(new BorderLayout());
        this.setUndecorated(false);
        this.setResizable(false);
        this.getContentPane().add(getUpdaterPanel(), BorderLayout.NORTH);
        this.getContentPane().add(getButtonPanel(), BorderLayout.SOUTH);
        this.setTitle(Messages.getTitle("OptionsAndHelp.Updater"));
        this.pack();

        if (getOwner() != null)
        {
            this.setLocation(getOwner().getX() + ((getOwner().getWidth() - this.getWidth()) / 2), getOwner().getY() + ((getOwner().getHeight() - this.getHeight()) / 2));
        } else
        {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation((screenSize.width - this.getWidth()) / 2, (screenSize.height - this.getHeight()) / 2);
        }

        this.setSize(this.getWidth(), this.getHeight());
    }

    private JScrollPane getUpdaterPanel()
    {    	
       	String[] updaterArgs       = { Messages.getWoPeDVersionWithTimestamp() };
       	String updaterText;
       	try{
       	if (updaterPanel == null)
        {
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
           	if(AutoUpdaterCall.getUpdateVerfuegbarBoolean()){
           		 updaterText	="<html><p>"+Messages.getStringReplaced("OptionsAndHelp.Updater.UItext1", updaterArgs)+"</p></html>";	
           	}
           	else{
           		 updaterText	="<html><p>"+Messages.getStringReplaced("OptionsAndHelp.Updater.UItext2", updaterArgs)+"</p></html>";	
           	}
           
            c.gridy = 2;
            c.insets = new Insets(0, 10, 0, 10);
            c.anchor = GridBagConstraints.WEST;
            updateTextLabel = new JLabel(updaterText);
            panel.add(updateTextLabel, c);
            
            if(AutoUpdaterCall.getUpdateVerfuegbarBoolean()){
            c.gridy = 3;
            c.insets = new Insets(0, 10, 0, 10);
            homepageLabel = new JLabel("<html><p>" + Messages.getString("OptionsAndHelp.Updater.UIlinktext") + "</p></html>");
            homepageLabel.addMouseListener(new LaunchDefaultBrowserAction(Messages.getString("OptionsAndHelp.Updater.UIlink"),homepageLabel));
            panel.add(homepageLabel, c);
            
            
            c.gridy = 4;
            c.insets = new Insets(0, 10, 0, 10);
           if(onStart){
            JCheckBox useByDefaultBox = new JCheckBox(
            		Messages.getString("OptionsAndHelp.Updater.DeaktivateOnStart"));
    			useByDefaultBox.setEnabled(true);
    			useByDefaultBox.setSelected(!
    					ConfigurationManager.getConfiguration().getAutoUpdateEnabled());
    			useByDefaultBox.setToolTipText("<html>"
    					+ Messages.getString("OptionsAndHelp.Updater.DeaktivateOnStart")
    					+ "</html>");
    			panel.add(useByDefaultBox, c);
    			useByDefaultBox.addChangeListener(
    					new ChangeListener(){
    				public void stateChanged(ChangeEvent e){
    					ConfigurationManager.getConfiguration().setAutoUpdateEnabled(
    							!useByDefaultBox.isSelected());	
    					
    				}
    					}		
    			);
            }
            
            else{}
     
            updaterPanel = new JScrollPane(panel);
        }
       	}
       	}
        catch(Exception e){}
       	
        return updaterPanel;
    }
    // Möglichkeit das AutoUpdate beim Starten zu deaktivieren
    /*public void deactivatePopUp(){
    	
    	
			
		}
    	
    }*/
    private JScrollPane getChangeLogPanel() throws IOException
    {
        if (changeLogPanel == null) {
        	
        	// Try to find the current working directory where Changelog.txt is located
 			String changeLog = "";
 			URL main = UpdaterUI.class.getResource("RunWoPeD.class");
 			File file = new File(main.getPath());  
 			String path = file.getAbsolutePath();
 			
 			// Check if we are running WoPeD from a jar file
 			if (path.contains("!")) {
 				int pos = path.indexOf("WoPeD-classes");
  				if (pos > -1) {
  					// Remove URL prefix
  					int ff = path.indexOf("file:") + 5;
  	 				path = path.substring(ff);
 					path = path.substring(0, pos - ff);
 					path = path.replace("%20", " ");
 				}
 			}
 			else {
 				// Running inside Eclipse, no jar file, go to folder directly
 				int pos = path.indexOf("WoPeD-Starter");
 				if (pos > -1) {
 					path = path.substring(0, pos);
 					path += "WoPeD-Installer" + File.separator + "build-tools";
 				}
 			}
        	
        	path += File.separator + "Changelog.txt";
        	
        	try {
        		int c;
            	FileReader f = new FileReader(path);
            	while ((c = f.read()) != -1) {
            		changeLog += (char)c;
            	}
            	f.close();
            } 
        	catch (IOException e) {
            	changeLog = path + " not found";
            }

            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            GridBagConstraints c1 = new GridBagConstraints();
            c1.gridy = 0;
            c1.gridx = 0;
            c1.insets = new Insets(5, 5, 5, 5);
            c1.anchor = GridBagConstraints.WEST;
            
            JTextArea text = new JTextArea();
            text.setBackground(panel.getBackground());
            text.append(changeLog);
            panel.add(text, c1);
            JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            panel2.add(panel);
            changeLogPanel = new JScrollPane(panel2);
            changeLogPanel.setPreferredSize(getUpdaterPanel().getSize());
        }
        return changeLogPanel;
    }

    private JPanel getButtonPanel()
    {
        if (buttonPanel == null)
        {
            buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridBagLayout());
            GridBagConstraints c1 = new GridBagConstraints();
            
            updateButton = new WopedButton(new ExecuteUpdate());
            updateButton.setMnemonic(KeyEvent.VK_A);
            updateButton.setIcon(new ImageIcon(getClass().getResource(Messages.getString("OptionsAndHelp.Updater.Icon"))));
            updateButton.setText(Messages.getString("OptionsAndHelp.Updater.ExecuteButton"));
           	
            try
            {
            	updateButton.setEnabled(AutoUpdaterCall.getUpdateVerfuegbarBoolean());
            }
            catch(Exception e){}
           	
            c1.gridy = 0;
            c1.gridx = 0;
            c1.insets = new Insets(10, 10, 10, 10);
            c1.anchor = GridBagConstraints.WEST;
            buttonPanel.add(updateButton, c1);

            /* Close Button */
            closeButton = new WopedButton(new DisposeWindowAction());
            closeButton.setMnemonic(KeyEvent.VK_C);
            closeButton.requestFocus();
            c1.gridy = 0;
            c1.gridx = 1;
            c1.insets = new Insets(10, 10, 10, 10);
            c1.anchor = GridBagConstraints.EAST;
            buttonPanel.add(closeButton, c1);
        }
        return buttonPanel;
    }
    
} // @jve:visual-info decl-index=0 visual-constraint="0,0"
