package com.Nepian.Teleports.Listener.RemoveEnd;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.Teleports.TeleportManager;
import com.Nepian.Teleports.Event.RemoveEndEvent;

public class RemoveEndExecute implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public static void onRemoveEnd(RemoveEndEvent event) {
		
		if (event.isCancelled()) {
			return;
		}
		
		String name = TeleportManager.getEndName(event.getBlock());
		
		TeleportManager.removeEnd(event.getBlock());
		
		event.getPlayer().sendMessage("Removed the end location of warp! (" + name + ")");
	}
}
