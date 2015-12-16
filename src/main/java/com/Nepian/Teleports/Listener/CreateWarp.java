package com.Nepian.Teleports.Listener;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.Nepian.Teleports.EventManager;
import com.Nepian.Teleports.TeleportManager;
import com.Nepian.Teleports.Event.CreateEndEvent;
import com.Nepian.Teleports.Event.CreateStartEvent;
import com.Nepian.Teleports.Util.ActionUtil;
import com.Nepian.Teleports.Util.ItemStackUtil;

public class CreateWarp implements Listener {

	@EventHandler
	public static void onPlayerInteract(PlayerInteractEvent event) {
		
		if (!ActionUtil.isRightClickedBlock(event.getAction())) {
			return;
		}
		
		if (!TeleportManager.isRegisteringItem(event.getPlayer().getItemInHand())) {
			return;
		}
		
		Player player = event.getPlayer();
		ItemStack item = player.getItemInHand();
		Block block = event.getClickedBlock();
		String name = ItemStackUtil.getDisplayName(item);
		
		Event eve;
		
		switch (TeleportManager.getType(block)) {
		case START:
			if (TeleportManager.isStart(block)) {
				player.sendMessage("This location already exist!");
				return;
			}
			eve = new CreateStartEvent(player, block, name, item);
			break;
		case END:
			if (TeleportManager.isEnd(block)) {
				player.sendMessage("This location already exist!");
				return;
			}
			if (TeleportManager.alreadyExist(name)) {
				player.sendMessage("This name already exists!");
				return;
			}
			eve = new CreateEndEvent(player, block, name, item);
			break;
		default:
			return;
		}
		
		if (name == null) {
			player.sendMessage("This watch does not have display name!");
			return;
		}
		
		EventManager.callEvent(eve);
	}
}
