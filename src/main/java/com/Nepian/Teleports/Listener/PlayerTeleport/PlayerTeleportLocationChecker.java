package com.Nepian.Teleports.Listener.PlayerTeleport;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.Teleports.TeleporterManager;
import com.Nepian.Teleports.Event.PlayerTeleportEvent;

public class PlayerTeleportLocationChecker implements Listener {

	@EventHandler(priority = EventPriority.LOW)
	public static void onPlayerTeleport(PlayerTeleportEvent event) {
		
		if (event.isCancelled()) {
			return;
		}
		
		if (TeleporterManager.hasTeleportLocation(event.getBlock())) {
			return;
		}
		
		String name = event.getTeleporterData().getName();
		event.getPlayer().sendMessage("This teleport is Closed! (" + name + ")");
		event.setCancelled(true);
	}
}
