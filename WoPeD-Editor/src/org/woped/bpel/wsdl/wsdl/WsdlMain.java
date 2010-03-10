package org.woped.bpel.wsdl.wsdl;

import org.woped.bpel.wsdl.Wsdl;
import org.woped.bpel.wsdl.wsdlFileRepresentation.WsdlFileRepresentation;

/*
 *	Eine Beispiel-Main Datei, die die Repr�sentation einer WSDL-Datei ausliest.
 *	--> Auf jeden Fall wieder zu l�schen!
 *		Selbiges gilt f�r die Datei Test-Files.example.wsdl
 */

public class WsdlMain {

	public static void main(String[] args) {

		Wsdl wsdl = new Wsdl();
		WsdlFileRepresentation wsdlFileRepresentation;
		try {

//			wsdlFileRepresentation = wsdl.readDataFromWSDL("HTTP://www-db.informatik.tu-muenchen.de/UniVerwaltung.wsdl");
			wsdlFileRepresentation = wsdl.readDataFromWSDL("http://www.esther-landes.de/wsdlFiles/example.wsdl");
//			wsdlFileRepresentation = wsdl.readDataFromWSDL("http://www.webservicex.net/globalweather.asmx?WSDL");


			wsdlFileRepresentation.getPartnerLinkTypes();
			} catch (Exception e) {
			e.printStackTrace();
		}
	}

}