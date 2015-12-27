package com.Nepian.Teleports.Listener.TeleporterCreating;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.Teleports.Configuration.Properties;
import com.Nepian.Teleports.Event.TeleporterCreatingEvent;

public class TeleporterCreatingPermissionChecker implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public static void onTeleporterCreating(TeleporterCreatingEvent event) {
		
		if (event.isCancelled()) {
			return;
		}
		
		if (event.getPlayer().hasPermission(Properties.PERM_ADMIN)) {
			return;
		}
		
		if (event.getPlayer().hasPermission(Properties.PERM_TELEPORTER_CREATE)) {
			return;
		}
		
		event.getPlayer().sendMessage("You don't have the permission.");
		event.setCancelled(true);
	}
}
