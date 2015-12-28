package com.Nepian.Teleports.Listener.RemoveTeleport;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.Teleports.TeleporterManager;
import com.Nepian.Teleports.Event.TeleporterRemovingEvent;

public class RemoveTeleportExecutor implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public static void onRemoveTeleport(TeleporterRemovingEvent event) {
		
		if (event.isCancelled()) {
			return;
		}
		
		TeleporterManager.remove(event);
		String name = event.getTeleportLocationData().getName();
		event.getPlayer().sendMessage("Removed this teleport! (" + name + ")");
	}
}
