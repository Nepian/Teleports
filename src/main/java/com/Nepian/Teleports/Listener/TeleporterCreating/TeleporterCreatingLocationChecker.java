package com.Nepian.Teleports.Listener.TeleporterCreating;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.Teleports.TeleporterManager;
import com.Nepian.Teleports.Event.TeleporterCreatingEvent;

public class TeleporterCreatingLocationChecker implements Listener {

	@EventHandler(priority = EventPriority.LOW)
	public static void onTeleporterCreating(TeleporterCreatingEvent event) {
		
		if (event.isCancelled()) {
			return;
		}
		
		if (!TeleporterManager.hasTeleporterData(event.getBlock())) {
			return;
		}
		
		event.getPlayer().sendMessage("This location already exists!");
		event.setCancelled(true);
	}
}
