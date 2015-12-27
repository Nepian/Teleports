package com.Nepian.Teleports.Listener.TeleporterCreating;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.Teleports.TeleporterManager;
import com.Nepian.Teleports.Event.TeleporterCreatingEvent;

public class TeleporterCreatingExecutor implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public static void onCreateTeleport(TeleporterCreatingEvent event) {
		
		if (event.isCancelled()) {
			return;
		}
		
		TeleporterManager.put(event);	
		String name = event.getName();
		String message = "Created the new teleport! (" + name + ")";
		event.getPlayer().sendMessage(message);
	}
}
