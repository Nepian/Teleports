package com.Nepian.Teleports;

import org.bukkit.command.CommandExecutor;

import com.Nepian.Teleports.Command.PrintWarpsCommand;

public class CommandManager {
	private static Main plugin;
	
	static {
		plugin = Main.getPlugin();
	}
	
	public static void load() {
		registerCommand("warplist", new PrintWarpsCommand());
	}
	
	private static void registerCommand(String name, CommandExecutor cmd) {
		plugin.getCommand(name).setExecutor(cmd);
	}
}
