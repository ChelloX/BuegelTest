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
 * For contact information please visit http://woped.ba-karlsruhe.de
 *
 */
package org.woped.editor.controller;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.woped.core.model.CreationMap;
import org.woped.core.model.IntPair;
import org.woped.core.model.PetriNetModelProcessor;
import org.woped.core.model.petrinet.OperatorTransitionModel;
import org.woped.core.model.petrinet.PetriNetModelElement;
import org.woped.core.model.petrinet.TransitionModel;
import org.woped.core.model.petrinet.TriggerModel;
import org.woped.core.utilities.Utils;
import org.woped.editor.controller.vc.EditorVC;
import org.woped.editor.utilities.Messages;

/**
 * @author waschtl
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TransitionPropertyEditor extends JDialog implements ActionListener
{
    // General
    private TransitionModel       transition                    = null;
    private EditorVC              editor                        = null;
    private JPanel                contentPanel                  = null;

    // Name
    private JPanel                namePanel                     = null;
    private JLabel                nameLabel                     = null;
    private JTextField            nameTextField                 = null;
    private JLabel                idLabel                       = null;
    private JTextField            idTextField                   = null;

    // Branching
    private JPanel                branchingPanel                = null;
    private JRadioButton          branchingNoneRadioButton      = null;
    private JRadioButton          branchingAndSplitRadioButton  = null;
    private JRadioButton          branchingAndJoinRadioButton   = null;
    private JRadioButton		  branchingAndSplitJoinRadioButton = null;
    private JRadioButton          branchingXorSplittRadioButton = null;
    private JRadioButton          branchingXorJoinRadioButton   = null;
    private JRadioButton		  branchingXorSplitJoinRadioButton = null;
    private ButtonGroup           branchingButtonGroup          = null;
    private JPanel                branchingNoneEntry            = null;
    private JPanel                branchingAndSplitEntry        = null;
    private JPanel                branchingAndJoinEntry         = null;
    private JPanel				  branchingAndSplitJoinEntry	= null;
    private JPanel                branchingXorSplitEntry        = null;
    private JPanel                branchingXorJoinEntry         = null;
    private JPanel				  branchingXorSplitJoinEntry	= null;
    private JLabel                branchingNoneIcon             = null;
    private JLabel                branchingAndSplitIcon         = null;
    private JLabel                branchingAndJoinIcon          = null;
    private JLabel				  branchingAndSplitJoinIcon		= null;
    private JLabel                branchingXorSplitIcon         = null;
    private JLabel                branchingXorJoinIcon          = null;
    private JLabel				  branchingXorSplitJoinIcon		= null;

    // Duration
    private JPanel                durationPanel                 = null;
    private JLabel                durationLabel                 = null;
    private JTextField            durationTextField             = null;
    private JComboBox             durationComboBox              = null;
    private static final String   COMBOBOX_YEARS_TEXT           = Messages.getString("Transition.Properties.Years");
    private static final String   COMBOBOX_MONTHS_TEXT          = Messages.getString("Transition.Properties.Months");
    private static final String   COMBOBOX_WEEKS_TEXT           = Messages.getString("Transition.Properties.Weeks");
    private static final String   COMBOBOX_DAYS_TEXT            = Messages.getString("Transition.Properties.Days");
    private static final String   COMBOBOX_HOURS_TEXT           = Messages.getString("Transition.Properties.Hours");
    private static final String   COMBOBOX_MINUTES_TEXT         = Messages.getString("Transition.Properties.Minutes");
    private static final String   COMBOBOX_SECONDS_TEXT         = Messages.getString("Transition.Properties.Seconds");
    private static final Object[] durationValues                = { COMBOBOX_SECONDS_TEXT, COMBOBOX_MINUTES_TEXT, COMBOBOX_HOURS_TEXT, COMBOBOX_DAYS_TEXT, 
                                                                    COMBOBOX_WEEKS_TEXT, COMBOBOX_MONTHS_TEXT, COMBOBOX_YEARS_TEXT };
    // Trigger
    private JPanel                triggerPanel                  = null;
    private JRadioButton          triggerNoneRadioButton        = null;
    private JRadioButton          triggerResourceRadioButton    = null;
    private JRadioButton          triggerMessageRadioButton     = null;
    private JRadioButton          triggerTimeRadioButton        = null;
    private ButtonGroup           triggerButtonGroup            = null;
    private JPanel                triggerNoneEntry              = null;
    private JPanel                triggerResourceEntry          = null;
    private JPanel                triggerMessageEntry           = null;
    private JPanel                triggerTimeEntry              = null;
    private JLabel                triggerResourceIcon           = null;
    private JLabel                triggerMessageIcon            = null;
    private JLabel                triggerTimeIcon               = null;
    private static final String   TRIGGER_NONE                  = Messages.getString("Transition.Properties.Trigger.None");
    private static final String   TRIGGER_MESSAGE               = Messages.getString("Transition.Properties.Trigger.Message");
    private static final String   TRIGGER_RESOURCE              = Messages.getString("Transition.Properties.Trigger.Resource");
    private static final String   TRIGGER_TIME                  = Messages.getString("Transition.Properties.Trigger.Time");

    // Resource
    private JPanel                resourcePanel                 = null;
    private JLabel                resourceRoleLabel             = null;
    private JLabel                resourceGroupLabel            = null;
    private JComboBox             resourceRoleComboBox          = null;
    private JComboBox             resourceGroupComboBox         = null;
    private DefaultComboBoxModel  roleComboBoxModel             = null;
    private DefaultComboBoxModel  groupComboBoxModel            = null;
    private JLabel                numResourcesLabel             = null;
    private JTextField            numResourcesTextField         = null;
    private static final String   ROLE_NONE                     = Messages.getString("Transition.Properties.Role.None");
    private static final String   GROUP_NONE                    = Messages.getString("Transition.Properties.Group.None");

    // Buttons
    private JPanel                buttonPanel                   = null;
    private JButton               buttonOk                      = null;
    private JButton               buttonCancel                  = null;

    public TransitionPropertyEditor(Frame owner, TransitionModel transition, EditorVC editor)
    {
        super(owner, true);
        this.transition = transition;
        this.editor = editor;
        this.setVisible(false);
        initialize();
        this.setSize(450, 460);
        this.setLocation(Utils.getCenterPoint(owner.getBounds(), this.getSize()));
        this.setVisible(true);
    }

    private void initialize()
    {
        this.setTitle(Messages.getString("Transition.Properties"));
        this.getContentPane().add(getContentPanel(), BorderLayout.NORTH);
        this.getContentPane().add(getButtonPanel(), BorderLayout.SOUTH);
        getNameTextField().requestFocus();
    }

    private JPanel getContentPanel()
    {
        if (contentPanel == null)
        {
            contentPanel = new JPanel();
            contentPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            contentPanel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;
            c.weightx = 1;
            c.weighty = 1;

            c.gridx = 0;
            c.gridy = 0;
            c.insets = new Insets(0, 0, 0, 0);
            contentPanel.add(getNamePanel(), c);

            c.gridx = 0;
            c.gridy = 1;
            c.insets = new Insets(0, 0, 0, 0);
            contentPanel.add(getBranchingPanel(), c);

            c.gridx = 0;
            c.gridy = 2;
            c.insets = new Insets(0, 0, 0, 0);
            contentPanel.add(getDurationPanel(), c);

            c.gridx = 0;
            c.gridy = 3;
            c.insets = new Insets(0, 0, 0, 0);
            contentPanel.add(getTriggerPanel(), c);

            c.gridx = 0;
            c.gridy = 4;
            c.insets = new Insets(0, 0, 0, 0);
            contentPanel.add(getResourcePanel(), c);
        }

        return contentPanel;
    }

    // **************************NamePanel******************************
    private JPanel getNamePanel()
    {
        if (namePanel == null)
        {
            namePanel = new JPanel();
            namePanel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            namePanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(Messages.getString("Transition.Properties.Identification")), BorderFactory.createEmptyBorder(5, 5,
                    0, 5)));

            c.weightx = 1;
            c.weighty = 1;
            c.anchor = GridBagConstraints.WEST;
            c.fill = GridBagConstraints.HORIZONTAL;

            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 1;
            c.insets = new Insets(0, 2, 0, 0);
            namePanel.add(getNameLabel(), c);

            c.gridx = 1;
            c.gridy = 0;
            c.gridwidth = 2;
            c.insets = new Insets(0, 10, 0, 10);
            namePanel.add(getNameTextField(), c);

            c.gridx = 3;
            c.gridy = 0;
            c.gridwidth = 1;
            c.insets = new Insets(0, 10, 0, 0);
            namePanel.add(getIdLabel(), c);

            c.gridx = 4;
            c.gridy = 0;
            c.gridwidth = 1;
            c.insets = new Insets(0, 10, 0, 10);
            namePanel.add(getIdTextField(), c);
        }

        return namePanel;
    }

    private JLabel getNameLabel()
    {
        if (nameLabel == null)
        {
            nameLabel = new JLabel(Messages.getString("Transition.Properties.Name") + ":");
        }

        return nameLabel;
    }

    private JTextField getNameTextField()
    {
        if (nameTextField == null)
        {
            nameTextField = new JTextField(transition.getNameValue());
            nameTextField.setPreferredSize(new Dimension(150, 20));
            nameTextField.setMinimumSize(new Dimension(150, 20));
            nameTextField.setMaximumSize(new Dimension(150, 20));
            nameTextField.addKeyListener(new KeyListener()
            {
                public void keyPressed(KeyEvent e)
                {
                    keyReleased(e);
                }

                public void keyTyped(KeyEvent e)
                {
                    keyReleased(e);
                }

                public void keyReleased(KeyEvent e)
                {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    {
                        apply();
                        TransitionPropertyEditor.this.dispose();
                    }
                }
            });
        }

        return nameTextField;
    }

    private JLabel getIdLabel()
    {
        if (idLabel == null)
        {
            idLabel = new JLabel("Id#: ");
        }

        return idLabel;
    }

    private JTextField getIdTextField()
    {
        if (idTextField == null)
        {
            idTextField = new JTextField();
            idTextField.setText("" + transition.getId());
            idTextField.setEditable(false);
            idTextField.setPreferredSize(new Dimension(40, 20));
        }

        return idTextField;
    }

    // ******************************TriggerPanel*****************************************
    private JPanel getTriggerPanel()
    {
        if (triggerPanel == null)
        {
            triggerPanel = new JPanel();
            triggerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(Messages.getString("Transition.Properties.Trigger")), BorderFactory
                    .createEmptyBorder(5, 5, 0, 5)));

            triggerButtonGroup = new ButtonGroup();
            triggerButtonGroup.add(getTriggerNoneRadioButton());
            triggerButtonGroup.add(getTriggerResourceRadioButton());
            triggerButtonGroup.add(getTriggerMessageRadioButton());
            triggerButtonGroup.add(getTriggerTimeRadioButton());

            triggerPanel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.WEST;

            c.gridx = 0;
            c.gridy = 0;
            c.insets = new Insets(0, 0, 0, 20);
            triggerPanel.add(getTriggerNoneEntry(), c);

            c.gridx = 1;
            c.gridy = 0;
            c.insets = new Insets(0, 20, 0, 0);
            triggerPanel.add(getTriggerResourceEntry(), c);

            c.gridx = 0;
            c.gridy = 1;
            c.insets = new Insets(0, 0, 0, 20);
            triggerPanel.add(getTriggerMessageEntry(), c);

            c.gridx = 1;
            c.gridy = 1;
            c.insets = new Insets(0, 20, 0, 0);
            triggerPanel.add(getTriggerTimeEntry(), c);

            setTriggerConfiguration();
        }

        return triggerPanel;
    }

    private void setTriggerConfiguration()
    {
        if (transition.getToolSpecific().getTrigger() == null)
        {
            getTriggerNoneRadioButton().setSelected(true);
            actionPerformed(new ActionEvent(getTriggerNoneRadioButton(), -1, TRIGGER_NONE));
        } else if (transition.hasTrigger())
        {
            if (transition.getToolSpecific().getTrigger().getTriggertype() == TriggerModel.TRIGGER_RESOURCE)
            {
                getTriggerResourceRadioButton().setSelected(true);
                actionPerformed(new ActionEvent(getTriggerResourceRadioButton(), -1, TRIGGER_RESOURCE));
            } else if (transition.getToolSpecific().getTrigger().getTriggertype() == TriggerModel.TRIGGER_EXTERNAL)
            {
                getTriggerMessageRadioButton().setSelected(true);
                actionPerformed(new ActionEvent(getTriggerMessageRadioButton(), -1, TRIGGER_MESSAGE));
            } else if (transition.getToolSpecific().getTrigger().getTriggertype() == TriggerModel.TRIGGER_TIME)
            {
                getTriggerTimeRadioButton().setSelected(true);
                actionPerformed(new ActionEvent(getTriggerTimeRadioButton(), -1, TRIGGER_TIME));
            }
        }
    }

    private JPanel getTriggerNoneEntry()
    {
        if (triggerNoneEntry == null)
        {
            triggerNoneEntry = new JPanel();
            triggerNoneEntry.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.WEST;
            c.gridx = 0;
            c.gridy = 0;
            triggerNoneEntry.add(getTriggerNoneRadioButton(), c);
        }

        return triggerNoneEntry;
    }

    private JPanel getTriggerResourceEntry()
    {
        if (triggerResourceEntry == null)
        {
            triggerResourceEntry = new JPanel();
            triggerResourceEntry.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.WEST;
            c.gridx = 0;
            c.gridy = 0;
            triggerResourceEntry.add(getTriggerResourceRadioButton(), c);
            c.gridx = 1;
            c.gridy = 0;
            triggerResourceEntry.add(getTriggerResourceIcon(), c);
        }

        return triggerResourceEntry;
    }

    private JPanel getTriggerMessageEntry()
    {
        if (triggerMessageEntry == null)
        {
            triggerMessageEntry = new JPanel();
            triggerMessageEntry.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.WEST;
            c.gridx = 0;
            c.gridy = 0;
            triggerMessageEntry.add(getTriggerMessageRadioButton(), c);
            c.gridx = 1;
            c.gridy = 0;
            triggerMessageEntry.add(getTriggerMessageIcon(), c);
        }

        return triggerMessageEntry;
    }

    private JPanel getTriggerTimeEntry()
    {
        if (triggerTimeEntry == null)
        {
            triggerTimeEntry = new JPanel();
            triggerTimeEntry.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.WEST;
            c.gridx = 0;
            c.gridy = 0;
            triggerTimeEntry.add(getTriggerTimeRadioButton(), c);
            c.gridx = 1;
            c.gridy = 0;
            c.insets = new Insets(0, 23, 0, 0);
            triggerTimeEntry.add(getTriggerTimeIcon(), c);
        }

        return triggerTimeEntry;
    }

    private JLabel getTriggerResourceIcon()
    {
        if (triggerResourceIcon == null)
        {
            triggerResourceIcon = new JLabel(Messages.getImageIcon("Popup.Trigger.AddResource"));
        }

        return triggerResourceIcon;
    }

    private JLabel getTriggerMessageIcon()
    {
        if (triggerMessageIcon == null)
        {
            triggerMessageIcon = new JLabel(Messages.getImageIcon("Popup.Trigger.AddExternal"));
        }

        return triggerMessageIcon;
    }

    private JLabel getTriggerTimeIcon()
    {
        if (triggerTimeIcon == null)
        {
            triggerTimeIcon = new JLabel(Messages.getImageIcon("Popup.Trigger.AddTime"));
        }

        return triggerTimeIcon;
    }

    private JRadioButton getTriggerNoneRadioButton()
    {
        if (triggerNoneRadioButton == null)
        {
            triggerNoneRadioButton = new JRadioButton(TRIGGER_NONE);
            triggerNoneRadioButton.setActionCommand(TRIGGER_NONE);
            triggerNoneRadioButton.addActionListener(this);
        }
        return triggerNoneRadioButton;
    }

    private JRadioButton getTriggerResourceRadioButton()
    {
        if (triggerResourceRadioButton == null)
        {
            triggerResourceRadioButton = new JRadioButton(TRIGGER_RESOURCE);
            triggerResourceRadioButton.setActionCommand(TRIGGER_RESOURCE);
            triggerResourceRadioButton.addActionListener(this);
        }
        return triggerResourceRadioButton;
    }

    private JRadioButton getTriggerMessageRadioButton()
    {
        if (triggerMessageRadioButton == null)
        {
            triggerMessageRadioButton = new JRadioButton(TRIGGER_MESSAGE);
            triggerMessageRadioButton.setActionCommand(TRIGGER_MESSAGE);
            triggerMessageRadioButton.addActionListener(this);
        }
        return triggerMessageRadioButton;
    }

    private JRadioButton getTriggerTimeRadioButton()
    {
        if (triggerTimeRadioButton == null)
        {
            triggerTimeRadioButton = new JRadioButton(TRIGGER_TIME);
            triggerTimeRadioButton.setActionCommand(TRIGGER_TIME);
            triggerTimeRadioButton.addActionListener(this);
        }
        return triggerTimeRadioButton;
    }

    // ******************************BranchingPanel****************************************
    private JPanel getBranchingPanel()
    {
        if (branchingPanel == null)
        {
            branchingPanel = new JPanel();
            branchingPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(Messages.getString("Transition.Properties.Branching")), BorderFactory.createEmptyBorder(5, 5,
                    0, 5)));

            branchingButtonGroup = new ButtonGroup();
            branchingButtonGroup.add(getBranchingNoneRadioButton());
            branchingButtonGroup.add(getBranchingAndSplitRadioButton());
            branchingButtonGroup.add(getBranchingAndJoinRadioButton());
            branchingButtonGroup.add(getBranchingAndSplitJoinRadioButton());
            branchingButtonGroup.add(getBranchingXorSplitRadioButton());
            branchingButtonGroup.add(getBranchingXorJoinRadioButton());
            branchingButtonGroup.add(getBranchingXorSplitJoinRadioButton());

            branchingPanel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.BOTH;
            c.anchor = GridBagConstraints.WEST;

            c.gridx = 0;
            c.gridy = 0;
            c.insets = new Insets(0, 0, 0, 0);
            branchingPanel.add(getBranchingNoneEntry(), c);
            c.gridx = 1;
            c.gridy = 0;
            c.insets = new Insets(0, 20, 0, 20);
            branchingPanel.add(getBranchingAndSplitEntry(), c);
            c.gridx = 2;
            c.gridy = 0;
            c.insets = new Insets(0, 0, 0, 0);
            branchingPanel.add(getBranchingAndJoinEntry(), c);
            c.gridx = 1;
            c.gridy = 1;
            c.insets = new Insets(0, 20, 0, 20);
            branchingPanel.add(getBranchingXorSplitEntry(), c);
            c.gridx = 2;
            c.gridy = 1;
            c.insets = new Insets(0, 0, 0, 0);
            branchingPanel.add(getBranchingXorJoinEntry(), c);

            c.gridx = 1;
            c.gridy = 2;
            c.insets = new Insets(0, 20, 0, 20);
            branchingPanel.add(getBranchingXorSplitJoinEntry(), c);
            c.gridx = 2;
            c.gridy = 2;
            c.insets = new Insets(0, 0, 0, 0);
            branchingPanel.add(getBranchingAndSplitJoinEntry(), c);
            
            checkBranching();
        }

        return branchingPanel;
    }

    private void checkBranching()
    {
        switch (transition.getType())
        {
            case PetriNetModelElement.TRANS_SIMPLE_TYPE: 
                getBranchingNoneRadioButton().setSelected(true);
                break;
            case PetriNetModelElement.TRANS_OPERATOR_TYPE: 
                switch (transition.getToolSpecific().getOperatorType())
                {
                    case OperatorTransitionModel.AND_SPLIT_TYPE:
                        getBranchingAndSplitRadioButton().setSelected(true);
                        break;
                    case OperatorTransitionModel.XOR_SPLIT_TYPE:
                        getBranchingXorSplitRadioButton().setSelected(true);
                        break;
                    case OperatorTransitionModel.AND_JOIN_TYPE:
                        getBranchingAndJoinRadioButton().setSelected(true);
                        break;
                    case OperatorTransitionModel.XOR_JOIN_TYPE:
                        getBranchingXorJoinRadioButton().setSelected(true);
                        break;
                    case OperatorTransitionModel.XOR_SPLITJOIN_TYPE:
                        getBranchingXorSplitJoinRadioButton().setSelected(true);
                        break;
                    case OperatorTransitionModel.AND_SPLITJOIN_TYPE:
                        getBranchingAndSplitJoinRadioButton().setSelected(true);
                        break;                                          	
                    default:
                }
            default:
        }
    }

    private JRadioButton getBranchingNoneRadioButton()
    {
        if (branchingNoneRadioButton == null)
        {
            branchingNoneRadioButton = new JRadioButton(Messages.getString("Transition.Properties.Branching.None"));
            branchingNoneRadioButton.setEnabled(false);
        }

        return branchingNoneRadioButton;
    }

    private JRadioButton getBranchingAndJoinRadioButton()
    {
        if (branchingAndJoinRadioButton == null)
        {
            branchingAndJoinRadioButton = new JRadioButton(Messages.getString("Transition.Properties.Branching.AndJoin"));
            branchingAndJoinRadioButton.setEnabled(false);
        }
        return branchingAndJoinRadioButton;
    }

    private JRadioButton getBranchingAndSplitRadioButton()
    {
        if (branchingAndSplitRadioButton == null)
        {
            branchingAndSplitRadioButton = new JRadioButton(Messages.getString("Transition.Properties.Branching.AndSplit"));
            branchingAndSplitRadioButton.setEnabled(false);
        }
        return branchingAndSplitRadioButton;
    }
    private JRadioButton getBranchingAndSplitJoinRadioButton()
    {
    	if (branchingAndSplitJoinRadioButton == null)
    	{
    		branchingAndSplitJoinRadioButton = new JRadioButton(Messages.getString("Transition.Properties.Branching.AndSplitJoin"));
    		branchingAndSplitJoinRadioButton.setEnabled(false);
    	}
    	return branchingAndSplitJoinRadioButton;
    }

    private JRadioButton getBranchingXorSplitRadioButton()
    {
        if (branchingXorSplittRadioButton == null)
        {
            branchingXorSplittRadioButton = new JRadioButton(Messages.getString("Transition.Properties.Branching.XorSplit"));
            branchingXorSplittRadioButton.setEnabled(false);
        }
        return branchingXorSplittRadioButton;
    }

    private JRadioButton getBranchingXorJoinRadioButton()
    {
        if (branchingXorJoinRadioButton == null)
        {
            branchingXorJoinRadioButton = new JRadioButton(Messages.getString("Transition.Properties.Branching.XorJoin"));
            branchingXorJoinRadioButton.setEnabled(false);
        }
        return branchingXorJoinRadioButton;
    }
    private JRadioButton getBranchingXorSplitJoinRadioButton()
    {
        if (branchingXorSplitJoinRadioButton == null)
        {
            branchingXorSplitJoinRadioButton = new JRadioButton(Messages.getString("Transition.Properties.Branching.XorSplitJoin"));
            branchingXorSplitJoinRadioButton.setEnabled(false);
        }
        return branchingXorSplitJoinRadioButton;
    }

    private JPanel getBranchingNoneEntry()
    {
        if (branchingNoneEntry == null)
        {
            branchingNoneEntry = new JPanel();
            branchingNoneEntry.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.WEST;
            c.gridx = 0;
            c.gridy = 0;
            branchingNoneEntry.add(getBranchingNoneRadioButton(), c);
            c.gridx = 1;
            c.gridy = 0;
            c.insets = new Insets(0, 20, 0, 0);
            branchingNoneEntry.add(getBranchingNoneIcon(), c);
        }

        return branchingNoneEntry;
    }

    private JPanel getBranchingAndSplitEntry()
    {
        if (branchingAndSplitEntry == null)
        {
            branchingAndSplitEntry = new JPanel();
            branchingAndSplitEntry.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.WEST;
            c.gridx = 0;
            c.gridy = 0;
            branchingAndSplitEntry.add(getBranchingAndSplitRadioButton(), c);
            c.gridx = 1;
            c.gridy = 0;
            c.insets = new Insets(0, 20, 0, 0);
            branchingAndSplitEntry.add(getBranchingAndSplitIcon(), c);
        }

        return branchingAndSplitEntry;
    }

    private JPanel getBranchingAndJoinEntry()
    {
        if (branchingAndJoinEntry == null)
        {
            branchingAndJoinEntry = new JPanel();
            branchingAndJoinEntry.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.WEST;
            c.gridx = 0;
            c.gridy = 0;
            branchingAndJoinEntry.add(getBranchingAndJoinRadioButton(), c);
            c.gridx = 1;
            c.gridy = 0;
            c.insets = new Insets(0, 20, 0, 0);
            branchingAndJoinEntry.add(getBranchingAndJoinIcon(), c);
        }

        return branchingAndJoinEntry;
    }

    private JPanel getBranchingAndSplitJoinEntry()
    {
        if (branchingAndSplitJoinEntry == null)
        {
            branchingAndSplitJoinEntry = new JPanel();
            branchingAndSplitJoinEntry.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.WEST;
            c.gridx = 0;
            c.gridy = 0;
            branchingAndSplitJoinEntry.add(getBranchingAndSplitJoinRadioButton(), c);
            c.gridx = 1;
            c.gridy = 0;
            c.insets = new Insets(0, 0, 0, 0);
            branchingAndSplitJoinEntry.add(getBranchingAndSplitJoinIcon(), c);
        }

        return branchingAndSplitJoinEntry;
    }
    
    private JPanel getBranchingXorSplitEntry()
    {
        if (branchingXorJoinEntry == null)
        {
            branchingXorSplitEntry = new JPanel();
            branchingXorSplitEntry.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.WEST;
            c.gridx = 0;
            c.gridy = 0;
            branchingXorSplitEntry.add(getBranchingXorSplitRadioButton(), c);
            c.gridx = 1;
            c.gridy = 0;
            c.insets = new Insets(0, 20, 0, 0);
            branchingXorSplitEntry.add(getBranchingXorSplitIcon(), c);
        }

        return branchingXorSplitEntry;
    }

    private JPanel getBranchingXorJoinEntry()
    {
        if (branchingXorJoinEntry == null)
        {
            branchingXorJoinEntry = new JPanel();
            branchingXorJoinEntry.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.WEST;
            c.gridx = 0;
            c.gridy = 0;
            branchingXorJoinEntry.add(getBranchingXorJoinRadioButton(), c);
            c.gridx = 1;
            c.gridy = 0;
            c.insets = new Insets(0, 20, 0, 0);
            branchingXorJoinEntry.add(getBranchingXorJoinIcon(), c);
        }

        return branchingXorJoinEntry;
    }
    
    private JPanel getBranchingXorSplitJoinEntry()
    {
        if (branchingXorSplitJoinEntry == null)
        {
            branchingXorSplitJoinEntry = new JPanel();
            branchingXorSplitJoinEntry.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.WEST;
            c.gridx = 0;
            c.gridy = 0;
            branchingXorSplitJoinEntry.add(getBranchingXorSplitJoinRadioButton(), c);
            c.gridx = 1;
            c.gridy = 0;
            c.insets = new Insets(0, 0, 0, 0);
            branchingXorSplitJoinEntry.add(getBranchingXorSplitJoinIcon(), c);
        }

        return branchingXorSplitJoinEntry;
    }

    private JLabel getBranchingNoneIcon()
    {
        if (branchingNoneIcon == null)
        {
            branchingNoneIcon = new JLabel(Messages.getImageIcon("Popup.Add.Transition"));
        }

        return branchingNoneIcon;
    }

    private JLabel getBranchingAndSplitIcon()
    {
        if (branchingAndSplitIcon == null)
        {
            branchingAndSplitIcon = new JLabel(Messages.getImageIcon("Popup.Add.AndSplit"));
        }

        return branchingAndSplitIcon;
    }

    private JLabel getBranchingAndJoinIcon()
    {
        if (branchingAndJoinIcon == null)
        {
            branchingAndJoinIcon = new JLabel(Messages.getImageIcon("Popup.Add.AndJoin"));
        }

        return branchingAndJoinIcon;
    }
    
    private JLabel getBranchingAndSplitJoinIcon()
    {
    	if (branchingAndSplitJoinIcon == null)
    		branchingAndSplitJoinIcon = new JLabel(Messages.getImageIcon("Popup.Add.AndSplitJoin"));
    	return branchingAndSplitJoinIcon;
    }

    private JLabel getBranchingXorSplitIcon()
    {
        if (branchingXorSplitIcon == null)
        {
            branchingXorSplitIcon = new JLabel(Messages.getImageIcon("Popup.Add.XorSplit"));
        }

        return branchingXorSplitIcon;
    }

    private JLabel getBranchingXorJoinIcon()
    {
        if (branchingXorJoinIcon == null)
        {
            branchingXorJoinIcon = new JLabel(Messages.getImageIcon("Popup.Add.XorJoin"));
        }

        return branchingXorJoinIcon;
    }
    private JLabel getBranchingXorSplitJoinIcon()
    {
    	if (branchingXorSplitJoinIcon == null)
    		branchingXorSplitJoinIcon = new JLabel(Messages.getImageIcon("Popup.Add.XorSplitJoin"));
    	return branchingXorSplitJoinIcon;
    }

    // *********************************DurationPanel*****************************************************
    private JPanel getDurationPanel()
    {
        if (durationPanel == null)
        {
            durationPanel = new JPanel();
            durationPanel.setLayout(new GridBagLayout());
            durationPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(Messages.getString("Transition.Properties.ExecutionTime")), BorderFactory.createEmptyBorder(5,
                    5, 0, 5)));
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.WEST;
            c.weightx = 1;
            c.weighty = 1;

            c.gridx = 0;
            c.gridy = 0;
            c.insets = new Insets(0, 2, 0, 0);
            durationPanel.add(getDurationLabel(), c);
            c.gridx = 1;
            c.gridy = 0;
            c.insets = new Insets(0, 0, 0, 10);
            durationPanel.add(getDurationTextfield(), c);
            c.gridx = 2;
            c.gridy = 0;
            c.insets = new Insets(0, 0, 0, 10);
            durationPanel.add(getDurationComboBox(), c);
        }

        return durationPanel;
    }

    private JLabel getDurationLabel()
    {
        if (durationLabel == null)
        {
            durationLabel = new JLabel(Messages.getString("Transition.Properties.Duration") + ":");
        }

        return durationLabel;
    }

    private JTextField getDurationTextfield()
    {
        if (durationTextField == null)
        {
            durationTextField = new JTextField();
            durationTextField.setText("");
            durationTextField.setMinimumSize(new Dimension(80, 20));
            durationTextField.setEnabled(false);
        }

        return durationTextField;
    }

    private JComboBox getDurationComboBox()
    {
        if (durationComboBox == null)
        {
            durationComboBox = new JComboBox(durationValues);
            durationComboBox.setMinimumSize(new Dimension(80, 20));
            durationComboBox.setEnabled(false);
        }

        return durationComboBox;
    }

    // **********************************************ResourcePanel******************************************************
    private JPanel getResourcePanel()
    {
        if (resourcePanel == null)
        {
            resourcePanel = new JPanel();
            resourcePanel.setLayout(new GridBagLayout());
            resourcePanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(Messages.getString("Transition.Properties.ResourceAssignment")), BorderFactory
                    .createEmptyBorder(5, 5, 0, 5)));

            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;
            c.anchor = GridBagConstraints.WEST;
            c.weightx = 1;
            c.weighty = 1;

            c.gridx = 0;
            c.gridy = 0;
            c.insets = new Insets(0, 0, 0, 0);
            resourcePanel.add(getResourceRoleLabel(), c);

            c.gridx = 1;
            c.gridy = 0;
            c.insets = new Insets(0, 5, 0, 5);
            resourcePanel.add(getResourceRoleComboBox(), c);

            c.gridx = 2;
            c.gridy = 0;
            c.insets = new Insets(0, 0, 0, 0);
            resourcePanel.add(getResourceGroupLabel(), c);

            c.gridx = 3;
            c.gridy = 0;
            c.insets = new Insets(0, 5, 0, 5);
            resourcePanel.add(getResourceGroupComboBox(), c);

            c.fill = GridBagConstraints.NONE;
            c.gridx = 0;
            c.gridy = 1;
            c.gridwidth = 3;
            c.insets = new Insets(5, 2, 5, 0);
            resourcePanel.add(getNumResourcesLabel(), c);

            c.gridx = 3;
            c.gridy = 1;
            c.gridwidth = 1;
            c.insets = new Insets(5, 5, 5, 0);
            resourcePanel.add(getNumResourcesTextField(), c);
        }

        return resourcePanel;
    }

    private JLabel getNumResourcesLabel()
    {
        if (numResourcesLabel == null)
        {
            numResourcesLabel = new JLabel(Messages.getString("Transition.Properties.AssignedResources")+ ": ");
        }

        return numResourcesLabel;
    }

    private JTextField getNumResourcesTextField()
    {
        if (numResourcesTextField == null)
        {
            numResourcesTextField = new JTextField();
            numResourcesTextField.setEditable(false);
            numResourcesTextField.setAlignmentY(JTextField.RIGHT_ALIGNMENT);
            numResourcesTextField.setMinimumSize(new Dimension(50, 20));
            numResourcesTextField.setMaximumSize(new Dimension(50, 20));
            numResourcesTextField.setPreferredSize(new Dimension(50, 20));
            updateNumResources();

        }

        return numResourcesTextField;
    }

    private void updateNumResources()
    {
        String role = resourceRoleComboBox.getSelectedItem().toString();
        String group = resourceGroupComboBox.getSelectedItem().toString();

        Vector res = ((PetriNetModelProcessor) (editor.getModelProcessor())).getResources();
        int count = 0;
        Vector rlist;

        for (int i = 0; i < res.size(); i++)
        {
            String name = res.get(i).toString();
            rlist = ((PetriNetModelProcessor) (editor.getModelProcessor())).getResourceClassesResourceIsAssignedTo(name);

            if (rlist.contains(role) & rlist.contains(group))
            {
                count++;
            }
        }

        numResourcesTextField.setText("" + count);
    }

    private JLabel getResourceRoleLabel()
    {
        if (resourceRoleLabel == null)
        {
            resourceRoleLabel = new JLabel(" " + Messages.getString("Transition.Properties.Role") + ": ");
        }

        return resourceRoleLabel;
    }

    private JLabel getResourceGroupLabel()
    {
        if (resourceGroupLabel == null)
        {
            resourceGroupLabel = new JLabel(" " + Messages.getString("Transition.Properties.Group") + ": ");
        }

        return resourceGroupLabel;
    }

    private DefaultComboBoxModel getRoleComboxBoxModel()
    {
        if (roleComboBoxModel == null)
        {
            if (((PetriNetModelProcessor) getEditor().getModelProcessor()).getRoles() != null)
            {
                roleComboBoxModel = new DefaultComboBoxModel();
                roleComboBoxModel.addElement(ROLE_NONE);

                for (Iterator iter = ((PetriNetModelProcessor) getEditor().getModelProcessor()).getRoles().iterator(); iter.hasNext();)
                {
                    roleComboBoxModel.addElement(iter.next());
                }

                if (!transition.hasResource())
                {
                    roleComboBoxModel.setSelectedItem(ROLE_NONE);
                } else
                {
                    String transRole = transition.getToolSpecific().getTransResource().getTransRoleName();
                    roleComboBoxModel.setSelectedItem(transRole);
                }
            }
        }

        return roleComboBoxModel;
    }

    private DefaultComboBoxModel getGroupComboxBoxModel()
    {
        if (groupComboBoxModel == null)
        {
            if (((PetriNetModelProcessor) getEditor().getModelProcessor()).getOrganizationUnits() != null)
            {
                groupComboBoxModel = new DefaultComboBoxModel();
                groupComboBoxModel.addElement(GROUP_NONE);

                for (Iterator iter = ((PetriNetModelProcessor) getEditor().getModelProcessor()).getOrganizationUnits().iterator(); iter.hasNext();)
                {
                    groupComboBoxModel.addElement(iter.next());
                }

                if (!transition.hasResource())
                {
                    groupComboBoxModel.setSelectedItem(GROUP_NONE);
                } else
                {
                    String transGroup = transition.getToolSpecific().getTransResource().getTransOrgUnitName();
                    groupComboBoxModel.setSelectedItem(transGroup);
                }
            }
        }

        return groupComboBoxModel;
    }

    private JComboBox getResourceRoleComboBox()
    {
        if (resourceRoleComboBox == null)
        {
            resourceRoleComboBox = new JComboBox(getRoleComboxBoxModel());
            resourceRoleComboBox.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0)
                {
                    updateNumResources();
                }

            });
            resourceRoleComboBox.setMinimumSize(new Dimension(130, 20));
            resourceRoleComboBox.setMaximumSize(new Dimension(130, 20));
            resourceRoleComboBox.setPreferredSize(new Dimension(130, 20));
        }

        return resourceRoleComboBox;
    }

    private JComboBox getResourceGroupComboBox()
    {
        if (resourceGroupComboBox == null)
        {
            resourceGroupComboBox = new JComboBox(getGroupComboxBoxModel());
            resourceGroupComboBox.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0)
                {
                    updateNumResources();
                }

            });
            resourceGroupComboBox.setMinimumSize(new Dimension(130, 20));
            resourceGroupComboBox.setMaximumSize(new Dimension(130, 20));
            resourceGroupComboBox.setPreferredSize(new Dimension(130, 20));
        }

        return resourceGroupComboBox;
    }

    // *****************************************************ButtonPanel****************************************************
    private JPanel getButtonPanel()
    {
        if (buttonPanel == null)
        {
            buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.add(getButtonOk());
            buttonPanel.add(getButtonCancel());
        }

        return buttonPanel;
    }

    private JButton getButtonOk()
    {
        if (buttonOk == null)
        {
            buttonOk = new JButton();
            buttonOk.setIcon(Messages.getImageIcon("Button.Ok"));
            buttonOk.setText(Messages.getTitle("Button.Ok"));

            buttonOk.setMnemonic(Messages.getMnemonic("Button.Ok"));
            buttonOk.setPreferredSize(new Dimension(120, 25));
            buttonOk.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    String selectedRole = getRoleComboxBoxModel().getSelectedItem().toString();
                    String selectedGroup = getGroupComboxBoxModel().getSelectedItem().toString();

                    if (selectedRole.equals(ROLE_NONE) && selectedGroup.equals(GROUP_NONE)
                        || !selectedRole.equals(ROLE_NONE) && !selectedGroup.equals(GROUP_NONE))
                    {
                        apply();
                        TransitionPropertyEditor.this.dispose();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(getContentPanel(), 
                                Messages.getString("TransitionEditor.Properties.ResourceError.Text"), 
                                Messages.getString("TransitionEditor.Properties.ResourceError.Title"),
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }

        return buttonOk;
    }

    private JButton getButtonCancel()
    {
        if (buttonCancel == null)
        {
            buttonCancel = new JButton();
            buttonCancel.setText(Messages.getTitle("Button.Cancel"));
            buttonCancel.setIcon(Messages.getImageIcon("Button.Cancel"));
            buttonCancel.setMnemonic(Messages.getMnemonic("Button.Cancel"));
            buttonCancel.setPreferredSize(new Dimension(120, 25));
            buttonCancel.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    TransitionPropertyEditor.this.dispose();
                }
            });
        }

        return buttonCancel;
    }

    // public static void main(String[] args)
    // {
    // TransitionPropertyEditor p = new TransitionPropertyEditor();
    // JFrame f = new JFrame();
    // f.getContentPane().add(p);
    // f.setSize(350, 350);
    // f.setVisible(true);
    // }

    private void apply()
    {
        int selectedTriggerType = -1;
        CreationMap map = transition.getCreationMap();
        
        // Check selected trigger type
        if (getTriggerNoneRadioButton().isSelected())
        {
            if (transition.hasResource())
            {
               getEditor().deleteCell(transition.getToolSpecific().getTransResource(), true);
            }

            if (transition.hasTrigger())
            {
                getEditor().deleteCell(transition.getToolSpecific().getTrigger(), true);
            }
        } 
        else if (getTriggerResourceRadioButton().isSelected())
       	{
       		selectedTriggerType = TriggerModel.TRIGGER_RESOURCE;
       	} 
       	else if (getTriggerTimeRadioButton().isSelected())
       	{
       		selectedTriggerType = TriggerModel.TRIGGER_TIME;
       	} 
       	else if (getTriggerMessageRadioButton().isSelected())
       	{
       		selectedTriggerType = TriggerModel.TRIGGER_EXTERNAL;
       	}
               
        if (transition.hasResource())
        {
           getEditor().deleteCell(transition.getToolSpecific().getTransResource(), true);
        }

        // Trigger Handling
       	map.setTriggerType(selectedTriggerType);
       	if (transition.hasTrigger())
       	{
       		if (transition.getToolSpecific().getTrigger().getTriggertype() != selectedTriggerType)
       		{
       			Point p = transition.getToolSpecific().getTrigger().getPosition();
       			getEditor().deleteCell(transition.getToolSpecific().getTrigger(), true);
                map.setTriggerPosition(p.x, p.y);
                getEditor().createTrigger(map);
            }
        } 
        else if (selectedTriggerType != -1)
        {
            getEditor().createTrigger(map);
        }
        
        // Rescource Handling
        if (selectedTriggerType == TriggerModel.TRIGGER_RESOURCE)
        {
            String selectedRole = getRoleComboxBoxModel().getSelectedItem().toString();
            String selectedGroup = getGroupComboxBoxModel().getSelectedItem().toString();

            if (!selectedRole.equals(ROLE_NONE) && !selectedGroup.equals(GROUP_NONE))
            {
                map.setResourceOrgUnit(selectedGroup);
                map.setResourceRole(selectedRole);
                if (transition.hasResource())
                    map.setResourcePosition(new IntPair(transition.getToolSpecific().getTransResource().getPosition()));
                getEditor().createTransitionResource(map);
            } 
            else
            {
                getEditor().deleteCell(transition.getToolSpecific().getTransResource(), true);
            }
        } 

        // Name changing handling
        transition.setNameValue(getNameTextField().getText());
        
        // Set change flag
        getEditor().setSaved(false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals(TRIGGER_MESSAGE) || e.getActionCommand().equals(TRIGGER_TIME) || e.getActionCommand().equals(TRIGGER_NONE))
        {
            getResourceRoleComboBox().setEnabled(false);
            getResourceGroupComboBox().setEnabled(false);
            getNumResourcesLabel().setEnabled(false);
            getNumResourcesTextField().setEnabled(false);
        } 
        else if (e.getActionCommand().equals(TRIGGER_RESOURCE))
        {
            getResourceRoleComboBox().setEnabled(true);
            getResourceGroupComboBox().setEnabled(true);
            getNumResourcesLabel().setEnabled(true);
            getNumResourcesTextField().setEnabled(true);
        }
    }

    /**
     * @return Returns the editor.
     */
    public EditorVC getEditor()
    {
        return editor;
    }
}