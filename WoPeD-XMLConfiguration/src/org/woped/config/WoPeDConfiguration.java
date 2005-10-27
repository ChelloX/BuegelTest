package org.woped.config;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Vector;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.woped.core.config.ConfigurationManager;
import org.woped.core.config.IConfiguration;
import org.woped.core.config.LoggerManager;

/**
 * @author <a href="mailto:slandes@kybeidos.de">Simon Landes </a> <br>
 *         <br>
 * 
 * All configuration data for woped. Could Read/save the configuration permament
 * from/to file. During runtime the configuration data is stored in the
 * generated XMLBeans Objects.
 * 
 * 10.10.2003
 */
public class WoPeDConfiguration implements IConfiguration {
	private static ConfigurationDocument confDoc = null;

	private static String currentWorkingDir = null;

	// DEFAULT Values
	public static String CONFIG_FILE = "." + File.separator + "configuration.xml";

	public static String DEFAULT_HOME = "." + File.separator + "nets" + File.separator;

	public static String CONFIG_BACKUP_FILE = "/org/woped/config/configuration.xml";

	private static int RECENTFILES_SIZE = 10;

	private int modelProcessor = 0;

	private static XmlOptions xmlOptions = new XmlOptions();

	private Vector runtimeRecentFiles = new Vector(RECENTFILES_SIZE);

	public WoPeDConfiguration() {
		initConfig();
	}

	private static ConfigurationDocument getDocument() {
		return confDoc;
	}

	public boolean initConfig() {
		// Set XML Options
		xmlOptions.setUseDefaultNamespace();
		xmlOptions.setSavePrettyPrint();
		xmlOptions.setSavePrettyPrintIndent(2);
		return readConfig();
	}

	/**
	 * TODO: DOCUMENTATION (silenco)
	 * 
	 * @return
	 */
	public boolean readConfig() {
		if (new File(CONFIG_FILE).exists()) {
			return readConfig(new File(CONFIG_FILE));
		} else {
			LoggerManager.warn(Constants.CONFIG_LOGGER, "User-Configuration not found. Try using Backup Configuration.");
			return readConfig(WoPeDConfiguration.class.getResourceAsStream(CONFIG_BACKUP_FILE));
		}
	}

	/**
	 * TODO: DOCUMENTATION (silenco)
	 * 
	 * @param is
	 * @return
	 */
	public boolean readConfig(InputStream is) {
		try {
			confDoc = ConfigurationDocument.Factory.parse(is);
			return readConfig(confDoc);
		} catch (XmlException e) {
			LoggerManager.error(Constants.CONFIG_LOGGER, "ERROR during parsing file.");
			return false;
		} catch (IOException e) {
			LoggerManager.error(Constants.CONFIG_LOGGER, "ERROR during reading file.");
			return false;
		}
	}

	/**
	 * TODO: DOCUMENTATION (silenco)
	 * 
	 * @param file
	 * @return
	 */
	public boolean readConfig(File file) {
		if (file.exists()) {
			try {
				// Parse the instance into the type generated from the schema.
				confDoc = ConfigurationDocument.Factory.parse(file);
				return readConfig(confDoc);
			} catch (XmlException e) {
				LoggerManager.error(Constants.CONFIG_LOGGER, "ERROR during parsing file.");
				return false;
			} catch (IOException e) {
				LoggerManager.error(Constants.CONFIG_LOGGER, "ERROR during reading file.");
				return false;
			}
		} else {
			LoggerManager.error(Constants.CONFIG_LOGGER, "File does not exist.");
			return false;
		}
	}

