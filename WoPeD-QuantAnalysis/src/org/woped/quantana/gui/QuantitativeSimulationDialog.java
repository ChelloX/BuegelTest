package org.woped.quantana.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import org.woped.core.config.DefaultStaticConfiguration;
import org.woped.core.model.ModelElementContainer;
import org.woped.core.model.PetriNetModelProcessor;
import org.woped.core.model.petrinet.ResourceClassModel;
import org.woped.core.model.petrinet.TransitionModel;
import org.woped.core.utilities.LoggerManager;
import org.woped.editor.controller.vc.EditorVC;
import org.woped.editor.controller.vc.StructuralAnalysis;
import org.woped.editor.utilities.Messages;
import org.woped.quantana.Constants;
import org.woped.quantana.graph.Node;
import org.woped.quantana.graph.WorkflowNetGraph;
import org.woped.quantana.gui.CapacityAnalysisDialog.MyTableHeaderRenderer;
import org.woped.quantana.model.ResUtilTableModel;
import org.woped.quantana.model.ServerTableModel;
import org.woped.quantana.model.TimeModel;
import org.woped.quantana.resourcealloc.Resource;
import org.woped.quantana.resourcealloc.ResourceAllocation;
import org.woped.quantana.resourcealloc.ResourceUtilization;
import org.woped.quantana.simulation.SimParameters;
import org.woped.quantana.simulation.Simulator;

