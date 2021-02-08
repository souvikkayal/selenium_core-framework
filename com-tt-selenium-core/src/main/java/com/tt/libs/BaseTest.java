package com.tt.libs;

import java.util.HashMap;

import com.tt.ui.Browser;
import com.tt.utils.Reporter;

public abstract class BaseTest {
	
	public Browser browser = null;
	protected HashMap<String, String> testdata = null;
	protected Reporter r = null;
	
	public final String browserType = "CHROME";
	
	public abstract void initializeTest(String url,String testName);
	
	public abstract void executeTest();
	public abstract void prepareTestData(String filePath);
	
	public void setTestData(String key,String value) {
		if(testdata != null) {
			testdata.put(key,value);
		}
	}
	
	public abstract void setTestData(String... arg);
	public  void setTestData(HashMap<String, String> data) {
		this.testdata = data;
	}
	
	public String d(String key)
	{
		return testdata.get(key).toString();
	}
	
	public void setReporter(Reporter r)
	{
	  	this.r = r;
	}
	
	public void closingTest() {
		r.stop();
	}
}
