package com.Nepian.Teleports.Listener.CreateStart;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.Teleports.TeleportManager;
import com.Nepian.Teleports.Event.CreateStartEvent;

public class CreateStartExecute implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public static void onCreateStart(CreateStartEvent event) {
		
		if (event.isCancelled()) {
			return;
		}
		
		String name = event.getName();
		String message = "Created the new starting location of warp! (" + name + ")";
		
		TeleportManager.putStart(event.getBlock(), name);
		event.getPlayer().sendMessage(message);
	}
}
