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
package org.woped.editor.gui.config;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.woped.core.config.ConfigurationManager;
import org.woped.gui.translations.Messages;

/**
 * @author Steffen Göhrig
 * <br>
 *         The <code>ConfLanguagePanel</code> is the
 *         <code>AbstractConfPanel</code> for the configuration of the language. <br>
 *         Created on: 26.11.2004 Last Change on: 14.11.2005
 */
@SuppressWarnings("serial")
public class ConfAutoUpdatePanel extends AbstractConfPanel {

	private JPanel settingsPanel = null;
	private JCheckBox useByDefaultBox = null;
	/**
	 * Constructor for ConfToolsPanel.
	 */
	public ConfAutoUpdatePanel(String name) {
		super(name);
		initialize();
	}

	/**
	 * @see AbstractConfPanel#applyConfiguration()
	 */
	public boolean applyConfiguration() {
		boolean newsetting = useByDefaultBox.isSelected();
		/*boolean oldsetting = ConfigurationManager.getConfiguration().getAutoUpdateEnabled();

		if (newsetting != oldsetting) {
			ConfigurationManager.getConfiguration().setAutoUpdateEnabled(
					useByDefaultBox.isSelected());	
			System.out.println("Wert abgespeichert"+useByDefaultBox.isSelected());
		}
		*/
		return true;
	}

	/**
	 * @see AbstractConfPanel#readConfiguration()
	 */
	public void readConfiguration() {
		/*getUseByDefaultBox().setSelected(
				ConfigurationManager.getConfiguration().getAutoUpdateEnabled());
		System.out.println("Wert ausgelesen"+ConfigurationManager.getConfiguration().getAutoUpdateEnabled());
	*/}

	private void initialize() {
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTH;
		c.fill = GridBagConstraints.HORIZONTAL;

	
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 1;
		contentPanel.add(getSettingsPanel(), c);

		// dummy
		c.fill = GridBagConstraints.VERTICAL;
		c.weighty = 1;
		c.gridy = 2;
		contentPanel.add(new JPanel(), c);

		setMainPanel(contentPanel);
	}

	public JPanel getSettingsPanel() {
		if (settingsPanel == null) {
			settingsPanel = new JPanel();
			settingsPanel.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.anchor = GridBagConstraints.WEST;

			settingsPanel
					.setBorder(BorderFactory.createCompoundBorder(
							BorderFactory
									.createTitledBorder(Messages
											.getString("Configuration.AutoUpdater.Boolean.Title")),
							BorderFactory.createEmptyBorder(10, 10, 10, 10)));

			
			c.weightx = 1;
			c.gridx = 0;
			c.gridy = 0;
		
			settingsPanel.add(getUseByDefaultBox(),c);
		}

//		settingsPanel.setVisible(getUseByDefaultBox().isSelected());
		return settingsPanel;
	}

	public void setSettingsPanel(JPanel settingsPanel) {
		this.settingsPanel = settingsPanel;
	}

	

	public JCheckBox getUseByDefaultBox() {
		if (useByDefaultBox == null) {
			useByDefaultBox = new JCheckBox(
					Messages.getString("Configuration.AutoUpdater.Boolean.Text"));
			useByDefaultBox.setEnabled(true);
			useByDefaultBox.setToolTipText("<html>"
					+ Messages.getString("Configuration.AutoUpdater.Boolean.Text")
					+ "</html>");
			
		}

		return useByDefaultBox;
	}

	public void setUseByDefaultBox(JCheckBox useByDefaultBox) {
		this.useByDefaultBox = useByDefaultBox;
	}

	
	// ################## GUI COMPONENTS #################### */
}