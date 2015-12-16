package com.Nepian.Teleports.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.Nepian.Teleports.EventManager;
import com.Nepian.Teleports.TeleportManager;
import com.Nepian.Teleports.Event.CancellableEvent;
import com.Nepian.Teleports.Event.RemoveEndEvent;
import com.Nepian.Teleports.Event.RemoveStartEvent;

public class RemoveWarp implements Listener {

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public static void onBlockBreak(BlockBreakEvent event) {
		CancellableEvent eve;
		
		switch (TeleportManager.getType(event.getBlock())) {
		case START:
			if (TeleportManager.isStart(event.getBlock())) {
				eve = new RemoveStartEvent(event.getPlayer(), event.getBlock());
				break;
			}
			return;
		case END:
			if (TeleportManager.isEnd(event.getBlock())) {
				eve = new RemoveEndEvent(event.getPlayer(), event.getBlock());
				break;
			}
			return;
		default: return;
		}
		
		EventManager.callEvent(eve);
		
		if (eve.isCancelled()) {
			event.setCancelled(true);
		}
	}
}
