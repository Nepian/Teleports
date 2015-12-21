package com.Nepian.Teleports;

import org.bukkit.command.CommandExecutor;

import com.Nepian.Teleports.Command.CommandHandler;

public class CommandManager {
	private static Main plugin;
	
	static {
		plugin = Main.getPlugin();
	}
	
	public static void load() {
		registerCommand("teleports", new CommandHandler());
	}
	
	private static void registerCommand(String name, CommandExecutor cmd) {
		plugin.getCommand(name).setExecutor(cmd);
	}
}