public class QuantitativeSimulationDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private EditorVC editor = null;

	private JPanel iatPanel = null;

	private JPanel stPanel = null;

	private JPanel queuePanel = null;

	private JPanel termPanel = null;
	
	private JPanel generalPanel = null;
	
	private JPanel distPanel = null;
	
	private JPanel statsPanel = null;
	
	private JPanel utilPanel = null;

	private JPanel buttonPanel = null;

	private int groupRoleNum = 0;

	private int resObjNum = 0;

	private double period = 480.0;

	private double lambda = 50.0;

	private StructuralAnalysis sa;

	private ModelElementContainer mec;

	private WorkflowNetGraph graph;

	private ResourceAllocation resAlloc;

	private JTextField txtRuns;

	private JTextField txtLambda;

	private JTextField txtPeriod;

	private JTextField txtIATInterval;

	private JTextField txtSTInterval;

	private JTextField txtIATStdDev;

	private JTextField txtSTStdDev;

	private JComboBox cboTimeUnits;
	
	private int periodIndex = 2;

	private ButtonGroup groupIAT;

	private ButtonGroup groupST;

	private ButtonGroup groupQD;

	private JCheckBox stop1;

	private JCheckBox stop2;
	
	private JTable tableServers;
	
	private JScrollPane serverTablePane;
	
	private Object[][] serverTableMatrix;
	
	private ServerTableModel serverTableModel;
	
	private JTable tableResUtil;
	
	private JScrollPane resUtilTablePane;
	
	private Object[][] resUtilTableMatrix;
	
	private ResUtilTableModel resUtilTableModel;

	private TimeModel tm = null;
	
	private Simulator sim;
	
	private String[] colServers = {
			Messages.getString("QuantAna.Simulation.Column.Names"),
			Messages.getString("QuantAna.Simulation.Column.L"),
			Messages.getString("QuantAna.Simulation.Column.Lq"),
			Messages.getString("QuantAna.Simulation.Column.Ls"),
			Messages.getString("QuantAna.Simulation.Column.W"),
			Messages.getString("QuantAna.Simulation.Column.Wq"),
			Messages.getString("QuantAna.Simulation.Column.Details")
	};
	
	private String[] ttipsServers = {
			Messages.getString("QuantAna.Simulation.ToolTip.Names"),
			Messages.getString("QuantAna.Simulation.ToolTip.L"),
			Messages.getString("QuantAna.Simulation.ToolTip.Lq"),
			Messages.getString("QuantAna.Simulation.ToolTip.Ls"),
			Messages.getString("QuantAna.Simulation.ToolTip.W"),
			Messages.getString("QuantAna.Simulation.ToolTip.Wq"),
			Messages.getString("QuantAna.Simulation.ToolTip.Details")
	};
	
	private String[] colResUtil = {
			Messages.getString("QuantAna.Simulation.Column.Object"),
			Messages.getString("QuantAna.Simulation.Column.Util")
	};
	
	private String[] ttipsResUtil = {
			Messages.getString("QuantAna.Simulation.ToolTip.Object"),
			Messages.getString("QuantAna.Simulation.ToolTip.Util")
	};
	
	private int numServers;
	
	/**
	 * This is the default constructor
	 */
	public QuantitativeSimulationDialog(JFrame owner, EditorVC editor) {
		super(owner, Messages.getTitle("QuantAna.Simulation"), true);
		this.editor = editor;
		sa = new StructuralAnalysis(editor);
		mec = editor.getModelProcessor().getElementContainer();
		graph = new WorkflowNetGraph(sa, mec);
		tm = new TimeModel(1, 1.0);
		
		initResourceAlloc();
		
		numServers = graph.getNumTransitions();
		
		initialize();
		LoggerManager.info(Constants.QUANTANA_LOGGER, Messages
				.getString("QuantAna.Started"));
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints constraints = new GridBagConstraints();
		setLayout(new GridBagLayout());
		constraints.insets = new Insets(5, 0, 5, 0);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 0;
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		getContentPane().add(getGeneralPanel(), constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(5, 10, 5, 10);
		getContentPane().add(getQueuePanel(), constraints);

		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(5, 10, 5, 10);
		getContentPane().add(getTermPanel(), constraints);

		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 2;
		constraints.insets = new Insets(5, 10, 5, 10);
		getContentPane().add(getButtonPanel(), constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.insets = new Insets(5, 10, 5, 10);
		getContentPane().add(getDistPanel(), constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weighty = 1;
		constraints.insets = new Insets(5, 10, 5, 10);
		getContentPane().add(getStatsPanel(), constraints);

		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weighty = 1;
		constraints.insets = new Insets(5, 10, 5, 10);
		getContentPane().add(getUtilPanel(), constraints);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width > 760 ? 760 : screenSize.width;
		int x = screenSize.width > width ? (screenSize.width - width) / 2 : 0;
		int height = screenSize.height > 740 ? 740 : screenSize.height;
		int y = screenSize.height > height ? (screenSize.height - height) / 2 : 0;
		this.setBounds(x, y, width, height);
		this.setVisible(true);
	}
	
	private JPanel getGeneralPanel(){
		if (generalPanel == null) {
			generalPanel = new JPanel();
			
			generalPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), Messages.getString("QuantAna.Simulation.GeneralProperties")));

			GridBagConstraints constraints = new GridBagConstraints();
			generalPanel.setLayout(new GridBagLayout());
			constraints.insets = new Insets(5, 5, 5, 5);
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 1;
			constraints.weighty = 0;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			
			txtLambda = new JTextField("50");
			txtLambda.setMinimumSize(new Dimension(100, 20));
			txtLambda.setMaximumSize(new Dimension(100, 20));
			txtLambda.setPreferredSize(new Dimension(100, 20));
			txtPeriod = new JTextField("8.0");
			txtPeriod.setMinimumSize(new Dimension(100, 20));
			txtPeriod.setMaximumSize(new Dimension(100, 20));
			txtPeriod.setPreferredSize(new Dimension(100, 20));
			JLabel lblLambda = new JLabel(Messages.getString("QuantAna.Simulation.Mean"));
			lblLambda.setMinimumSize(new Dimension(100, 20));
			lblLambda.setMaximumSize(new Dimension(100, 20));
			lblLambda.setPreferredSize(new Dimension(100, 20));
			lblLambda.setHorizontalAlignment(SwingConstants.RIGHT);
			JLabel lblPeriod = new JLabel(Messages.getString("QuantAna.Simulation.Period"));
			lblPeriod.setMinimumSize(new Dimension(100, 20));
			lblPeriod.setMaximumSize(new Dimension(100, 20));
			lblPeriod.setPreferredSize(new Dimension(100, 20));
			lblPeriod.setHorizontalAlignment(SwingConstants.RIGHT);
			cboTimeUnits = new JComboBox(Constants.TIMEUNITS);
			cboTimeUnits.setMinimumSize(new Dimension(100, 20));
			cboTimeUnits.setMaximumSize(new Dimension(100, 20));
			cboTimeUnits.setPreferredSize(new Dimension(100, 20));
			cboTimeUnits.setSelectedIndex(periodIndex);
			
			constraints.gridx = 0;
			constraints.gridy = 0;
			generalPanel.add(lblLambda);
			
			constraints.gridx = 1;
			constraints.gridy = 0;
			generalPanel.add(txtLambda);
			
			constraints.gridx = 2;
			constraints.gridy = 0;
			generalPanel.add(lblPeriod);
			
			constraints.gridx = 3;
			constraints.gridy = 0;
			generalPanel.add(txtPeriod);
			
			constraints.gridx = 4;
			constraints.gridy = 0;
			generalPanel.add(cboTimeUnits);
		}

		return generalPanel;
	}
	
	private JPanel getDistPanel(){
		if (distPanel == null) {
			distPanel = new JPanel();
			distPanel.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.insets = new Insets(5, 5, 5, 5);
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 1;
			constraints.weighty = 0;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;

			constraints.insets = new Insets(5, 5, 5, 30);
			constraints.gridx = 0;
			constraints.gridy = 0;
			distPanel.add(getIATPanel(), constraints);

			constraints.insets = new Insets(5, 5, 5, 30);
			constraints.gridx = 1;
			constraints.gridy = 0;
			distPanel.add(getSTPanel(), constraints);
		}
		
		return distPanel;
	}
	
	private JPanel getStatsPanel(){
		if (statsPanel == null) {
			statsPanel = new JPanel();
			statsPanel.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createTitledBorder(Messages
							.getString("QuantAna.Simulation.ServerStats")),
					BorderFactory.createEmptyBorder(5, 5, 0, 5)));

			statsPanel.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.insets = new Insets(5, 5, 5, 5);
			constraints.fill = GridBagConstraints.VERTICAL;
			constraints.anchor = GridBagConstraints.EAST;
			constraints.weightx = 0;
			constraints.weighty = 0;
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			JLabel lblDummy = new JLabel();
			lblDummy.setMinimumSize(new Dimension(100, 10));
			statsPanel.add(lblDummy, constraints);

			constraints.fill = GridBagConstraints.BOTH;
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.weighty = 1;
			statsPanel.add(getServerTablePane(), constraints);
			statsPanel.setMinimumSize(new Dimension(730, 240));
		}
		
		return statsPanel;
	}
	
	private JScrollPane getServerTablePane() {
		if (serverTablePane == null) {
			serverTablePane = new JScrollPane(getServerTable());
			serverTablePane.setBorder(BorderFactory.createEmptyBorder());
			serverTablePane.setWheelScrollingEnabled(true);
			serverTablePane.setMinimumSize(new Dimension(720, 210));
		}
		return serverTablePane;
	}
	
	private JTable getServerTable(){
		if (tableServers == null) {
			serverTableMatrix = new Object[numServers][colServers.length];
			String[] servNames = graph.getTransitions();
			
			for (int i = 0; i < numServers; i++){
				serverTableMatrix[i][0] = servNames[i];
			}
			
			serverTableModel = new ServerTableModel(colServers, serverTableMatrix);
			serverTableModel.addTableModelListener(new TableModelListener() {
				public void tableChanged(TableModelEvent e) {
				}
			});
			
			tableServers = new JTable(serverTableModel) {
				private static final long serialVersionUID = 11L;

				// Implement table header tool tips.
				protected JTableHeader createDefaultTableHeader() {
					JTableHeader jt = new JTableHeader(columnModel) {
						private static final long serialVersionUID = 12L;

						public String getToolTipText(MouseEvent e) {
							Point p = e.getPoint();
							int index = columnModel.getColumnIndexAtX(p.x);
							int realIndex = columnModel.getColumn(index)
									.getModelIndex();
							return ttipsServers[realIndex];
						}
					};
					jt.setDefaultRenderer(new MyTableHeaderRenderer());
					return jt;
				}
			};
			
			tableServers.setDefaultRenderer(Object.class,
					new MyTableCellRenderer());

			tableServers.setDefaultEditor(JButton.class, new ButtonCellEditor());
		}
		
		return tableServers;
	}
	
	private JPanel getUtilPanel(){
		if (utilPanel == null) {
			utilPanel = new JPanel();
			utilPanel.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createTitledBorder(Messages
							.getString("QuantAna.Simulation.ResUtil")),
					BorderFactory.createEmptyBorder(5, 5, 0, 5)));

			utilPanel.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.insets = new Insets(5, 5, 5, 5);
			constraints.fill = GridBagConstraints.VERTICAL;
			constraints.anchor = GridBagConstraints.EAST;
			constraints.weightx = 0;
			constraints.weighty = 1;
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			utilPanel.add(getResUtilTablePane(), constraints);
			utilPanel.setMinimumSize(new Dimension(730, 240));
		}
		
		return utilPanel;
	}
	
	private JScrollPane getResUtilTablePane(){
		if (resUtilTablePane == null) {
			resUtilTablePane = new JScrollPane(getResUtilTable());
			resUtilTablePane.setBorder(BorderFactory.createEmptyBorder());
			resUtilTablePane.setWheelScrollingEnabled(true);
			resUtilTablePane.setMinimumSize(new Dimension(720, 210));

		}
		return resUtilTablePane;
	}
	
	private JTable getResUtilTable(){
		if (tableResUtil == null) {
			resUtilTableMatrix = new Object[resObjNum][colResUtil.length];
			Object[] resObjNames = resAlloc.getResources().values().toArray();
			
			for (int i = 0; i < resObjNum; i++){
				resUtilTableMatrix[i][0] = ((Resource)resObjNames[i]).getName();
			}
			
			resUtilTableModel = new ResUtilTableModel(colResUtil, resUtilTableMatrix);
			resUtilTableModel.addTableModelListener(new TableModelListener() {
				public void tableChanged(TableModelEvent e) {
				}
			});
			
			tableResUtil = new JTable(resUtilTableModel) {
				private static final long serialVersionUID = 11L;

				// Implement table header tool tips.
				protected JTableHeader createDefaultTableHeader() {
					JTableHeader jt = new JTableHeader(columnModel) {
						private static final long serialVersionUID = 12L;

						public String getToolTipText(MouseEvent e) {
							Point p = e.getPoint();
							int index = columnModel.getColumnIndexAtX(p.x);
							int realIndex = columnModel.getColumn(index)
									.getModelIndex();
							return ttipsResUtil[realIndex];
						}
					};
					jt.setDefaultRenderer(new MyTableHeaderRenderer());
					return jt;
				}
			};
			
			tableResUtil.setDefaultRenderer(Object.class,
					new MyTableCellRenderer());
		}
		
		return tableResUtil;
	}
	
	static class MyTableCellRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			super.getTableCellRendererComponent(table, value, isSelected,
					hasFocus, row, column);

			setFont(DefaultStaticConfiguration.DEFAULT_TABLE_FONT);
			setBackground(DefaultStaticConfiguration.DEFAULT_CELL_BACKGROUND_COLOR);
			
			if (column == 0){
				setHorizontalAlignment(LEFT);
				return this;
			}
			else if (column == 6){
				setHorizontalAlignment(CENTER);
				JButton b = new JButton("...");
				b.setSize(20,10);
				b.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						JOptionPane.showMessageDialog(null, "Details are shown here.");
					}
				});
				
				return b;
			}
			else {
				setHorizontalAlignment(RIGHT);
				return this;
			}
		}
	}

	/*private JSplitPane getSimResultPanel() {
		if (simResultPanel == null) {
			startSimulation();
			JScrollPane contentPane = new JScrollPane(getContentPanel());
			JScrollPane scrollPane = new JScrollPane(getTree());
			scrollPane.setWheelScrollingEnabled(true);
			simResultPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, contentPane);
			simResultPanel.setBorder(BorderFactory.createTitledBorder(
							BorderFactory.createEtchedBorder(),
							Messages
									.getString("QuantAna.Simulation.SimResults")));
			GridBagConstraints constraints = new GridBagConstraints();
			simResultPanel.setLayout(new GridBagLayout());
			constraints.insets = new Insets(5, 5, 5, 5);
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 0;
			constraints.weighty = 0;
			constraints.gridwidth = 3;
			constraints.gridheight = 1;
			constraints.gridx = 0;
			constraints.gridy = 0;
			simResultPanel.setOneTouchExpandable(true);
			simResultPanel.setDividerSize(8);
			simResultPanel.setDividerLocation(200);
			simResultPanel.setResizeWeight(1);
		}
		return simResultPanel;
	}*/

	/*private JPanel getContentPanel() {
		if (simParamPanel == null) {
			simParamPanel = new JPanel();
			GridBagConstraints constraints = new GridBagConstraints();
			simParamPanel.setLayout(new GridBagLayout());
			constraints.insets = new Insets(5, 5, 5, 5);
			constraints.anchor = GridBagConstraints.LINE_START;
			constraints.weightx = 0;
			constraints.weighty = 0;
			constraints.gridwidth = 3;
			constraints.gridheight = 1;
			constraints.gridx = 0;
			constraints.gridy = 0;
			simParamPanel.add(getIATPanel(), constraints);		

			JPanel jp = new JPanel();
			jp.setLayout(new GridBagLayout());
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.gridx = 0;
			constraints.gridy = 0;
			jp.add(getServiceTimeDistributionPanel(), constraints);
			constraints.gridx = 1;
			constraints.gridy = 0;
			jp.add(getQueueingDisciplinePanel(), constraints);
			constraints.gridx = 2;
			constraints.gridy = 0;
			jp.add(getTerminationConditionPanel(), constraints);
			
			constraints.gridwidth = 1;
			constraints.gridx = 0;
			constraints.gridy = 1;
			simParamPanel.add(jp, constraints);		
		}
		return simParamPanel;
	}*/

	private JPanel getIATPanel() {
		if (iatPanel == null) {
			iatPanel = new JPanel();
			iatPanel.setBorder(BorderFactory
							.createTitledBorder(
									BorderFactory.createEtchedBorder(),
									Messages.getString("QuantAna.Simulation.ArrivalRateDistribution")));
			GridBagConstraints constraints = new GridBagConstraints();
			iatPanel.setLayout(new GridBagLayout());
			constraints.insets = new Insets(5, 5, 5, 5);
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 1;
			constraints.weighty = 0;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			groupIAT = new ButtonGroup();
			JRadioButton optIATConstant = new JRadioButton(Messages
					.getString("QuantAna.Simulation.Constant"), false);
			JRadioButton optIATPoisson = new JRadioButton(Messages
					.getString("QuantAna.Simulation.Poisson"), true);
			JRadioButton optIATGaussian = new JRadioButton(Messages
					.getString("QuantAna.Simulation.Gaussian"), false);
			optIATConstant.setHorizontalAlignment(SwingConstants.LEFT);
			optIATConstant.setActionCommand("IAT_UNIFORM");
			optIATConstant.setPreferredSize(new Dimension(100, 20));
			optIATConstant.setMinimumSize(new Dimension(100, 20));
			optIATConstant.setMaximumSize(new Dimension(100, 20));
			optIATPoisson.setHorizontalAlignment(SwingConstants.LEFT);
			optIATPoisson.setActionCommand("IAT_EXP");
			optIATPoisson.setPreferredSize(new Dimension(100, 20));
			optIATPoisson.setMinimumSize(new Dimension(100, 20));
			optIATPoisson.setMaximumSize(new Dimension(100, 20));
			optIATGaussian.setHorizontalAlignment(SwingConstants.LEFT);
			optIATGaussian.setActionCommand("IAT_GAUSS");
			optIATGaussian.setPreferredSize(new Dimension(100, 20));
			optIATGaussian.setMinimumSize(new Dimension(100, 20));
			optIATGaussian.setMaximumSize(new Dimension(100, 20));
			
			groupIAT.add(optIATConstant);
			groupIAT.add(optIATPoisson);
			groupIAT.add(optIATGaussian);
			
			txtIATInterval = new JTextField();
			txtIATInterval.setEnabled(false);
			txtIATInterval.setMinimumSize(new Dimension(40, 20));
			txtIATInterval.setMaximumSize(new Dimension(40, 20));
			txtIATInterval.setPreferredSize(new Dimension(40, 20));
			txtIATStdDev = new JTextField();
			txtIATStdDev.setEnabled(false);
			txtIATStdDev.setMinimumSize(new Dimension(40, 20));
			txtIATStdDev.setMaximumSize(new Dimension(40, 20));
			txtIATStdDev.setPreferredSize(new Dimension(40, 20));
			JLabel lblInterval = new JLabel(Messages.getString("QuantAna.Simulation.Interval"));
			lblInterval.setMinimumSize(new Dimension(120, 20));
			lblInterval.setMaximumSize(new Dimension(120, 20));
			lblInterval.setPreferredSize(new Dimension(120, 20));
			lblInterval.setHorizontalAlignment(SwingConstants.RIGHT);
			JLabel lblDeviation = new JLabel(Messages.getString("QuantAna.Simulation.Deviation"));
			lblDeviation.setMinimumSize(new Dimension(120, 20));
			lblDeviation.setMaximumSize(new Dimension(120, 20));
			lblDeviation.setPreferredSize(new Dimension(120, 20));
			lblDeviation.setHorizontalAlignment(SwingConstants.RIGHT);
			
			constraints.gridx = 0;
			constraints.gridy = 0;
			iatPanel.add(optIATConstant, constraints);
			constraints.gridx = 1;
			constraints.gridy = 0;
			iatPanel.add(lblInterval, constraints);
			constraints.gridx = 2;
			constraints.gridy = 0;
			iatPanel.add(txtIATInterval, constraints);
			constraints.gridx = 3;
			constraints.gridy = 0;
			iatPanel.add(new JLabel("%"), constraints);
			constraints.gridx = 0;
			constraints.gridy = 1;
			iatPanel.add(optIATPoisson, constraints);
			constraints.gridx = 0;
			constraints.gridy = 2;
			iatPanel.add(optIATGaussian, constraints);
			constraints.gridx = 1;
			constraints.gridy = 2;
			iatPanel.add(lblDeviation, constraints);
			constraints.gridx = 2;
			constraints.gridy = 2;
			iatPanel.add(txtIATStdDev, constraints);
			
			optIATConstant.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					txtIATInterval.setEnabled(true);
					txtIATStdDev.setEnabled(false);
				}
			});

			optIATPoisson.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					txtIATInterval.setEnabled(false);
					txtIATStdDev.setEnabled(false);
				}
			});

			optIATGaussian.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					txtIATInterval.setEnabled(false);
					txtIATStdDev.setEnabled(true);
				}
			});
			
			iatPanel.setMinimumSize(new Dimension(450, 120));
			iatPanel.setMaximumSize(new Dimension(450, 120));
			iatPanel.setPreferredSize(new Dimension(450, 120));
		}
		
		return iatPanel;
	}

	private JPanel getSTPanel() {
		if (stPanel == null) {
			stPanel = new JPanel();
			stPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), Messages.getString("QuantAna.Simulation.ServiceTimeDistribution")));
			
			GridBagConstraints constraints = new GridBagConstraints();
			stPanel.setLayout(new GridBagLayout());
			constraints.insets = new Insets(5, 5, 5, 5);
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 1;
			constraints.weighty = 0;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;

			groupST = new ButtonGroup();
			JRadioButton optSTConstant = new JRadioButton(Messages
					.getString("QuantAna.Simulation.Constant"), false);
			JRadioButton optSTPoisson = new JRadioButton(Messages
					.getString("QuantAna.Simulation.Poisson"), true);
			JRadioButton optSTGaussian = new JRadioButton(Messages
					.getString("QuantAna.Simulation.Gaussian"), false);
			optSTConstant.setHorizontalAlignment(SwingConstants.LEFT);
			optSTConstant.setActionCommand("ST_UNIFORM");
			optSTConstant.setPreferredSize(new Dimension(100, 20));
			optSTConstant.setMinimumSize(new Dimension(100, 20));
			optSTConstant.setMaximumSize(new Dimension(100, 20));
			optSTPoisson.setHorizontalAlignment(SwingConstants.LEFT);
			optSTPoisson.setActionCommand("ST_EXP");
			optSTPoisson.setPreferredSize(new Dimension(100, 20));
			optSTPoisson.setMinimumSize(new Dimension(100, 20));
			optSTPoisson.setMaximumSize(new Dimension(100, 20));
			optSTGaussian.setHorizontalAlignment(SwingConstants.LEFT);
			optSTGaussian.setActionCommand("ST_GAUSS");
			optSTGaussian.setPreferredSize(new Dimension(100, 20));
			optSTGaussian.setMinimumSize(new Dimension(100, 20));
			optSTGaussian.setMaximumSize(new Dimension(100, 20));

			groupST.add(optSTConstant);
			groupST.add(optSTPoisson);
			groupST.add(optSTGaussian);
			
			txtSTInterval = new JTextField();
			txtSTInterval.setEnabled(false);
			txtSTInterval.setMinimumSize(new Dimension(40, 20));
			txtSTInterval.setMaximumSize(new Dimension(40, 20));
			txtSTInterval.setPreferredSize(new Dimension(40, 20));
			txtSTStdDev = new JTextField();
			txtSTStdDev.setEnabled(false);
			txtSTStdDev.setMinimumSize(new Dimension(40, 20));
			txtSTStdDev.setMaximumSize(new Dimension(40, 20));
			txtSTStdDev.setPreferredSize(new Dimension(40, 20));
			JLabel lblInterval = new JLabel(Messages.getString("QuantAna.Simulation.Interval"));
			lblInterval.setMinimumSize(new Dimension(120, 20));
			lblInterval.setMaximumSize(new Dimension(120, 20));
			lblInterval.setPreferredSize(new Dimension(120, 20));
			lblInterval.setHorizontalAlignment(SwingConstants.RIGHT);
			JLabel lblDeviation = new JLabel(Messages.getString("QuantAna.Simulation.Deviation"));
			lblDeviation.setMinimumSize(new Dimension(120, 20));
			lblDeviation.setMaximumSize(new Dimension(120, 20));
			lblDeviation.setPreferredSize(new Dimension(120, 20));
			lblDeviation.setHorizontalAlignment(SwingConstants.RIGHT);
			
			constraints.gridx = 0;
			constraints.gridy = 0;
			stPanel.add(optSTConstant, constraints);
			constraints.gridx = 1;
			constraints.gridy = 0;
			stPanel.add(lblInterval, constraints);
			constraints.gridx = 2;
			constraints.gridy = 0;
			stPanel.add(txtSTInterval, constraints);
			constraints.gridx = 3;
			constraints.gridy = 0;
			stPanel.add(new JLabel("%"), constraints);
			constraints.gridx = 0;
			constraints.gridy = 1;
			stPanel.add(optSTPoisson, constraints);
			constraints.gridx = 0;
			constraints.gridy = 2;
			stPanel.add(optSTGaussian, constraints);
			constraints.gridx = 1;
			constraints.gridy = 2;
			stPanel.add(lblDeviation, constraints);
			constraints.gridx = 2;
			constraints.gridy = 2;
			stPanel.add(txtSTStdDev, constraints);
			
			optSTConstant.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					txtSTInterval.setEnabled(true);
					txtSTStdDev.setEnabled(false);
				}
			});

			optSTPoisson.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					txtSTInterval.setEnabled(false);
					txtSTStdDev.setEnabled(false);
				}
			});

			optSTGaussian.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					txtSTInterval.setEnabled(false);
					txtSTStdDev.setEnabled(true);
				}
			});
			
			stPanel.setMinimumSize(new Dimension(450, 120));
			stPanel.setMaximumSize(new Dimension(450, 120));
			stPanel.setPreferredSize(new Dimension(450, 120));
		}
		
		return stPanel;
	}

	private JPanel getQueuePanel() {
		if (queuePanel == null) {
			queuePanel = new JPanel();
			queuePanel
					.setBorder(BorderFactory
							.createTitledBorder(
									BorderFactory.createEtchedBorder(),
									Messages
											.getString("QuantAna.Simulation.QueueingDiscipline")));

			GridBagConstraints constraints = new GridBagConstraints();
			queuePanel.setLayout(new GridBagLayout());
			constraints.insets = new Insets(5, 5, 5, 5);
//			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 0;
			constraints.weighty = 0;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			groupQD = new ButtonGroup();

			JRadioButton opt_q_1 = new JRadioButton(Messages
					.getString("QuantAna.Simulation.QueueingFIFO"), true);
			opt_q_1.setPreferredSize(new Dimension(180, 20));
			opt_q_1.setMinimumSize(new Dimension(180, 20));
			opt_q_1.setMaximumSize(new Dimension(180, 20));
			opt_q_1.setActionCommand("QUEUE_FIFO");
			groupQD.add(opt_q_1);

			JRadioButton opt_q_2 = new JRadioButton(Messages
					.getString("QuantAna.Simulation.QueueingLIFO"), false);
			opt_q_2.setPreferredSize(new Dimension(180, 20));
			opt_q_2.setMinimumSize(new Dimension(180, 20));
			opt_q_2.setMaximumSize(new Dimension(180, 20));
			opt_q_2.setActionCommand("QUEUE_LIFO");
			groupQD.add(opt_q_2);
			JLabel dummy = new JLabel();
			dummy.setPreferredSize(new Dimension(180, 20));
			dummy.setMinimumSize(new Dimension(180, 20));
			dummy.setMaximumSize(new Dimension(180, 20));
			
			constraints.gridx = 0;
			constraints.gridy = 0;
			queuePanel.add(opt_q_1, constraints);

			constraints.gridx = 0;
			constraints.gridy = 1;
			queuePanel.add(opt_q_2, constraints);

			constraints.gridx = 0;
			constraints.gridy = 2;
			queuePanel.add(dummy, constraints);

		}
		return queuePanel;
	}

	private JPanel getTermPanel() {
		if (termPanel == null) {
			termPanel = new JPanel();
			termPanel
					.setBorder(BorderFactory.createTitledBorder(BorderFactory
							.createEtchedBorder(), Messages
							.getString("QuantAna.Simulation.TerminationRule")));

			GridBagConstraints constraints = new GridBagConstraints();
			termPanel.setLayout(new GridBagLayout());
			constraints.insets = new Insets(5, 5, 5, 5);
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 0;
			constraints.weighty = 0;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			JLabel lblRuns = new JLabel(Messages
					.getString("QuantAna.Simulation.NumRuns"));
			lblRuns.setPreferredSize(new Dimension(80, 20));
			lblRuns.setMinimumSize(new Dimension(80, 20));
			lblRuns.setMaximumSize(new Dimension(80, 20));
			txtRuns = new JTextField("1");
			txtRuns.setPreferredSize(new Dimension(80, 20));
			txtRuns.setMinimumSize(new Dimension(80, 20));
			txtRuns.setMaximumSize(new Dimension(80, 20));
			txtRuns.setHorizontalAlignment(SwingConstants.RIGHT);
			stop1 = new JCheckBox(Messages
					.getString("QuantAna.Simulation.CasesCompleted"));
			stop2 = new JCheckBox(Messages
					.getString("QuantAna.Simulation.TimeElapsed"));
			stop1.setSelected(true);
			stop2.setSelected(true);
			constraints.gridx = 0;
			constraints.gridy = 0;
			termPanel.add(lblRuns, constraints);
			constraints.gridx = 1;
			constraints.gridy = 0;
			termPanel.add(txtRuns, constraints);
			constraints.gridx = 0;
			constraints.gridy = 1;
			termPanel.add(stop1, constraints);
			constraints.gridx = 0;
			constraints.gridy = 2;
			termPanel.add(stop2, constraints);
		}
		return termPanel;
	}

	private JPanel getButtonPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.insets = new Insets(5, 25, 5, 5);
			constraints.weightx = 0;
			constraints.weighty = 0;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
