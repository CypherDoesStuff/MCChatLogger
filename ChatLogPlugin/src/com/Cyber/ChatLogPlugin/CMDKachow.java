package com.Cyber.ChatLogPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CMDKachow implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		ChatLogPlugin.singleton.getServer().broadcastMessage("KACHOW");
		
		return true;
	}
}
