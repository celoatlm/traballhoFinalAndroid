package rb.edu.utfpr.trabalhofinal.control;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;



public class ResponseWebService {

	public static String SOAP_ACTION = "http://web/";
	private static String URL = "http://192.168.0.5:8080/OportunidadesWeb/services/OportunidadesSWPort";
	private static String NAMESPACE = "http://web/";
	private static ResponseWebService responseWebService  = new ResponseWebService();
	
	private ResponseWebService(){
		
	}
	public static ResponseWebService getInstance(){
		return responseWebService;
	}
	
	public Boolean getLogin(String user, String senha){
		Boolean aux = false;
		SoapObject request = new SoapObject(NAMESPACE, "OportunidadesSW");
		request.addProperty("user", "user");
		request.addProperty("senha", "user");
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		//envelope.dotNet=true;
		envelope.setOutputSoapObject(request);
		
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		
		try {

			//Chamada ao WS
			
			androidHttpTransport.call(SOAP_ACTION+"OportunidadesSW/login", envelope);
			
			SoapObject so = (SoapObject) envelope.getResponse();
			aux = (Boolean)so.getProperty(0);
			} catch (IOException e) {
			//showDialog
				Log.e("webservice", "erro");
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				Log.e("webservice", e.getMessage());
			}

		return aux;
	}
	
}
