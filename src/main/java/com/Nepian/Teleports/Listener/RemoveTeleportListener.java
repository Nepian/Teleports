package com.Nepian.Teleports.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.Nepian.Teleports.EventManager;
import com.Nepian.Teleports.TeleportManager;
import com.Nepian.Teleports.Data.TeleportType;
import com.Nepian.Teleports.Event.CancellableEvent;
import com.Nepian.Teleports.Event.RemoveTeleportEvent;

public class RemoveTeleportListener implements Listener {

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public static void onBlockBreak(BlockBreakEvent event) {
		
		if (TeleportType.isOther(event.getBlock())) {
			return;
		}
		
		if (!TeleportManager.isTeleportLocation(event.getBlock())) {
			return;
		}
		
		CancellableEvent eve = new RemoveTeleportEvent(event.getPlayer(), event.getBlock());
		EventManager.callEvent(eve);
		
		if (eve.isCancelled()) {
			event.setCancelled(true);
		}
	}
}
