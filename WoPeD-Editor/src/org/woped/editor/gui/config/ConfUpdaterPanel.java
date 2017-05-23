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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 *
 * For contact information please visit http://woped.dhbw-karlsruhe.de
 *
 */
package org.woped.editor.gui.config;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import org.woped.config.ApromoreServer;
import org.woped.core.config.ConfigurationManager;
import org.woped.gui.lookAndFeel.WopedButton;
import org.woped.gui.translations.Messages;
import org.woped.apromore.ApromoreAccess;
import org.woped.apromore.ApromorePasswordSecurity;

/**
 * @author Steffen Goehrig
 */
@SuppressWarnings("serial")
public class ConfUpdaterPanel extends AbstractConfPanel {

	// Panels
	private JPanel enabledPanel = null;
	private JPanel listPanel = null;
	private JPanel buttonPanel = null;
	private JPanel settingsPanel = null;
	private JPanel proxyPanel = null;

	// Boxes
	private JComboBox<String> serverComboBox = null;
	private JCheckBox useBox = null;
	private JCheckBox useProxyBox = null;

	// Buttons
	private WopedButton addButton = null;
	private WopedButton changeButton = null;
	private WopedButton deleteButton = null;
	private WopedButton saveButton = null;
	private WopedButton testButton = null;
	// Test DEUTIZ
	private WopedButton cancelButton = null;

	// Labels
	private JLabel serverNameLabel = null;
	private JLabel serverNameLabelUser = null;

	private JLabel serverURLLabel = null;
	private JLabel serverPortLabel = null;
	private JLabel managerPathLabel = null;
	private JLabel usernameLabel = null;
	private JLabel passwordLabel = null;
	private JLabel useProxyLabel = null;
	private JLabel proxyNameLabel = null;
	private JLabel proxyPortLabel = null;

	// TextFields
	private JTextField serverNameText = null;
	private JTextField serverNameTextUser = null;
	private JTextField serverURLText = null;
	private JTextField serverPortText = null;
	private JTextField managerPathText = null;
	private JTextField usernameText = null;
	private JPasswordField passwordText = null;
	private JTextField proxyNameText = null;
	private JTextField proxyPortText = null;

	private int currentIndex;
	private String name;
	private String url;
	private int port;
	private String path;
	private String user;
	private String password;
	private boolean useProxy;
	private String proxyUrl;
	private int proxyPort;
	private String[] serverBoxItems;
	private ApromoreServer[] servers;
	private boolean addServer = false;
	private boolean changeServer = false;
	private boolean inputOK = true;
	private int apromoreServerID;
	private int selected;
	private boolean nameAlreadyInUse = false;

	/**
	 * Constructor for ConfToolsPanel.
	 */
	public ConfUpdaterPanel(String name) {
		super(name);
		initialize();

	}

	/**
	 * @see AbstractConfPanel#applyConfiguration() saves actual configuration in
	 *      xml-file
	 */
	public boolean applyConfiguration() {

		boolean newsetting = useBox.isSelected();
		boolean oldsetting = ConfigurationManager.getConfiguration().getApromoreUse();

		if (newsetting != oldsetting) {
			ConfigurationManager.getConfiguration().setApromoreUse(newsetting);
			JOptionPane.showMessageDialog(this, Messages.getString("Configuration.Apromore.Dialog.Restart.Message"),
					Messages.getString("Configuration.Apromore.Dialog.Restart.Title"), JOptionPane.INFORMATION_MESSAGE);
		}



		return true;

	}