	/**
	 * TODO: DOCUMENTATION (silenco)
	 * 
	 * @param confDoc
	 * @return
	 */
	public boolean readConfig(ConfigurationDocument confDoc) {

		// Create an instance of a type generated from schema to hold the
		// XML.
		ConfigurationDocument.Configuration config;
		if (confDoc != null && (config = confDoc.getConfiguration()) != null) {
			// GUI
			setLookAndFeel(config.getGui().getWindow().getLookAndFeel());
			setWindowX(config.getGui().getWindow().getX());
			setWindowY(config.getGui().getWindow().getY());
			setWindowSize(new Dimension(config.getGui().getWindow().getWidth(), config.getGui().getWindow().getHeight()));
			// SYSTEM
			// Recent
			runtimeRecentFiles = new Vector();
			for (int i = 0; i < getDocument().getConfiguration().getGeneral().getRecentFilesArray().length; i++) {
				if (getDocument().getConfiguration().getGeneral().getRecentFilesArray()[i].getName() != null)
					runtimeRecentFiles.addElement(new WoPeDRecentFile(getDocument().getConfiguration().getGeneral().getRecentFilesArray()[i].getName(), getDocument().getConfiguration().getGeneral()
							.getRecentFilesArray()[i].getPath()));
			}
			// on the xmlbeans-model
			if (config.getGeneral().getHomedir() == null || config.getGeneral().getHomedir().equals("")) {
				if (!new File(DEFAULT_HOME).mkdir()) {
					setHomedir(DEFAULT_HOME);
					LoggerManager.error(Constants.CONFIG_LOGGER, "Could not create standard homedir");
				} else {
					LoggerManager.info(Constants.CONFIG_LOGGER, "standard homedir created");
				}
			} else {
				setHomedir(config.getGeneral().getHomedir());
				// LoggerManager.info(Constants.CONFIG_LOGGER,
				// config.getGeneral().getHomedir());
			}

			// ...Import
			setImportToolspecific(config.getTools().getImporting().getToolspecific());
			// ...Export
			setExportToolspecific(config.getTools().getExporting().getToolspecific());
			// TPN Export
			setTpnSaveElementAsName(config.getTools().getExporting().getTpnExportElementAsName());
			// ...Tools
			setUseWoflan(config.getTools().getWoflan().getUseWoflan());
			setWoflanPath(config.getTools().getWoflan().getWoflanPath());
			// Editor
			setEditingOnCreation(config.getEditor().getEditOnCreation());
			setInsertCOPYwhenCopied(config.getEditor().getInsertCopy());
			setSmartEditing(config.getEditor().getSmartEditing());

			LoggerManager.info(Constants.CONFIG_LOGGER, "Configuration loaded.");
			return true;
		} else {
			LoggerManager.error(Constants.CONFIG_LOGGER, "Could not load Configuration.");
			return false;
		}
	}

	/**
	 * TODO: DOCUMENTATION (silenco)
	 * 
	 * @return
	 */
	public boolean saveConfig() {
		return saveConfig(new File(CONFIG_FILE));
	}

	/**
	 * TODO: DOCUMENTATION (silenco)
	 * 
	 * @param file
	 * @return
	 */
	public boolean saveConfig(File file) {
		try {
			// Map the recent file!
			getDocument().getConfiguration().getGeneral().setRecentFilesArray(new RecentFile[0]);
			RecentFile xmlRecent;
			for (Iterator iter = runtimeRecentFiles.iterator(); iter.hasNext();) {
				WoPeDRecentFile recent = (WoPeDRecentFile) iter.next();
				xmlRecent = getDocument().getConfiguration().getGeneral().addNewRecentFiles();
				xmlRecent.setName(recent.getName());
				xmlRecent.setPath(recent.getPath());

			}

			getDocument().save(file, xmlOptions);
			LoggerManager.info(Constants.CONFIG_LOGGER, "Configuration saved.");
			return true;
		} catch (IOException e) {
			LoggerManager.error(Constants.CONFIG_LOGGER, "COULD NOT SAVE CONFIGURATION");
			return false;
		}
	}

	/**
	 * Returns the editingOnCreation.
	 * 
	 * @return boolean
	 */
	public boolean isEditingOnCreation() {
		return getDocument().getConfiguration().getEditor().getEditOnCreation();
	}

	/**
	 * Sets the editingOnCreation.
	 * 
	 * @param editingOnCreation
	 *            The editingOnCreation to set
	 */
	public void setEditingOnCreation(boolean editingOnCreation) {
		if (editingOnCreation != getDocument().getConfiguration().getEditor().getEditOnCreation()) {
			getDocument().getConfiguration().getEditor().setEditOnCreation(editingOnCreation);
		}
	}

