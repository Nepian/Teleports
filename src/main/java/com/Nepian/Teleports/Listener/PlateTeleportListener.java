package com.Nepian.Teleports.Listener;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.Nepian.Teleports.ListenerManager;
import com.Nepian.Teleports.TeleportManager;
import com.Nepian.Teleports.Data.TeleportType;
import com.Nepian.Teleports.Event.PlayerTeleportEvent;
import com.Nepian.Teleports.Util.BlockUtil;

public class PlateTeleportListener implements Listener {

	@EventHandler
	public static void onPlayerInteract(PlayerInteractEvent event) {
		
		if (event.getAction() != Action.PHYSICAL) {
			return;
		}
		
		if (!BlockUtil.isStonePlate(event.getClickedBlock())) {
			return;
		}
		
		Block block = BlockUtil.getBlockUnder(event.getClickedBlock());
		
		if (TeleportType.isOther(block)) {
			return;
		}
		
		if (!TeleportManager.hasTeleportLocationData(block)) {
			return;
		}
		
		String name = TeleportManager.getTeleportName(block);
		Player player = event.getPlayer();
		
		if (!TeleportManager.hasTeleportLocation(block)) {
			player.sendMessage("This teleport is Closed! (" + name + ")");
			return;
		}
		
		Location teleportLocation = TeleportManager.getTeleportLocation(name);
		
		Event eve = new PlayerTeleportEvent(player, block, teleportLocation, name);
		
		ListenerManager.callEvent(eve);
	}
}
