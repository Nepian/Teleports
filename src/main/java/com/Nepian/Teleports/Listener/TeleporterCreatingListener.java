package com.Nepian.Teleports.Listener;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.Nepian.Teleports.ListenerManager;
import com.Nepian.Teleports.Data.TeleporterSettingType;
import com.Nepian.Teleports.Data.TeleporterType;
import com.Nepian.Teleports.Event.TeleporterCreatingEvent;
import com.Nepian.Teleports.Util.ItemStackUtil;

public class TeleporterCreatingListener implements Listener {

	@EventHandler
	public static void onPlayerInteract(PlayerInteractEvent event) {
		
		if (!isRightClickedBlock(event.getAction())) {
			return;
		}
		
		Block block = event.getClickedBlock();
		
		if (TeleporterType.isOther(block)) {
			return;
		}
		
		ItemStack item = event.getPlayer().getItemInHand();
		
		if (!TeleporterSettingType.isTeleporterCreatingItem(item)) {
			return;
		}
		
		Player player = event.getPlayer();
		String name = ItemStackUtil.getDisplayName(item);
		Event eve = new TeleporterCreatingEvent(player, block, name);
		
		ListenerManager.callEvent(eve);
	}
	
	/* Private Method ---------------------------------------------------------------------------*/
	
	private static boolean isRightClickedBlock(Action action) {
		return action == Action.RIGHT_CLICK_BLOCK;
	}
}
