package com.Nepian.Teleports.Listener;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.Nepian.Teleports.ListenerManager;
import com.Nepian.Teleports.TeleportManager;
import com.Nepian.Teleports.Data.TeleportLocationData;
import com.Nepian.Teleports.Data.TeleportType;
import com.Nepian.Teleports.Event.CancellableEvent;
import com.Nepian.Teleports.Event.RemoveTeleportEvent;

public class RemoveTeleportListener implements Listener {

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public static void onBlockBreak(BlockBreakEvent event) {
		
		if (TeleportType.isOther(event.getBlock())) {
			return;
		}
		
		if (!TeleportManager.hasTeleportLocationData(event.getBlock())) {
			return;
		}
		
		Player player = event.getPlayer();
		Block block = event.getBlock();
		TeleportLocationData data = TeleportManager.getTeleportLocationData(block);
		CancellableEvent eve = new RemoveTeleportEvent(player, block, data);
		ListenerManager.callEvent(eve);
		
		if (eve.isCancelled()) {
			event.setCancelled(true);
		}
	}
}
