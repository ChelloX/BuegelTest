package org.woped.file.t2p;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.woped.core.config.ConfigurationManager;
import org.woped.core.utilities.FileFilterImpl;
import org.woped.core.utilities.Platform;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.util.Optional;
import java.util.Scanner;
import javax.swing.*;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlainTextFileReader implements FileReader {
	private JFileChooser chooser = new JFileChooser();
	private StringBuilder sb; 
	
	@Override
	public String read()  {
		sb = new StringBuilder();
		int res = 0;
		File file;
		String lastdir = "";
		String abspath;

        if (ConfigurationManager.getConfiguration().isHomedirSet()) {
            lastdir = ConfigurationManager.getConfiguration().getHomedir();
        }

        if (ConfigurationManager.getConfiguration().isCurrentWorkingdirSet()) {
            lastdir = ConfigurationManager.getConfiguration().getCurrentWorkingdir();
        }

        if (!Platform.isMac()) {

			// Configuring the possible file extensions for the upload
            chooser.setCurrentDirectory(new File(lastdir));
            chooser.addChoosableFileFilter(new FileFilterImpl(FileFilterImpl.SAMPLEFilter, "ASCII text", "txt"));
			chooser.addChoosableFileFilter(new FileFilterImpl(FileFilterImpl.SAMPLEFilter, "Word", "doc"));
			chooser.addChoosableFileFilter(new FileFilterImpl(FileFilterImpl.SAMPLEFilter, "Word (2007 - 365)", "docx"));

            // Open the prepared file input dialog
            res = chooser.showOpenDialog(null);

			if (res == JFileChooser.APPROVE_OPTION) {
				file = chooser.getSelectedFile();
				abspath = file.getAbsolutePath();

				String[] regExps = {"docx?+", "txt"};
				String fileType = getExtensionByStringHandling(abspath).get();

				switch (fileType) {
					case "doc":
					case "docx":
						if (!readTextFromWordDocument(file)) return null;
						break;
					case "txt":
						if (!readTxtFile(file)) return null;
						break;
					default:
						//JFrame errorDialog: file extension not known
						break;
				}
            } else {
				return null;
			}
		}
		else {
            // Mac part, let's do the same with the awt.FileDialog
            JFrame frame = null;
			String fn;
			FileDialog fileDialog;

            fileDialog = new FileDialog(frame, "Choose a file", FileDialog.LOAD);
            fileDialog.setDirectory(lastdir);

            // Set fileFilter to txt files here
			fileDialog.setFilenameFilter(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith(".txt");
				}
			});

			fileDialog.setVisible(true);

			fn = fileDialog.getFile();
			if (fn != null) {
				abspath = fileDialog.getDirectory() + fn;
			}
			else {
			    return null;
            }

			file = new File(abspath);

			if (!readTxtFile(file)) return null;
		}

		// Set the new working dir to the current files location
        ConfigurationManager.getConfiguration().setCurrentWorkingdir(abspath.substring(0, abspath.lastIndexOf(File.separator)));

		// return the result of the StringBuilding
		return sb.toString();
	}

	/**
	 * This method reads the plain text information of a txt-file.
	 *
	 * @author Moritz Bielefeld, Semjon Geist, Benjamin Kanzler
	 * @param file
	 * @return
	 */
	private boolean readTxtFile(File file) {
		// Reading the information from the choosen file
		try {
			Scanner input = new Scanner(file);
            while (input.hasNext()) {
				sb.append(input.nextLine());
				sb.append('\n');
			}
			input.close();
		} catch (Exception e) {
			// TODO log information about the exception
			return false;
		}
		return true;
	}

	/**
	 *
	 * @author Moritz Bielefeld, Semjon Geist, Benjamin Kanzler
	 * @param filename
	 * @return
	 */
	public Optional<String> getExtensionByStringHandling(String filename) {
		return Optional.ofNullable(filename)
				.filter(f -> f.contains("."))
				.map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}

	/**
	 * Method to read text from doc and docx files
	 *
	 * @author Moritz Bielefeld, Semjon Geist, Benjamin Kanzler
	 * @param file
	 * @return
	 */
	private boolean readTextFromWordDocument(File file){
		try {
			FileInputStream fis = new FileInputStream(file.getAbsoluteFile());
			XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
			XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
			sb.append(extractor.getText());
		} catch(Exception ex) {
			ex.printStackTrace();
			// TODO Errorwindow
			return false;
		}
		return true;
	}

}


