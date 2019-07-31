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
package org.woped.file.t2p;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Port;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;

import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import org.woped.core.controller.AbstractApplicationMediator;
import org.woped.core.controller.IEditor;
import org.woped.editor.controller.vc.EditorVC;
import org.woped.file.PNMLImport;
import org.woped.file.t2p.FileReader.NoFileException;
import org.woped.gui.lookAndFeel.WopedButton;
import org.woped.gui.translations.Messages;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="mailto:freytag@dhbw-karlsruhe.de">Thomas Freytag </a> <br>
 *         <br>
 *         TODO: DOCUMENTATION (tfreytag)
 *
 *         17.01.2005
 */

@SuppressWarnings("serial")
public class T2PUI extends JDialog {
	private JTextAreaWithHint textArea;

	private JDialog loadDialog;

	private AbstractApplicationMediator mediator;

	private boolean requested = false;
	private SwingWorker<HttpResponse, Void> bgTask;

	private String inputText;

	private boolean recordingFlag = false;

	private WopedButton btnRecording;

	private boolean initialStart = true;

	private String result = "";

	private LiveSpeechRecognizer recognizer;

	private Configuration configuration;

	private boolean speechRecognizerThreadRunning = false;

	private boolean ignoreSpeechRecognitionResults = false;

	private boolean resourcesThreadRunning;

	private String speechRecognitionResult;

	private ExecutorService eventsExecutorService = Executors.newFixedThreadPool(2);

	private Logger logger = Logger.getLogger(getClass().getName());

	public T2PUI(AbstractApplicationMediator mediator) {
		this(null, mediator);
	}

