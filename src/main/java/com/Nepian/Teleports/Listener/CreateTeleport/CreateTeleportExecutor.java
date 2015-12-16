package com.Nepian.Teleports.Listener.CreateTeleport;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.Teleports.TeleportManager;
import com.Nepian.Teleports.Event.CreateTeleportEvent;

public class CreateTeleportExecutor implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public static void onCreateTeleport(CreateTeleportEvent event) {
		
		if (event.isCancelled()) {
			return;
		}
		
		TeleportManager.put(event);	
		String name = event.getName();
		String message = "Created the new teleport! (" + name + ")";
		event.getPlayer().sendMessage(message);
	}
}
