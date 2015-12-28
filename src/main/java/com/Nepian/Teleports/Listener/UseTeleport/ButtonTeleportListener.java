package com.Nepian.Teleports.Listener.UseTeleport;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.Nepian.Teleports.ListenerManager;
import com.Nepian.Teleports.TeleporterManager;
import com.Nepian.Teleports.Data.TeleporterData;
import com.Nepian.Teleports.Data.TeleporterType;
import com.Nepian.Teleports.Event.PlayerTeleportEvent;
import com.Nepian.Teleports.Util.BlockUtil;

public class ButtonTeleportListener implements Listener {

	@EventHandler
	public static void onPlayerInteract(PlayerInteractEvent event) {
		
		if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}
		
		if (!BlockUtil.isButton(event.getClickedBlock())) {
			return;
		}
		
		Block block = BlockUtil.getBlockRelative(event.getClickedBlock());
		
		if (!TeleporterType.isStart(block)) {
			return;
		}
		
		if (!TeleporterManager.hasTeleporterData(block)) {
			return;
		}
		
		TeleporterData data = TeleporterManager.getTeleporterData(block);
		Player player = event.getPlayer();
		Event eve = new PlayerTeleportEvent(player, block, data);
		
		ListenerManager.callEvent(eve);
	}
}