	/**
	 * Constructor for AboutUI.
	 *
	 * @param owner
	 * @throws HeadlessException
	 */
	public T2PUI(Frame owner, AbstractApplicationMediator mediator) throws HeadlessException {
		super(owner, true);
		this.mediator = mediator;
		initialize();
	}
	/**
	 * This method initializes and layouts the about information
	 *
	 * @return void
	 */
	private void initialize() {
		this.setVisible(false);
		this.getContentPane().setLayout(new BorderLayout());
		this.setUndecorated(false);
		this.setResizable(true);

		textArea = new JTextAreaWithHint();

		this.setTitle(Messages.getString("T2P.tooltip"));
		this.getContentPane().add(wrapTextArea(initializeTextArea(textArea)), BorderLayout.CENTER);
		this.getContentPane().add(initializeButtonsPanel(), BorderLayout.SOUTH);

		this.pack();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - this.getWidth()) / 3, (screenSize.height - this.getHeight()) / 3);
		Dimension size = new Dimension(600, 440);
		this.setSize(size);

		// set prev text if available

		int index = 0;
		boolean doesContain = false;

		if (mediator.getViewControllers().containsKey("EDITOR_VC_" + index)) {
			doesContain = true;
			while(mediator.getViewControllers().containsKey("EDITOR_VC_" + index)) {
				index++;
			}
			index--;
		}

		if (doesContain) {
			String lastTextInput = ((EditorVC) mediator.getViewControllers().get("EDITOR_VC_" + index)).getEditorPanel().getT2PText();
			textArea.setText(lastTextInput);
		}


		//COnfiguration für SpeechRegognizer initilaisieren
		logger.log(Level.INFO, "Loading Speech Recognizer...\n");
		configuration = new Configuration();

		configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
		configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

	}


	private JTextAreaWithHint initializeTextArea(JTextAreaWithHint ta) {

		Font f = new Font("Lucia Grande", Font.PLAIN, 13);
		String hint = Messages.getString("T2PUI.HowTo");

		ta.setFont(f);
		ta.changeHintText(hint);
		ta.setLineWrap(true);
		ta.setWrapStyleWord(true);

		ta.requestFocus();
		ta.requestFocusInWindow();
		ta.setMargin(new Insets(10, 10, 10, 10));

		return ta;
	}

	private JScrollPane wrapTextArea(JTextAreaWithHint ta) {
		JScrollPane scrollPane = new JScrollPane(ta);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		return scrollPane;
	}

	private JPanel initializeButtonsPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

		buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

		String[] lang = { Messages.getString("T2PUI.Lang"), Messages.getString("T2PUI.Lang.English")};
		JComboBox<String> langBox = new JComboBox<String>(lang);
		langBox.setSelectedIndex(1);

		// TODO: Listener for ComboBox Changes

		WopedButton btnGenerate = new WopedButton(new AbstractAction() {
			public void actionPerformed(ActionEvent arg0) {
				request();
			}
		});

		btnGenerate.setMnemonic(KeyEvent.VK_A);
		btnGenerate.setText(Messages.getString("T2PUI.Button.Generate.Text"));
		btnGenerate.setIcon(new ImageIcon(getClass().getResource(Messages.getString("T2PUI.Button.Generate.Icon"))));


		WopedButton btnErase = new WopedButton(new AbstractAction() {
			public void actionPerformed(ActionEvent arg0) {
				clearTextArea();
			}
		});

		btnErase.setMnemonic(KeyEvent.VK_L);
		btnErase.setText(Messages.getString("T2PUI.Button.Clear.Text"));
		btnErase.setIcon(new ImageIcon(getClass().getResource(Messages.getString("T2PUI.Button.Clear.Icon"))));


		WopedButton btnUpload = new WopedButton(new AbstractAction() {
			public void actionPerformed(ActionEvent arg0) {
				readFile();
			}
		});

		btnUpload.setMnemonic(KeyEvent.VK_C);
		btnUpload.setText(Messages.getString("T2PUI.Button.Read.Text"));
		btnUpload.setIcon(new ImageIcon(getClass().getResource(Messages.getString("T2PUI.Button.Read.Icon"))));

		btnRecording = new WopedButton(new AbstractAction() {
			public void actionPerformed(ActionEvent arg0) {
				recording();
			}
		});

		btnRecording.setMnemonic(KeyEvent.VK_R);
		btnRecording.setText(Messages.getString("T2PUI.Button.Record.Speech"));
		btnRecording.setIcon(new ImageIcon(getClass().getResource(Messages.getString("T2PUI.Button.Record.Icon"))));

		buttonPanel.add(btnRecording);

		buttonPanel.add(btnUpload);
		buttonPanel.add(btnErase);
		buttonPanel.add(langBox);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(btnGenerate);

		return buttonPanel;
	}

	private void request() {
		if (requested) return;
		requested = true;

		inputText = textArea.getText();

		if (!inputText.isEmpty()) {
			httpBackgroundWorker(inputText);
			showLoadingBox();
		} else{
			showErrorPopUp("T2PUI.NoText.Title", "T2PUI.NoText.Text");
		}

		requested = false;
	}

	private void showLoadingBox() {
		JOptionPane jop = new JOptionPane();
		jop.setMessageType(JOptionPane.INFORMATION_MESSAGE);
		jop.setMessage(Messages.getString("T2PUI.Loading.Text"));

		loadDialog = jop.createDialog(this, Messages.getString("T2PUI.Loading.Title"));
		jop.setOptions(new String[]{Messages.getString("T2PUI.Loading.Cancel")});
		loadDialog.setVisible(true);

		// Thread gets blocked and awaits an UI action.

		if (bgTask != null && !bgTask.isDone() && !bgTask.isCancelled()) {
			bgTask.cancel(true);
		}
	}

	private void displayPMNL(String pnml) {
		PNMLImport pnmlImport = new PNMLImport(mediator);
		InputStream stream = new ByteArrayInputStream(pnml.getBytes(StandardCharsets.UTF_8));

		pnmlImport.run(stream, Messages.getString("Document.T2P.Output"), true);

		IEditor[] editor = pnmlImport.getEditor();

		EditorVC evc = ((EditorVC) editor[0]);

		try {
			if (inputText != null) {
				evc.getEditorPanel().showT2PBar(inputText);
			}

			evc.startBeautify(0, 0, 0);

		} catch (ArithmeticException exc) {
			close();

			// error popup
		}

		close();
	}

	private void showErrorPopUp(String titleId, String msgId) {
		String text[] = { Messages.getString("Dialog.Ok") };

		String msg = Messages.getStringReplaced(msgId, null);
		String title = Messages.getString(titleId);
		int optionType = JOptionPane.YES_NO_CANCEL_OPTION;
		int messageType = JOptionPane.ERROR_MESSAGE;

		int value = JOptionPane.showOptionDialog(
				null, msg, title, optionType, messageType, null, text, text[0]
		);
	}


	private void httpBackgroundWorker(String text) {
		if (bgTask != null && !bgTask.isDone()) return;

		// TODO: Hardcoded URL!!!
		String url = "http://193.196.7.214:8080/t2p/generate";

		bgTask = new SwingWorker<HttpResponse, Void>() {
			HttpRequest req;

			@Override
			protected HttpResponse doInBackground() throws Exception {
				req = new HttpRequest(url, text);
				return req.getResponse();
			}

			@Override
			protected void done() {
				try {
					HttpResponse res = get();

					String PNML = res.body;
					int httpCode = res.responseCode;

					if (!PNML.isEmpty() && httpCode == 200) {
						displayPMNL(PNML);
					} else {
						if (req != null) req.cancel();
						if (loadDialog != null) loadDialog.dispose();

						if (httpCode == 400) {
							showErrorPopUp("T2PUI.400Error.Title", "T2PUI.400Error.Text");
						} else if (httpCode == 500) {
							showErrorPopUp("T2PUI.500Error.Title", "T2PUI.GeneralError.Text");
						} else if (httpCode == 503) {
							showErrorPopUp("T2PUI.503Error.Title", "T2PUI.503Error.Text");
						} else {
							showErrorPopUp("T2PUI.GeneralError.Title", "T2PUI.GeneralError.Text");
						}
					}
				} catch (Exception e) {
					if (req != null) req.cancel();
					if (loadDialog != null) loadDialog.dispose();

					//showErrorPopUp("T2PUI.GeneralError.Title", "T2PUI.GeneralError.Text");
				}
			}
		};

		bgTask.execute();
	}

	private void close() {
		recognizer.stopRecognition();
		this.dispose();
	}

	public void clearTextArea() {
		if (textArea.getText() != null) {
			textArea.setText(null);
		}
	}

	public void readFile() {
		PlainTextFileReader r = new PlainTextFileReader();
		try {
			textArea.setText(r.read());
		} catch (NoFileException e) {
			showErrorPopUp("T2PUI.NoFile.Title", "T2PUI.NoFile.Text");
		}
	}

	/**
	 * Diese Klasse prüft ob Aufnahme starten oder Aufnahme beenden gedrückt wurde
	 */
	public void recording() {
		if(recordingFlag == false) {
			recordingFlag = true;
			btnRecording.setText(Messages.getString("T2PUI.Button.Record.Stop"));

			if(initialStart){
				try{
					recognizer = new LiveSpeechRecognizer(configuration);
				} catch(IOException io) {
					logger.log(Level.SEVERE, null, io);
				}

				startResourcesThread();
				startSpeechRecognition();
				initialStart = false;
			} else {
				speechRecognizerThreadRunning = true;
				result = "";
			}
		} else {
			recordingFlag = false;
			btnRecording.setText(Messages.getString("T2PUI.Button.Record.Speech"));

			speechRecognizerThreadRunning = false;
			//recognizer.stopRecognition();
			textArea.setText(result);
		}
	}

	/**
	 * Diese Klasse ist für die SpeechRecognition zuständig
	 */
	public synchronized void startSpeechRecognition() {

		//Check lock
		if (speechRecognizerThreadRunning)

			logger.log(Level.INFO, "Speech Recognition Thread already running...\n");
		else
			//Prüfe ob Thread bereits läuft
			eventsExecutorService.submit(() -> {

				//locks
				speechRecognizerThreadRunning = true;
				ignoreSpeechRecognitionResults = false;

				//Starte Recognition
				recognizer.startRecognition(true);

				//Informationen
				logger.log(Level.INFO, "You can start to speak...\n");

				try {
					while (speechRecognizerThreadRunning) {
						/*
						 * Während der Laufzeit der SpeechRecognition
						 */
						SpeechResult speechResult = recognizer.getResult();

						//Check if we ignore the speech recognition results
						if (!ignoreSpeechRecognitionResults) {

							//Prüfe result
							if (speechResult == null)
								logger.log(Level.INFO, "I can't understand what you said.\n");
							else {

								//Wörter Extraktion durch hypothesis
								speechRecognitionResult = speechResult.getHypothesis();

								//Wörter ausgeben und String hinzufügen
								System.out.println("You said: [" + speechRecognitionResult + "]\n");
								result+= speechRecognitionResult +" ";
							}
						} else
							logger.log(Level.INFO, "Ingoring Speech Recognition Results...");

					}
				} catch (Exception ex) {
					logger.log(Level.WARNING, null, ex);
					speechRecognizerThreadRunning = false;
				}

				logger.log(Level.INFO, "SpeechThread has exited...");

			});
	}

	/**
	 * Diese klasse startet eine Thread, welcher überprüft ob alle notwenigen Ressourcen für den Speechrecognizer verfügbar sind
	 */
	public void startResourcesThread() {

		//Prüfe ob Thread bereits läuft
		if (resourcesThreadRunning)
			logger.log(Level.INFO, "Resources Thread already running...\n");
		else
			//Starte ExecutorService
			eventsExecutorService.submit(() -> {
				try {

					//Lock
					resourcesThreadRunning = true;

					//Mikrophon Vorhanden prüfen
					while (true) {

						//Mikrophon Verfügbarkeit prüfen
						if (!AudioSystem.isLineSupported(Port.Info.MICROPHONE))
							logger.log(Level.INFO, "Microphone is not available.\n");

						Thread.sleep(150);
					}

				} catch (InterruptedException ex) {
					logger.log(Level.WARNING, null, ex);
					resourcesThreadRunning = false;
				}
			});
	}

}