package com.Nepian.Teleports.Listener.AfterCommand;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.MetadataValue;

import com.Nepian.Teleports.Main;
import com.Nepian.Teleports.TeleporterManager;
import com.Nepian.Teleports.Configuration.Properties;
import com.Nepian.Teleports.Data.TeleporterData;
import com.Nepian.Teleports.Data.TeleporterType;
import com.Nepian.Teleports.Metadata.MetadataKeys;
import com.Nepian.Teleports.Util.ActionUtil;

public class ChangePrivateListener implements Listener {

	@EventHandler
	public static void onChangePrivate(PlayerInteractEvent event) {
		
		if (!ActionUtil.isClickedBlock(event.getAction())) {
			return;
		}
		
		Player player = event.getPlayer();
		
		if (!player.hasMetadata(MetadataKeys.CHANGE_PRIVATE)) {
			return;
		}
		
		Block block = event.getClickedBlock();

		if (!isTeleporter(block)) {
			player.sendMessage("This block is NOT Teleport block!");
			removeMetadata(player);
			return;
		}
		
		TeleporterData data = TeleporterManager.getTeleporterData(block);
		
		if (!isAdmin(player)) {
			if (!isOwner(player, data)) {
				player.sendMessage("You are not owner!");
				removeMetadata(player);
				return;
			}
		}
		
		for (MetadataValue metadataValue : player.getMetadata(MetadataKeys.CHANGE_PRIVATE)) {
			boolean isPrivate = metadataValue.asBoolean();
			
			data.changePrivate(isPrivate);
			removeMetadata(player);
			
			player.sendMessage("Success changing private -> " + isPrivate);
		}
	}
	
	/* Private Method ---------------------------------------------------------------------------*/
	
	private static void removeMetadata(Player player) {
		player.removeMetadata(MetadataKeys.CHANGE_PRIVATE, Main.getPlugin());
	}
	
	private static boolean isTeleporter(Block block) {
		return !TeleporterType.isOther(block) && TeleporterManager.hasTeleporterData(block);
	}
	
	private static boolean isAdmin(Player player) {
		return player.hasPermission(Properties.PERM_ADMIN);
	}
	
	private static boolean isOwner(Player player, TeleporterData data) {
		return player.equals(data.getOwner());
	}
}
