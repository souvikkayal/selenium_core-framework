package com.tt.utils;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {
	public static String getFileName(String filePath)
	{
		String output = "";
		
		File f = new File(filePath);
		output = f.getName();
		
		return output;
	}
	public static String getAbsolutePath(String filePath)
	{
		String output = "";
		
		File f = new File(filePath);
		output = f.getAbsolutePath();
		
		return output;
	}
	public static boolean exists(String filePath)
	{
		return (new File(filePath)).exists();
	}
	
	public static void createFolders(String filePath)
	{
		try
		{
		Files.createDirectories(Paths.get(filePath));
		}
		catch(Exception e)
		{
			
		}
	}
	
	public static void createTextFile(String path,String fileName) {
		try {
			String fullFilePath = path + fileName + ".txt";
			File f = new File(fullFilePath);
			if(!f.exists() && f.createNewFile()) {
				System.out.println("File \""+ fullFilePath + " \"is created successfully");
			}
			else if(f.exists()){
				System.out.println("File already exist");
			}
			else {
				System.out.println("Unexpected error occured and File was not created");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void writeToFile(String filePath,String content) {
		try {
			FileWriter fw = new FileWriter(filePath);
			fw.write(content);
			fw.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void appendToFile(String filePath,String content) {
		try {
			if(!exists(filePath)) {
				System.out.println("File does not exist,can not append content to it.");
			}
			else {
				FileWriter fw = new FileWriter(filePath,true);
				fw.append("\n");
				fw.append(DateUtil.getCurrentDate("dd-MMM-yyyy HH:mm:ss")+":"+content);
				fw.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		System.out.println(FileUtil.getFileName("D:\\Selenium File Generate\\DemoReport.html"));
		System.out.println(FileUtil.getAbsolutePath("D:\\Selenium File Generate\\DemoReport.html"));
		System.out.println(FileUtil.exists("D:\\Selenium File Generate\\DemoReport.html"));
		FileUtil.createFolders("D:\\Selenium File Generate\\Files");
		
		FileUtil.createTextFile("D:\\Selenium File Generate\\Files\\", "log");
		
		//FileUtil.writeToFile("D:\\Selenium File Generate\\Files\\log.txt", "This is the first line of this text file.");
		FileUtil.appendToFile("D:\\Selenium File Generate\\Files\\log.txt", "appending newline in This next line of this text file.");
		
	}

}
