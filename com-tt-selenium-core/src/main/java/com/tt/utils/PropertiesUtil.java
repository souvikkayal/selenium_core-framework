package com.tt.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

	 public String filePath = "";
	 public String onEnv = "";
	 Properties prop;
	 String currInstance = "";
	 
	 public PropertiesUtil(String filePath) {
		 this.filePath = filePath;
		 prop = new Properties();
		 try {
			 InputStream is = new FileInputStream(this.filePath);
			 prop.load(is);
			 is.close();
			 currInstance = prop.getProperty("run_on_instance");
			 System.out.println("Scripts needs to run on: "+currInstance);
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		 
	 }
	 
	 public String getPropertyValue(String propName) {
		 return getPropertyValue(propName,currInstance);
	 }
	 
	 public String getPropertyValue(String propName,String prefix) {
		 String ret = "";
		 if(prefix!=null && !"".equals(prefix))
			 propName=prefix+"_"+propName;
		 ret = prop.getProperty(propName);
		 
		 System.out.println("Value of property ["+propName+"]:"+ret);
		 return ret;
	 }
	 
	 
	 
	 
	 public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getOnEnv() {
		return onEnv;
	}

	public void setOnEnv(String onEnv) {
		this.onEnv = onEnv;
	}

	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}

	public String getCurrInstance() {
		return currInstance;
	}

	public void setCurrInstance(String currInstance) {
		this.currInstance = currInstance;
	}

	public static void main(String args[]) {
		 PropertiesUtil props = new PropertiesUtil("D:\\Selenium File Generate\\properties\\env.properties");
		 String userName = props.getPropertyValue("user_name");
		 String userPassword = props.getPropertyValue("user_password");
		 String appUrl = props.getPropertyValue("app_url");
	 }
}
