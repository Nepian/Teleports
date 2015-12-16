package com.Nepian.Teleports.Listener.RemoveStart;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.Teleports.TeleportManager;
import com.Nepian.Teleports.Event.RemoveStartEvent;

public class RemoveStartExecute implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public static void onRemoveStart(RemoveStartEvent event) {
		
		if (event.isCancelled()) {
			return;
		}
		
		String name = TeleportManager.getStartName(event.getBlock());
		
		TeleportManager.removeStart(event.getBlock());
		
		event.getPlayer().sendMessage("Removed the starting location of warp! (" + name + ")");
	}
}
