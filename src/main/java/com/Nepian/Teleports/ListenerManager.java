package com.Nepian.Teleports;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;

import com.Nepian.Teleports.Listener.ChangeTeleporterDirectionListener;
import com.Nepian.Teleports.Listener.GetTeleportLocationDataListener;
import com.Nepian.Teleports.Listener.TeleporterCreatingListener;
import com.Nepian.Teleports.Listener.TeleporterRemovingListener;
import com.Nepian.Teleports.Listener.AfterCommand.AddMemberListener;
import com.Nepian.Teleports.Listener.AfterCommand.ChangePrivateListener;
import com.Nepian.Teleports.Listener.PlayerTeleport.PlayerTeleportEndPrivateChecker;
import com.Nepian.Teleports.Listener.PlayerTeleport.PlayerTeleportExecutor;
import com.Nepian.Teleports.Listener.PlayerTeleport.PlayerTeleportLocationChecker;
import com.Nepian.Teleports.Listener.PlayerTeleport.PlayerTeleportStartPrivateChecker;
import com.Nepian.Teleports.Listener.RemoveTeleport.RemoveTeleportExecutor;
import com.Nepian.Teleports.Listener.RemoveTeleport.RemoveTeleportOwnerChecker;
import com.Nepian.Teleports.Listener.TeleporterCreating.TeleporterCreatingExecutor;
import com.Nepian.Teleports.Listener.TeleporterCreating.TeleporterCreatingLocationChecker;
import com.Nepian.Teleports.Listener.TeleporterCreating.TeleporterCreatingNameChecker;
import com.Nepian.Teleports.Listener.TeleporterCreating.TeleporterCreatingPermissionChecker;
import com.Nepian.Teleports.Listener.UseTeleport.ButtonTeleportListener;
import com.Nepian.Teleports.Listener.UseTeleport.PlateTeleportListener;
import com.Nepian.Teleports.Listener.UseTeleport.SignTeleportListener;

public class ListenerManager {
	private static final Main plugin;

	static {
		plugin = Main.getPlugin();
	}
	
	public static void load() {
		registerListener(new TeleporterCreatingListener());
		registerListener(new TeleporterRemovingListener());
		
		registerListener(new PlateTeleportListener());
		registerListener(new ButtonTeleportListener());
		registerListener(new SignTeleportListener());
		
		registerListener(new ChangeTeleporterDirectionListener());
		registerListener(new GetTeleportLocationDataListener());
		
		registerTeleporterCreatingListener();
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
	
	private static void registerTeleporterCreatingListener() {
		registerListener(new TeleporterCreatingPermissionChecker());
		registerListener(new TeleporterCreatingLocationChecker());
		registerListener(new TeleporterCreatingNameChecker());
		registerListener(new TeleporterCreatingExecutor());
	}
	
	private static void registerRemoveTeleportListener() {
		registerListener(new RemoveTeleportOwnerChecker());
		registerListener(new RemoveTeleportExecutor());
	}
	
	private static void registerPlayerTeleportListener() {
		registerListener(new PlayerTeleportLocationChecker());
		registerListener(new PlayerTeleportStartPrivateChecker());
		registerListener(new PlayerTeleportEndPrivateChecker());
		registerListener(new PlayerTeleportExecutor());
	}
	
	private static void registerAfterCommandListener() {
		registerListener(new AddMemberListener());
		registerListener(new ChangePrivateListener());
	}
}
