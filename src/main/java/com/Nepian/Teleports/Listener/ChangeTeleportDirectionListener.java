package com.Nepian.Teleports.Listener;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.Nepian.Teleports.TeleportManager;
import com.Nepian.Teleports.Data.TeleportLocationData;
import com.Nepian.Teleports.Data.TeleportSettingType;
import com.Nepian.Teleports.Data.TeleportType;
import com.Nepian.Teleports.Util.LocationUtil.Yaw;

public class ChangeTeleportDirectionListener implements Listener {

	@EventHandler
	public static void onPlayerInteract(PlayerInteractEvent event) {
		
		if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}
		
		Player player = event.getPlayer();
		
		if (!TeleportSettingType.isDirectionChangeItem(player.getItemInHand())) {
			return;
		}
		
		Block block = event.getClickedBlock();
		
		if (!TeleportType.isEnd(block)) {
			return;
		}
		
		if (!TeleportManager.isTeleportBlock(block)) {
			player.sendMessage("This block is NOT teleport blcok!");
			return;
		}
		
		TeleportLocationData data = TeleportManager.getTeleportLocationData(block);
		Location teleportLocation = data.getTeleportLocation();
		
		teleportLocation.setYaw(Yaw.getYawNextDirection(teleportLocation.getYaw()));
		data.setTeleportLocation(teleportLocation);
		
		String direction = Yaw.getDirection(teleportLocation.getYaw()).toString();
		
		player.sendMessage("Change teleport direction -> " + direction + " !");
	}
}
