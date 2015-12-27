package com.Nepian.Teleports.Listener.PlayerTeleport;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.Teleports.TeleporterManager;
import com.Nepian.Teleports.Data.TeleporterData;
import com.Nepian.Teleports.Event.PlayerTeleportEvent;

public class PlayerTeleportEndPrivateChecker implements Listener {

	@EventHandler(priority = EventPriority.HIGH)
	public static void onPlayerTeleport(PlayerTeleportEvent event) {
		
		if (event.isCancelled()) {
			return;
		}
		
		String name = event.getTeleporterData().getName();
		TeleporterData data = TeleporterManager.getEndLocationData(name);
		OfflinePlayer player = event.getPlayer();
		OfflinePlayer owner = data.getOwner();
		
		if (data.isPrivate()) {
			
			if (player.equals(owner)) {
				return;
			}
			
			if (data.hasMember(player)) {
				return;
			}
			
			event.getPlayer().sendMessage("This end teleporter is private.");
			event.setCancelled(true);
		}
	}
}
