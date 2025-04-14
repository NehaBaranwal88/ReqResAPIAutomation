package com.api.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//Read Request property file
public class ReadParamUtils {
	
	public static Properties RequestParams(){
		try {
			Properties props = new Properties();
	        String filepath= System.getProperty("user.dir")+"\\params\\Request_params.properties";
	        FileInputStream fis = new FileInputStream(filepath);
	        props.load(fis);
	        fis.close();
	        return props;
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		return null;
        
    }

}
