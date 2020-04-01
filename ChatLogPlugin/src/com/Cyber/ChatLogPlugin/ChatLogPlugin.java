package com.Cyber.ChatLogPlugin;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

public class ChatLogPlugin extends JavaPlugin{

	public static ChatLogPlugin singleton;
	
	@Override
	public void onEnable()
	{
		singleton = this;
		checkDataFolder();
		getPlayerDirectoryHash();
		
		getServer().getPluginManager().registerEvents(new ChatLogger(), this);
		this.getCommand("BnH").setExecutor(new CMDBoatsHoes());
		this.getCommand("Kachow").setExecutor(new CMDKachow());
	}
	
	@Override
	public void onDisable()
	{
		LogManager.Manager.SerializeHash(LogManager.Manager.playerDirectories);
	}
	
	private void checkDataFolder()
	{		
		File dataFolder = getDataFolder();
		if(!dataFolder.exists())
		{		
			System.out.println("[CybersChatLog] Data Folder Not Found! Creating Directory.");
			dataFolder.mkdirs();
		}
		
		LogManager.Manager.homeDirectory = getDataFolder().toString();
	}
	
	private void getPlayerDirectoryHash()
	{
		File dirFile = new File(LogManager.Manager.homeDirectory + "/directoryMap.ser");
		if(dirFile.exists())
			LogManager.Manager.playerDirectories = LogManager.Manager.DeserializeHash();
	}
}
