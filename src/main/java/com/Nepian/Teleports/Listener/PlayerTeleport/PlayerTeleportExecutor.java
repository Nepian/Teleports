package com.Nepian.Teleports.Listener.PlayerTeleport;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.Nepian.Teleports.TeleporterManager;
import com.Nepian.Teleports.Event.PlayerTeleportEvent;

public class PlayerTeleportExecutor implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public static void onPlayerWarp(PlayerTeleportEvent event) {
		
		if (event.isCancelled()) {
			return;
		}
		
		Player player = event.getPlayer();
		String name = event.getTeleporterData().getName();
		Location location = TeleporterManager.getTeleportLocation(name);
		
		player.teleport(location, TeleportCause.PLUGIN);
		player.sendMessage("Warp to " + name + " !");

	}
}
