package com.Nepian.Teleports.Listener.TeleporterCreating;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.Teleports.TeleporterManager;
import com.Nepian.Teleports.Data.TeleporterType;
import com.Nepian.Teleports.Event.TeleporterCreatingEvent;

public class TeleporterCreatingNameChecker implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	public static void onTeleportCreating(TeleporterCreatingEvent event) {
		
		if (event.isCancelled()) {
			return;
		}
		
		if (event.getName() == null) {
			event.getPlayer().sendMessage("This item does not have display name!");
			event.setCancelled(true);
			return;
		}
		
		if (TeleporterType.isEnd(event.getBlock())) {
			if (TeleporterManager.hasName(event.getName())) {
				event.getPlayer().sendMessage("This name already exists.");
				event.setCancelled(true);
				return;
			}
		}
	}
}
