package com.Cyber.ChatLogPlugin;

import java.util.Date;

public class PlayerLogEntry {
	LogType type;
	String message;
	Date date;
	
	public PlayerLogEntry(LogType type)
	{
		if(type == LogType.join)
			this.message = "Joined The Server";
		else if(type == LogType.leave)
			this.message = "Left The Server";
		
		this.type = type;
		this.date = new Date();		
	}
	
	public PlayerLogEntry(LogType type, String message)
	{
		this.type = type;
		this.message = message;
		this.date = new Date();		
	}
}
