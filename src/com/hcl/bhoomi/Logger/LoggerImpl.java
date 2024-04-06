package com.hcl.bhoomi.Logger;

import java.util.logging.Logger;

public class LoggerImpl {
	
	Logger l=Logger.getLogger("My Logegr");
	
	public void info(String msg)
	{
		System.out.println("ENtered into info");
		l.info(msg);
	}
	public void warning(String msg)
	{
		l.warning(msg);
	}

}
