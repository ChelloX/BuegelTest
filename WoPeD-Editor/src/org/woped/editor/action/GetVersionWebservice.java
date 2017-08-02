package org.woped.editor.action;

	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.net.HttpURLConnection;
	import java.net.MalformedURLException;
	import java.net.URL;
import java.util.ArrayList;

	public class GetVersionWebservice {
		// http://localhost:8080/RESTfulExample/json/product/get
		public static String getVersionString(){
			try {

				URL url = new URL("http://woped.dhbw-karlsruhe.de/woped/version/getVersion.php");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}
				BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
				String output=br.readLine();

				int index=output.indexOf(":");
				index=index+2;
				int indexEnd=output.indexOf("\"}");
				output=output.substring(index,indexEnd);
				 
					conn.disconnect();
					return output;
				
				  } catch (MalformedURLException e) {

					e.printStackTrace();
					return "";
				  } catch (IOException e) {

					e.printStackTrace();
					return "";
				  } catch (Exception e) {
						e.printStackTrace();
						return "";
				}
		}
		public static ArrayList<Integer> getVersion() {
			String tempVersNummer=getVersionString();
			
         	return seperateVersion(tempVersNummer);
		}
		public static ArrayList<Integer> seperateVersion(String tempVersNummer){

            int tempIndex=tempVersNummer.indexOf(".");
            
            ArrayList<Integer> arrayInt=new ArrayList<Integer>();
            while(tempVersNummer.length()>1)
            {
            arrayInt.add(Integer.parseInt(tempVersNummer.substring(0,tempIndex)));
            tempVersNummer=tempVersNummer.substring(tempIndex+1);
            tempIndex=tempVersNummer.indexOf(".");
           }
            arrayInt.add(Integer.parseInt(tempVersNummer.substring(0)));
             
			return arrayInt;
		}
	}


