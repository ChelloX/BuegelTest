package org.woped.editor.action;

import java.awt.event.ActionEvent;
import java.io.IOException;

@SuppressWarnings("serial")
public class ExecuteUpdate extends WoPeDAction {

	private static boolean updateAvailable = false;

	public static boolean getUpdateAvailable() {
		return updateAvailable;
	}

	public ExecuteUpdate(String propertiesPrefix) {
		super(propertiesPrefix);
	}

	public ExecuteUpdate() {
		super("Window.Dispose");
	}

	public void actionPerformed(ActionEvent arg0) {
		try {
			HttpDownloader.download();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		System.exit(0);
	}
}
