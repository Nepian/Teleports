package com.Nepian.Teleports.Listener.RemoveTeleport;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.Teleports.TeleportManager;
import com.Nepian.Teleports.Event.RemoveTeleportEvent;

public class RemoveTeleportExecutor implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public static void onRemoveTeleport(RemoveTeleportEvent event) {
		
		if (event.isCancelled()) {
			return;
		}
		
		TeleportManager.remove(event);
		String name = event.getName();
		event.getPlayer().sendMessage("Removed this teleport! (" + name + ")");
	}
}
