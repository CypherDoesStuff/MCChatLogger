package com.Cyber.ChatLogPlugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ChatLogger implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		LogManager.Manager.AddPlayer(event.getPlayer());
		LogManager.Manager.LogEntry(event.getPlayer(), new PlayerLogEntry(LogType.join));
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event)
	{
		LogManager.Manager.LogEntry(event.getPlayer(), new PlayerLogEntry(LogType.leave));
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event)
	{
		LogManager.Manager.LogEntry(event.getPlayer(), new PlayerLogEntry(LogType.chat, event.getMessage()));
	}
	
	@EventHandler
	public void onPlayerSendCommand(PlayerCommandPreprocessEvent event)
	{
		LogManager.Manager.LogEntry(event.getPlayer(), new PlayerLogEntry(LogType.command, event.getMessage()));
	}
}
