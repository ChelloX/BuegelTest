package org.woped.bpel.gui.transitionproperties;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.woped.core.model.petrinet.TransitionModel;
import org.woped.editor.controller.TransitionPropertyEditor;

/**
 * @author Esther Landes
 *
 * This is a panel in the transition properties, which enables the user to
 * maintain data for a "receive" BPEL activity.
 *
 * Created on 14.01.2008
 */

@SuppressWarnings("serial")
public class BPELreceivePanel extends BPELadditionalPanel {

	JComboBox partnerLinkComboBox = null;
	JButton newPartnerLinkButton = null;
	JComboBox operationComboBox = null;
	JComboBox variableComboBox = null;
	JButton newVariableButton = null;

	public BPELreceivePanel(TransitionPropertyEditor t_editor,
			TransitionModel transition) {

		super(t_editor, transition);

		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		c.weightx = 1;
		c.weighty = 1;

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 0, 0);
		add(new JLabel("Partner Link:"), c);

		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 0, 0);
		add(getPartnerLinkComboBox(), c);

		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 0, 0);
		c.fill = GridBagConstraints.NONE;
		add(getNewPartnerLinkButton(), c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 0, 0);
		c.fill = GridBagConstraints.HORIZONTAL;
		add(new JLabel("Operation:"), c);

		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 0, 0);
		add(getOperationComboBox(), c);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 0, 0);
		add(new JLabel("Variable:"), c);

		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 0, 0);
		add(getVariableComboBox(), c);

		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 0, 0);
		c.fill = GridBagConstraints.NONE;
		add(getNewVariableButton(), c);
	}

	private JComboBox getPartnerLinkComboBox() {
		if (partnerLinkComboBox == null) {
			partnerLinkComboBox = new JComboBox();
			partnerLinkComboBox.setPreferredSize(dimension);

			// fill partnerLinkComboBox with partner links
			String[] partnerLinks = modelElementContainer.getPartnerLinkList();
			for(String partnerLink : partnerLinks){
				partnerLinkComboBox.addItem(partnerLink);
			}
		}
		return partnerLinkComboBox;
	}

	private JButton getNewPartnerLinkButton() {
		if (newPartnerLinkButton == null) {
			setLinkToBPELreceivePanel(this);
			newPartnerLinkButton = new JButton(NEW);
			newPartnerLinkButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showNewPartnerLinkDialog();
				}
			});
		}
		return newPartnerLinkButton;
	}

	private JComboBox getOperationComboBox() {
		if (operationComboBox == null) {
			operationComboBox = new JComboBox();
			operationComboBox.setPreferredSize(dimension);
		}
		return operationComboBox;
	}

	private JComboBox getVariableComboBox() {
		if (variableComboBox == null) {
			variableComboBox = new JComboBox();
			variableComboBox.setPreferredSize(dimension);
		}
		return variableComboBox;
	}

	private JButton getNewVariableButton() {
		if (newVariableButton == null) {
			newVariableButton = new JButton(NEW);

			newVariableButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showNewVariableDialog();
				}
			});

		}
		return newVariableButton;
	}

	
	public boolean allFieldsFilled(){
		if (partnerLinkComboBox.getSelectedItem() == null || operationComboBox.getSelectedItem() == null || variableComboBox.getSelectedItem() == null){
			return false;
		}
		else
			return true;
	}
	

//	fill partnerLinkComboBox with partner links
	public void defineContentOfPartnerLinkComboBox(){
		partnerLinkComboBox.removeAllItems();
		String[] partnerLinks = modelElementContainer.getPartnerLinkList();
		for(String partnerLink : partnerLinks){
			partnerLinkComboBox.addItem(partnerLink);
		}
	}


	// ***************** content getter methods **************************

	public String getPartnerLink() {
		if (partnerLinkComboBox.getSelectedItem() == null)
			return "";
		return partnerLinkComboBox.getSelectedItem().toString();
	}

	public String getOperation() {
		if (operationComboBox.getSelectedItem() == null)
			return "";
		return operationComboBox.getSelectedItem().toString();
	}

	public String getVariable() {
		if (variableComboBox.getSelectedItem() == null)
			return "";
		return variableComboBox.getSelectedItem().toString();
	}

	// ***************** content setter methods **************************

	public void setPartnerLink(String partnerLink) {
		partnerLinkComboBox.addItem(partnerLink);
	}

	public void setOperation(String operation) {
		operationComboBox.addItem(operation);
	}

	public void setVariable(String variable) {
		variableComboBox.addItem(variable);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		this.repaint();
	}

	@Override
	public void saveInfomation() {
		this.transition.setBaseActivity(new Receive(this.transition
				.getNameValue(), this.getPartnerLink(), this.getOperation(),
				this.getVariable()));
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "receive";
	}

	@Override
	public void showPanel(JPanel panel, GridBagConstraints c) {
		panel.add(this,c);
	}

}
