package org.woped.qualanalysis.paraphrasing.controller;

import org.woped.core.config.ConfigurationManager;
import org.woped.core.controller.IEditor;
import org.woped.file.t2p.HttpRequest;
import org.woped.file.t2p.HttpResponse;
import org.woped.gui.translations.Messages;
import org.woped.qualanalysis.p2t.P2TSideBar;
import org.woped.qualanalysis.p2t.Process2Text;
import org.woped.qualanalysis.paraphrasing.webservice.PNMLExport;
import org.woped.qualanalysis.paraphrasing.webservice.ProcessToTextWebService;
import org.woped.qualanalysis.paraphrasing.webservice.ProcessToTextWebServiceImpl;

import javax.swing.*;
import javax.xml.ws.WebServiceException;
import java.io.ByteArrayOutputStream;

public class WebServiceThread extends Thread {

	private P2TSideBar paraphrasingPanel = null;
	private String[][] result = null;
	private boolean isFinished;

	public WebServiceThread(P2TSideBar paraphrasingPanel) {
		this.paraphrasingPanel = paraphrasingPanel;
		isFinished = false;
	}

	public boolean getIsFinished() {
		return isFinished;
	}

	public void run() {
		IEditor editor = paraphrasingPanel.getEditor();
		String url = "http://" + ConfigurationManager.getConfiguration().getProcess2TextServerHost() + ":"
				+ ConfigurationManager.getConfiguration().getProcess2TextServerPort()
				+ ConfigurationManager.getConfiguration().getProcess2TextServerURI()
				+ "/ProcessToTextWebService?wsdl";

		String[] arg = {url};

		if (editor.getModelProcessor().getElementContainer().getRootElements().size() > 3) {
// Use Webservice to call P2T
			try {
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				new PNMLExport().saveToStream(editor, stream);
				String text = stream.toString();
				String output;

				// old Webservice
				ProcessToTextWebServiceImpl pttService = new ProcessToTextWebServiceImpl();
				ProcessToTextWebService port = pttService.getProcessToTextWebServicePort();
				output = port.generateTextFromProcessSpecification(text);


				// new WebService
				/*
				url = "http://" + ConfigurationManager.getConfiguration().getProcess2TextServerHost() + ":"
				+ ConfigurationManager.getConfiguration().getProcess2TextServerPort()
				+ ConfigurationManager.getConfiguration().getProcess2TextServerURI()
				+ "/generate";
				HttpRequest req = new HttpRequest(url, text);
				HttpResponse res = req.getResponse();
				output = res.getBody();
				*/

				//End Comment here!
				//Do Not Comment the Following!!
				output = output.replaceAll("\\s*\n\\s*", "");
				isFinished = true;
				paraphrasingPanel.setNaturalTextParser(new Process2Text(output));
				paraphrasingPanel.setThreadInProgress(false);
			} catch (WebServiceException wsEx) {
				isFinished = true;
				JOptionPane.showMessageDialog(null,
						Messages.getString("Paraphrasing.Webservice.Error.Webserviceexception.Message", arg),
						Messages.getString("Paraphrasing.Webservice.Error.Title"), JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ex) {
				isFinished = true;
				JOptionPane.showMessageDialog(null,
						Messages.getString("Paraphrasing.Webservice.Error.Exception.Message", arg),
						Messages.getString("Paraphrasing.Webservice.Error.Title"), JOptionPane.INFORMATION_MESSAGE);

			} finally {
				paraphrasingPanel.showLoadingAnimation(false);
				paraphrasingPanel.enableButtons(true);
				paraphrasingPanel.setThreadInProgress(false);
			}


//	Alternative code for calling P2T locally (not via Webservice)
/*
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			new PNMLExport().saveToStream(editor, stream);
			String text = stream.toString();
			String output = "";
			org.woped.p2t.textGenerator.TextGenerator tg = new org.woped.p2t.textGenerator.TextGenerator();
			try {
				output = tg.toText(text, true);
			}
			catch (Exception e) {
				e.printStackTrace();
			}

			isFinished = true;
			output = output.replaceAll("\\s*\n\\s*", "");
			paraphrasingPanel.setNaturalTextParser(new Process2Text(output));
			paraphrasingPanel.setThreadInProgress(false);
			paraphrasingPanel.showLoadingAnimation(false);
			paraphrasingPanel.enableButtons(true);
			paraphrasingPanel.setThreadInProgress(false);

		} else {
			JOptionPane.showMessageDialog(null, Messages.getString("Paraphrasing.Webservice.Numberelements.Message"),
					Messages.getString("Paraphrasing.Webservice.Error.Title"), JOptionPane.INFORMATION_MESSAGE);
		}
	}

			/**
			 * Extracts the phrases from an PNML file and saves it to the result variable
			 *
			 * @throws XmlException
			 * @author Martin Meitz
			 */
/*	@SuppressWarnings("unused")

	private String extractDescriptionFromWebservice(String xmlString) throws XmlException {
		DocumentBuilderFactory xmlBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder xmlBuilder;
		String result = null;
		try {
			xmlBuilder = xmlBuilderFactory.newDocumentBuilder();
			Document document = xmlBuilder.parse(new InputSource(new StringReader(xmlString)));
			XPath xPath = XPathFactory.newInstance().newXPath();
			result = xPath.evaluate("/pnml/text", document.getChildNodes());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unused")
	private void extractDescription(String xmlString) throws XmlException {

		PnmlDocument pnmlDoc = PnmlDocument.Factory.parse(xmlString);
		PnmlType pnmlTag = pnmlDoc.getPnml();

		if (pnmlTag.getNetArray().length > 0) {
			NetType netTag = pnmlTag.getNetArray(0);

			if (netTag.isSetText()) {
				TextType textTag = netTag.getText();

				if (textTag.getPhraseArray().length > 0) {
					PhraseType[] phraseTag = textTag.getPhraseArray();

					this.result = new String[phraseTag.length][2];
					for (int i = 0; i < phraseTag.length; i++) {
						this.result[i][0] = phraseTag[i].getIds().trim();
						this.result[i][1] = phraseTag[i].getStringValue().trim();
					}
				} else {
					JOptionPane.showMessageDialog(null,
							Messages.getString("Paraphrasing.Webservice.Parsing.Empty.Message"),
							Messages.getString("Paraphrasing.Webservice.Parsing.Empty.Title"),
							JOptionPane.INFORMATION_MESSAGE);
				} */
		}
	}
}