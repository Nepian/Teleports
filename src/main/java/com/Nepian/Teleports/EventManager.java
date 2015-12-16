package com.Nepian.Teleports;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;

import com.Nepian.Teleports.Listener.CreateWarp;
import com.Nepian.Teleports.Listener.ExecutePlateWarp;
import com.Nepian.Teleports.Listener.RemoveWarp;
import com.Nepian.Teleports.Listener.CreateEnd.CreateEndExecute;
import com.Nepian.Teleports.Listener.CreateStart.CreateStartExecute;
import com.Nepian.Teleports.Listener.PlayerWarp.PlayerWarpExecutor;
import com.Nepian.Teleports.Listener.RemoveEnd.RemoveEndExecute;
import com.Nepian.Teleports.Listener.RemoveStart.RemoveStartExecute;

public class EventManager {
	private static final Main plugin;

	static {
		plugin = Main.getPlugin();
	}
	
	public static void load() {
		registerEvent(new CreateWarp());
		registerEvent(new RemoveWarp());
		registerEvent(new ExecutePlateWarp());
		
		registerCreateStartEvent();
		registerCreateEndEvent();
		registerRemoveStartEvent();
		registerRemoveEndEvent();
		registerPlayerWarpEEvent();
	}

	public static void callEvent(Event event) {
		Bukkit.getPluginManager().callEvent(event);
	}

	public static void registerEvent(Listener listener) {
		plugin.getServer().getPluginManager().registerEvents(listener, plugin);
	}
	
	/* Private Methods --------------------------------------------------------------------------*/
	
	private static void registerCreateStartEvent() {
		registerEvent(new CreateStartExecute());
	}
	
	private static void registerCreateEndEvent() {
		registerEvent(new CreateEndExecute());
	}
	
	private static void registerRemoveStartEvent() {
		registerEvent(new RemoveStartExecute());
	}
	
	private static void registerRemoveEndEvent() {
		registerEvent(new RemoveEndExecute());
	}
	
	private static void registerPlayerWarpEEvent() {
		registerEvent(new PlayerWarpExecutor());
	}
}
