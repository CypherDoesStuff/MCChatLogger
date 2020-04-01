package com.Cyber.ChatLogPlugin;

import org.bukkit.entity.*;
import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.inventory.*;

public class CMDBoatsHoes implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			
			ItemStack boats = new ItemStack(Material.DARK_OAK_BOAT);
			ItemStack hoes = new ItemStack(Material.WOODEN_HOE, 3);			
			boats.setAmount(3);
			
			player.getInventory().addItem(boats, hoes);
		}
		else
		{
			System.out.println("Command Sent From Non Player: " + sender);
		}
		
		return true;
	}
}
