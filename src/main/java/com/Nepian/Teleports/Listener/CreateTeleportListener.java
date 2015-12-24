package com.Nepian.Teleports.Listener;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.Nepian.Teleports.ListenerManager;
import com.Nepian.Teleports.TeleportManager;
import com.Nepian.Teleports.Data.TeleportSettingType;
import com.Nepian.Teleports.Data.TeleportType;
import com.Nepian.Teleports.Event.CreateTeleportEvent;
import com.Nepian.Teleports.Util.ActionUtil;
import com.Nepian.Teleports.Util.ItemStackUtil;

public class CreateTeleportListener implements Listener {

	@EventHandler
	public static void onPlayerInteract(PlayerInteractEvent event) {
		
		if (!ActionUtil.isRightClickedBlock(event.getAction())) {
			return;
		}
		
		Player player = event.getPlayer();
		ItemStack item = player.getItemInHand();
		
		if (!TeleportSettingType.isCreateItem(item)) {
			return;
		}
		
		Block block = event.getClickedBlock();
		
		if (TeleportType.isOther(block)) {
			return;
		}
		
		if (TeleportManager.hasTeleportLocationData(block)) {
			player.sendMessage("This location already exist!");
			return;
		}
		
		if (!ItemStackUtil.hasDisplayName(item)) {
			player.sendMessage("This item does not have display name!");
			return;
		}
		
		String name = ItemStackUtil.getDisplayName(item);
		
		if (TeleportType.isEnd(block)) {
			if (TeleportManager.hasName(name)) {
				player.sendMessage("This name already exists!");
				return;
			}
		}
		
		Event eve = new CreateTeleportEvent(player, block, name);
		ListenerManager.callEvent(eve);
	}
}
