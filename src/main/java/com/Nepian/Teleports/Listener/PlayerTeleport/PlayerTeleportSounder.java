package com.Nepian.Teleports.Listener.PlayerTeleport;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.Teleports.Event.PlayerTeleportEvent;
import com.Nepian.Teleports.Util.PlayerUtil;

public class PlayerTeleportSounder implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public static void onPlayerTeleport(PlayerTeleportEvent event) {
		
		if (event.isCancelled()) {
			return;
		}
		
		Player player = event.getPlayer();
		Sound sound = Sound.ENDERMAN_TELEPORT;
		
		PlayerUtil.playSound(player, sound, 1, 1);
	}
}
