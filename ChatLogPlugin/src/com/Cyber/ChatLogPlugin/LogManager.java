package com.Cyber.ChatLogPlugin;

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.*;
import java.text.SimpleDateFormat;

import org.bukkit.entity.Player;

public class LogManager {
	
	public static class Manager{
		
		static List<UUID> players = new ArrayList<UUID>();
		static Hashtable<UUID, String> playerDirectories = new Hashtable<UUID, String>();
		static Hashtable<UUID, String> playerNames = new Hashtable<UUID, String>();
		static Hashtable<UUID, List<PlayerLogEntry>> playerLogs = new Hashtable<UUID, List<PlayerLogEntry>>();
		
		static String homeDirectory;
		
		public static void AddPlayer(Player player)
		{
			if(!player.hasPermission("chatLog.log"))
				return;
			
			UUID playerID = player.getUniqueId();
			
			if(!players.contains(playerID))
			{
				InitalizePlayer(player, playerID);
			}
		}
		
		public static void InitalizePlayer(Player player, UUID playerID)
		{
			players.add(playerID);
			
			if(!playerDirectories.containsKey(playerID))
				playerDirectories.put(playerID, homeDirectory + "/" + player.getName() + ".txt");
			
			playerNames.put(playerID, player.getName());
			playerLogs.put(playerID, new ArrayList<PlayerLogEntry>());
			
			File file = new File(playerDirectories.get(playerID));
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public static void LogEntry(Player player, PlayerLogEntry entry)
		{
			if(!player.hasPermission("chatLog.log"))
				return;
			
			if(!players.contains(player.getUniqueId()))
				InitalizePlayer(player, player.getUniqueId());
				
			playerLogs.get(player.getUniqueId()).add(entry);
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String writeString = "";
			
			switch(entry.type)
			{
			case join:
				writeString = player.getName() + " Joined at: " + formatter.format(entry.date) + "\r\n";
				break;
			case leave:
				writeString = player.getName() + " Left at: " + formatter.format(entry.date) + "\r\n";
				break;
			case chat:
				writeString = "    [Chat] " + entry.message + " at: " + formatter.format(entry.date) + "\r\n";
				break;
			case command:
				writeString = "    [Command] " + entry.message + " at: " + formatter.format(entry.date) + "\r\n";
				break;
			}
			
			Path path = Paths.get(playerDirectories.get(player.getUniqueId()));
			
			try {
				Files.write(path, writeString.getBytes(), StandardOpenOption.APPEND);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public static void SerializeHash(Hashtable<UUID, String> hash)
		{
			try {
				FileOutputStream fos = new FileOutputStream(homeDirectory + "/directoryMap.ser");
	            ObjectOutputStream oos = new ObjectOutputStream(fos);
	            oos.writeObject(hash);
	            oos.close();
	            fos.close();
	            System.out.printf("[CybersChatLog] Serialized Player File Locations");
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
		@SuppressWarnings("unchecked")
		public static Hashtable<UUID, String> DeserializeHash()
		{
		      Hashtable<UUID, String> hash = null;
		      
		      try
		      {
		         FileInputStream fis = new FileInputStream(homeDirectory + "/directoryMap.ser");
		         ObjectInputStream ois = new ObjectInputStream(fis);
		         hash = (Hashtable<UUID, String>) ois.readObject();
		         ois.close();
		         fis.close();
		      }
		      catch(IOException ioe)
		      {
		         ioe.printStackTrace();
		         return null;
		      }
		      catch(ClassNotFoundException c)
		      {
		         System.out.println("Class not found");
		         c.printStackTrace();
		         return null;
		      }
		      
		         System.out.println("[CybersChatLog] Deserialized Player File Locations");
		      return hash;
		}
	}
}