	/**
	 * Returns the insertCOPYwhencopied.
	 * 
	 * @return boolean
	 */
	public boolean isInsertCOPYwhenCopied() {
		return getDocument().getConfiguration().getEditor().getInsertCopy();
	}

	/**
	 * Sets the insertCOPYwhencopied.
	 * 
	 * @param insertCOPYwhencopied
	 *            The insertCOPYwhencopied to set
	 */
	public void setInsertCOPYwhenCopied(boolean insertCOPYwhenCopied) {
		getDocument().getConfiguration().getEditor().setInsertCopy(insertCOPYwhenCopied);
	}

	/**
	 * Returns the exportToolspecific.
	 * 
	 * @return boolean
	 */
	public boolean isExportToolspecific() {
		return getDocument().getConfiguration().getTools().getExporting().getToolspecific();
	}

	/**
	 * Returns the importToolspecific.
	 * 
	 * @return boolean
	 */
	public boolean isImportToolspecific() {
		return getDocument().getConfiguration().getTools().getImporting().getToolspecific();
	}

	/**
	 * Sets the exportToolspecific.
	 * 
	 * @param exportToolspecific
	 *            The exportToolspecific to set
	 */
	public void setExportToolspecific(boolean exportToolspecific) {
		getDocument().getConfiguration().getTools().getExporting().setToolspecific(exportToolspecific);
	}

	/**
	 * Sets the importToolspecific.
	 * 
	 * @param importToolspecific
	 *            The importToolspecific to set
	 */
	public void setImportToolspecific(boolean importToolspecific) {
		getDocument().getConfiguration().getTools().getImporting().setToolspecific(importToolspecific);
	}

	/**
	 * Returns the recentFiles.
	 * 
	 * @return Vector
	 */
	public Vector getRecentFiles() {
		return runtimeRecentFiles;
	}

	/**
	 * TODO: optimize?
	 * 
	 * @param name
	 * @param path
	 */
	public void addRecentFile(String name, String path) {
		try {
			WoPeDRecentFile recent = new WoPeDRecentFile(name, path);
			// delete the old entry if exists.
			for (int idx = 0; idx < runtimeRecentFiles.size(); idx++) {
				if (((org.woped.config.WoPeDRecentFile) runtimeRecentFiles.get(idx)).getPath().equals(path)) {
					// delete the entry
					runtimeRecentFiles.remove(idx);
				}
			}
			// add the the new one only if file exists
			if (new File(recent.getPath()).exists()) {
				if (runtimeRecentFiles.size() == RECENTFILES_SIZE)
					runtimeRecentFiles.remove(RECENTFILES_SIZE - 1);
				runtimeRecentFiles.add(0, recent);
			}
		} catch (NullPointerException ne) {
			LoggerManager.error(Constants.CONFIG_LOGGER, "Could not store Recentfile data.");
		}

	}

	public void removeRecentFile(String name, String path) {
		// delete the old entry if exists.
		for (int idx = 0; idx < runtimeRecentFiles.size(); idx++) {
			if (((org.woped.config.WoPeDRecentFile) runtimeRecentFiles.get(idx)).getPath().equals(path)) {
				// delete the entry
				runtimeRecentFiles.remove(idx);
			}
		}
	}

	public void removeAllRecentFiles() {
		runtimeRecentFiles = new Vector(RECENTFILES_SIZE);
	}

	/**
	 * Returns the windowSize.
	 * 
	 * @return Dimension
	 */
	public Dimension getWindowSize() {
		return new Dimension(getDocument().getConfiguration().getGui().getWindow().getWidth(), getDocument().getConfiguration().getGui().getWindow().getHeight());
	}

	/**
	 * Returns the windowX.
	 * 
	 * @return int
	 */
	public int getWindowX() {
		return getDocument().getConfiguration().getGui().getWindow().getX();
	}

	/**
	 * Returns the windowY.
	 * 
	 * @return int
	 */
	public int getWindowY() {
		return getDocument().getConfiguration().getGui().getWindow().getY();
	}

