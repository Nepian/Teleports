package com.Nepian.Teleports.Listener.CreateEnd;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.Teleports.TeleportManager;
import com.Nepian.Teleports.Event.CreateEndEvent;

public class CreateEndExecute implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public static void onCreateEnd(CreateEndEvent event) {
		
		if (event.isCancelled()) {
			return;
		}
		
		String name = event.getName();
		String message = "Created the new end location of warp! (" + name + ")";
		
		TeleportManager.putEnd(event.getBlock(), name);
		event.getPlayer().sendMessage(message);
	}
}