//			constraints.anchor = GridBagConstraints.LINE_END;
//			constraints.fill = GridBagConstraints.HORIZONTAL;

			JButton btnStart = new JButton();
			btnStart.setText(Messages.getTitle("QuantAna.Button.Start"));
			btnStart.setIcon(Messages.getImageIcon("QuantAna.Button.Start"));
			btnStart.setMinimumSize(new Dimension(120, 25));
			btnStart.setMaximumSize(new Dimension(120, 25));
			btnStart.setPreferredSize(new Dimension(120, 25));
			btnStart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					startSimulation();
				}
			});
			constraints.gridx = 0;
			constraints.gridy = 0;
			buttonPanel.add(btnStart, constraints);

			JButton btnConf = new JButton();
			btnConf.setText(Messages.getTitle("QuantAna.Button.TimeModel"));
			btnConf.setIcon(Messages.getImageIcon("QuantAna.Button.TimeModel"));
			btnConf.setMinimumSize(new Dimension(120, 25));
			btnConf.setMaximumSize(new Dimension(120, 25));
			btnConf.setPreferredSize(new Dimension(120, 25));
			btnConf.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					getTimeModelDialog();
				}
			});
			constraints.gridx = 0;
			constraints.gridy = 1;
			buttonPanel.add(btnConf, constraints);
			
			JButton btnProtocol = new JButton();
			btnProtocol.setText(Messages.getTitle("QuantAna.Button.Protocol"));
			btnProtocol.setIcon(Messages.getImageIcon("QuantAna.Button.Protocol"));
			btnProtocol.setMinimumSize(new Dimension(120, 25));
			btnProtocol.setMaximumSize(new Dimension(120, 25));
			btnProtocol.setPreferredSize(new Dimension(120, 25));
			btnProtocol.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
