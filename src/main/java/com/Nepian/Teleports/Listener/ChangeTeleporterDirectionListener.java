package com.Nepian.Teleports.Listener;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.Nepian.Teleports.TeleporterManager;
import com.Nepian.Teleports.Configuration.Properties;
import com.Nepian.Teleports.Data.TeleporterData;
import com.Nepian.Teleports.Data.TeleporterSettingType;
import com.Nepian.Teleports.Data.TeleporterType;
import com.Nepian.Teleports.Util.LocationUtil.Yaw;

public class ChangeTeleporterDirectionListener implements Listener {

	@EventHandler
	public static void onPlayerInteract(PlayerInteractEvent event) {
		
		if (!isRightClickedBlock(event.getAction())) {
			return;
		}
		
		Player player = event.getPlayer();
		
		if (!isDirectionChangingItem(player.getItemInHand())) {
			return;
		}
		
		Block block = event.getClickedBlock();
		
		if (!isEndTeleporter(block)) {
			return;
		}
		
		if (!isTeleporter(block)) {
			player.sendMessage("This block is NOT teleport blcok!");
			return;
		}
		
		TeleporterData data = TeleporterManager.getTeleporterData(block);
		
		if (!isAdmin(player)) {
			if (!data.hasMember(player)) {
				player.sendMessage("You are not member of this teleporter.");
				return;
			}
		}
		
		Location teleportLocation = data.getTeleportLocation();
		final float changingYaw = Yaw.getYawNextDirection(teleportLocation.getYaw());
		
		teleportLocation.setYaw(changingYaw);
		data.setTeleportLocation(teleportLocation);
		
		final String direction = Yaw.getDirection(teleportLocation.getYaw()).toString();
		
		player.sendMessage("Direction was changed to " + direction + " !");
	}
	
	/* Private Method ---------------------------------------------------------------------------*/
	
	private static boolean isRightClickedBlock(Action action) {
		return action == Action.RIGHT_CLICK_BLOCK;
	}
	
	private static boolean isDirectionChangingItem(ItemStack item) {
		return TeleporterSettingType.isDirectionChangingItem(item);
	}
	
	private static boolean isEndTeleporter(Block block) {
		return TeleporterType.isEnd(block);
	}
	
	private static boolean isTeleporter(Block block) {
		return TeleporterManager.hasTeleporterData(block);
	}
	
	private static boolean isAdmin(Player player) {
		return player.hasPermission(Properties.PERM_ADMIN);
	}
}