	/**
	 * Sets the windowSize.
	 * 
	 * @param windowSize
	 *            The windowSize to set
	 */
	public void setWindowSize(Dimension windowSize) {
		getDocument().getConfiguration().getGui().getWindow().setHeight((int) windowSize.getHeight());
		getDocument().getConfiguration().getGui().getWindow().setWidth((int) windowSize.getWidth());
	}

	/**
	 * Sets the windowX.
	 * 
	 * @param windowX
	 *            The windowX to set
	 */
	public void setWindowX(int windowX) {
		getDocument().getConfiguration().getGui().getWindow().setX(windowX);
	}

	/**
	 * Sets the windowY.
	 * 
	 * @param windowY
	 *            The windowY to set
	 */
	public void setWindowY(int windowY) {
		getDocument().getConfiguration().getGui().getWindow().setY(windowY);
	}

	/**
	 * Returns the homedir.
	 * 
	 * @return String
	 */
	public String getHomedir() {
		if (isHomedirSet())
			return getDocument().getConfiguration().getGeneral().getHomedir();
		else
			return DEFAULT_HOME;
	}

	public boolean isHomedirSet() {
		return !(getDocument().getConfiguration().getGeneral().getHomedir() == null || getDocument().getConfiguration().getGeneral().getHomedir() == "");
	}

	/**
	 * Sets the homedir.
	 * 
	 * @param homedir
	 *            The homedir to set
	 */
	public void setHomedir(String homedir) {
		getDocument().getConfiguration().getGeneral().setHomedir(homedir);
	}

	/**
	 * Sets the recentFiles.
	 * 
	 * @param recentFiles
	 *            The recentFiles to set / public void setRecentFiles(Vector
	 *            recentFiles) {
	 *            getDocument().getConfiguration().getGeneral().setRecentFilesArray((RecentFile[])recentFiles.toArray()); }
	 */

	/**
	 * Returns the useWoflan.
	 * 
	 * @return boolean
	 */
	public boolean isUseWoflan() {
		return getDocument().getConfiguration().getTools().getWoflan().getUseWoflan();
	}

	/**
	 * Returns the woflanPath.
	 * 
	 * @return String
	 */
	public String getWoflanPath() {
		return getDocument().getConfiguration().getTools().getWoflan().getWoflanPath();
	}

	/**
	 * Sets the useWoflan.
	 * 
	 * @param useWoflan
	 *            The useWoflan to set
	 */
	public void setUseWoflan(boolean useWoflan) {
		getDocument().getConfiguration().getTools().getWoflan().setUseWoflan(useWoflan);
	}

	/**
	 * Sets the woflanPath.
	 * 
	 * @param woflanPath
	 *            The woflanPath to set
	 */
	public void setWoflanPath(String woflanPath) {
		getDocument().getConfiguration().getTools().getWoflan().setWoflanPath(woflanPath);
	}

	/**
	 * Returns the smartEditing.
	 * 
	 * @return boolean
	 */
	public boolean isSmartEditing() {
		return getDocument().getConfiguration().getEditor().getSmartEditing();
	}

	/**
	 * Sets the smartEditing.
	 * 
	 * @param smartEditing
	 *            The smartEditing to set
	 */
	public void setSmartEditing(boolean smartEditing) {
		getDocument().getConfiguration().getEditor().setSmartEditing(smartEditing);
	}

	/**
	 * TODO: DOCUMENTATION (silenco)
	 * 
	 * @return
	 */
	public boolean isTpnSaveElementAsName() {
		return getDocument().getConfiguration().getTools().getExporting().getTpnExportElementAsName();
	}

	/**
	 * TODO: DOCUMENTATION (silenco)
	 * 
	 * @param b
	 */
	public void setTpnSaveElementAsName(boolean b) {
		getDocument().getConfiguration().getTools().getExporting().setTpnExportElementAsName(b);
	}

	/**
	 * TODO: DOCUMENTATION (silenco)
	 * 
	 * @param className
	 */
	public void setLookAndFeel(String className) {
		getDocument().getConfiguration().getGui().getWindow().setLookAndFeel(className);
	}

	/**
	 * TODO: DOCUMENTATION (silenco)
	 * 
	 * @return
	 */
	public String getLookAndFeel() {
		return getDocument().getConfiguration().getGui().getWindow().getLookAndFeel();
	}

