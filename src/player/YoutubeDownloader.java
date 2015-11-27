package player;

import java.io.*;
import java.net.*;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class YoutubeDownloader {
//	
//	public static void main(String[] args){
//		YoutubeDownloader yb = new YoutubeDownloader();
//		try {
//			System.out.println(yb.getDownloadURL("https://www.youtube.com/watch?v=vButlCZPSDw"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
    public FileOutputStream downloadSong(final String urlString, final File filename) throws MalformedURLException, IOException {
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        try {
            in = new BufferedInputStream(new URL(urlString).openStream());
            fout = new FileOutputStream(filename);

            final byte data[] = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
                fout.write(data, 0, count);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (fout != null) {
                fout.close();
            }
            return fout;
        }
    }
    
    public String[] getDownloadURL(String url) throws Exception {
        URL urlService = new URL("http://youtubeinmp3.com/fetch/?format=JSON&video="+url);
        URLConnection yc = urlService.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine, output = "";
        while ((inputLine = in.readLine()) != null) 
        	output += inputLine;
          //System.err.println(inputLine);
        
        //link = linkParser(output);
        
        JSONObject j = jsonParser(output);
        String link = getURLSong(j);
        String title = getSongTitle(j);
        String[] s = {link, title};
        in.close();
        return s;
    }
    
    public JSONObject jsonParser(String toParse) throws ParseException{
    	JSONParser jsonParser = new JSONParser();
    	JSONObject jsonObject = (JSONObject) jsonParser.parse(toParse);
    	return jsonObject;
    }
    
    public String getURLSong(JSONObject jsonObject) throws ParseException{
    	String downloadLink = (String) jsonObject.get("link");
		return downloadLink;
    }
    
    public String getSongTitle(JSONObject jsonObject){
    	String songTitle = (String) jsonObject.get("title");
		return songTitle;
    }
}
