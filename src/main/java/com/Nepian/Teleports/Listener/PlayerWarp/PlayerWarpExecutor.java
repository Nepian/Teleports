package com.Nepian.Teleports.Listener.PlayerWarp;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.Nepian.Teleports.TeleportManager;
import com.Nepian.Teleports.Event.PlayerWarpEvent;

public class PlayerWarpExecutor implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public static void onPlayerWarp(PlayerWarpEvent event) {
		
		if (event.isCancelled()) {
			return;
		}
		
		Player player = event.getPlayer();
		Location location = event.getLocation();
		String name = TeleportManager.getEndName(TeleportManager.getWarp(event.getBlock()).getBlock());
		
		player.teleport(location, TeleportCause.PLUGIN);
		player.sendMessage("Warp to " + name + " !");

	}
}
