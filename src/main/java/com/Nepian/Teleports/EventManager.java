package com.Nepian.Teleports;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;

import com.Nepian.Teleports.Listener.CreateTeleportListener;
import com.Nepian.Teleports.Listener.PlateTeleportListener;
import com.Nepian.Teleports.Listener.RemoveTeleportListener;
import com.Nepian.Teleports.Listener.CreateTeleport.CreateTeleportExecutor;
import com.Nepian.Teleports.Listener.PlayerWarp.PlayerWarpExecutor;
import com.Nepian.Teleports.Listener.RemoveTeleport.RemoveTeleportExecutor;

public class EventManager {
	private static final Main plugin;

	static {
		plugin = Main.getPlugin();
	}
	
	public static void load() {
		registerEvent(new CreateTeleportListener());
		registerEvent(new RemoveTeleportListener());
		registerEvent(new PlateTeleportListener());
		
		registerCreateTeleportEvent();
		registerRemoveTeleportEvent();
		registerPlayerTeleportEvent();
	}

	public static void callEvent(Event event) {
		Bukkit.getPluginManager().callEvent(event);
	}

	public static void registerEvent(Listener listener) {
		plugin.getServer().getPluginManager().registerEvents(listener, plugin);
	}
	
	/* Private Methods --------------------------------------------------------------------------*/
	
	private static void registerCreateTeleportEvent() {
		registerEvent(new CreateTeleportExecutor());
	}
	
	private static void registerRemoveTeleportEvent() {
		registerEvent(new RemoveTeleportExecutor());
	}
	
	private static void registerPlayerTeleportEvent() {
		registerEvent(new PlayerWarpExecutor());
	}
}
