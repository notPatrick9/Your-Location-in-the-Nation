package ThingsToDo;

import java.net.URI;


import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

public class AboutTheArea {
	public String getThingsTodo(String Zipcode) throws Exception {
	//public static void main(String[] args) throws Exception {
		
		
		String About = null;
		//String Zipcode = "17402";
		
		//Check to see if the caller passed a valid Zipcode
		if(Integer.parseInt(Zipcode) < 00501 || Integer.parseInt(Zipcode) > 99950) {
			throw new UnsupportedOperationException("Not a valid zipcode");
		}
		
		URL url = null;
		try {
			url = new URL("https://api.openai.com/v1/completions");
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String API_Key = "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";
		URLConnection conn = (HttpURLConnection) url.openConnection();
		
		
		conn.setRequestProperty("Authorization", "Bearer" +" "+ API_Key);
		
		
		
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setDoOutput(true); // Triggers POST.
		
		String jsonInputString = "{\r\n"
				+ "    \"model\": \"text-davinci-003\",\r\n"
				+ "    \"prompt\": \"Give information and fun things to do for the zipcode " + Zipcode +"dont include name of the town its in\",\r\n"
				+ "    \"max_tokens\": 350,\r\n"
				+ "    \"temperature\": 0\r\n"
				+ "  }";
		
		
		
		
		try(OutputStream os = conn.getOutputStream()) {
		    byte[] input = jsonInputString.getBytes("utf-8");
		    os.write(input, 0, input.length);			
		}
		
		
		
		try(BufferedReader br = new BufferedReader(
				  new InputStreamReader(conn.getInputStream(), "utf-8"))) {
				    StringBuilder response = new StringBuilder();
				    String responseLine = null;
				    while ((responseLine = br.readLine()) != null) {
				        response.append(responseLine.trim());
				    }
				    
				    
				    
				    
				    //parsing json file for Chat GPT response - after
				    
				    String Json = response.toString();
				    //creating new json parser
				    JSONParser parser = new JSONParser();
				    //parsing json response
				    JSONObject json = (JSONObject) parser.parse(Json);
				    //getting nested array "choices" in json response
				    JSONArray choices =  (JSONArray) json.get("choices");
				    if(choices.isEmpty()) {
				    	throw new Exception("Response was empty");
				    }
				    //creating hashmap from array
				    HashMap<String, String> getText = (HashMap) choices.get(0);
				    //getting value from text key
				    About = (String) getText.get("text");
				    
				    }
		
		
		//check to see if a valid connection was made
		if(((HttpURLConnection) conn).getResponseCode() != 200) {
			About = "An error occured when trying to get information about the area.";
		}
		
		
		
	
		//System.out.print(About);
		return About;
		
		
		}
	
		
		
		
		
		
	

}
