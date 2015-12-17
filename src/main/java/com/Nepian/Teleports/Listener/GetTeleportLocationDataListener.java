package com.Nepian.Teleports.Listener;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.Nepian.Teleports.TeleportManager;
import com.Nepian.Teleports.Data.TeleportLocationData;
import com.Nepian.Teleports.Data.TeleportSettingType;

public class GetTeleportLocationDataListener implements Listener {

	@EventHandler
	public static void onPlayerInteract(PlayerInteractEvent event) {
		
		if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}
		
		Player player = event.getPlayer();
		
		if (!TeleportSettingType.isGetInfoItem(player.getItemInHand())) {
			return;
		}
		
		Block block = event.getClickedBlock();
		
		if (!TeleportManager.hasTeleportLocationData(block)) {
			player.sendMessage("This block is NOT Teleport block!");
			return;
		}
		
		TeleportLocationData data = TeleportManager.getTeleportLocationData(block);
		
		String strData = data.toString();
		
		player.sendMessage(strData);
	}
}