	public void saveConfig() {
/*
		if (ConfigurationManager.getConfiguration().isSetApromoreServers()) {
			currentIndex = ConfigurationManager.getConfiguration().getCurrentApromoreIndex();
			serverComboBox.setSelectedIndex(currentIndex);
			servers = ConfigurationManager.getConfiguration().getApromoreServers();
			setTextFields();
		} else {
			setDefaultApromoreServer();
			servers = ConfigurationManager.getConfiguration().getApromoreServers();
			ConfigurationManager.getConfiguration().setApromoreUse(useBox.isSelected());
			ConfigurationManager.getConfiguration().setCurrentApromoreIndex(serverComboBox.getSelectedIndex());
			ConfigurationManager.getConfiguration().setApromoreServerName(// getServerNameText().getText()
																			// +
					getServerNameTextUser().getText());
			ConfigurationManager.getConfiguration().setApromoreServerURL(getServerURLText().getText());
			ConfigurationManager.getConfiguration()
					.setApromoreServerPort(Integer.parseInt(getServerPortText().getText()));
			ConfigurationManager.getConfiguration().setApromoreManagerPath(getManagerPathText().getText());
			ConfigurationManager.getConfiguration().setApromoreUsername(getUsernameText().getText());
			try {
				password = ApromorePasswordSecurity.encode(passwordText.getPassword().toString());
				ConfigurationManager.getConfiguration().setApromorePassword(password);
			} catch (NoSuchAlgorithmException | InvalidKeyException | UnsupportedEncodingException
					| NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			ConfigurationManager.getConfiguration().setApromoreUseProxy(useProxyBox.isSelected());
			ConfigurationManager.getConfiguration().setApromoreProxyName(getProxyNameText().getText());
			ConfigurationManager.getConfiguration()
					.setApromoreProxyPort(Integer.parseInt(getProxyPortText().getText()));
		}
*/
	}

	/**
	 * @see AbstractConfPanel#readConfiguration() reads actual configuration
	 *      from xml-file
	 */
	public void readConfiguration() {
		/*getUseBox().setSelected(ConfigurationManager.getConfiguration().getApromoreUse());
		if (ConfigurationManager.getConfiguration().isSetApromoreServers())
			getServersComboBox().setSelectedIndex(ConfigurationManager.getConfiguration().getCurrentApromoreIndex());

		getServerNameTextUser().setText(ConfigurationManager.getConfiguration().getApromoreServerName());
		String sname = ConfigurationManager.getConfiguration().getApromoreServerName();

		getServerNameText().setText(sname.substring(sname.indexOf("@") + 1));
		getServerNameTextUser().setText(ConfigurationManager.getConfiguration().getApromoreServerName());
		getServerURLText().setText(ConfigurationManager.getConfiguration().getApromoreServerURL());
		getServerPortText().setText("" + ConfigurationManager.getConfiguration().getApromoreServerPort());
		getManagerPathText().setText(ConfigurationManager.getConfiguration().getApromoreManagerPath());
		getUsernameText().setText(ConfigurationManager.getConfiguration().getApromoreUsername());
		if (ConfigurationManager.getConfiguration().isSetApromorePassword() == true)
			try {
				getPasswordText().setText(
						ApromorePasswordSecurity.decode(ConfigurationManager.getConfiguration().getApromorePassword()));
			} catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException
					| BadPaddingException | IOException e) {
				e.printStackTrace();
			}
		getUseProxyCheckbox().setSelected(ConfigurationManager.getConfiguration().getApromoreUseProxy());
		if (getUseProxyCheckbox().isSelected() == true && getUseBox().isSelected() == true) {
			proxyPanel.setVisible(true);
		} else {
			proxyPanel.setVisible(false);
		}
		getProxyNameText().setText(ConfigurationManager.getConfiguration().getApromoreProxyName());
		getProxyPortText().setText("" + ConfigurationManager.getConfiguration().getApromoreProxyPort());
		getSaveButton().setEnabled(false);
		getCancelButton().setEnabled(false);
*/
	}

	private void initialize() {
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTH;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		contentPanel.add(getEnabledPanel(), c);
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 1;
		
		contentPanel.add(new JPanel(), c);
		setMainPanel(contentPanel);

		// saveConfig();
		// updateSettingsPanel();

	}

	// ################## GUI COMPONENTS #################### */

	// Panels

	private JPanel getEnabledPanel() {
		if (enabledPanel == null) {
			enabledPanel = new JPanel();
			enabledPanel.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.anchor = GridBagConstraints.WEST;
			enabledPanel.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createTitledBorder(Messages.getTitle("Configuration.AutoUpdater.Boolean.Text")),
					BorderFactory.createEmptyBorder(10, 10, 10, 10)));
			c.weightx = 1;
			c.gridx = 1;
			c.gridy = 0;
			//enabledPanel.add(getUseBox(), c);
		}
		return enabledPanel;
	}
}