//					getProtocol();
				}
			});
			constraints.gridx = 0;
			constraints.gridy = 2;
			buttonPanel.add(btnProtocol, constraints);
		}
		
		return buttonPanel;
	}

	private void getTimeModelDialog() {
		new TimeModelDialog(this, tm);
	}

	public void updContents() {
//		getContentPanel().validate();
	}

	public WorkflowNetGraph getGraph() {
		return graph;
	}

	private void startSimulation() {
		SimParameters sp = new SimParameters();
		lambda = Double.parseDouble(txtLambda.getText());
		period = Double.parseDouble(txtPeriod.getText());
		sp.setLambda(lambda);
		sp.setTimeOfPeriod(period);

		sp.setRuns(Integer.parseInt(txtRuns.getText()));

		/*String op1 = groupIAT.getSelection().getActionCommand();
		if (op1.equals("IAT_UNIFORM")) {
			sp.setDistCases(ProbabilityDistribution.DIST_TYPE_UNIFORM);
			sp.setCPara1(Double.parseDouble(txt_p2_11.getText()));
			sp.setCPara2(Double.parseDouble(txt_p2_12.getText()));
		} else if (op1.equals("IAT_GAUSS")) {
			sp.setDistCases(ProbabilityDistribution.DIST_TYPE_GAUSS);
			sp.setCPara1(Double.parseDouble(txt_p2_31.getText()));
			sp.setCPara2(Double.parseDouble(txt_p2_32.getText()));
		} else {
			sp.setDistCases(ProbabilityDistribution.DIST_TYPE_EXP);
			sp.setCPara1(1/lambda*period);
			sp.setCPara2(0);
		}

		String op2 = groupST.getSelection().getActionCommand();
		if (op2.equals("ST_UNIFORM")) {
			sp.setDistServ(ProbabilityDistribution.DIST_TYPE_UNIFORM);
		} else if (op2.equals("ST_GAUSS")) {
			sp.setDistServ(ProbabilityDistribution.DIST_TYPE_GAUSS);
		} else {
			sp.setDistServ(ProbabilityDistribution.DIST_TYPE_EXP);
		}*/

		String op3 = groupQD.getSelection().getActionCommand();
		if (op3.equals("QUEUE_LIFO")) {
			sp.setQueue(Simulator.QD_LIFO);
		} else {
			sp.setQueue(Simulator.QD_FIFO);
		}

		if (stop1.isSelected()) {
			if (stop2.isSelected())
				sp.setStop(Simulator.STOP_BOTH);
			else
				sp.setStop(Simulator.STOP_CASE_DRIVEN);
		} else if (stop2.isSelected()) {
			sp.setStop(Simulator.STOP_TIME_DRIVEN);
		} else {
			sp.setStop(Simulator.STOP_NONE);
		}

		if (groupRoleNum > 2 && resObjNum > 1) {
			sp.setResUse(Simulator.RES_USED);
		} else {
			sp.setResUse(Simulator.RES_NOT_USED);
		}

		sim = new Simulator(this, graph, new ResourceUtilization(resAlloc), sp);
		sim.start();
//		updContents();
	}

	private void initResourceAlloc() {
		PetriNetModelProcessor pmp = (PetriNetModelProcessor) editor
				.getModelProcessor();

		ArrayList<String> roles = new ArrayList<String>();
		ArrayList<String> groups = new ArrayList<String>();
		Vector rVec = (Vector) pmp.getRoles();
		Vector gVec = (Vector) pmp.getOrganizationUnits();

		groupRoleNum = rVec.size() + gVec.size();

		for (int i = 0; i < rVec.size(); i++)
			roles.add(((ResourceClassModel) rVec.get(i)).getName());

		for (int i = 0; i < gVec.size(); i++)
			groups.add(((ResourceClassModel) gVec.get(i)).getName());

		Iterator iter = getTransModels().iterator();

		resAlloc = new ResourceAllocation(roles, groups, iter, pmp);

		resObjNum = resAlloc.getResources().size();
	}

	private LinkedList<TransitionModel> getTransModels() {
		LinkedList<TransitionModel> lst = new LinkedList<TransitionModel>();
		ArrayList<String> ids = new ArrayList<String>();
		Node[] nodes = graph.getNodeArray();

		for (int i = 0; i < nodes.length; i++)
			if (graph.isTransition(nodes[i].getId()))
				ids.add(nodes[i].getId());

		for (int i = 0; i < ids.size(); i++) {
			lst.add((TransitionModel) mec.getElementById(ids.get(i)));
		}

		return lst;
	}
	
	/*private JPanel getContentPanel() {
		if (contentPanel == null) {
			contentPanel = new JPanel();


		}
		return contentPanel;
	}*/
	
	/*private JTree getTree() {
		if (itemTree == null) {
			DefaultMutableTreeNode root = new DefaultMutableTreeNode("Items");
			DefaultMutableTreeNode curRoot = root;

			DefaultMutableTreeNode proto = new DefaultMutableTreeNode(
					"Protocol");
			curRoot.add(proto);
			panelList.put("Protocol", new ProtocolPanel(this));
			DefaultMutableTreeNode proc = new DefaultMutableTreeNode("Process");
			curRoot.add(proc);
			panelList.put("Process", new ProcessPanel(this));
			curRoot = proc;

			for (Server s : sim.getServerList().values()) {
				String name = s.getName();
				String id = s.getId();
				curRoot.add(new DefaultMutableTreeNode(s.toString()));
				panelList.put(s.getId(), new ServerPanel(this, id, name));
			}

			generatePanelContent();

			itemTree = new JTree(root);
			itemTree.setRootVisible(false);
			itemTree.putClientProperty("JTree.lineStyle", "Angled");

			itemTree.setCellRenderer(new TreeRenderer());

			itemTree.getSelectionModel().setSelectionMode(
					TreeSelectionModel.SINGLE_TREE_SELECTION);
			itemTree.addTreeSelectionListener(new TreeSelectionListener() {
				public void valueChanged(TreeSelectionEvent e) {
					TreePath path = itemTree.getSelectionPath();
					if (path == null)
						return;
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path
							.getLastPathComponent();
					String s = (String) selectedNode.getUserObject();
					updatePanelView(s);
				}
			});
		}

		return itemTree;
	}*/

	/*private void generatePanelContent() {
		curPanel = panelList.get("Protocol");
		contentPanel.add(curPanel);
		curPanel.setVisible(true);
		
		for (JPanel p : panelList.values()) {
			if (p instanceof ProtocolPanel) {

			} else if (p instanceof ProcessPanel) {

			} else {
				ServerPanel q = (ServerPanel) p;
				Server s = sim.getServerList().get(q.getId());
				double t = sim.getClock();
				q.setValues(s.getNumCalls(), s.getBusy() / t, s.getQueueLen()
						/ t, s.getMaxWaitTimeOfCase(), s.getMaxQueueLength(), s
						.getZeroDelays(), s.getNumAccess(),
						s.getNumDeparture(), s.getMaxNumCasesInParallel(), s
								.getNumCasesInParallel());
			}
		}
	}

	private void updatePanelView(String key) {
		String id = produceID(key);

		contentPanel.getComponent(0).setVisible(false);
		contentPanel.remove(curPanel);
		curPanel = panelList.get(id);
		contentPanel.add(curPanel);
		curPanel.setVisible(true);
	}

	private String produceID(String key) {
		if (key.equals("Protocol") || key.equals("Process"))
			return key;
		else
			return key.substring(key.indexOf("(") + 1, key.indexOf(")"));
	}
	
	public void getProtocol() {
		File f = (new File(sim.getProtocolName())).getAbsoluteFile();

		try {
			XMLReader xr = XMLReaderFactory.createXMLReader();

			handler = new DefaultHandler() {

				private long min = new Date().getTime();

				private long max = 0;

//				private int count = 0;
//				private int rec = 0;
				
				private boolean millis = false;
				private boolean msg = false;

				public void startDocument() {
//					protocolText += "--- Protocol Start ---\n\n";
					txtProtocol.append("--- Protocol Start ---\n\n");
				}

				public void startElement(String uri, String lname,
						String qname, Attributes attr) {
					try {
//						if (lname.equalsIgnoreCase("record"))
//							rec++;
//
//						// if (lname.equalsIgnoreCase("date")) count = 0;
						
						if (lname.equalsIgnoreCase("millis")) millis = true;
						
						if (lname.equalsIgnoreCase("message")) msg = true;

					} catch (Exception e) {
						// e.printStackTrace();
					}
				}

				public void characters(char[] ch, int start, int length) {
					count++;
					if (rec == 1 && count == 2) {
						String s = String.copyValueOf(ch, start, length);
						long l = Long.parseLong(s);
						min = l;
						max = l;
					}

					if (rec > 1 && count == 2) {
						String s = String.copyValueOf(ch, start, length);
						long l = Long.parseLong(s);
						if (l > max)
							max = l;
					}

					if (count == 9) {
						String s = String.copyValueOf(ch, start, length);
//						protocolText += s + "\n";
						txtProtocol.append(s + "\n");
					}
					
					String s = String.copyValueOf(ch, start, length);
					
					if (msg) txtProtocol.append(s + "\n");
					
					if (millis)	{
						long l = Long.parseLong(s);
						if (l > 0 && l < min) min = l;
						if (l > max) max = l;
					}
				}

				public void endElement(String uri, String lname, String qname) {
//					if (lname.equalsIgnoreCase("record"))
//						count = 0;
					
					if (lname.equalsIgnoreCase("millis")) millis = false;
					
					if (lname.equalsIgnoreCase("message")) msg = false;
				}

				public void endDocument() {
//					protocolText += "\n\nsimulation took " + (max - min) + " ms";
//					protocolText += "\n\n--- Protocol End ---";
					
					txtProtocol.append("\n\nsimulation took " + (max - min) + " ms");
					txtProtocol.append("\n\n--- Protocol End ---");
				}
			};

			xr.setContentHandler(handler);
			xr.setErrorHandler(handler);

			FileReader r = new FileReader(f);
			xr.parse(new InputSource(r));

		} catch (Exception e) {
			e.printStackTrace();
		}

//		return protocolText;
	}*/
	
	public Simulator getSimulator() {
		return sim;
	}
	
} // @jve:decl-index=0:visual-constraint="4,4"