	/**
	 * TODO: DOCUMENTATION (silenco)
	 * 
	 * @param showGrid
	 */
	public void setShowGrid(boolean showGrid) {
		getDocument().getConfiguration().getEditor().setShowGrid(showGrid);
	}

	/**
	 * TODO: DOCUMENTATION (silenco)
	 * 
	 * @return
	 */
	public boolean isShowGrid() {
		return getDocument().getConfiguration().getEditor().getShowGrid();
	}

	/**
	 * TODO: DOCUMENTATION (silenco)
	 * 
	 * @param width
	 */
	public void setArrowWidth(int width) {
		getDocument().getConfiguration().getEditor().setArrowWidth(width);
	}

	/**
	 * TODO: DOCUMENTATION (silenco)
	 * 
	 * @return
	 */
	public int getArrowWidth() {
		if (getDocument().getConfiguration().getEditor().isSetArrowWidth())
			return getDocument().getConfiguration().getEditor().getArrowWidth();
		else
			return ConfigurationManager.getStandardConfiguration().getArrowWidth();
	}

	/**
	 * TODO: DOCUMENTATION (silenco)
	 * 
	 * @param headSize
	 */
	public void setArrowheadSize(int headSize) {
		getDocument().getConfiguration().getEditor().setArrowheadSize(headSize);
	}

	/**
	 * TODO: DOCUMENTATION (silenco)
	 * 
	 * @return
	 */
	public int getArrowheadSize() {
		if (getDocument().getConfiguration().getEditor().isSetArrowheadSize())
			return getDocument().getConfiguration().getEditor().getArrowheadSize();
		else
			return ConfigurationManager.getStandardConfiguration().getArrowheadSize();
	}

	/**
	 * TODO: DOCUMENTATION (silenco)
	 * 
	 * @return
	 */
	public boolean isFillArrowHead() {
		if (getDocument().getConfiguration().getEditor().isSetArrowFilledHead())
			return getDocument().getConfiguration().getEditor().getArrowFilledHead();
		else
			return ConfigurationManager.getStandardConfiguration().isFillArrowHead();
	}

	/**
	 * TODO: DOCUMENTATION (silenco)
	 * 
	 * @param fill
	 */
	public void setFillArrowHead(boolean fill) {
		getDocument().getConfiguration().getEditor().setArrowFilledHead(fill);
	}

	/**
	 * TODO: DOCUMENTATION (silenco)
	 * 
	 * @return
	 */
	public boolean isRoundRouting() {
		if (getDocument().getConfiguration().getEditor().isSetRoundRouting())
			return getDocument().getConfiguration().getEditor().getRoundRouting();
		else
			return false;
	}

	/**
	 * TODO: DOCUMENTATION (silenco)
	 * 
	 * @param round
	 */
	public void setRoundRouting(boolean round) {
		getDocument().getConfiguration().getEditor().setRoundRouting(round);
	}

	/**
	 * TODO: DOCUMENTATION (silenco)
	 * 
	 * @return
	 */
	public String getCurrentWorkingDir() {
		return currentWorkingDir;
	}

	/**
	 * TODO: DOCUMENTATION (silenco)
	 * 
	 * @param string
	 */
	public void setCurrentWorkingDir(String dir) {
		currentWorkingDir = dir;
	}

	public int getModelProcessorType() {
		return modelProcessor;
	}

	public void setModelProcessorType(int type) {
		modelProcessor = type;

	}

	public Color getSelectionColor() {
		return ConfigurationManager.getStandardConfiguration().getSelectionColor();
	}

	public void setSelectionColor(Color color) {
		ConfigurationManager.getStandardConfiguration().setSelectionColor(color);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.woped.editor.config.IConfiguration#getPortColor()
	 */
	public Color getPortColor() {
		return ConfigurationManager.getStandardConfiguration().getPortColor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.woped.editor.config.IConfiguration#setPortColor(java.awt.Color)
	 */
	public void setPortColor(Color color) {
		ConfigurationManager.getStandardConfiguration().setPortColor(color);
	}
}