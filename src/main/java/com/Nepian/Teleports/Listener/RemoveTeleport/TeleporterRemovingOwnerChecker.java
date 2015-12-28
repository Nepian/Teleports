package com.Nepian.Teleports.Listener.RemoveTeleport;

import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.Teleports.Data.TeleporterData;
import com.Nepian.Teleports.Event.TeleporterRemovingEvent;

public class TeleporterRemovingOwnerChecker implements Listener {

	@EventHandler(priority = EventPriority.LOW)
	public static void onRemoveTeleport(TeleporterRemovingEvent event) {
		
		if (event.isCancelled()) {
			return;
		}
		
		TeleporterData data = event.getTeleportLocationData();
		UUID owner = data.getOwner().getUniqueId();
		UUID player = event.getPlayer().getUniqueId();
		
		if (!player.equals(owner)) {
			event.setCancelled(true);
			event.getPlayer().sendMessage("You are not owner of this teleport!");
		}
	}
}
