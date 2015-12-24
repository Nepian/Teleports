package com.Nepian.Teleports.Listener.AfterCommand;

import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.MetadataValue;

import com.Nepian.Teleports.Main;
import com.Nepian.Teleports.TeleportManager;
import com.Nepian.Teleports.Data.TeleportLocationData;
import com.Nepian.Teleports.Data.TeleportType;
import com.Nepian.Teleports.Metadata.AddMemberMetadata;
import com.Nepian.Teleports.Metadata.MetadataKeys;
import com.Nepian.Teleports.Util.ActionUtil;

public class AddMemberListener implements Listener {

	@EventHandler
	public static void onAddMember(PlayerInteractEvent event) {
		
		if (!ActionUtil.isClickedBlock(event.getAction())) {
			return;
		}
		
		if (!event.getPlayer().hasMetadata(MetadataKeys.ADD_MEMBER)) {
			return;
		}
		
		Player player = event.getPlayer();
		
		if (TeleportType.isOther(event.getClickedBlock())) {
			player.sendMessage("This block is Not Teleport Block!");
			return;
		}
		
		Block block = event.getClickedBlock();
		
		if (!TeleportManager.hasTeleportLocationData(block)) {
			player.sendMessage("This block is NOT Teleport block!");
			return;
		}
		
		for (MetadataValue metadataValue : player.getMetadata(MetadataKeys.ADD_MEMBER)) {
			AddMemberMetadata meta = (AddMemberMetadata) metadataValue.value();
			OfflinePlayer member = meta.getPlayer();
			TeleportLocationData data = TeleportManager.getTeleportLocationData(block);
			
			data.addMemebr(member);
			player.removeMetadata(MetadataKeys.ADD_MEMBER, Main.getPlugin());
			
			player.sendMessage("Success adding a member " + member.getName());
		}
	}
}
