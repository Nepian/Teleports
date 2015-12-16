package com.Nepian.Teleports;

import org.bukkit.plugin.java.JavaPlugin;

import com.Nepian.Teleports.Configuration.Config;

public class Main extends JavaPlugin {
	private static Main plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		
		Config.load();
		EventManager.load();
		CommandManager.load();
		TeleportManager.load();
	}
	
	@Override
	public void onDisable() {
		TeleportManager.save();
		Config.save();
	}
	
	public static Main getPlugin() {
		return plugin;
	}
}
