package org.woped.bpel.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;

import org.woped.translations.Messages;
import org.woped.core.utilities.SwingUtils;
import org.woped.editor.controller.vc.EditorVC;

/**
 * @author Lavinia Posler
 * 
 * This is the class for maintaining operations which are needed for BPEL Export.
 *
 * Created on 15.01.2008
 */

public class EditorOperations extends JPanel {
	private JPanel 					operationBpelPanel 					 = null;
	private JPanel                 	operationBpelPreviewPanel            = null;
	private JLabel                 	operationBpelPreviewLabel            = null;
	private JButton                	operationBpelPreviewButton           = null;
	
	private JDialog					bpelTextDialog 						 = null;
	private JTextField             	operationBpelTextField       	     = null;
	
	private EditorVC               editor;
    
	public EditorVC getEditor()
    {
        return editor;
    }
    

    public EditorOperations(EditorVC editor)
    {
        this.editor = editor;
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 0;
        add(getOperationBpelPreviewPanel(), c);
        this.setVisible(true);
        
    }

    
  private JPanel getOperationBpelPanel()
    {
        if (operationBpelPanel == null)
        {
        	operationBpelPanel = new JPanel();
        	operationBpelPanel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            c.weightx = 1;
            c.weighty = 1;
            c.gridx = 0;
            c.gridy = 0;
            c.fill = GridBagConstraints.BOTH;
            c.insets = new Insets(0, 0, 0, 0);
            operationBpelPanel.add(getOperationBpelPreviewPanel(), c);
        }
		this.operationBpelPanel.setVisible(true);
        return operationBpelPanel;
    }
   
    private JPanel getOperationBpelPreviewPanel()
    {
        if (operationBpelPreviewPanel == null)
        {
        	operationBpelPreviewPanel = new JPanel();
        	operationBpelPreviewPanel.setBorder(BorderFactory
                    .createCompoundBorder(BorderFactory.createTitledBorder(Messages.getString("PetriNet.Operations.BpelPrev.Title")), BorderFactory.createEmptyBorder()));
            operationBpelPreviewPanel.setLayout(new GridBagLayout());
        	GridBagConstraints c = new GridBagConstraints();

            c.weightx = 1;
            c.weighty = 1;
            c.gridx = 0;
            c.gridy = 0;
            c.fill = GridBagConstraints.BOTH;
            c.insets = new Insets(0, 0, 0, 0);
            operationBpelPreviewPanel.add(getOperationBpelPreviewLabel(), c);
            
            c.gridx = 1;
            c.gridy = 0;
            c.fill = GridBagConstraints.BOTH;
            c.insets = new Insets(0, 0, 0, 0);
            operationBpelPreviewPanel.add(getOperationBpelPreviewButton(), c);
            
        }
        this.operationBpelPreviewPanel.setVisible(true);

        return operationBpelPreviewPanel;
    }
    
    private JLabel getOperationBpelPreviewLabel()
    {
        if (operationBpelPreviewLabel == null)
        {
        	operationBpelPreviewLabel = new JLabel(Messages.getString("PetriNet.Operations.BpelPrev.Text"));
        }
        
        return operationBpelPreviewLabel;
    }
	
    private JButton getOperationBpelPreviewButton()
    {
        if (operationBpelPreviewButton == null)
        {
        	operationBpelPreviewButton = new JButton();
        	operationBpelPreviewButton.setText(Messages.getString("PetriNet.Operations.BpelPrev.Button"));
        	operationBpelPreviewButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                	
                	String buttonText = e.getActionCommand();
                    
                    if ( buttonText.equals(Messages.getString("PetriNet.Operations.BpelPrev.Button")) ) {
                       
                	bpelTextDialog = new JDialog();
                	bpelTextDialog.setVisible(false);
                	bpelTextDialog.setTitle(Messages.getString("PetriNet.Operations.BpelPrev.NewDialog"));
                	bpelTextDialog.setSize(500,500);
                	bpelTextDialog.setLocation(150,150);
                	bpelTextDialog.setLayout(new GridBagLayout());
					GridBagConstraints c = new GridBagConstraints();
					
					c.fill = GridBagConstraints.HORIZONTAL;
					c.anchor = GridBagConstraints.WEST;
					c.weightx = 1;
					c.weighty = 1;
					
					c.gridx = 0;
                    c.gridy = 0;
                    c.fill = GridBagConstraints.BOTH;
                    c.insets = new Insets(0, 0, 0, 0);
                    bpelTextDialog.add(getOperationBpelTextField(), c);
                    bpelTextDialog.setVisible(true);
                    
                	}
                	
                	
                    
                }
            });
        }
        
        return operationBpelPreviewButton;
    }
    
    
   
    private JTextField getOperationBpelTextField()
    {
        if (operationBpelTextField == null)
        {
        	operationBpelTextField = new JTextField();
            SwingUtils.setFixedWidth(operationBpelTextField, 490);
            operationBpelTextField.setText(Messages.getString("bitte entsprechend f�llen @ Frank :-)"));
            operationBpelTextField.setEditable(false);
            
        }
        operationBpelTextField.setVisible(true);    
        return operationBpelTextField;
        }
        
}
