package com.Nepian.Teleports.Listener.PlayerTeleport;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.Teleports.Data.TeleporterData;
import com.Nepian.Teleports.Event.PlayerTeleportEvent;

public class PlayerTeleportStartPrivateChecker implements Listener {

	@EventHandler(priority = EventPriority.HIGH)
	public static void onPlayerTeleport(PlayerTeleportEvent event) {
		
		if (event.isCancelled()) {
			return;
		}
		
		TeleporterData data = event.getTeleporterData();
		OfflinePlayer player = event.getPlayer();
		OfflinePlayer owner = data.getOwner();
		
		if (data.isPrivate()) {
			
			if (player.equals(owner)) {
				return;
			}
			
			if (data.hasMember(player)) {
				return;
			}
			
			event.getPlayer().sendMessage("This teleporter is private.");
			event.setCancelled(true);
		}
	}
}
