package com.Nepian.Teleports.Listener.AfterCommand;

import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.MetadataValue;

import com.Nepian.Teleports.Main;
import com.Nepian.Teleports.TeleporterManager;
import com.Nepian.Teleports.Configuration.MetadataKeys;
import com.Nepian.Teleports.Configuration.Properties;
import com.Nepian.Teleports.Data.TeleporterData;
import com.Nepian.Teleports.Data.TeleporterType;
import com.Nepian.Teleports.Util.ActionUtil;

public class AddMemberListener implements Listener {

	@EventHandler
	public static void onAddMember(PlayerInteractEvent event) {
		
		if (!isClickedBlock(event.getAction())) {
			return;
		}
		
		if (!hasMetadata(event.getPlayer())) {
			return;
		}
		
		Player player = event.getPlayer();
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
		
		for (MetadataValue metadataValue : player.getMetadata(MetadataKeys.ADD_MEMBER)) {
			OfflinePlayer member = (OfflinePlayer) metadataValue.value();

			data.addMemebr(member);
			removeMetadata(player);
			
			player.sendMessage("Success adding a member " + member.getName());
		}
	}
	
	/* Private Method ---------------------------------------------------------------------------*/
	
	private static void removeMetadata(Player player) {
		player.removeMetadata(MetadataKeys.ADD_MEMBER, Main.getPlugin());
	}
	
	private static boolean isOwner(Player player, TeleporterData data) {
		return player.equals(data.getOwner());
	}
	
	private static boolean isTeleporter(Block block) {
		return !TeleporterType.isOther(block) && TeleporterManager.hasTeleporterData(block);
	}
	
	private static boolean hasMetadata(Player player) {
		return player.hasMetadata(MetadataKeys.ADD_MEMBER);
	}
	
	private static boolean isClickedBlock(Action action) {
		return ActionUtil.isClickedBlock(action);
	}
	
	private static boolean isAdmin(Player player) {
		return player.hasPermission(Properties.PERM_ADMIN);
	}
}
