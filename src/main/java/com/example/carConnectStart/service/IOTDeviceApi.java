package com.example.carConnectStart.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import org.json.*;

import org.springframework.stereotype.Service;

@Service
public class IOTDeviceApi {
	
	public String getData(String pid)
	{
		String command = "obd monitor "+pid+":5000 -c fake";

        String response = "";
        String result="";
        try {   
          Process p = Runtime.getRuntime().exec(command);
          p.getOutputStream().close();
         int count =0;
          InputStream processStdOutput = p.getInputStream();
          Reader r = new InputStreamReader(processStdOutput);
          BufferedReader br = new BufferedReader(r);
         
          while((response=br.readLine())!=null) 
          {     
                if(++count==6) 
                {
                        p.destroy();
                        br.close();
                        result=response;
                        break;
                }  
          }
          
             

        } catch (Exception e) {
          e.printStackTrace();
          return "";
        }
        JSONObject json = new JSONObject(result);
        
		return json.getString("value");
	}

}
