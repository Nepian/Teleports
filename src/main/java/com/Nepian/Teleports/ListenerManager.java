package com.Nepian.Teleports;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;

import com.Nepian.Teleports.Listener.ChangeTeleportDirectionListener;
import com.Nepian.Teleports.Listener.CreateTeleportListener;
import com.Nepian.Teleports.Listener.GetTeleportLocationDataListener;
import com.Nepian.Teleports.Listener.PlateTeleportListener;
import com.Nepian.Teleports.Listener.RemoveTeleportListener;
import com.Nepian.Teleports.Listener.AfterCommand.AddMemberListener;
import com.Nepian.Teleports.Listener.CreateTeleport.CreateTeleportExecutor;
import com.Nepian.Teleports.Listener.PlayerWarp.PlayerWarpExecutor;
import com.Nepian.Teleports.Listener.RemoveTeleport.RemoveTeleportExecutor;
import com.Nepian.Teleports.Listener.RemoveTeleport.RemoveTeleportOwnerChecker;

public class ListenerManager {
	private static final Main plugin;

	static {
		plugin = Main.getPlugin();
	}
	
	public static void load() {
		registerListener(new CreateTeleportListener());
		registerListener(new RemoveTeleportListener());
		registerListener(new PlateTeleportListener());
		registerListener(new ChangeTeleportDirectionListener());
		registerListener(new GetTeleportLocationDataListener());
		
		registerCreateTeleportListener();
		registerRemoveTeleportListener();
		registerPlayerTeleportListener();
		registerAfterCommandListener();
	}

	public static void callEvent(Event event) {
		Bukkit.getPluginManager().callEvent(event);
	}

	public static void registerListener(Listener listener) {
		plugin.getServer().getPluginManager().registerEvents(listener, plugin);
	}
	
	/* Private Methods --------------------------------------------------------------------------*/
	
	private static void registerCreateTeleportListener() {
		registerListener(new CreateTeleportExecutor());
	}
	
	private static void registerRemoveTeleportListener() {
		registerListener(new RemoveTeleportOwnerChecker());
		registerListener(new RemoveTeleportExecutor());
	}
	
	private static void registerPlayerTeleportListener() {
		registerListener(new PlayerWarpExecutor());
	}
	
	private static void registerAfterCommandListener() {
		registerListener(new AddMemberListener());
	}
}
