package com.hcl.bhoomi.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.PrintWriter;
import java.time.LocalDateTime;

public class CustomerLogger {
	
	static String filepath="C:\\JavaPractice\\TestWS\\ShareRideProject\\log.txt";
	static FileWriter fw;
	
	public static void writeLogs(String message)
	{
		
			try {
				System.out.println("Entered into logger");
				if(fw==null)
				{
					fw=new FileWriter(filepath,true);
				}
				BufferedWriter bw=new BufferedWriter(fw);
				PrintWriter pw=new PrintWriter(bw);
				LocalDateTime dt=LocalDateTime.now();
				pw.println(message+"----"+dt);
				pw.flush();
				pw.close();
				bw.close();
				fw.close();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		
	}
	
	
	

}
