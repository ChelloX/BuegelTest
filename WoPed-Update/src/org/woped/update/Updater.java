package org.woped.update;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Updater {

	public static void main(String[] args) {
		String rootPath = new File("").getAbsolutePath();
		System.out.println(rootPath);
		
		String fileName = null;
		
		try{
			fileName = args[0];
		}catch(ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
		}
		
		if (fileName == null){
			System.exit(0);
		}
		
		System.out.println(fileName);
		if (!rootPath.substring(rootPath.length() - 1).equals("/")) {
			rootPath = rootPath + "/";
		}
		try {
			
			rootPath = unzip(rootPath, fileName);
			copyFiles(new File(rootPath), new File("").getAbsolutePath());
		} catch (IOException e1) {
			e1.printStackTrace();
			System.exit(0);
		}

		cleanup(rootPath, fileName);
		//TODO: Woped im aktuellen pfad starten
		String[] cmd = { new File("").getAbsolutePath() + "\\WoPeD.exe"  /*TODO: For testing pls insert absolute path of woped exe*/ };
		try {
			Runtime runtime = Runtime.getRuntime();
			runtime.exec(cmd);
		} catch (IOException exception) {
			System.out.println("Caught IOException: " + exception.getMessage());
		}
		System.exit(0);
	}

	private static String unzip(String rootPath, String zipFilename) throws IOException {
		int BUFFER = 2048;
		BufferedOutputStream dest = null;
		BufferedInputStream is = null;

		ZipFile zipfile = new ZipFile(rootPath + zipFilename);
		rootPath = rootPath + zipFilename.split("\\.")[0] + "/";
		Enumeration e = zipfile.entries();
		new File(rootPath).mkdir();
		while (e.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) e.nextElement();
			if (entry.isDirectory()) {
				new File(rootPath + entry.getName()).mkdir();
			} else {
				new File(rootPath + entry.getName()).createNewFile();
				is = new BufferedInputStream(zipfile.getInputStream(entry));

				byte[] data = new byte[BUFFER];
				FileOutputStream fos = new FileOutputStream(rootPath + entry.getName());
				dest = new BufferedOutputStream(fos, BUFFER);
				int count;
				while ((count = is.read(data, 0, BUFFER)) != -1) {
					dest.write(data, 0, count);
				}
				dest.flush();
				dest.close();
				is.close();
			}
		}
		zipfile.close();
		return rootPath;
	}

	private static void cleanup(String rootPath, String zipFileName) {
		System.out.println("Cleanup Files");
		File f = new File(rootPath + zipFileName);
		f.delete();
		remove(new File(rootPath));
		new File(rootPath).delete();
		System.out.println("Cleanup Files finshed");
	}

	private static void remove(File fileToRemove) {
		File[] files = fileToRemove.listFiles();
		for (File ff : files) {
			if (ff.isDirectory()) {
				remove(ff);
				ff.delete();
			} else {
				ff.delete();
			}
		}
	}

	private static void copyFiles(File rootFolder, String destDir) throws IOException {
		System.out.println("Copy Files");
		File[] allFilesInFolder = rootFolder.listFiles();
		for (File file : allFilesInFolder) {
			if (file.isDirectory()) {
				new File(destDir + "/" + file.getName()).mkdir();
				copyFiles(file, destDir + "/" + file.getName());
			} else {
				if (!file.getName().contains("config")){
					copy(file.getAbsolutePath(), destDir + "/" + file.getName());
				}
			}
		}
		System.out.println("Copy Files Finished");
	}

	public static void copy(String srcFile, String destFile) throws java.io.FileNotFoundException, IOException {
		File f1 = new File(srcFile);
		File f2 = new File(destFile);
		InputStream in = new java.io.FileInputStream(f1);
		OutputStream out = new FileOutputStream(f2);
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
	}
}
